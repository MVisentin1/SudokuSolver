package com.visentin.sudoku.util.exceptions;

public class CellWrongSolvedStateAccessException extends RuntimeException {
    public CellWrongSolvedStateAccessException(String message) {
        super(message);
    }
}
