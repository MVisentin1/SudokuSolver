package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;

public class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {

    // -------------------- constructor -----------------------------
    public SolverCell(SolverCandidate[] candidates, int value) {
        super(candidates, value);
        for (SolverCandidate candidate : candidates) {
            candidate.attachCell(this);
        }
    }
}
