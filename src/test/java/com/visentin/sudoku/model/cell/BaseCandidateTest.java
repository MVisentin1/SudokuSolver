package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseCandidateTest {


    @Nested
    @DisplayName("Constructor and Initialization")
    class Initialization {

        @Test
        @DisplayName("Should initialize with correct number and state")
        void shouldInitializeCorrectly() {
            TestCandidate candidate = new TestCandidate(5, false);
            assertEquals(5, candidate.getNumber());
            assertFalse(candidate.isEliminated());
        }

        @Test
        @DisplayName("Should throw if getting cell before attachment")
        void shouldThrowIfCellAccessedBeforeAttachment() {
            TestCandidate candidate = new TestCandidate(1, false);
            assertThrows(AssertionError.class, candidate::getCell,
                    "Should fail because cell is still null");
        }
    }

    @Nested
    @DisplayName("Cell Attachment")
    class Attachment {

        private TestCell dummyCell;

        @BeforeEach
        void setUp() {
            CellFactory<TestCell, TestCandidate, TestHouse> cellFactory
                    = new CellFactory<>(TestCell::new, TestCandidate::new);
            dummyCell = cellFactory.createSolvedCell(1);
        }

        @Test
        @DisplayName("Should successfully attach a cell")
        void shouldAttachCell() {
            TestCandidate candidate = new TestCandidate(1, false);
            candidate.attachCell(dummyCell);

            assertEquals(dummyCell, candidate.getCell());
        }

        @Test
        @DisplayName("Should throw when attaching a cell twice")
        void shouldThrowOnDoubleAttachment() {
            TestCandidate candidate = new TestCandidate(1, false);
            candidate.attachCell(dummyCell);

            assertThrows(IllegalStateException.class, () -> candidate.attachCell(dummyCell));
        }

        @Test
        @DisplayName("Should throw when attaching null")
        void shouldThrowOnNullAttachment() {
            TestCandidate candidate = new TestCandidate(1, false);
            assertThrows(NullPointerException.class, () -> candidate.attachCell(null));
        }
    }

    @Nested
    @DisplayName("State Management")
    class StateManagement {

        @Test
        @DisplayName("Should toggle eliminated state")
        void shouldSetEliminated() {
            TestCandidate candidate = new TestCandidate(1, false);

            candidate.eliminate();
            assertTrue(candidate.isEliminated());

            candidate.setToActive();
            assertFalse(candidate.isEliminated());
        }
    }
}