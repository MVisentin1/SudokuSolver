package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.SolverCell;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

public class SolverHouse extends BaseHouse<SolverCell, SolverHouse> {

    SolverHouse(SudokuSet solvedNumbers, SolverCell[] cells) {
        super(solvedNumbers, cells);
    }
}
