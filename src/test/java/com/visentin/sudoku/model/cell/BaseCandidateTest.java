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
            TestCandidate candidate = new TestCandidate(5);
            assertEquals(5, candidate.getNumber());
        }

        @Test
        @DisplayName("Should throw if getting cell before attachment")
        void shouldThrowIfCellAccessedBeforeAttachment() {
            TestCandidate candidate = new TestCandidate(1);
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
            TestCandidate candidate = new TestCandidate(1);
            candidate.attachCell(dummyCell);

            assertEquals(dummyCell, candidate.getCell(), "The attached cell should match the assigned dummyCell.");
        }

        @Test
        @DisplayName("Should throw when attaching a cell twice")
        void shouldThrowWhenCellAttachedTwice() {
            TestCandidate candidate = new TestCandidate(1);
            candidate.attachCell(dummyCell);

            assertThrows(IllegalStateException.class, () -> candidate.attachCell(dummyCell));
        }

        @Test
        @DisplayName("Should throw when passing null to attachCell")
        void shouldThrowWhenAttachingNull() {
            TestCandidate candidate = new TestCandidate(1);

            assertThrows(NullPointerException.class, () -> candidate.attachCell(null));
        }

        @Test
        @DisplayName("Should throw when attaching a cell twice")
        void shouldThrowOnDoubleAttachment() {
            TestCandidate candidate = new TestCandidate(1);
            candidate.attachCell(dummyCell);

            assertThrows(IllegalStateException.class, () -> candidate.attachCell(dummyCell));
        }

        @Test
        @DisplayName("Should throw when attaching null")
        void shouldThrowOnNullAttachment() {
            TestCandidate candidate = new TestCandidate(1);
            assertThrows(NullPointerException.class, () -> candidate.attachCell(null));
        }
    }
}