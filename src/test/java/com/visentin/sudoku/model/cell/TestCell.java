package com.visentin.sudoku.model.cell;

public class TestCell extends CellBase<TestCandidate> {
    private final TestCandidate[] candidates = new TestCandidate[9];

    TestCell(TestCandidate[] candidates, int value) {
        super(candidates, value);
        for (TestCandidate candidate : candidates) {
            candidate.attachCell(this);
        }
        System.arraycopy(candidates, 0, this.candidates, 0, 9);
    }

    TestCandidate[] getCandidates() {
        return candidates;
    }

    static TestCell createTestCell(boolean solved) {
        TestCandidate[] candidates = new TestCandidate[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = new TestCandidate(i + 1, !solved);
        }
        int value = solved ? 2 : 0;
        return new TestCell(candidates, value);
    }
}
