package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.TestCell;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

public class TestHouse extends BaseHouse<TestCell, TestHouse> {
    TestHouse(SudokuSet solvedNumbers, TestCell[] cells) {
        super(solvedNumbers, cells);
    }
}
