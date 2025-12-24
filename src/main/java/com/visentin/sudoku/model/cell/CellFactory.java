package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.Objects;

public class CellFactory {

    public static UserCell createSolvedUserCell(int value) {
        BaseCell.checkDigit(value);
        UserCell cell = new UserCell(new SudokuSet(1 << value), getUserCandidates(), value, true);
        for (int i = 1; i <= 9; i++) {
            cell.candidates[i].attachCell(cell);
        }
        return cell;
    }

    public static UserCell createUnsolvedUserCell(SudokuSet eliminatedCandidates) {
        Objects.requireNonNull(eliminatedCandidates, "eliminatedCandidates cannot be null");
        UserCell cell = new UserCell(eliminatedCandidates, getUserCandidates(), 0, false);
        for (int i = 1; i <= 9; i++) {
            cell.candidates[i].attachCell(cell);
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
        for (int i = 1; i <= 9; i++) {
            cell.candidates[i].attachCell(cell);
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
