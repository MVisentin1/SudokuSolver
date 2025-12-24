package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.dataStructures.SudokuSet;

public class CellFactory {

    public static UserCell createSolvedUserCell(int value) {
        BaseCell.checkDigit(value);
        UserCell cell = new UserCell(new SudokuSet(1 << value), getUserCandidates(), value, true);
        for (UserCandidate candidate : cell.candidates) {
            candidate.attachCell(cell);
        }
        return cell;
    }

    public static UserCell createUnsolvedUserCell(SudokuSet eliminatedCandidates) {
        UserCell cell = new UserCell(eliminatedCandidates, getUserCandidates(), 0, false);
        for (UserCandidate candidate : cell.candidates) {
            candidate.attachCell(cell);
        }
        return cell;
    }

    public static SolverCell createSolverCellFromUserCell(UserCell userCell, SudokuSet eliminatedCandidates) {
        SudokuSet set = userCell.isSolved() ? new SudokuSet(1 << userCell.getValue()) : eliminatedCandidates;
        SolverCandidate[] candidates = new SolverCandidate[10];
        for (int i = 1; i <= 9; i++) {
            if (set.contains(i)) {
                candidates[i] = new SolverCandidate(i);
            }
        }
        SolverCell cell = new SolverCell(set, candidates);
        for (SolverCandidate candidate : cell.candidates) {
            candidate.attachCell(cell);
        }
        return cell;
    }

    private static UserCandidate[] getUserCandidates() {
        UserCandidate[] candidates = new UserCandidate[10];
        for (int i = 1; i <= 9; i++) {
            candidates[i] = new UserCandidate(i);
        }
        return candidates;
    }
}
