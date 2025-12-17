package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.TestCell;
import com.visentin.sudoku.model.grid.house.TestHouse;

import static com.visentin.sudoku.model.grid.house.TestHouse.createTestHouse;

public class TestGrid extends GridBase<TestCell> {
    TestGrid(TestHouse[] rows, TestHouse[] columns, TestHouse[] boxes) {
        super(rows, columns, boxes);
    }

    static TestGrid createTestGrid() {
        TestHouse[] rows = new TestHouse[9];
        TestHouse[] columns = new TestHouse[9];
        TestHouse[] boxes = new TestHouse[9];
        return new TestGrid(rows, columns, boxes);
    }
}
