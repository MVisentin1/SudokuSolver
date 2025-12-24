package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.BaseCell;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.Arrays;

public abstract class BaseHouse<
        T extends BaseCell<T, ?, H>,
        H extends BaseHouse<T, H>> {
    protected SudokuSet solvedNumbers;
    private final T[] cells;

    BaseHouse(SudokuSet solvedNumbers, T[] cells) {
        assert solvedNumbers != null : "solvedNumbers cannot be null";
        this.solvedNumbers = solvedNumbers;
        assert cells != null : "cells cannot be null";
        assert cells.length == 10 : "cells must have length 10";
        this.cells = Arrays.copyOf(cells, cells.length);
    }

    public SudokuSet getSolvedNumbers() {
        return new SudokuSet(solvedNumbers);
    }

    public T getCell(int i) {
        return cells[i];
    }
}
