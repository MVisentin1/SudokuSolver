package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.UserCell;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

public class UserHouse extends BaseHouse<UserCell, UserHouse> {
    UserHouse(SudokuSet solvedNumbers, UserCell[] cells) {
        super(solvedNumbers, cells);
    }
}
