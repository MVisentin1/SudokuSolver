package com.visentin.sudoku.model.cell;

import org.junit.jupiter.api.Test;

import static com.visentin.sudoku.model.cell.TestCell.createTestCell;
import static org.junit.jupiter.api.Assertions.*;

class BaseCandidateTest {


    @Test
    void attachCell_SetsCellToCandidate() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);
        c.attachCell(cell);
        assertEquals(cell, c.getCell());
    }

    @Test
    void constructor_InitializesFields() {
        TestCandidate ct = new TestCandidate(1, true);
        TestCandidate cf = new TestCandidate(1, false);
        assertEquals(1, ct.getNumber());
        assertEquals(1, cf.getNumber());
        assertTrue(ct.isEliminated());
        assertFalse(cf.isEliminated());
    }
}