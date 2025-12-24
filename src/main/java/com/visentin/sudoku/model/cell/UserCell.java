package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.UserHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import javafx.beans.property.*;

import java.util.Arrays;

public final class UserCell extends BaseCell<UserCell, UserCandidate, UserHouse> {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty setProperty = new SimpleIntegerProperty();
    private final boolean fixed;

    // ---------------- constructor ---------------------------
    UserCell(SudokuSet set, UserCandidate[] candidates, int value, boolean fixed) {
        super(set, candidates);
        assert value >= 0 && value <= 9 : "value must be between 0 and 9";
        this.value.set(value);
        this.fixed = fixed;
    }

    // ---------------- properties -----------------------------
    public ReadOnlyIntegerProperty getValueProperty() {
        return value;
    }
    public ReadOnlyIntegerProperty getSetProperty(){ return setProperty;}
    private void syncSetProperty() {
        setProperty.set(set.getMask());
    }

    public boolean isFixed() {
        return fixed;
    }

    @Override
    public int getValue() {
        if (!isSolved()) throw new IllegalStateException("Cell is not solved");
        return value.get();
    }

    @Override
    public boolean isSolved() {
        return value.get() != 0;
    }

    public void addCandidate(int i) {
        set.add(i);
        syncSetProperty();
    }

    @Override
    public void eliminateCandidate(int i) {
        super.eliminateCandidate(i);
        syncSetProperty();
    }

    public void solve(int value) {
        if (isSolved()) throw new IllegalStateException("Cannot solve a solved cell");
        checkDigit(value);
        this.value.set(value);
    }

    public void unsolve() {
        if (fixed) throw new IllegalStateException("Cannot unsolve a fixed cell");
        if (!isSolved()) throw new IllegalStateException("Cannot unsolve an unsolved cell");
        this.value.set(0);
    }
}
