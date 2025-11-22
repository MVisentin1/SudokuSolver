package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CandidateBase;
import com.visentin.sudoku.model.cell.CellBase;
import com.visentin.sudoku.model.grid.house.HouseBase;

public abstract class GridBase<C extends CellBase<?>> {
    private final HouseBase<C>[] rows;
    private final HouseBase<C>[] columns;
    private final HouseBase<C>[] boxes;

    GridBase(HouseBase<C>[] rows, HouseBase<C>[] columns, HouseBase<C>[] boxes) {
        assert (rows != null && columns != null && boxes != null) :  "rows cannot be null";
        assert (rows.length == 9 && columns.length == 9 && boxes.length == 9) : "rows, columns and boxes must have 9 cells each";
        this.rows = rows;
        this.columns = columns;
        this.boxes = boxes;
    }

    public HouseBase<C> getRow(int i) {
        indexValidation(i);
        return rows[i-1];
    }

    public HouseBase<C> getColumn(int i) {
        indexValidation(i);
        return columns[i-1];
    }

    public HouseBase<C> getBox(int i) {
        indexValidation(i);
        return boxes[i-1];
    }

    private void indexValidation(int i){
        if(i < 1 || i > 9){
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
    }
}
