package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;
import javafx.beans.property.*;

public class CandidateUI extends CandidateBase<CellUI> {

    private final BooleanProperty eliminated = new SimpleBooleanProperty();
    private final ObjectProperty<CandidateHighlightMode> highlightMode = new SimpleObjectProperty<>(CandidateHighlightMode.NONE);

    // -------------- constructor ------------------
    public CandidateUI(int number, boolean eliminated) {
        super(number, eliminated);
        this.eliminated.set(eliminated);
    }

    // -------------- properties getters --------------------
    public ReadOnlyBooleanProperty eliminatedProperty() {
        return eliminated;
    }
    public ReadOnlyObjectProperty<CandidateHighlightMode> highlightModeProperty() {
        return highlightMode;
    }

    // -------------- eliminated field property sync --------------
    @Override public void setEliminated(boolean eliminated) {
        super.setEliminated(eliminated);
        this.eliminated.set(eliminated);
    }
    // -------------- mode field property sync --------------------
    @Override public void setHighlight(CandidateHighlightMode highlightMode) {
        super.setHighlight(highlightMode);
        this.highlightMode.set(highlightMode);
    }
}
