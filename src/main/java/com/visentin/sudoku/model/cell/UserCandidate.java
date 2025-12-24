package com.visentin.sudoku.model.cell;

public class UserCandidate extends BaseCandidate<UserCell, UserCandidate> {

    UserCandidate(int number) {
        super(number);
    }

    public boolean isEliminated() {
        return cell.findCandidate(number).isEmpty();
    }
}
