package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.TestCell;

import java.util.Set;


public class TestHouse extends HouseBase<TestCell> {
    TestHouse(TestCell[] cells) {
        super(cells);
    }

    public static TestHouse createTestHouse(Set<Integer> n) {
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

    public static TestHouse createTestHouse() {
         return createTestHouse(Set.of(1,2,3,4,7,8,9));
    }
}
