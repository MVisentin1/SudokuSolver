package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.dataStructures.SudokuSet;
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
            dummyCell = new TestCell(SudokuSet.emptySet(), new TestCandidate[10]);
        }

        @Test
        @DisplayName("Should successfully attach a cell")
        void shouldAttachCell() {
            TestCandidate candidate = new TestCandidate(1);
            candidate.attachCell(dummyCell);

            assertEquals(dummyCell, candidate.getCell(), "The attached cell should match the assigned dummyCell.");
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
    }

    @Nested
    @DisplayName("Elimination Status")
    class EliminationStatus {
        @Test
        @DisplayName("Should return true if candidate is eliminated")
        void shouldBeEliminated() {
            TestCell cell = TestCell.createUnsolvedTestCell();
            if (cell.findCandidate(1).isPresent()) {
                cell.eliminateCandidate(1);
            }
            assertTrue(cell.candidates[1].isEliminated());
        }

        @Test
        @DisplayName("Should be consistent with cell.findCandidate method")
        void shouldBeConsistentWithCellFindCandidate(){
            TestCell cell = TestCell.createUnsolvedTestCell();
            for (int i = 1; i <= 9; i++) {
                assertEquals(cell.findCandidate(i).isPresent(), !cell.candidates[i].isEliminated());
            }
        }
    }
}