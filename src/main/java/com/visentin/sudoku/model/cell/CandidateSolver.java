package com.visentin.sudoku.model.cell;

public class CandidateSolver extends CandidateBase<CellSolver> {

    public CandidateSolver(int number, CellSolver cell, boolean eliminated) {
        super(number, eliminated);
    }
}
