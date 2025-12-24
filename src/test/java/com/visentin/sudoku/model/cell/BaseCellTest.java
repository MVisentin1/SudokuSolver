package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BaseCellTest {

    @Nested
    @DisplayName("Constructor and Initialization")
    class Initialization {

        @Test
        @DisplayName("Should initializes fields with correct state")
        void shouldInitializeCorrectly() {
            SudokuSet set = SudokuSet.emptySet();
            set.add(1).add(2).add(3);
            TestCell cell = new TestCell(set, TestCandidate.getCandidateArray());
            assertEquals(set.getMask(), cell.set.getMask());
        }
    }

    @Nested
    @DisplayName("House Attachment")
    class Attachment {
        private TestCell cell;

        @BeforeEach
        void setUp(){
            cell = new TestCell(SudokuSet.emptySet(), TestCandidate.getCandidateArray());
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
        void shouldThrowOnDoubleColumnAttachment() {
            TestHouse mockColumn = mock(TestHouse.class);
            cell.attachColumn(mockColumn);
            assertThrows(IllegalStateException.class, () -> cell.attachColumn(mockColumn));
        }

        @Test
        @DisplayName("Should throw on double box attachment")
        void shouldThrowOnDoubleBoxAttachment() {
            TestHouse mockBox = mock(TestHouse.class);
            cell.attachBox(mockBox);
            assertThrows(IllegalStateException.class, () -> cell.attachBox(mockBox));
        }

        @Test
        @DisplayName("Should attach row successfully")
        void shouldAttachRowSuccessfully(){
            TestHouse mockRow = mock(TestHouse.class);
            cell.attachRow(mockRow);
            assertEquals(mockRow, cell.getRow());
        }

        @Test
        @DisplayName("Should attach column successfully")
        void shouldAttachColumnSuccessfully(){
            TestHouse mockColumn = mock(TestHouse.class);
            cell.attachColumn(mockColumn);
            assertEquals(mockColumn, cell.getColumn());
        }

        @Test
        @DisplayName("Should attach box successfully")
        void shouldAttachBoxSuccessfully(){
            TestHouse mockBox = mock(TestHouse.class);
            cell.attachBox(mockBox);
            assertEquals(mockBox, cell.getBox());
        }
    }

    @Nested
    @DisplayName("Candidate Accessors")
    class CandidateAccessors {

        @Test
        @DisplayName("Should throw when attempting to eliminate a candidate from a solved cell")
        void shouldThrowWhenEliminatingCandidateFromSolvedCell() {
            TestCell solvedCell = new TestCell(SudokuSet.emptySet().add(1), TestCandidate.getCandidateArray()) {
                @Override
                public boolean isSolved() {
                    return true;
                }
            };

            assertThrows(IllegalStateException.class, () -> solvedCell.eliminateCandidate(1));
        }

        @Test
        @DisplayName("Should throw when attempting to eliminate invalid candidates (not between 1-9)")
        void shouldThrowWhenEliminatingInvalidCandidate() {
            TestCell cell = new TestCell(SudokuSet.emptySet(), TestCandidate.getCandidateArray());
            assertThrows(IllegalArgumentException.class, () -> cell.eliminateCandidate(0));
            assertThrows(IllegalArgumentException.class, () -> cell.eliminateCandidate(10));
        }

        @Test
        @DisplayName("Should eliminate candidate successfully")
        void shouldEliminateCandidateSuccessfully() {
            SudokuSet set = SudokuSet.emptySet();
            set.add(1).add(2).add(3);
            TestCell cell = new TestCell(set, TestCandidate.getCandidateArray());

            cell.eliminateCandidate(2);

            assertTrue(cell.findCandidate(1).isPresent());
            assertFalse(cell.findCandidate(2).isPresent());
            assertTrue(cell.findCandidate(3).isPresent());
        }
    }
}