package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;

import java.util.List;

public class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {

    // -------------------- constructor -----------------------------
    SolverCell(List<SolverCandidate> candidates, int value, boolean fixed) {
        super(candidates, value, fixed);
    }
}
