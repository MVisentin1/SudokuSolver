package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.BaseCell;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseHouse<
        T extends BaseCell<T, ?, H>,
        H extends BaseHouse<T, H>> {
    private final List<T> cells;
    BaseHouse(T[] cells) {
        assert cells.length == 9 : "cells must have 9 cells";
        this.cells = List.of(cells);
    }

    public T getCell(int i) {
        if(i < 1 || i > 9){
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
        return cells.get(i-1);
    }
    public List<T> getCells() {
        return this.cells;
    }
    public Set<Integer> getSolvedNumbers() {
        Set<Integer> solvedNumbers = new HashSet<>();
        for(int i = 0; i < 9; i++){
            if(cells.get(i).isSolved()){
                solvedNumbers.add(i+1);
            }
        }
        return solvedNumbers;
    }
}
