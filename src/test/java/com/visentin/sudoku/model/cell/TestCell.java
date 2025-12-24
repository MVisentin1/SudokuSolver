package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;


public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    TestCell(SudokuSet set, TestCandidate[] candidates) {
        super(set, candidates);
    }

    @Override
    public int getValue() {
        return set.numberOfTrailingZeros();
    }

    @Override
    public boolean isSolved() {
        return set.cardinality() == 1;
    }
}
