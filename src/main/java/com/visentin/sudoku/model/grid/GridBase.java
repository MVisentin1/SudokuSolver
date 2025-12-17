package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellBase;
import com.visentin.sudoku.model.grid.house.HouseBase;

import java.util.List;

public abstract class GridBase<C extends CellBase<?>> {
    private final List<HouseBase<C>> rows;
    private final List<HouseBase<C>> columns;
    private final List<HouseBase<C>> boxes;

    GridBase(List<HouseBase<C>> rows, List<HouseBase<C>> columns, List<HouseBase<C>> boxes) {
        this.rows = List.copyOf(rows);
        this.columns = List.copyOf(columns);
        this.boxes = List.copyOf(boxes);
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
