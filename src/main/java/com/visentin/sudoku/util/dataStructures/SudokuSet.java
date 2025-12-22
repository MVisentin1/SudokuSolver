package com.visentin.sudoku.util.dataStructures;

public class SudokuSet {
    private int mask;

    public SudokuSet() {
        this.mask = 0;
    }

    public SudokuSet(int mask) {
        this.mask = mask;
    }

    public void add(int i){
        indexValidation(i);
        mask |= (1 << i);
    }

    public void remove(int i){
        indexValidation(i);
        mask &= ~(1 << i);
    }

    public boolean contains(int i){
        indexValidation(i);
        return (mask & (1 << i)) != 0;
    }

    public boolean isEmpty(){
        return mask == 0;
    }

    public int size(){
        return Integer.bitCount(mask);
    }

    public void intersection(SudokuSet other){
        this.mask &= other.mask;
    }

    public void union(SudokuSet other){
        this.mask |= other.mask;
    }

    public static SudokuSet intersection(SudokuSet o1, SudokuSet o2){
        return new SudokuSet(o1.mask & o2.mask);
    }

    public static SudokuSet union(SudokuSet o1, SudokuSet o2){
        return new SudokuSet(o1.mask | o2.mask);
    }

    private static void indexValidation(int i) {
        if (i < 1 || i > 9){
            throw new IllegalArgumentException("Index must be between 1 and 9");
        }
    }
}
