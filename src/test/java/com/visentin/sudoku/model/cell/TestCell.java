package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;

public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    private TestCandidate[] candidates = new TestCandidate[9];

    TestCell(TestCandidate[] candidates, int value) {
        super(candidates, value);
        for (TestCandidate candidate : candidates) {
            candidate.attachCell(this);
        }
        this.candidates = candidates;
    }

    TestCandidate[] getCandidates() {
        return candidates;
    }

    public static TestCell createTestCell(boolean solved) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, false);
        }
        int value = solved ? 2 : 0;
        return new TestCell(candidates, value);
    }

    public static TestCell createSolvedTestCell(int value) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, false);
        }
        return new TestCell(candidates, value);
    }
}
