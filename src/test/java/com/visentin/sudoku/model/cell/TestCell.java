package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;


public class TestCell extends BaseCell<TestCell, TestCandidate, TestHouse> {
    TestCell(SudokuSet set, TestCandidate[] candidates) {
        super(set, candidates);
    }

    @Override
    public int getValue() {
        return set.numberOfTrailingZeros();
    }

    @Override
    public boolean isSolved() {
        return set.cardinality() == 1;
    }

    static TestCell createUnsolvedTestCell() {
        TestCell cell = new TestCell(SudokuSet.emptySet().add(1).add(2).add(3), TestCandidate.getCandidateArray());
        for (int i = 1; i < 10; i++) {
            cell.candidates[i].attachCell(cell);
        }
        return cell;
    }
}
