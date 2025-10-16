package com.visentin.sudoku.model.cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellBaseTest {
    @Test
    void constructor_NumberNotBetween1And9_AssertionErrorThrown() {
        final int number = 0;
        assertThrows(IllegalArgumentException.class, () -> new TestCell(new TestCandidate[9], number));
        final int number1 = 10;
        assertThrows(IllegalArgumentException.class, () -> new TestCell(new TestCandidate[9], number1));
    }
}