package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.cell.UserCell;
import com.visentin.sudoku.model.grid.house.UserHouse;

import java.util.List;

public class UserGrid extends BaseGrid<UserCell, UserHouse> {

    UserGrid(List<UserHouse> rows, List<UserHouse> columns, List<UserHouse> boxes) {
        super(rows, columns, boxes);
    }
}
