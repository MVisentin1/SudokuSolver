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
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("number must be between 1 and 9");
        }
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
        assert this.mode != mode : "already set at mode : " + this.mode;
        this.mode = mode;
    }
    public boolean isEliminated() {
        return eliminated;
    }
    public void setEliminated(boolean eliminated) {
        assert this.eliminated != eliminated : "already set at eliminated : " + this.eliminated;
        this.eliminated = eliminated;
    }
}
