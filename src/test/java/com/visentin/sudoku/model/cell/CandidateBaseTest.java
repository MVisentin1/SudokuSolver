package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import org.junit.jupiter.api.Test;

import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

class CandidateBaseTest {
    
    static class TestCell extends CellBase<TestCandidate> {
        TestCell(int value) {
            super(new TestCandidate[9], value);
        }
    }
    static class TestCandidate extends CandidateBase<TestCell> {
        TestCandidate(int number, TestCell cell, boolean eliminated) {
            super(number, eliminated);
        }
    }
    @Test
    void constructor_NumberNotBetween1And9_ThrowsException() {
        int number = 0;
        int number1 = -1;
        int number2 = 10;

        assertThrows(Exception.class, () -> new TestCandidate(number, new TestCell(1) , false));
        assertThrows(Exception.class, () -> new TestCandidate(number1, new TestCell(1), false));
        assertThrows(Exception.class, () -> new TestCandidate(number2, new TestCell(1), false));
    }

    @Test
    void constructor_UnsolvedCell_InitializesFields() {
        int number = 2;
        boolean eliminated = false;
        TestCell cell = new TestCell(0);

        TestCandidate c = new TestCandidate(number, cell, eliminated);
        TestCandidate c1 = new TestCandidate(1, cell, !eliminated);

        assertNotNull(c);
        assertEquals(number, c.getNumber());
        assertEquals(cell, c.getCell());
        assertEquals(eliminated, c.isEliminated());
        assertEquals(CandidateHighlightMode.NONE, c.getMode());

        assertNotNull(c1);
        assertEquals(!eliminated, c1.isEliminated());
    }

    @Test
    void constructor_SolvedCell_InitializesFields() {
        int number = 2;
        boolean eliminated = false;
        TestCell cell = new TestCell(1);
        cell.setAsSolved(3);

        TestCandidate c = new TestCandidate(number, cell, eliminated);
        TestCandidate c1 = new TestCandidate(1, cell, !eliminated);

        assertNotNull(c);
        assertEquals(number, c.getNumber());
        assertEquals(cell, c.getCell());
        assertEquals(eliminated, c.isEliminated());
        assertEquals(CandidateHighlightMode.NONE, c.getMode());

        assertNotNull(c1);
        assertEquals(!eliminated, c1.isEliminated());
    }


    @Test
    void setMode_SameMode_AssertionErrorThrown() {
        CandidateHighlightMode mode = CandidateHighlightMode.ON;

        TestCandidate c = new TestCandidate(1, new TestCell(0), false);
        c.setHighlight(mode);

        assertErrorIfEnabled(() -> c.setHighlight(mode));
    }

    @Test
    void setEliminated_SameBool_AssertionErrorThrown() {
        boolean eliminated = true;

        TestCandidate c = new TestCandidate(1, new TestCell(0), eliminated);

        assertErrorIfEnabled(() -> c.setEliminated(eliminated));
    }
}