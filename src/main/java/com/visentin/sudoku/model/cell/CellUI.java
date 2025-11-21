package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CellHighlightMode;
import javafx.beans.property.*;

public class CellUI extends CellBase<CandidateUI> {
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final ObjectProperty<CellHighlightMode> highlightMode = new SimpleObjectProperty<>(CellHighlightMode.NONE);

    // ---------------- constructor ---------------------------
    public CellUI(CandidateUI[] candidates, int value) {
        super(candidates, value);
        this.value.set(value);
        for (CandidateUI candidate : candidates) {
            candidate.attachCell(this);
        }
    }

    // ---------------- properties -----------------------------
    public ReadOnlyIntegerProperty valueProperty() {
        return value;
    }
    public ReadOnlyObjectProperty<CellHighlightMode> highlightModeProperty() {
        return highlightMode;
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
    @Override public void setHighlightMode(CellHighlightMode highlightMode) {
        super.setHighlightMode(highlightMode);
        this.highlightMode.set(highlightMode);
    }

}
