package com.visentin.sudoku.model.cell;

public class SolverCandidate extends BaseCandidate<SolverCell, SolverCandidate> {

    public SolverCandidate(int number, boolean eliminated) {
        super(number, eliminated);
    }
}
