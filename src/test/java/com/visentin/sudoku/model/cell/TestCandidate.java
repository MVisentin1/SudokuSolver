package com.visentin.sudoku.model.cell;

public class TestCandidate extends BaseCandidate<TestCell, TestCandidate>{
    TestCandidate(int number) {
        super(number);
    }

    static TestCandidate[] getCandidateArray(){
        TestCandidate[] candidates = new TestCandidate[10];
        for (int i = 1; i < candidates.length; i++) {
            candidates[i] = new TestCandidate(i);
        }
        return candidates;
    }
}
