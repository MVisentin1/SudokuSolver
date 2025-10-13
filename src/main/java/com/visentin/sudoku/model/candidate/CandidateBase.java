package com.visentin.sudoku.model.candidate;

import com.visentin.sudoku.model.cell.CellBase;
import com.visentin.sudoku.util.enums.CandidateHighlightMode;

public abstract class CandidateBase<C extends CellBase<?>> {
    private final int number;
    private final C cell;
    private CandidateHighlightMode mode =  CandidateHighlightMode.NONE;
    private boolean eliminated;

    // ------------ constructor ----------------------
    protected CandidateBase(int number, C cell, boolean eliminated) {
        this.number = number;
        this.cell = cell;
        this.eliminated = eliminated;
    }

    // ----------- field access  ---------------------
    public int getNumber() {
        return number;
    }
    public C getCell() {
        return cell;
    }
    public CandidateHighlightMode getMode() {
        return mode;
    }
    public void setMode(CandidateHighlightMode mode) {
        if (this.mode == mode) {
            throw new IllegalStateException("already set at mode : " + mode);
        }
        this.mode = mode;
    }
    public boolean isEliminated() {
        return eliminated;
    }
    public void setEliminated(boolean eliminated) {
        if (this.eliminated == eliminated) {
            throw new IllegalStateException("already eliminated");
        }
        this.eliminated = eliminated;
    }
}
