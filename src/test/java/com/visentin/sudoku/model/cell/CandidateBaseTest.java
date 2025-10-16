package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import org.junit.jupiter.api.Test;

import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

class CandidateBaseTest {
    
    static class TestCell extends CellBase<TestCandidate> {
        TestCell(int value, TestCandidate[] candidates) {
            super(candidates, value);
            for (TestCandidate candidate : candidates) {
                candidate.attachCell(this);
            }
        }
    }
    static class TestCandidate extends CandidateBase<TestCell> {
        TestCandidate(int number, boolean eliminated) {
            super(number, eliminated);
        }
    }

    TestCell createTestCell(boolean solved) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, !solved);
        }
        int value = solved ? 2 : 0;
        return new TestCell(value, candidates);
    }

    @Test
    void attachCell_AlreadyInitialized_AssertionErrorThrown() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);

        c.attachCell(cell);
        assertErrorIfEnabled(() -> c.attachCell(cell));
    }
    @Test
    void attachCell_NullCell_ErrorThrown() {
        TestCandidate c = new TestCandidate(1, false);
        assertErrorIfEnabled(() -> c.attachCell(null));
    }

    @Test
    void attachCell_CellSolved_SetsAccessibleToFalse() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(true);

        c.attachCell(cell);
        assertFalse(c.isAccessible());
    }

    @Test
    void attachCell_CellUnsolved_SetsAccessibleToTrue() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);

        c.attachCell(cell);
        assertTrue(c.isAccessible());
    }

    @Test
    void attachCell_SetsInitializedToTrue() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);

        c.attachCell(cell);
        assertTrue(c.isInitialized());
    }

    @Test
    void constructor_NumberNotBetween1And9_AssertionErrorThrown() {
        final int number = 0;
        assertThrows(IllegalArgumentException.class, () -> new TestCandidate(number, false));
        final int number1 = 10;
        assertThrows(IllegalArgumentException.class, () -> new TestCandidate(number1, false));
    }

    @Test
    void constructor_InitializesFields() {
        TestCandidate ct = new TestCandidate(1, true);
        TestCandidate cf = new TestCandidate(1, false);
        assertEquals(1, ct.getNumber());
        assertEquals(1, cf.getNumber());
        assertFalse(ct.isInitialized());
        assertFalse(cf.isInitialized());
        assertEquals(CandidateHighlightMode.NONE, ct.getMode());
        assertEquals(CandidateHighlightMode.NONE, cf.getMode());
        assertTrue(ct.isEliminated());
        assertFalse(cf.isEliminated());
    }

    @Test
    void setHighlight_SameMode_AssertionErrorThrown() {
        CandidateHighlightMode mode = CandidateHighlightMode.ON;
        TestCandidate c = new TestCandidate(1, false);
        c.setHighlight(mode);
        assertErrorIfEnabled(() -> c.setHighlight(mode));
    }

    @Test
    void setEliminated_SameEliminated_AssertionErrorThrown() {
        boolean eliminated = true;
        TestCandidate c = new TestCandidate(1, false);
        c.setEliminated(eliminated);
        assertErrorIfEnabled(() -> c.setEliminated(eliminated));
    }

    @Test
    void setAccessible_AlreadyAccessible_AssertionErrorThrown() {
        TestCandidate c = new TestCandidate(1, false);
        boolean solved = true;
        TestCell cell = createTestCell(solved);
        c.attachCell(cell);
        assertErrorIfEnabled(() -> c.setAccessible(!solved));
    }
}