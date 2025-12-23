package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.SolverCandidateHighlightMode;
import com.visentin.sudoku.util.enums.UserCandidateHighlightMode;
import javafx.beans.property.*;

public class UserCandidate extends BaseCandidate<UserCell, UserCandidate> {

    private final ObjectProperty<SolverCandidateHighlightMode> solverHighlightMode
            = new SimpleObjectProperty<>(SolverCandidateHighlightMode.NONE);
    private final ObjectProperty<UserCandidateHighlightMode> userHighlightMode
            = new SimpleObjectProperty<>(UserCandidateHighlightMode.NONE);

    // -------------- constructor ------------------
    UserCandidate(int number) {
        super(number);
    }

    // -------------- properties getters --------------------
    public ReadOnlyObjectProperty<SolverCandidateHighlightMode> getSolverHighlightModeProperty() {
        return solverHighlightMode;
    }
    public ReadOnlyObjectProperty<UserCandidateHighlightMode> getUserHighlightModeProperty() {
        return userHighlightMode;
    }

    // -------------- mode field property sync --------------------
    public SolverCandidateHighlightMode getSolverHighlightMode() {
        return solverHighlightMode.get();
    }
    public void setSolverHighlightMode(SolverCandidateHighlightMode solverHighlightMode) {
        if (solverHighlightMode == null) {
            throw new NullPointerException("highlightMode must not be null");
        }
        assert this.solverHighlightMode.get() != solverHighlightMode : "already set at mode : " + solverHighlightMode;
        this.solverHighlightMode.set(solverHighlightMode);
    }
    public UserCandidateHighlightMode getUserHighlightMode() {
        return userHighlightMode.get();
    }
    public void setUserHighlightMode(UserCandidateHighlightMode userHighlightMode) {
        if (userHighlightMode == null) {
            throw new NullPointerException("highlightMode must not be null");
        }
        assert this.userHighlightMode.get() != userHighlightMode : "already set at mode : " + userHighlightMode;
        this.userHighlightMode.set(userHighlightMode);
    }
}
