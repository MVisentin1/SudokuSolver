package com.visentin.sudoku.model.cell;

import java.util.List;
import java.util.Optional;

public abstract class CellBase<C extends CandidateBase<?>> {
    private int value;
    private final List<C> candidates;

    // ------------ constructor -------------------------------
    CellBase(C[] candidates, int value) {
        assert candidates.length == 9 : "candidates must have 9 candidates";
        assert value >= 0 && value <= 9 : "value must be between 0 and 9";
        this.value = value;
        this.candidates = List.of(candidates);
    }

    // ------------  getters setters ----------------------------
    public int getValue() {
        return this.value;
    }


    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.value != 0;
    }
    public void solve(int value) {
        valueValidation(value);
        assert this.value == 0 : "already set at value : " + this.value;
        this.value = value;
    }
    public void unsolve() {
        assert this.value != 0 : "already unsolved";
        this.value = 0;
    }

    // ------------ candidates ----------------------------------
    public Optional<C> findCandidate(int i){
        C candidate = this.candidates.get(i-1);
        if (isSolved() || candidate.isEliminated()){
            return Optional.empty();
        }
        return Optional.of(candidate);
    }
    public void addCandidate(int i) {
        if (isSolved()) {
            throw new IllegalStateException("Cannot add candidates to solved cell");
        }
        assert this.candidates.get(i-1).isEliminated() : "candidate already eliminated";
        this.candidates.get(i-1).setEliminated(false);
    }
    public void removeCandidate(int i){
        if (isSolved()) {
            throw new IllegalStateException("Cannot remove candidates to solved cell");
        }
        this.candidates.get(i-1).setEliminated(true);
    }

    // ------------ private helpers -----------------------------
    private void valueValidation(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("invalid value");
        }
    }

}
