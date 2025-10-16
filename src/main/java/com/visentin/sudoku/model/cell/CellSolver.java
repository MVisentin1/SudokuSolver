package com.visentin.sudoku.model.cell;

public class CellSolver extends CellBase<CandidateSolver> {

    // -------------------- constructor -----------------------------
    protected CellSolver(CandidateSolver[] candidates, int value) {
        super(candidates, value);
        for (CandidateSolver candidate : candidates) {
            candidate.attachCell(this);
        }
    }
}
