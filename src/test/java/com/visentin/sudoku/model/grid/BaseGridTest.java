package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.TestHouse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.visentin.sudoku.util.AssertionTestUtils.assertErrorIfEnabled;
import static org.junit.jupiter.api.Assertions.*;

public class BaseGridTest {

    @Test
    void constructor_houseListIsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestGrid(null, null, null));
    }

    @Test
    void constructor_houseListSizeNotEquals9_throwsAssertionError() {
        List<TestHouse> rows = new ArrayList<>(8);
        List<TestHouse> columns = new ArrayList<>(8);
        List<TestHouse> boxes = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            rows.add(TestHouse.createTestHouse());
            columns.add(TestHouse.createTestHouse());
            boxes.add(TestHouse.createTestHouse());
        }
        assertErrorIfEnabled(() -> new TestGrid(rows, columns, boxes));
    }

    @Test
    void constructor_houseListContainsNull_throwsAssertionError() {
        List<TestHouse> rows = new ArrayList<>(9);
        List<TestHouse> columns = new ArrayList<>(9);
        List<TestHouse> boxes = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                rows.add(null);
                columns.add(null);
                boxes.add(null);
            } else {
                rows.add(TestHouse.createTestHouse());
            }
        }

        assertErrorIfEnabled(() -> new TestGrid(rows, columns, boxes));
    }

    @Test
    void constructor_correctInput_correctOrder() {
        List<TestHouse> rows = TestGrid.createTestHouseList();
        List<TestHouse> columns = TestGrid.createTestHouseList();
        List<TestHouse> boxes = TestGrid.createTestHouseList();
        TestGrid grid = new TestGrid(rows, columns, boxes);
        for (int i = 1; i < 10; i++) {
            assertEquals(rows.get(i-1), grid.getRow(i));
            assertEquals(columns.get(i-1), grid.getColumn(i));
            assertEquals(boxes.get(i-1), grid.getBox(i));
        }
    }

    @Test
    void getRow_invalidIndex_throwsIndexOutOfBoundsException() {
        TestGrid grid = TestGrid.createTestGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getRow(0));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getRow(10));
    }

    @Test
    void getColumn_invalidIndex_throwsIndexOutOfBoundsException() {
        TestGrid grid = TestGrid.createTestGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getColumn(0));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getColumn(10));
    }

    @Test
    void getBox_invalidIndex_throwsIndexOutOfBoundsException() {
        TestGrid grid = TestGrid.createTestGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getBox(0));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getBox(10));
    }

}
