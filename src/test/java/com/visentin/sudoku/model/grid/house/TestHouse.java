package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.TestCell;

public class TestHouse extends BaseHouse<TestCell, TestHouse> {
    TestHouse(TestCell[] cells) {
        super(cells);
    }
}
