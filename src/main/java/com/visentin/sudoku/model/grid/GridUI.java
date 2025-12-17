package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.HouseUI;

import java.util.List;

public class GridUI extends GridBase<HouseUI> {

    GridUI(List<HouseUI> rows, List<HouseUI> columns, List<HouseUI> boxes) {
        super(rows, columns, boxes);
    }
}
