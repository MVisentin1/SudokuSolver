package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.Objects;
import java.util.Optional;

public abstract class BaseCell<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {
    protected SudokuSet set;
    private H row = null, column = null, box = null;

    // ------------ Constructor -------------------------------
    BaseCell(SudokuSet set) {
        assert set != null : "set cannot be null";
        this.set = set;
    }

    // ------------ House Initialization ----------------------
    final void attachRow(H row) {
        Objects.requireNonNull(row, "Row cannot be null");
        if (this.row != null) {
            throw new IllegalStateException("Row already attached");
        }
        this.row = row;
    }
    final void attachColumn(H column) {
        Objects.requireNonNull(column, "Column cannot be null");
        if (this.column != null) {
            throw new IllegalStateException("Column already attached");
        }
        this.column = column;
    }
    final void attachBox(H box) {
        Objects.requireNonNull(box, "Box cannot be null");
        if (this.box != null) {
            throw new IllegalStateException("Box already attached");
        }
        this.box = box;
    }

    // ------------ Getters ----------------------------------
    public abstract int getValue();
    public final H getRow() {
        assert row != null : "Row not attached";
        return row;
    }
    public final H getColumn() {
        assert column != null : "Column not attached";
        return column;
    }
    public final H getBox() {
        assert box != null : "Box not attached";
        return box;
    }
    public abstract boolean isSolved();

    // ------------ Candidates ----------------------------------
    abstract C[] getCandidates();
    public final Optional<C> findCandidate(int i) {
        C[] candidates = getCandidates();
        if (set.contains(i)) {
            assert candidates[i-1] != null : "Candidate not initialized, set is source of truth";
            return Optional.of(candidates[i]);
        } else {
            return Optional.empty();
        }
    }
    public void eliminateCandidate(int i){
        checkDigit(i);
        if (isSolved()) throw new IllegalStateException("Cannot eliminate candidates on solved cells");
        set.remove(i);
    }

    // ------------ Helper -------------------------------------
    public static void checkDigit(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("Value must be 1-9");
        }
    }
}
