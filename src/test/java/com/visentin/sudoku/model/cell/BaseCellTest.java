package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

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
            SudokuSet set = SudokuSet.emptySet();
            set.add(1);
            set.add(2);
            List<TestCandidate> candidateList = cellFactory.getCandidateList(set);

            TestCell cell = new TestCell(candidateList, set, false);
            List<TestCandidate> cellCandidateList = cell.getCandidateList();

            for (int i = 0; i < 9; i++) {
                assertEquals(candidateList.get(i), cellCandidateList.get(i),
                        "Candidate list doesn't contain given candidates");
                assertEquals(!set.contains(i+1), cell.findCandidate(i+1).orElseThrow().isEliminated(),
                        "Candidate list doesn't contain given candidates");
            }
            assertFalse(cell.isFixed());
        }
    }

    @Nested
    @DisplayName("House Attachment")
    class Attachment {
        private TestCell cell;

        @BeforeEach
        void setUp(){
            this.cell = new TestCell(cellFactory.getCandidateList(SudokuSet.emptySet()), SudokuSet.emptySet(), false);
        }

        @Test
        @DisplayName("Should throw on null row")
        void shouldThrowOnNullRow(){
            assertThrows(NullPointerException.class, () -> cell.attachRow(null));
        }

        @Test
        @DisplayName("Should throw on null column")
        void shouldThrowOnNullColumn(){
            assertThrows(NullPointerException.class, () -> cell.attachColumn(null));
        }

        @Test
        @DisplayName("Should throw on null box")
        void shouldThrowOnNullBox(){
            assertThrows(NullPointerException.class, () -> cell.attachBox(null));
        }

        @Test
        @DisplayName("Should throw on double row attachment")
        void shouldThrowOnDoubleRowAttachment(){
            TestHouse mockRow = mock(TestHouse.class);
            cell.attachRow(mockRow);
            assertThrows(IllegalStateException.class, () -> cell.attachRow(mockRow));
        }

        @Test
        @DisplayName("Should throw on double column attachment")
        void shouldThrowOnDoubleColumnAttachment(){
            TestHouse mockColumn = mock(TestHouse.class);
            cell.attachColumn(mockColumn);
            assertThrows(IllegalStateException.class, () -> cell.attachColumn(mockColumn));
        }

        @Test
        @DisplayName("Should throw on double box attachment")
        void shouldThrowOnDoubleBoxAttachment(){
            TestHouse mockBox = mock(TestHouse.class);
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
            TestCell cell = cellFactory.createUnsolvedCell(SudokuSet.emptySet());
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
            TestCell cell = cellFactory.createUnsolvedCell(SudokuSet.emptySet());
            assertThrows(IllegalStateException.class, cell::getValue,
                    "Should fail to prevent accessing value of unsolved cell");
        }

        @Test
        @DisplayName("Should solve cell correctly")
        void shouldSolveCell(){
            TestCell cell = cellFactory.createUnsolvedCell(SudokuSet.emptySet());
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
            TestCell cell = cellFactory.createUnsolvedCell(SudokuSet.emptySet());
            assertThrows(IllegalArgumentException.class, () -> cell.solve(11));
        }
    }

    @Nested
    @DisplayName("Candidate Management")
    class CandidateManagement {
        private SudokuSet allActive;

        @BeforeEach
        void setUp() {
            allActive = SudokuSet.emptySet();
        }

        @Test
        @DisplayName("Should eliminate candidate correctly")
        void shouldEliminateCandidate(){
            TestCell cell = cellFactory.createUnsolvedCell(allActive);
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
            TestCell cell = cellFactory.createUnsolvedCell(allActive);
            cell.eliminateCandidate(1);
            assertTrue(cell.getCandidateList().getFirst().isEliminated());
        }

    }






}