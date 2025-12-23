package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.SolverHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SolverCell extends BaseCell<SolverCell, SolverCandidate, SolverHouse> {
    private SolverCandidate[] candidates = new SolverCandidate[10];
    // -------------------- constructor -----------------------------
    SolverCell(SudokuSet set, SolverCandidate[] candidates) {
        super(set);
        assert candidates != null : "candidates cannot be null";
        assert candidates.length == 10 : "candidates must contain 9 candidates";
        this.candidates = Arrays.copyOf(candidates, candidates.length);
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

    @Override
    Optional<SolverCandidate> findCandidate(int i) {
        if (!set.contains(i)) {
            return Optional.empty();
        } else {
            return Optional.of(candidates[i]);
        }
    }
}
