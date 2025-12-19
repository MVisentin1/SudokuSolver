package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellFactory<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {

    @FunctionalInterface
    public interface CellConstructor<T, C> {
        T create(List<C> candidates, int value, boolean fixed);
    }

    @FunctionalInterface
    public interface CandidateConstructor<C> {
        C create(int number, boolean eliminated);
    }

    private final CellConstructor<T, C> cellConstructor;
    private final CandidateConstructor<C> candidateConstructor;

    public CellFactory(CellConstructor<T, C> cellConstructor, CandidateConstructor<C> candidateConstructor) {
        this.cellConstructor = cellConstructor;
        this.candidateConstructor = candidateConstructor;
    }

    public T createSolvedCell(int value) {
        assert value >= 1 && value <= 9 : "invalid value";
        boolean[] eliminatedCandidates = new boolean[10];
        Arrays.fill(eliminatedCandidates, true);
        eliminatedCandidates[value] = false;
        return createCell(eliminatedCandidates, value, true);
    }

    public T createUnsolvedCell(boolean[] eliminatedCandidates) {
        assert eliminatedCandidates != null : "eliminatedCandidates cannot be null";
        assert eliminatedCandidates.length == 10 : "invalid eliminatedCandidates array size";
        return createCell(eliminatedCandidates, 0, false);
    }

    private T createCell(boolean[] eliminatedCandidates, int value, boolean fixed) {
        ArrayList<C> candidates = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            candidates.add(candidateConstructor.create(i, eliminatedCandidates[i]));
        }
        T cell = cellConstructor.create(candidates, value, fixed);
        for (C candidate : candidates) {
            candidate.attachCell(cell);
        }
        return cell;
    }
}
