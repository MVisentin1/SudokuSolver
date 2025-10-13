package com.visentin.sudoku.model.candidate;

import com.visentin.sudoku.model.cell.CellUI;
import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import javafx.beans.property.*;

public class CandidateUI extends CandidateBase<CellUI> {

    private final BooleanProperty eliminated = new SimpleBooleanProperty();
    private final ObjectProperty<CandidateHighlightMode> mode = new SimpleObjectProperty<>(CandidateHighlightMode.NONE);

    // -------------- constructor ------------------
    public CandidateUI(int number, CellUI cell, boolean eliminated) {
        super(number, cell, eliminated);
        this.eliminated.set(eliminated);
    }

    // -------------- properties getters --------------------
    public ReadOnlyBooleanProperty eliminatedProperty() {
        return eliminated;
    }
    public ReadOnlyObjectProperty<CandidateHighlightMode> modeProperty() {
        return mode;
    }

    // -------------- eliminated field property sync --------------
    @Override public void setEliminated(boolean eliminated) {
        super.setEliminated(eliminated);
        this.eliminated.set(eliminated);
    }
    // -------------- mode field property sync --------------------
    @Override public void setMode(CandidateHighlightMode mode) {
        super.setMode(mode);
        this.mode.set(mode);
    }
}
