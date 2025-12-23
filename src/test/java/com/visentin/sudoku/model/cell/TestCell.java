package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.Optional;

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
    Optional<TestCandidate> findCandidate(int i) {
        if (isSolved() || !set.contains(i)) {
            return Optional.empty();
        } else {
            return Optional.of(new TestCandidate(i));
        }
    }
}
