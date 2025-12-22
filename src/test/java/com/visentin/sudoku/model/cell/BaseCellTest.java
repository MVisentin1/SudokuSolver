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
        @DisplayName("Should unsolve cell correctly")
        void shouldUnsolveCell(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            cell.unsolve();
            assertFalse(cell.isSolved());
        }
        @Test
        @DisplayName("Should throw when unsolving unsolved cell")
        void shouldThrowOnUnsolvingUnsolvedCell(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, false);
            cell.unsolve();
            assertThrows(IllegalStateException.class, cell::unsolve,
                    "Should fail to prevent unsolving unsolved cell");
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
    @DisplayName("Fixed State Management")
    class FixedStateManagement {
        @Test
        @DisplayName("Should throw on unsolving fixed cell")
        void shouldThrowOnFixedValueChange(){
            TestCell cell = new TestCell(cellFactory.getCandidateList(new boolean[10]), 1, true);
            assertThrows(IllegalStateException.class, cell::unsolve);
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
        @DisplayName("Should add candidate correctly")
        void shouldAddCandidate(){
            TestCell cell = cellFactory.createUnsolvedCell(eliminatedCandidates);
            cell.addCandidate(1);
            assertFalse(cell.getCandidateList().getFirst().isEliminated());
        }

        @Test
        @DisplayName("Should throw when adding candidate to solved cell")
        void shouldThrowOnAddingCandidateToSolvedCell(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertThrows(IllegalStateException.class, () -> cell.addCandidate(2));
        }

        @Test
        @DisplayName("Should throw when adding invalid candidate")
        void shouldThrowOnAddingInvalidCandidate(){
            TestCell cell = cellFactory.createUnsolvedCell(eliminatedCandidates);
            assertThrows(IllegalArgumentException.class, () -> cell.addCandidate(11));
        }

        @Test
        @DisplayName("Should preserve active status when adding active candidate")
        void shouldPreserveActiveStatus(){
            TestCell cell = cellFactory.createUnsolvedCell(activeCandidates);
            cell.addCandidate(1);
            assertFalse(cell.getCandidateList().getFirst().isEliminated());
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

        @Test
        @DisplayName("Should find active candidate correctly")
        void shouldFindActive(){
            TestCell cell = cellFactory.createUnsolvedCell(activeCandidates);
            assertEquals(cell.getCandidateList().getFirst(), cell.findCandidate(1).orElseThrow());
        }

        @Test
        @DisplayName("Should return empty on finding eliminated candidates")
        void shouldFindEmptyOnEliminatedCandidates(){
            TestCell cell = cellFactory.createUnsolvedCell(eliminatedCandidates);
            assertEquals(Optional.empty(), cell.findCandidate(1));
        }

        @Test
        @DisplayName("Should return empty when finding in solved cell")
        void shouldFindEmptyOnSolved(){
            TestCell cell = cellFactory.createSolvedCell(1);
            assertEquals(Optional.empty(), cell.findCandidate(1));
        }

        @Test
        @DisplayName("Should throw on invalid candidate")
        void shouldThrowOnInvalidCandidate(){
            TestCell cell = cellFactory.createUnsolvedCell(activeCandidates);
            assertThrows(IllegalArgumentException.class, () -> cell.findCandidate(11));
        }

    }






}