package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.CellBase;

import java.util.HashSet;
import java.util.Set;

public abstract class HouseBase<C extends CellBase<?>> {
    private final C[] cells;
    HouseBase(C[] cells) {
        assert cells != null :  "cells cannot be null";
        assert cells.length == 9 : "cells must have 9 cells";
        for (C cell : cells) {
            assert cell != null : "cell cannot be null";
        }
        this.cells = cells;
    }

    public C getCell(int i) {
        if(i < 1 || i > 9){
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
        return this.cells[i-1];
    }
    public C[] getCells() {
        return this.cells;
    }
    public Set<Integer> getSolvedNumbers() {
        Set<Integer> solvedNumbers = new HashSet<>();
        for(int i = 0; i < 9; i++){
            if(cells[i].isSolved()){
                solvedNumbers.add(i+1);
            }
        }
        return solvedNumbers;
    }
}
