package com.visentin.sudoku.model.candidate;

import com.visentin.sudoku.model.cell.CellSolver;

public class CandidateSolver extends CandidateBase<CellSolver> {

    public CandidateSolver(int number, CellSolver cell, boolean eliminated) {
        super(number, cell, eliminated);
    }
}
