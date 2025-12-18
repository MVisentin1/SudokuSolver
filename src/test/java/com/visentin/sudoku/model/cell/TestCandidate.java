package com.visentin.sudoku.model.cell;

public class TestCandidate extends BaseCandidate<TestCell, TestCandidate> {
    TestCandidate(int number, boolean eliminated) {
        super(number, eliminated);
    }
}
