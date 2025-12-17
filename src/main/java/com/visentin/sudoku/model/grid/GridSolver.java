package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.HouseSolver;

import java.util.List;

public class GridSolver extends GridBase<HouseSolver>{

    GridSolver(List<HouseSolver> rows, List<HouseSolver> columns, List<HouseSolver> boxes) {
        super(rows, columns, boxes);
    }
}
