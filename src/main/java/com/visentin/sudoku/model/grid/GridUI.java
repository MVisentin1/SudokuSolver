package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.CellUI;
import com.visentin.sudoku.model.grid.house.HouseBase;

import java.util.List;

public class GridUI extends GridBase<CellUI> {

    GridUI(List<HouseBase<CellUI>> rows, List<HouseBase<CellUI>> columns, List<HouseBase<CellUI>> boxes) {
        super(rows, columns, boxes);
    }
}
