package com.visentin.sudoku.model.cell;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BaseCellTest {

    private static TestCell createTestCell(boolean solved, int value, boolean eliminated) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, eliminated);
        }
        return new TestCell(candidates, solved ? value : 0);
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









}