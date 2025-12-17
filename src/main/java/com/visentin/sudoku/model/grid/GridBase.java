package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.HouseBase;


import java.util.List;

public abstract class GridBase<C extends HouseBase<?>> {
    private final List<C> rows;
    private final List<C> columns;
    private final List<C> boxes;

    GridBase(List<C> rows, List<C> columns, List<C> boxes) {
        assert rows.size() == 9 : "rows must have 9 houses";
        assert columns.size() == 9 : "columns must have 9 houses";
        assert boxes.size() == 9 : "boxes must have 9 houses";

        this.rows = List.copyOf(rows);
        this.columns = List.copyOf(columns);
        this.boxes = List.copyOf(boxes);

    }

    public C getRow(int i) {
        return rows.get(i-1);
    }

    public C getColumn(int i) {
        return columns.get(i-1);
    }

    public C getBox(int i) {
        return boxes.get(i-1);
    }
}
