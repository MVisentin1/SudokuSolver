package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.UserHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import com.visentin.sudoku.util.enums.SolverCellHighlightMode;
import com.visentin.sudoku.util.enums.UserCellHighlightMode;
import javafx.beans.property.*;

import java.util.List;
import java.util.Optional;

public class UserCell extends BaseCell<UserCell, UserCandidate, UserHouse> {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final ObjectProperty<SolverCellHighlightMode> solverHighlightMode
            = new SimpleObjectProperty<>(SolverCellHighlightMode.NONE);
    private final ObjectProperty<UserCellHighlightMode> userHighlightMode
            = new SimpleObjectProperty<>(UserCellHighlightMode.NONE);
    private SudokuSet previousSet;

    // ---------------- constructor ---------------------------
    UserCell(List<UserCandidate> candidates, SudokuSet set, boolean fixed) {
        super(candidates, set, fixed);
        if (fixed) {
            this.value.set(getValue());
        } else {
            this.value.set(0);
        }
    }

    // ---------------- properties -----------------------------
    public ReadOnlyIntegerProperty getValueProperty() {
        return value;
    }
    public ReadOnlyObjectProperty<SolverCellHighlightMode> getSolverHighlightModeProperty() {
        return solverHighlightMode;
    }
    public ReadOnlyObjectProperty<UserCellHighlightMode> getUserHighlightModeProperty() {
        return userHighlightMode;
    }

    // ---------------- value field property sync --------------
    @Override
    Optional<UserCandidate> findCandidate(int i) {
        return Optional.of(candidateList.get(i-1));
    }

    @Override public void solve(int value) {
        this.previousSet = new SudokuSet(candidateSet);
        super.solve(value);
        this.value.set(value);
    }

    public void unsolve() {
        if (isFixed()) return;
        if (previousSet == null) throw new IllegalStateException("Cannot unsolve an unsolved cell");
        candidateSet = previousSet;
        previousSet = null;
        this.value.set(0);
    }




    // ---------------- mode field property sync ---------------
    public SolverCellHighlightMode getSolverHighlightMode() {
        return solverHighlightMode.get();
    }
    public void setSolverHighlightMode(SolverCellHighlightMode solverHighlightMode) {
        if (solverHighlightMode == null) {
            throw new NullPointerException("solverHighlightMode must not be null");
        }
        assert this.solverHighlightMode.get() != solverHighlightMode : "already set at mode : " + this.solverHighlightMode;
        this.solverHighlightMode.set(solverHighlightMode);
    }
    public UserCellHighlightMode getUserHighlightMode() {
        return userHighlightMode.get();
    }
    public void setUserHighlightMode(UserCellHighlightMode userHighlightMode) {
        if (userHighlightMode == null) {
            throw new NullPointerException("userHighlightMode must not be null");
        }
        assert this.userHighlightMode.get() != userHighlightMode : "already set at mode : " + this.userHighlightMode;
        this.userHighlightMode.set(userHighlightMode);
    }

}
