package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;

public abstract class CandidateBase<C extends CellBase<?>> {
    private final int number;
    private final C cell;
    private CandidateHighlightMode mode;
    private boolean eliminated;
    private boolean accessible;

    // ------------ constructor ----------------------
    protected CandidateBase(int number, C cell, boolean eliminated) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("number must be between 1 and 9");
        }
        this.number = number;
        this.cell = cell;
        this.mode = CandidateHighlightMode.NONE;
        this.eliminated = eliminated;
        this.accessible = !cell.isSolved();
    }

    // ----------- field getters  ---------------------
    public int getNumber() {
        return number;
    }
    public C getCell() {
        return cell;
    }
    public CandidateHighlightMode getMode() {
        return mode;
    }
    public boolean isEliminated() {
        return eliminated;
    }
    public boolean isAccessible() {
        return accessible;
    }

    // ---------- field setters -----------------------
    public void setHighlight(CandidateHighlightMode mode) {
        assert this.mode != mode : "already set at mode : " + this.mode;
        this.mode = mode;
    }
    public void setEliminated(boolean eliminated) {
        assert this.eliminated != eliminated : "already set at eliminated : " + this.eliminated;
        this.eliminated = eliminated;
    }

    void setAccessibility(boolean accessible) {
        assert this.accessible != accessible : "already set at accessible : " + this.accessible;
        this.accessible = accessible;
    }

    private void ensureCandidateIsAccessible() {
        if (!isAccessible()) {
            throw new IllegalStateException("Candidate is not accessible");
        }
    }
}
