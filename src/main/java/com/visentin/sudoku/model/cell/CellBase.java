package com.visentin.sudoku.model.cell;


import com.visentin.sudoku.model.candidate.CandidateBase;
import com.visentin.sudoku.util.enums.CellHighlightMode;
import com.visentin.sudoku.util.exceptions.CellWrongSolvedStateAccessException;

import java.util.Optional;

public abstract class CellBase<C extends CandidateBase<?>> {
    private int value;
    private final C[] candidates;
    private CellHighlightMode mode = CellHighlightMode.NONE;

    // ------------ constructor -------------------------------
    protected CellBase(C[] candidates, int value) {
        if (candidates.length != 9) {
            throw new IllegalArgumentException("Cell must contain 9 candidates");
        }
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("invalid value, must be between 0 and 9");
        }
        this.candidates = candidates;
        this.value = value;
    }

    // ------------ getters setters ----------------------------
    public int getValue() {
        return this.value;
    }
    public CellHighlightMode getMode() {
        return mode;
    }
    public void setMode(CellHighlightMode mode) {
        assert this.mode != mode : "already set at mode : " + this.mode;
        this.mode = mode;
    }

    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.value != 0;
    }
    public void setAsSolved(int value) {
        valueValidation(value);
        assert this.value == 0 || this.value != value : "already set at value : " + this.value;
        this.value = value;
    }
    public void setAsUnsolved() {
        assert this.value != 0 : "already unsolved";
        this.value = 0;
    }

    // ------------ candidates ----------------------------------
    public Optional<C> findCandidate(int i){
        candidateIndexValidation(i);
        assert !isSolved() : "Cannot query candidates of a solved cell";
        C candidate = this.candidates[i-1];
        if (candidate.isEliminated()){
            return Optional.empty();
        }
        return Optional.of(candidate);
    }
    public void addCandidate(int i) {
        candidateIndexValidation(i);
        if (isSolved()) {
            throw new CellWrongSolvedStateAccessException("Cannot add candidates to solved cell");
        }
        this.candidates[i-1].setEliminated(false);
    }
    public void removeCandidate(int i){
        candidateIndexValidation(i);
        if (isSolved()) {
            throw new CellWrongSolvedStateAccessException("Cannot remove candidates to solved cell");
        }
        this.candidates[i-1].setEliminated(true);
    }

    // ------------ private helpers -----------------------------
    private void valueValidation(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("invalid value");
        }
    }
    private void candidateIndexValidation(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("invalid candidate");
        }
    }

}
