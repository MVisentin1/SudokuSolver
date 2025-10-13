package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.candidate.CandidateSolver;

public class CellSolver extends CellBase<CandidateSolver> {

    // -------------------- constructor -----------------------------
    protected CellSolver(CandidateSolver[] candidates, int value) {
        super(candidates, value);
    }
}
