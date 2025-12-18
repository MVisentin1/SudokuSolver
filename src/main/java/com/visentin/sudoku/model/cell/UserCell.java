package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.UserHouse;
import com.visentin.sudoku.util.enums.SolverCellHighlightMode;
import com.visentin.sudoku.util.enums.UserCellHighlightMode;
import javafx.beans.property.*;

public class UserCell extends BaseCell<UserCell, UserCandidate, UserHouse> {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final ObjectProperty<SolverCellHighlightMode> solverHighlightMode
            = new SimpleObjectProperty<>(SolverCellHighlightMode.NONE);
    private final ObjectProperty<UserCellHighlightMode> userHighlightMode
            = new SimpleObjectProperty<>(UserCellHighlightMode.NONE);

    // ---------------- constructor ---------------------------
    public UserCell(UserCandidate[] candidates, int value) {
        super(candidates, value);
        this.value.set(value);
        for (UserCandidate candidate : candidates) {
            candidate.attachCell(this);
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
    @Override public void solve(int value) {
        super.solve(value);
        this.value.set(value);
    }
    @Override public void unsolve() {
        super.unsolve();
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
