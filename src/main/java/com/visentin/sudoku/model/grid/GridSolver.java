package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellSolver;
import com.visentin.sudoku.model.grid.house.HouseBase;

public class GridSolver extends GridBase<CellSolver>{
    public GridSolver(HouseBase<CellSolver>[] rows, HouseBase<CellSolver>[] columns, HouseBase<CellSolver>[] boxes) {
        super(rows, columns, boxes);
    }
}
