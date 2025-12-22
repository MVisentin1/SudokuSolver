package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.List;
import java.util.Optional;

public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    TestCell(List<TestCandidate> candidates, SudokuSet set, boolean fixed) {
        super(candidates, set, fixed);
    }

    @Override
    Optional<TestCandidate> findCandidate(int i) {
        return Optional.of(candidateList.get(i-1));
    }
}
