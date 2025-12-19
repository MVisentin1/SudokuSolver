package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;

import java.util.List;
import java.util.Objects;
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
        Objects.requireNonNull(row, "Row cannot be null");
        if (this.row != null) {
            throw new IllegalStateException("Row already attached");
        }
        this.row = row;
    }
    void attachColumn(H column) {
        Objects.requireNonNull(column, "Column cannot be null");
        if (this.column != null) {
            throw new IllegalStateException("Column already attached");
        }
        this.column = column;
    }
    void attachBox(H box) {
        Objects.requireNonNull(box, "Box cannot be null");
        if (this.box != null) {
            throw new IllegalStateException("Box already attached");
        }
        this.box = box;
    }

    // ------------  getters  ------------------------------------
    public int getValue() {
        assert isSolved() : "Cannot get value of unsolved cell";
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
    List<C> getCandidates() {
        return candidates;
    }



    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.value != 0;
    }
    public void solve(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("invalid value");
        }
        if (this.value != 0) {
            throw new IllegalStateException("Cannot solve already solved cell");
        }
        this.value = value;
    }
    public void unsolve() {
        if (fixed) {
            throw new IllegalStateException("Cannot unsolve a fixed cell");
        }
        if (!isSolved()) {
            throw new IllegalStateException("Cannot unsolve an unsolved cell");
        }
        this.value = 0;
    }

    // ------------ candidates ----------------------------------
    public Optional<C> findCandidate(int i){
        if (i < 1 || i > 9){
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
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
        if (i < 1 || i > 9) {
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
        assert this.candidates.get(i-1).isEliminated() : "candidate not eliminated";
        this.candidates.get(i-1).setEliminated(false);
    }
    public void removeCandidate(int i){
        if (isSolved()) {
            throw new IllegalStateException("Cannot remove candidates from solved cell");
        }
        if (i < 1 || i > 9) {
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
        assert !this.candidates.get(i-1).isEliminated() : "candidate already eliminated";
        this.candidates.get(i-1).setEliminated(true);
    }
}
