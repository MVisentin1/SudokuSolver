package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.candidate.CandidateBase;
import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import com.visentin.sudoku.util.enums.CellHighlightMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellBaseTest {

    static class TestCandidate extends CandidateBase<TestCell> {
        protected TestCandidate(int number, TestCell cell, boolean eliminated) {
            super(number, cell, eliminated);
        }
    }

    static class TestCell extends CellBase<TestCandidate> {
        protected TestCell(TestCandidate[] candidates, int value) {
            super(candidates, value);
        }
    }

    // Helper to create a default TestCell with non-eliminated candidates
    private TestCell createTestCell() {
        TestCandidate[] candidates = new TestCandidate[9];
        TestCell cell = new TestCell(candidates, 0);
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, cell, false);
        }
        return cell;
    }

    @Test
    void constructor_CandidatesLengthNot9_ThrowsException() {
        TestCandidate[] candidates = new TestCandidate[4];
        TestCandidate[] candidates1 = new TestCandidate[9];


        assertThrows(Exception.class, () -> new TestCell(candidates, 1));
        assertThrows(Exception.class, () -> new TestCell(candidates1, 1));
    }

    @Test
    void constructor_InvalidValue_ThrowsException() {
        int value = -1;
        int value1 = 10;
        TestCandidate[] candidates = new TestCandidate[9];
        assertThrows(Exception.class, () -> new TestCell(candidates, value));
        assertThrows(Exception.class, () -> new TestCell(candidates, value1));
    }

    @Test
    void constructor_CorrectParams_InitializesFields() {
        TestCandidate[] candidates = new TestCandidate[9];
        int cellValue = 5;
        boolean eliminated = true;
        TestCell cell = new TestCell(candidates, cellValue);
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, cell, eliminated);
        }

        // Assertions
        assertEquals(cellValue, cell.getValue());
        assertEquals(CellHighlightMode.NONE, cell.getMode());

        // Check each candidate
        for (int i = 0; i < 9; i++) {
            TestCandidate candidate = candidates[i];
            assertEquals(i + 1, candidate.getNumber());
            assertEquals(cell, candidate.getCell());
            assertEquals(eliminated, candidate.isEliminated());
            assertEquals(CandidateHighlightMode.NONE, candidate.getMode());
        }
    }

    @Test
    void setMode() {

    }

    @Test
    void setAsSolved() {
    }

    @Test
    void setAsUnsolved() {
    }

    @Test
    void findCandidate() {
    }

    @Test
    void addCandidate() {
    }

    @Test
    void removeCandidate() {
    }
}