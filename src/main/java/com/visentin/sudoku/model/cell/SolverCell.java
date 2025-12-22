package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.List;
import java.util.Optional;

public class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {

    // -------------------- constructor -----------------------------
    SolverCell(List<SolverCandidate> candidates, SudokuSet set, boolean fixed) {
        super(candidates, set, fixed);
    }

    @Override
    Optional<SolverCandidate> findCandidate(int i) {
        if (candidateList.get(i-1).isEliminated()) return Optional.empty();
        return Optional.of(candidateList.get(i-1));
    }
}
