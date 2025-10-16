package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import com.visentin.sudoku.util.enums.CellHighlightMode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

class CellBaseTest {

    static class TestCandidate extends CandidateBase<TestCell> {
        protected TestCandidate(int number, TestCell cell, boolean eliminated) {
            super(number, eliminated);
        }
    }

    static class TestCell extends CellBase<TestCandidate> {
        protected TestCell(TestCandidate[] candidates, int value) {
            super(candidates, value);
        }
    }

    // Helper to create a default unsolved TestCell with non-eliminated candidates
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
        TestCandidate[] candidates1 = new TestCandidate[10];


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
    void setMode_SameMode_AssertionErrorThrown() {
        CellHighlightMode mode = CellHighlightMode.ON;

        TestCell c = createTestCell();
        c.setMode(mode);

        assertErrorIfEnabled(() -> c.setMode(mode));
    }

    @Test
    void setAsSolved_SameValue_AssertionErrorThrown() {
        TestCell c = createTestCell();
        int value = 5;

        c.setAsSolved(value);
        assertErrorIfEnabled(() -> c.setAsSolved(value));
    }

    @Test
    void setAsUnsolved_AlreadyUnsolved_AssertionErrorThrown() {
        TestCell c = createTestCell();

        assertErrorIfEnabled(c::setAsUnsolved);
    }

    @Test
    void findCandidate_CellSolved_AssertionErrorThrown() {
        TestCell c = createTestCell();
        c.setAsSolved(5);
        assertErrorIfEnabled(() -> c.findCandidate(1));
    }

    @Test
    void findCandidate_CandidateNotPresent_EmptyOptionalReturned() {
        TestCell c = createTestCell();
        int value = 5;
        c.removeCandidate(value);
        assertFalse(c.findCandidate(value).isPresent());
    }

    @Test
    void findCandidate_CandidatePresent_OptionalWithCandidateReturned() {
        TestCell c = createTestCell();
        int value = 5;

        Optional<TestCandidate> candidate = c.findCandidate(value);

        assertTrue(candidate.isPresent());
        assertEquals(value, candidate.get().getNumber());
        assertEquals(c, candidate.get().getCell());
        assertFalse(candidate.get().isEliminated());
    }

    @Test
    void addCandidate_CellSolved_ErrorThrown() {
        TestCell c = createTestCell();
        c.setAsSolved(5);

        assertThrows(Exception.class, () -> c.addCandidate(1));
    }

    @Test
    void addCandidate_NormalCase_CandidateAdded() {
        TestCell c = createTestCell();
        int value = 5;

        c.removeCandidate(value);
        c.addCandidate(value);
        Optional<TestCandidate> candidate = c.findCandidate(value);
        if (candidate.isPresent()) {
            assertEquals(value, candidate.get().getNumber());
            assertEquals(c, candidate.get().getCell());
            assertFalse(candidate.get().isEliminated());
        } else {
            fail("Candidate not found");
        }
    }

    @Test
    void removeCandidate_CellSolved_ErrorThrown() {
        TestCell c = createTestCell();
        c.setAsSolved(5);

        assertThrows(Exception.class, () -> c.removeCandidate(1));
    }

    @Test
    void removeCandidate_NormalCase_CandidateRemoved() {
        TestCell c = createTestCell();
        int value = 5;

        c.removeCandidate(value);
        Optional<TestCandidate> candidate = c.findCandidate(value);
        if (candidate.isPresent()) {
            fail("Candidate not removed");
        }
    }
}