package com.visentin.s.model.cell;

import com.visentin.s.model.candidate.CandidateUI;
import com.visentin.s.util.enums.CellHighlightMode;
import javafx.beans.property.*;

public class CellUI extends CellBase<CandidateUI> {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final ObjectProperty<CellHighlightMode> mode = new SimpleObjectProperty<>(CellHighlightMode.NONE);

    // ---------------- constructor ---------------------------
    public CellUI(CandidateUI[] candidates, int value) {
        super(candidates, value);
        this.value.set(value);
    }

    // ---------------- properties -----------------------------
    public ReadOnlyIntegerProperty valueProperty() {
        return value;
    }
    public ReadOnlyObjectProperty<CellHighlightMode> modeProperty() {
        return mode;
    }

    // ---------------- value field property sync --------------
    @Override public void setAsSolved(int value, boolean overwrite) {
        super.setAsSolved(value, overwrite);
        this.value.set(value);
    }
    @Override public void setAsUnsolved(boolean overwrite) {
        super.setAsUnsolved(overwrite);
        this.value.set(0);
    }

    // ---------------- mode field property sync ---------------
    @Override public void setMode(CellHighlightMode mode) {
        super.setMode(mode);
        this.mode.set(mode);
    }

}
