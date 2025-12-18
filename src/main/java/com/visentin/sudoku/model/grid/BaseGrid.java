package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.BaseCell;
import com.visentin.sudoku.model.grid.house.BaseHouse;


import java.util.List;

public abstract class BaseGrid<
        T extends BaseCell<T, ?, H>,
        H extends BaseHouse<T, H>> {
    private final List<H> rows;
    private final List<H> columns;
    private final List<H> boxes;

    BaseGrid(List<H> rows, List<H> columns, List<H> boxes) {
        assert rows.size() == 9 : "rows must have 9 houses";
        assert columns.size() == 9 : "columns must have 9 houses";
        assert boxes.size() == 9 : "boxes must have 9 houses";

        this.rows = List.copyOf(rows);
        this.columns = List.copyOf(columns);
        this.boxes = List.copyOf(boxes);

    }

    public H getRow(int i) {
        return rows.get(i-1);
    }

    public H getColumn(int i) {
        return columns.get(i-1);
    }

    public H getBox(int i) {
        return boxes.get(i-1);
    }
}
