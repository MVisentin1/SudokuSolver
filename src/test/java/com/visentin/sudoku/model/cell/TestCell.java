package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;

import java.util.List;

public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    TestCell(List<TestCandidate> candidates, int value, boolean fixed) {
        super(candidates, value, fixed);
    }
}
