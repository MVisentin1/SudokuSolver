package com.visentin.sudoku.model.candidate;

import com.visentin.sudoku.model.cell.CellBase;
import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import com.visentin.sudoku.util.enums.CellHighlightMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CandidateBaseTest {
    
    static class TestCell extends CellBase<TestCandidate> {
        TestCell() {
            super(new TestCandidate[9], 1);
        }
    }
    static class TestCandidate extends CandidateBase<TestCell> {
        TestCandidate(int number, TestCell cell, boolean eliminated) {
            super(number, cell, eliminated);
        }
    }
    @Test
    void constructor_NumberNotBetween1And9_ThrowsException() {
        int number = 0;
        int number1 = -1;
        int number2 = 10;

        assertThrows(Exception.class, () -> new TestCandidate(number, new TestCell() , false));
        assertThrows(Exception.class, () -> new TestCandidate(number1, new TestCell(), false));
        assertThrows(Exception.class, () -> new TestCandidate(number2, new TestCell(), false));
    }

    @Test
    void constructor_InitializeFields() {
        int number = 2;
        boolean eliminated = false;
        TestCell cell = new TestCell();

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
    void setMode_SameMode_ExceptionThrown() {
        CandidateHighlightMode mode = CandidateHighlightMode.ON;

        TestCandidate c = new TestCandidate(1, new TestCell(), false);
        TestCandidate c1 = new TestCandidate(1, new TestCell(), false);
        c1.setMode(mode);

        assertThrows(Exception.class,  () -> c.setMode(c.getMode()));
        assertThrows(Exception.class,  () -> c1.setMode(mode));
    }

    @Test
    void setMode_DifferentMode_SetsMode() {
        CandidateHighlightMode mode = CandidateHighlightMode.ON;

        TestCandidate c = new TestCandidate(1, new TestCell(), false);
        c.setMode(mode);

        assertEquals(mode, c.getMode());
    }

    @Test
    void setEliminated_SameBool_ExceptionThrown() {
        boolean eliminated = true;
        TestCandidate c = new TestCandidate(1, new TestCell(), eliminated);
        assertThrows(Exception.class,  () -> c.setEliminated(eliminated));
    }

    @Test
    void setEliminated_DifferentBool_SetsEliminated() {
        boolean eliminated = true;

        TestCandidate c = new TestCandidate(1, new TestCell(), !eliminated);
        c.setEliminated(eliminated);

        assertEquals(eliminated, c.isEliminated());
    }
}