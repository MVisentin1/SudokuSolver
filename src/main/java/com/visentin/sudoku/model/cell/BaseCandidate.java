package com.visentin.sudoku.model.cell;


import java.util.Objects;

public abstract class BaseCandidate<
        T extends BaseCell<T, C, ?>,
        C extends BaseCandidate<T, C>> {
    private final int number;
    private T cell = null;
    private boolean eliminated;

    // Constructor
    BaseCandidate(int number, boolean eliminated) {
        assert number >= 1 && number <= 9 : "Number must be between 1 and 9";
        this.number = number;
        this.eliminated = eliminated;
    }

    // Method for 2-step initialization
    void attachCell(T cell) {
        Objects.requireNonNull(cell, "cell cannot be null");
        if (this.cell != null) {
            throw new IllegalStateException("Cell already attached");
        }
        this.cell = cell;
    }

    // ----------- field getters  ---------------------
    public int getNumber() {
        return number;
    }
    public T getCell() {
        assert cell != null : "Cell not attached";
        return cell;
    }
    public boolean isEliminated() {
        return eliminated;
    }

    // ---------- field setters -----------------------
    public void eliminate(){
        eliminated = true;
    }
    public void setToActive(){
        eliminated = false;
    }
}
