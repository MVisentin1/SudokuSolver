package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
            ArrayList<TestCandidate> candidateList = cellFactory.getCandidateList(new boolean[10]);

            TestCell cell = new TestCell(candidateList, 1, false);
            List<TestCandidate> cellCandidateList = cell.getCandidates();

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






}