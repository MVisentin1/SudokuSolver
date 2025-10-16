package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.enums.CandidateHighlightMode;

import java.util.Optional;

public final class AccessibleCandidate<C extends CellBase<?>> {
    private final CandidateBase<C> candidate;

    private AccessibleCandidate(CandidateBase<C> candidate) {
        this.candidate = candidate;
    }

    public static <C extends CellBase<?>>
    Optional<AccessibleCandidate<C>> of (CandidateBase<C> candidate) {
        if (!candidate.isInitialized()) {
            throw new IllegalStateException("Candidate must be initialized");
        }
        return !candidate.getCell().isSolved()
                ? Optional.of(new AccessibleCandidate<>(candidate))
                : Optional.empty();
    }

    // ----------- Safe accessors ---------------------
    public int getNumber() {
        return candidate.getNumber();
    }

    public C getCell() {
        return candidate.getCell();
    }

    public boolean isEliminated() {
        return candidate.isEliminated();
    }

    public void setEliminated(boolean eliminated) {
        candidate.setEliminated(eliminated);
    }

    public void setHighlight(CandidateHighlightMode mode) {
        candidate.setHighlight(mode);
    }

    public CandidateHighlightMode getMode() {
        return candidate.getMode();
    }
}
