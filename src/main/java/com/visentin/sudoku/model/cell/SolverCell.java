package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;

import java.util.List;

public class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {

    // -------------------- constructor -----------------------------
    public SolverCell(List<SolverCandidate> candidates, int value) {
        super(candidates, value);
    }
}
