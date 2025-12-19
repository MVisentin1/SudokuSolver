package com.visentin.sudoku.model.cell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCellCandidateCreationTest {

    @Test
    void testCreateUnsolvedCell() {
        TestCell cell = TestCell.createTestCell(false);

        // Cell value
        assertEquals(0, cell.getValue());
        assertFalse(cell.isSolved());

        // All candidates initialized correctly
        TestCandidate[] candidates = cell.getCandidates();
        assertEquals(9, candidates.length);

        for (int i = 0; i < 9; i++) {
            TestCandidate c = candidates[i];
            assertEquals(i + 1, c.getNumber());
            assertFalse(c.isEliminated());   // unsolved cell → candidates not eliminated
        }
    }

    @Test
    void testCreateSolvedCell() {
        TestCell cell = TestCell.createTestCell(true);

        // Cell value
        assertEquals(2, cell.getValue());
        assertTrue(cell.isSolved());

        // All candidates initialized correctly
        TestCandidate[] candidates = cell.getCandidates();
        assertEquals(9, candidates.length);

        for (int i = 0; i < 9; i++) {
            TestCandidate c = candidates[i];
            assertEquals(i + 1, c.getNumber());
            assertFalse(c.isEliminated());   // solved cell → candidates eliminated
        }
    }
}
