package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.SolverHouse;

import java.util.List;

public class SolverGrid extends BaseGrid<SolverHouse> {

    SolverGrid(List<SolverHouse> rows, List<SolverHouse> columns, List<SolverHouse> boxes) {
        super(rows, columns, boxes);
    }
}
