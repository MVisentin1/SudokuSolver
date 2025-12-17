package com.visentin.sudoku.model.cell;

public abstract class CandidateBase<C extends CellBase<?>> {
    private final int number;
    private C cell;
    private boolean eliminated;
    private boolean initialized = false;

    // Constructor
    CandidateBase(int number, boolean eliminated) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("number must be between 1 and 9");
        }
        this.number = number;
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
    public int getNumber() {
        return number;
    }
    public C getCell() {
        return cell;
    }
    public boolean isEliminated() {
        return eliminated;
    }

    public boolean isInitialized() {
        return initialized;
    }

    // ---------- field setters -----------------------
    public void setEliminated(boolean eliminated) {
        assert this.eliminated != eliminated : "already set at eliminated : " + this.eliminated;
        this.eliminated = eliminated;
    }
}
