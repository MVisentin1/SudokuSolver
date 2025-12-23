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
            for (int i = 0; i < candidateList.size(); i++) {
                assertEquals(i+1, candidateList.get(i).getNumber());
                assertEquals(candidateList.get(i), cellCandidateList.get(i));
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
}