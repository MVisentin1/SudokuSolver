package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.TestCell;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHouse extends HouseBase<TestCell> {
    TestHouse(TestCell[] cells) {
        super(cells);
    }

    static TestHouse createTestHouse(Set<Integer> n) {
        TestCell[] cells = new TestCell[9];
        for (int i = 0; i < 9; i++) {
            if(n.contains(i+1)) {
                cells[i] = TestCell.createSolvedTestCell(i+1);
            } else {
                cells[i] = TestCell.createTestCell(false);
            }
        }
        return new TestHouse(cells);
    }
}
