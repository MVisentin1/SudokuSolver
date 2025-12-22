package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BaseCellTest {

    private final CellFactory<TestCell, TestCandidate, TestHouse> cellFactory
            = new CellFactory<>(TestCell::new, TestCandidate::new);

    @Nested
    @DisplayName("Constructor and Initialization")
    class Initialization {

        @Test
        @DisplayName("Should initializes fields with correct state")
        void shouldInitializeCorrectly(){
            ArrayList<TestCandidate> candidateList = cellFactory.getCandidateList(new boolean[10]);

            TestCell cell = new TestCell(candidateList, 1, false);
            List<TestCandidate> cellCandidateList = cell.getCandidateList();

            for (int i = 0; i < 9; i++) {
                assertEquals(candidateList.get(i), cellCandidateList.get(i),
                        "Candidate list doesn't contain given candidates");
            }
            assertEquals(1, cell.getValue());
            assertFalse(cell.isFixed());
        }
    }

    @Nested
    @DisplayName("House Attachment")
    class Attachment {

        @Test
        @DisplayName("Should throw on null row")
        void shouldThrowOnNullRow(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            assertThrows(NullPointerException.class, () -> cell.attachRow(null));
        }

        @Test
        @DisplayName("Should throw on null column")
        void shouldThrowOnNullColumn(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            assertThrows(NullPointerException.class, () -> cell.attachColumn(null));
        }

        @Test
        @DisplayName("Should throw on null box")
        void shouldThrowOnNullBox(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            assertThrows(NullPointerException.class, () -> cell.attachBox(null));
        }

        @Test
        @DisplayName("Should throw on double row attachment")
        void shouldThrowOnDoubleRowAttachment(){
            TestHouse mockRow = mock(TestHouse.class);
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            cell.attachRow(mockRow);
            assertThrows(IllegalStateException.class, () -> cell.attachRow(mockRow));
        }

        @Test
        @DisplayName("Should throw on double column attachment")
        void shouldThrowOnDoubleColumnAttachment(){
            TestHouse mockColumn = mock(TestHouse.class);
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            cell.attachColumn(mockColumn);
            assertThrows(IllegalStateException.class, () -> cell.attachColumn(mockColumn));
        }

        @Test
        @DisplayName("Should throw on double box attachment")
        void shouldThrowOnDoubleBoxAttachment(){
            TestHouse mockBox = mock(TestHouse.class);
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            cell.attachBox(mockBox);
            assertThrows(IllegalStateException.class, () -> cell.attachBox(mockBox));
        }
    }

    @Nested
    @DisplayName("Value Management")
    class ValueManagement {

        @Test
        @DisplayName("Should throw on invalid value")
        void shouldThrowOnInvalidValue(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertThrows(IllegalArgumentException.class, () -> cell.solve(11));
        }

        @Test
        @DisplayName("Should throw on invalid value")
        void shouldThrowOnInvalidValue2(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertThrows(IllegalArgumentException.class, () -> cell.solve(0),
                    "Should fail to prevent using solve to unsolve");
        }

        @Test
        @DisplayName("Should solve cell with valid value")
        void shouldSolveCell(){
            TestCell cell = cellFactory.createUnsolvedCell(new boolean[10]);
            cell.solve(2);
            assertEquals(2, cell.getValue());
        }
    }

    @Nested
    @DisplayName("Solve State Management")
    class SolveStateManagement {

        @Test
        @DisplayName("Should throw on unsolved value access")
        void shouldThrowOnUnsolvedValueAccess(){
            TestCell cell = cellFactory.createUnsolvedCell(new boolean[10]);
            assertThrows(IllegalStateException.class, cell::getValue,
                    "Should fail to prevent accessing value of unsolved cell");
        }

        @Test
        @DisplayName("Should solve cell correctly")
        void shouldSolveCell(){
            TestCell cell = cellFactory.createUnsolvedCell(new boolean[10]);
            cell.solve(1);
            assertTrue(cell.isSolved());
        }

        @Test
        @DisplayName("Should throw when solving solved cell")
        void shouldThrowOnSolvingSolvedCell(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertThrows(IllegalStateException.class, () -> cell.solve(1),
                    "Should fail to prevent changing value of solved cell");
        }

        @Test
        @DisplayName("Should throw on invalid solve value")
        void shouldThrowOnInvalidSolveValue(){
            TestCell cell = cellFactory.createUnsolvedCell(new boolean[10]);
            assertThrows(IllegalArgumentException.class, () -> cell.solve(11));
        }
    }

    @Nested
    @DisplayName("Candidate Management")
    class CandidateManagement {
        private boolean[] eliminatedCandidates;
        private boolean[] activeCandidates;

        @BeforeEach
        void setUp() {
            eliminatedCandidates = new boolean[10];
            activeCandidates = new boolean[10];
            Arrays.fill(eliminatedCandidates, true);
        }

        @Test
        @DisplayName("Should eliminate candidate correctly")
        void shouldEliminateCandidate(){
            TestCell cell = cellFactory.createUnsolvedCell(activeCandidates);
            cell.eliminateCandidate(1);
            assertTrue(cell.getCandidateList().getFirst().isEliminated());
        }

        @Test
        @DisplayName("Should throw when eliminating candidate from solved cell")
        void shouldThrowOnEliminatingCandidateFromSolvedCell(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertThrows(IllegalStateException.class, () -> cell.eliminateCandidate(1));
        }

        @Test
        @DisplayName("Should preserve elimination status when eliminating eliminated candidate")
        void shouldPreserveEliminationStatus(){
            TestCell cell = cellFactory.createUnsolvedCell(eliminatedCandidates);
            cell.eliminateCandidate(1);
            assertTrue(cell.getCandidateList().getFirst().isEliminated());
        }

    }






}