package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;

import java.util.List;
import java.util.Optional;

public abstract class BaseCell<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {
    private int value;
    private final boolean fixed;
    private final List<C> candidates;
    private H row = null, column = null, box = null;

    // ------------ constructor -------------------------------
    BaseCell(List<C> candidates, int value, boolean fixed) {
        assert candidates != null : "candidates cannot be null";
        assert candidates.size() == 9 : "candidates array must have 9 candidates";
        assert value >= 0 && value <= 9 : "value must be between 0 and 9";
        this.value = value;
        this.fixed = fixed;
        this.candidates = List.copyOf(candidates);
    }

    // ------------ attach methods ----------------------------
    void attachRow(H row) {
        assert this.row == null : "Row already attached";
        this.row = row;
    }
    void attachColumn(H column) {
        assert this.column == null : "Column already attached";
        this.column = column;
    }
    void attachBox(H box) {
        assert this.box == null : "Box already attached";
        this.box = box;
    }

    // ------------  getters  ------------------------------------
    public int getValue() {
        assert !isSolved() : "Cannot get value of solved cell";
        return this.value;
    }
    public H getRow() {
        assert row != null : "Row not attached";
        return row;
    }
    public H getColumn() {
        assert column != null : "Column not attached";
        return column;
    }
    public H getBox() {
        assert box != null : "Box not attached";
        return box;
    }
    public boolean isFixed() {
        return fixed;
    }



    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.value != 0;
    }
    public void solve(int value) {
        assert value >= 1 && value <= 9 : "invalid value";
        assert this.value == 0 : "already set at value : " + this.value;
        this.value = value;
    }
    public void unsolve() {
        assert !fixed : "Cannot unsolve fixed cell";
        assert this.value != 0 : "already unsolved";
        this.value = 0;
    }

    // ------------ candidates ----------------------------------
    public Optional<C> findCandidate(int i){
        assert i >= 1 && i <= 9 : "invalid candidate index";
        C candidate = this.candidates.get(i-1);
        if (candidate.isEliminated()){
            return Optional.empty();
        }
        return Optional.of(candidate);
    }
    public void addCandidate(int i) {
        assert !isSolved() : "Cannot add candidates to solved cell";
        assert this.candidates.get(i-1).isEliminated() : "candidate already eliminated";
        this.candidates.get(i-1).setEliminated(false);
    }
    public void removeCandidate(int i){
        assert !isSolved() : "Cannot remove candidates from solved cell";
        this.candidates.get(i-1).setEliminated(true);
    }
}
