package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.BaseHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CellFactory<
        T extends BaseCell<T, C, H>,
        C extends BaseCandidate<T, C>,
        H extends BaseHouse<T, H>> {

    @FunctionalInterface
    public interface CellConstructor<T, C> {
        T create(List<C> candidates, SudokuSet set, boolean fixed);
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
        BaseCell.checkDigit(value);
        SudokuSet eliminatedCandidates = SudokuSet.emptySet();
        eliminatedCandidates.add(value);
        return createCell(eliminatedCandidates, true);
    }

    public T createUnsolvedCell(SudokuSet eliminatedCandidates) {
        Objects.requireNonNull(eliminatedCandidates, "eliminatedCandidates cannot be null");
        eliminatedCandidates.negate();
        return createCell(eliminatedCandidates, false);
    }

    private T createCell(SudokuSet candidateSet, boolean fixed) {
        assert candidateSet != null : "candidateSet cannot be null";
        List<C> candidateList = getCandidateList(candidateSet);
        T cell = cellConstructor.create(candidateList, candidateSet, fixed);
        for (C candidate : candidateList) {
            candidate.attachCell(cell);
        }
        return cell;
    }

    // Extracted for testing purposes
    List<C> getCandidateList(SudokuSet candidateSet) {
        List<C> candidateList = new ArrayList<>(9);
        for (int i = 1; i <= 9; i++) {
            boolean eliminated = !candidateSet.contains(i);
            candidateList.add(candidateConstructor.create(i, eliminated));
        }
        return candidateList;
    }
}
