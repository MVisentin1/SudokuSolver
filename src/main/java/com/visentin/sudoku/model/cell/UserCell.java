package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.UserHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import com.visentin.sudoku.util.enums.SolverCellHighlightMode;
import com.visentin.sudoku.util.enums.UserCellHighlightMode;
import javafx.beans.property.*;

import java.util.List;
import java.util.Optional;

public class UserCell extends BaseCell<UserCell, UserCandidate, UserHouse> {
    private final List<UserCandidate> candidates;
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty setProperty = new SimpleIntegerProperty();
    private final boolean fixed;

    // ---------------- constructor ---------------------------
    UserCell(SudokuSet set, List<UserCandidate> candidates, int value, boolean fixed) {
        super(set);
        assert candidates != null : "candidates cannot be null";
        assert candidates.size() == 9 : "candidates must contain 9 candidates";
        this.candidates = List.copyOf(candidates);
        assert value >= 0 && value <= 9 : "value must be between 0 and 9";
        this.value.set(value);
        assert fixed || value != 0 : "fixed cells must have a value";
        assert !fixed || value == 0 : "unfixed cells cannot have a value";
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


    @Override
    public int getValue() {
        if (!isSolved()) throw new IllegalStateException("Cell is not solved");
        return value.get();
    }

    @Override
    public boolean isSolved() {
        return value.get() != 0;
    }

    @Override
    public void eliminateCandidate(int i) {
        super.eliminateCandidate(i);
        syncSetProperty();
    }

    @Override
    Optional<UserCandidate> findCandidate(int i) {
        if (isSolved() || !set.contains(i)) {
            return Optional.empty();
        } else {
            return Optional.of(candidates.get(i-1));
        }
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
