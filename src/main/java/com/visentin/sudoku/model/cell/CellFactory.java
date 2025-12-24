package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.dataStructures.SudokuSet;

public class CellFactory {

    public static UserCell createSolvedUserCell(int value) {
        BaseCell.checkDigit(value);
        return new UserCell(new SudokuSet(1 << value), getUserCandidates(), value, true);
    }

    public static UserCell createUnsolvedUserCell(SudokuSet eliminatedCandidates) {
        return new UserCell(new SudokuSet(eliminatedCandidates.negate()), getUserCandidates(), 0, false);
    }

    public static SolverCell createSolverCellFromUserCell(UserCell userCell, SudokuSet eliminatedCandidates) {
        SudokuSet set = userCell.isSolved() ? new SudokuSet(1 << userCell.getValue()) : eliminatedCandidates.negate();
        SolverCandidate[] candidates = new SolverCandidate[10];
        for (int i = 1; i <= 9; i++) {
            if (set.contains(i)) {
                candidates[i] = new SolverCandidate(i);
            }
        }
        return new SolverCell(set, candidates);
    }

    private static UserCandidate[] getUserCandidates() {
        UserCandidate[] candidates = new UserCandidate[10];
        for (int i = 1; i <= 9; i++) {
            candidates[i] = new UserCandidate(i);
        }
        return candidates;
    }
}
