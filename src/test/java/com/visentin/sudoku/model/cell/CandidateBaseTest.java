package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import org.junit.jupiter.api.Test;

import static com.visentin.sudoku.model.cell.TestCell.createTestCell;
import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

class CandidateBaseTest {

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
    void attachCell_SetsInitializedToTrue() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);

        c.attachCell(cell);
        assertTrue(c.isInitialized());
    }

    @Test
    void attachCell_SetsCellToCandidate() {
        TestCandidate c = new TestCandidate(1, false);
        TestCell cell = createTestCell(false);
        c.attachCell(cell);
        assertEquals(cell, c.getCell());
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
        assertEquals(CandidateHighlightMode.NONE, ct.getHighlightMode());
        assertEquals(CandidateHighlightMode.NONE, cf.getHighlightMode());
        assertTrue(ct.isEliminated());
        assertFalse(cf.isEliminated());
    }

    @Test
    void setHighlight_NullMode_NullPointerExceptionThrown() {
        TestCandidate c = new TestCandidate(1, false);
        assertThrows(NullPointerException.class, () -> c.setHighlight(null));
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
}