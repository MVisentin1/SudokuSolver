package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;

import java.util.List;
import java.util.Optional;

public abstract class BaseCell<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {
    private int value;
    private final List<C> candidates;
    private H row = null;
    private H column = null;
    private H box = null;

    // ------------ constructor -------------------------------
    BaseCell(C[] candidates, int value) {
        assert candidates.length == 9 : "candidates must have 9 candidates";
        assert value >= 0 && value <= 9 : "value must be between 0 and 9";
        this.value = value;
        this.candidates = List.of(candidates);
    }

    public void attachRow(H row) {
        assert this.row == null : "Row already attached";
        this.row = row;
    }

    public void attachColumn(H column) {
        assert this.column == null : "Column already attached";
        this.column = column;
    }

    public void attachBox(H box) {
        assert this.box == null : "Box already attached";
        this.box = box;
    }

    // ------------  getters setters ----------------------------
    public int getValue() {
        return this.value;
    }
    public H getRow() {
        return row;
    }
    public H getColumn() {
        return column;
    }
    public H getBox() {
        return box;
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
