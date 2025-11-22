package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellUI;
import com.visentin.sudoku.model.grid.house.HouseBase;

public class GridUI extends GridBase<CellUI> {
    public GridUI(HouseBase<CellUI>[] rows, HouseBase<CellUI>[] columns, HouseBase<CellUI>[] boxes) {
        super(rows, columns, boxes);
    }
}
