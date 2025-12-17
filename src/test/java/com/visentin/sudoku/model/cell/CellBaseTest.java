package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CellHighlightMode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

class CellBaseTest {

    private static TestCell createTestCell(boolean solved, int value, boolean eliminated) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, eliminated);
        }
        return new TestCell(candidates, solved ? value : 0);
    }
    @Test
    void constructor_NullCandidates_NullPointerExceptionThrown() {
        assertThrows(NullPointerException.class, () -> new TestCell(null, 0));
    }

    @Test
    void constructor_CandidatesNullElement_NullPointerExceptionThrown() {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, false);
            if (i == 4) {
                candidates[i] = null;
            }
        }
        assertThrows(NullPointerException.class, () -> new TestCell(candidates, 0));
    }

    @Test
    void constructor_CandidatesLengthNot9_AssertionErrorThrown() {
        int length = 8;
        assertErrorIfEnabled(() -> new TestCell(new TestCandidate[length], 0));
    }

    @Test
    void constructor_ValueNotBetween0And9_AssertionErrorThrown() {
        final int value = -1;
        assertErrorIfEnabled(() -> new TestCell(new TestCandidate[9], value));
        final int value1 = 10;
        assertErrorIfEnabled(() -> new TestCell(new TestCandidate[9], value1));
    }

    @Test void constructor_SolvedCell_InitializesFields() {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, false);
        }
        int value = 2;
        TestCell cell = new TestCell(candidates, value);
        assertEquals(value, cell.getValue());
        assertEquals(candidates, cell.getCandidates());
        assertEquals(CellHighlightMode.NONE, cell.getHighlightMode());
    }

    @Test
    void constructor_UnsolvedCell_InitializesFields() {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, false);
        }
        int value = 0;
        TestCell cell = new TestCell(candidates, value);
        assertEquals(value, cell.getValue());
        assertEquals(candidates, cell.getCandidates());
        assertEquals(CellHighlightMode.NONE, cell.getHighlightMode());
    }

    @Test
    void setHighlightMode_NullMode_NullPointerExceptionThrown() {
        TestCell cell = createTestCell(false, 0, false);
        assertThrows(NullPointerException.class, () -> cell.setHighlightMode(null));
    }

    @Test
    void setHighlightMode_SameMode_AssertionErrorThrown() {
        CellHighlightMode mode = CellHighlightMode.ON;
        TestCell cell = createTestCell(false, 0, false);
        cell.setHighlightMode(mode);
        assertErrorIfEnabled(() -> cell.setHighlightMode(mode));
    }

    @Test
    void solve_ValueNotBetween0And9_IllegalArgumentExceptionThrown() {
        final int value = 0;
        TestCell cell = createTestCell(false, 0, false);
        assertThrows(IllegalArgumentException.class, () -> cell.solve(value));
        final int value1 = 10;
       assertThrows(IllegalArgumentException.class, () -> cell.solve(value1));
    }

    @Test
    void solve_AlreadySolved_AssertionErrorThrown() {
        TestCell cell = createTestCell(true, 2, false);
        assertErrorIfEnabled(() -> cell.solve(1));
    }

    @Test
    void unsolve_AlreadyUnsolved_AssertionErrorThrown() {
        TestCell cell = createTestCell(false, 0, false);
        assertErrorIfEnabled(cell::unsolve);
    }

    @Test
    void find_InvalidCandidate_ArrayIndexOutOfBoundsExceptionThrown() {
        TestCell cell = createTestCell(false, 0, false);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.findCandidate(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.findCandidate(10));
    }
    @Test
    void find_CandidateAlreadyEliminated_OptionalEmptyReturned() {
        TestCell cell = createTestCell(false, 0, true);
        Optional<TestCandidate> candidate = cell.findCandidate(1);
        assertFalse(candidate.isPresent());
    }

    @Test
    void find_CandidateNotEliminated_OptionalReturnedEqualsCandidate() {
        TestCell cell = createTestCell(false, 0, false);
        int candidateNumber = 1;
        Optional<TestCandidate> candidate = cell.findCandidate(candidateNumber);
        assertTrue(candidate.isPresent());
        assertEquals(cell.getCandidates()[candidateNumber - 1], candidate.get());
    }

    @Test
    void addCandidate_InvalidCandidate_ArrayIndexOutOfBoundsExceptionThrown() {
        TestCell cell = createTestCell(false, 0, false);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.addCandidate(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.addCandidate(10));
    }
    @Test
    void addCandidate_CellIsSolved_IllegalStateExceptionThrown() {
        TestCell cell = createTestCell(true, 2, false);
        assertThrows(IllegalStateException.class, () -> cell.addCandidate(1) );
    }
    @Test
    void addCandidate_CandidateAlreadyInCell_AssertionErrorThrown() {
        TestCell cell = createTestCell(false, 0, true);
        int candidateNumber = 1;
        cell.addCandidate(candidateNumber);
        assertErrorIfEnabled(() -> cell.addCandidate(candidateNumber));
    }

    @Test
    void removeCandidate_InvalidCandidate_ArrayIndexOutOfBoundsExceptionThrown() {
        TestCell cell = createTestCell(false, 0, false);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.removeCandidate(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cell.removeCandidate(10));
    }

    @Test
    void removeCandidate_CellIsSolved_IllegalStateExceptionThrown() {
        TestCell cell = createTestCell(true, 2, false);
        assertThrows(IllegalStateException.class, () -> cell.removeCandidate(1));
    }

    @Test
    void removeCandidate_CandidateNotInCell_AssertionErrorThrown() {
        TestCell cell = createTestCell(false, 0, false);
        int candidateNumber = 1;
        cell.removeCandidate(candidateNumber);
        assertErrorIfEnabled(() -> cell.removeCandidate(candidateNumber));
    }








}