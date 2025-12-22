package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseCell<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {
    protected final List<C> candidateList;
    protected SudokuSet candidateSet = new SudokuSet();
    private final boolean fixed;
    private H row = null, column = null, box = null;

    // ------------ constructor -------------------------------
    BaseCell(List<C> candidateList, SudokuSet candidateSet, boolean fixed) {
        assert candidateList != null : "candidates cannot be null";
        assert candidateList.size() == 9 : "candidates array must have 9 candidates";
        this.candidateList = List.copyOf(candidateList);
        this.candidateSet = candidateSet;
        this.fixed = fixed;
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
        if (!isSolved()) {
            throw new IllegalStateException("Cannot get value of unsolved cell");
        }
        return Integer.numberOfTrailingZeros(candidateSet.getMask());
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
    List<C> getCandidateList() {
        return candidateList;
    }
    SudokuSet getCandidateSet() {
        return new SudokuSet(candidateSet);
    }



    // ------------ solved status -------------------------------
    public boolean isSolved(){
        return this.candidateSet.cardinality() == 1;
    }
    public void solve(int value) {
        checkDigit(value);
        if (isSolved()) {
            throw new IllegalStateException("Cannot solve an already solved cell");
        }
        for (C candidate : candidateList) {
            candidate.setEliminated(candidate.getNumber() != value);
        }
        this.candidateSet.clearAllBut(value);
    }

    // ------------ candidates ----------------------------------

    abstract Optional<C> findCandidate(int i);

     void eliminateCandidate(int i){
        checkDigit(i);
        if (isSolved()) {
            throw new IllegalStateException("Cannot eliminate candidates from solved cell");
        }
        C candidate = this.candidateList.get(i-1);
        if (candidate.isEliminated()){
            return;
        }
        candidate.setEliminated(true);
    }

    public static void checkDigit(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("Value must be 1-9");
        }
    }
}
