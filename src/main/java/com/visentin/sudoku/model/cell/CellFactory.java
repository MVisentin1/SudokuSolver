package com.visentin.sudoku.model.cell;

public class CellFactory {

    // ------------------ cellUI creation ---------------------------
    private static CellUI createCellUI(int value, boolean eliminated) {
        CandidateUI[] candidates = new CandidateUI[9];
        CellUI cell = new CellUI(candidates, value);
        for (int i = 1; i < 10; i++) {
            candidates[i] = new CandidateUI(i, cell, eliminated);
        }
        return cell;
    }

    public static CellUI cellUI_WithValue(int value) {
        return createCellUI(value, true);
    }
    public static CellUI cellUI_WithCandidates() {
        return createCellUI(0, false);
    }
    public static CellUI cellUI_WithoutCandidates() {
        return createCellUI(0, true);
    }

    // ----------------- cellSolver creation ------------------------
    private static CellSolver createCellSolver(int value, boolean eliminated) {
        CandidateSolver[] candidates = new CandidateSolver[9];
        CellSolver cell = new CellSolver(candidates, value);
        for (int i = 1; i < 10; i++) {
            candidates[i] = new CandidateSolver(i, cell, eliminated);
        }
        return cell;
    }

    public static CellSolver cellSolver_WithValue(int value) {
        return createCellSolver(value, true);
    }
    public static CellSolver cellSolver_WithCandidates() {
        return createCellSolver(0, false);
    }

}
