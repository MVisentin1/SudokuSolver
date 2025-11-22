package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellBase;
import com.visentin.sudoku.model.grid.house.HouseBase;

import java.util.List;

public abstract class GridBase<C extends CellBase<?>> {
    private final List<HouseBase<C>> rows;
    private final List<HouseBase<C>> columns;
    private final List<HouseBase<C>> boxes;

    GridBase(HouseBase<C>[] rows, HouseBase<C>[] columns, HouseBase<C>[] boxes) {
        assert (rows.length == 9 && columns.length == 9 && boxes.length == 9) : "rows, columns and boxes must have 9 cells each";
        this.rows = List.of(rows);
        this.columns = List.of(columns);
        this.boxes = List.of(boxes);
    }

    public HouseBase<C> getRow(int i) {
        indexValidation(i);
        return rows.get(i-1);
    }

    public HouseBase<C> getColumn(int i) {
        indexValidation(i);
        return columns.get(i-1);
    }

    public HouseBase<C> getBox(int i) {
        indexValidation(i);
        return boxes.get(i-1);
    }

    private void indexValidation(int i){
        if(i < 1 || i > 9){
            throw new IndexOutOfBoundsException("index must be 1 to 9");
        }
    }
}
