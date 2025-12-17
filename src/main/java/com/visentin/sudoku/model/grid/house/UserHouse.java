package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.UserCell;

public class UserHouse extends BaseHouse<UserCell> {
    UserHouse(UserCell[] cells) {
        super(cells);
    }
}
