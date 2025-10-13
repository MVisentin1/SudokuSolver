package com.visentin.s.util.exceptions;

public class CellWrongSolvedStateAccessException extends RuntimeException {
    public CellWrongSolvedStateAccessException() {
        super("use isSolved() before cell state access");
    }
}
