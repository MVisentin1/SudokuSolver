package com.visentin.sudoku.model.cell;

public class SolverCell extends BaseCell<SolverCandidate> {

    // -------------------- constructor -----------------------------
    public SolverCell(SolverCandidate[] candidates, int value) {
        super(candidates, value);
        for (SolverCandidate candidate : candidates) {
            candidate.attachCell(this);
        }
    }
}
