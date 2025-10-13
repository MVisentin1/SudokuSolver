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
        if (this.mode == mode) {
            throw new IllegalStateException("already set at : " + mode);
        }
        this.mode = mode;
    }

    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.value != 0;
    }
    public void setAsSolved(int value, boolean overwrite) {
        valueValidation(value);
        if (!overwrite && this.value != 0) {
            throw new CellWrongSolvedStateAccessException();
        }
        this.value = value;
    }
    public void setAsUnsolved(boolean overwrite) {
        if (!overwrite && this.value == 0) {
            throw new CellWrongSolvedStateAccessException();
        }
        this.value = 0;
    }

    // ------------ candidates ----------------------------------
    public Optional<C> findCandidate(int i){
        candidateIndexValidation(i);
        C candidate = this.candidates[i-1];
        if (candidate.isEliminated()){
            return Optional.empty();
        }
        return Optional.of(candidate);
    }
    public void addCandidate(int i) {
        candidateIndexValidation(i);
        this.candidates[i-1].setEliminated(false);
    }
    public void removeCandidate(int i){
        candidateIndexValidation(i);
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
