package com.visentin.sudoku.util.dataStructures;

public class SudokuSet {
    private static final int FULL_MASK = 0x3FE; // Bits 1-9 set to 1
    private int mask;

    private SudokuSet() {
        this.mask = 0;
    }
    public SudokuSet(int mask) {
        this.mask = mask;
    }
    public SudokuSet(SudokuSet other){
        this.mask = other.mask;
    }
    public static SudokuSet emptySet(){
        return new SudokuSet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuSet)) return false;
        return this.mask == ((SudokuSet) o).mask;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(mask);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 1; i <= 9; i++) {
            if (contains(i)) sb.append(i).append(" ");
        }
        return sb.toString().trim() + "}";
    }

    public int getMask() {
        return mask;
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
    public int cardinality(){
        return Integer.bitCount(mask);
    }
    public int numberOfTrailingZeros(){
        return Integer.numberOfTrailingZeros(mask & FULL_MASK);
    }
    public void negate() {
        mask = (~mask) & FULL_MASK;
    }

    public void intersection(SudokuSet other){
        this.mask &= other.mask;
    }
    public void union(SudokuSet other){
        this.mask |= other.mask;
    }
    public void clearAllBut(int i){
        indexValidation(i);
        mask = (1 << i);
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
