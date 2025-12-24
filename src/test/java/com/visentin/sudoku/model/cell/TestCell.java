package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;


public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    TestCell(SudokuSet set) {
        super(set);
    }

    @Override
    public int getValue() {
        return set.numberOfTrailingZeros();
    }

    @Override
    public boolean isSolved() {
        return set.cardinality() == 1;
    }

    @Override
    TestCandidate[] getCandidates() {
        TestCandidate[] candidates = new TestCandidate[10];
        for (int i = 1; i < candidates.length; i++) {
            candidates[i] = new TestCandidate(i);
        }
        return candidates;
    }
}
