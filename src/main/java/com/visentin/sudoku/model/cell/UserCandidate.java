package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.SolverCandidateHighlightMode;
import com.visentin.sudoku.util.enums.UserCandidateHighlightMode;
import javafx.beans.property.*;

public class UserCandidate extends BaseCandidate<UserCell, UserCandidate> {

    private final BooleanProperty eliminated = new SimpleBooleanProperty();
    private final ObjectProperty<SolverCandidateHighlightMode> solverHighlightMode
            = new SimpleObjectProperty<>(SolverCandidateHighlightMode.NONE);
    private final ObjectProperty<UserCandidateHighlightMode> userHighlightMode
            = new SimpleObjectProperty<>(UserCandidateHighlightMode.NONE);

    // -------------- constructor ------------------
    UserCandidate(int number, boolean eliminated) {
        super(number, eliminated);
        this.eliminated.set(eliminated);
    }

    // -------------- properties getters --------------------
    public ReadOnlyBooleanProperty getEliminatedProperty() {
        return eliminated;
    }
    public ReadOnlyObjectProperty<SolverCandidateHighlightMode> getSolverHighlightModeProperty() {
        return solverHighlightMode;
    }
    public ReadOnlyObjectProperty<UserCandidateHighlightMode> getUserHighlightModeProperty() {
        return userHighlightMode;
    }

    // -------------- eliminated field property sync --------------
    @Override public void setEliminated(boolean eliminated) {
        super.setEliminated(eliminated);
        this.eliminated.set(eliminated);
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
