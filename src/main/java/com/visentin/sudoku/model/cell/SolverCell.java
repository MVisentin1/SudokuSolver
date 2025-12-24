package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;


public final class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {
    // -------------------- constructor -----------------------------
    SolverCell(SudokuSet set, SolverCandidate[] candidates) {
        super(set, candidates);
    }

    @Override
    public int getValue() {
        if (!isSolved()) throw new IllegalStateException("Cell is not solved");
        return set.numberOfTrailingZeros();
    }

    @Override
    public boolean isSolved() {
        return set.cardinality() == 1;
    }

}
