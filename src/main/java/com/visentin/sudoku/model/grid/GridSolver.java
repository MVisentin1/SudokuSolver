package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellSolver;
import com.visentin.sudoku.model.grid.house.HouseBase;

import java.util.List;

public class GridSolver extends GridBase<CellSolver>{

    GridSolver(List<HouseBase<CellSolver>> rows, List<HouseBase<CellSolver>> columns, List<HouseBase<CellSolver>> boxes) {
        super(rows, columns, boxes);
    }
}
