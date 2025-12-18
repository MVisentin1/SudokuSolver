package com.visentin.sudoku.model.cell;


public abstract class BaseCandidate<
        T extends BaseCell<T, C, ?>,
        C extends BaseCandidate<T, C>> {
    private final int number;
    private T cell = null;
    private boolean eliminated;

    // Constructor
    BaseCandidate(int number, boolean eliminated) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("number must be between 1 and 9");
        }
        this.number = number;
        this.eliminated = eliminated;
    }

    // Method for 2-step initialization
    void attachCell(T cell) {
        assert this.cell == null : "Cell already attached";
        assert cell != null : "Cell must not be null";

        this.cell = cell;
    }

    // ----------- field getters  ---------------------
    public int getNumber() {
        return number;
    }
    public T getCell() {
        return cell;
    }
    public boolean isEliminated() {
        return eliminated;
    }

    // ---------- field setters -----------------------
    public void setEliminated(boolean eliminated) {
        assert this.eliminated != eliminated : "already set at eliminated : " + this.eliminated;
        this.eliminated = eliminated;
    }
}
