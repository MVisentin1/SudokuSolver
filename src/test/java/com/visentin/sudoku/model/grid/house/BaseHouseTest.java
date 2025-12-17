package com.visentin.sudoku.model.grid.house;

import com.visentin.sudoku.model.cell.TestCell;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.visentin.sudoku.model.cell.TestCell.createTestCell;
import static org.junit.jupiter.api.Assertions.*;
import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;

public class BaseHouseTest {
    @Test
    void constructor_NullArray_NullPointerExceptionThrown() {
        assertThrows(NullPointerException.class, () -> new TestHouse(null));
    }

    @Test
    void constructor_ArrayNotLength9_AssertionErrorThrown() {
        TestCell cell = createTestCell(true);
        TestCell[] cells = {cell,cell};
        assertErrorIfEnabled(() -> new TestHouse(cells));
    }

    @Test
    void constructor_ArrayContainsNull_NullPointerExceptionThrown() {
        TestCell cell = createTestCell(true);
        TestCell[] cells = {cell, cell, cell, cell, cell, cell, cell, cell, null};
        assertThrows(NullPointerException.class, () -> new TestHouse(cells));
    }

    @Test
    void constructor_CorrectInput_CorrectOrder() {
        TestCell cell = createTestCell(true);
        TestCell cell1 = createTestCell(false);
        TestCell[] cells = {cell, cell, cell1, cell, cell1, cell, cell1, cell, cell};
        assertEquals(cell1, new TestHouse(cells).getCell(5));
        assertEquals(cell, new TestHouse(cells).getCell(1));
        assertEquals(cell, new TestHouse(cells).getCell(8));
    }

    @Test
    void getCell_InvalidIndex_ThrowsIndexOutOfBoundsException() {
        TestHouse house = TestHouse.createTestHouse(new HashSet<>());
        assertThrows(IndexOutOfBoundsException.class, () -> house.getCell(0));
        assertThrows(IndexOutOfBoundsException.class, () -> house.getCell(10));
    }

    @Test
    void getSolvedNumbers_CorrectSize() {
        Integer[] arr = {1, 3, 4, 8, 9};
        Set<Integer> n = new HashSet<>();
        Collections.addAll(n, arr);
        TestHouse house = TestHouse.createTestHouse(n);
        assertEquals(n.size(), house.getSolvedNumbers().size());
    }

    @Test
    void getSolvedNumbers_CorrectContent() {
        Integer[] arr = {1, 3, 4, 8, 9};
        Set<Integer> n = new HashSet<>();
        Collections.addAll(n, arr);
        TestHouse house = TestHouse.createTestHouse(n);
        Set<Integer> actual = house.getSolvedNumbers();
        for (Integer i : n) {
            assertTrue(actual.contains(i));
        }
    }
}
