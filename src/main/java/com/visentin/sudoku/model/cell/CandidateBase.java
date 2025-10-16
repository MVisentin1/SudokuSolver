package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;

public abstract class CandidateBase<C extends CellBase<?>> {
    private final int number;
    private C cell;
    private CandidateHighlightMode mode;
    private boolean eliminated;
    private boolean initialized = false;

    // Constructor
    CandidateBase(int number, boolean eliminated) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("number must be between 1 and 9");
        }
        this.number = number;
        this.mode = CandidateHighlightMode.NONE;
        this.eliminated = eliminated;
    }

    // Method for 2-step initialization
    void attachCell(C cell) {
        assert !this.initialized : "Candidate already initialized";
        assert cell != null : "Cell must not be null";

        this.cell = cell;
        this.initialized = true;
    }

    // ----------- field getters  ---------------------
    int getNumber() {
        return number;
    }
    C getCell() {
        return cell;
    }
    CandidateHighlightMode getMode() {
        return mode;
    }
    boolean isEliminated() {
        return eliminated;
    }

    boolean isInitialized() {
        return initialized;
    }

    // ---------- field setters -----------------------
    void setHighlight(CandidateHighlightMode mode) {
        assert this.mode != mode : "already set at mode : " + this.mode;
        this.mode = mode;
    }
    void setEliminated(boolean eliminated) {
        assert this.eliminated != eliminated : "already set at eliminated : " + this.eliminated;
        this.eliminated = eliminated;
    }
}
