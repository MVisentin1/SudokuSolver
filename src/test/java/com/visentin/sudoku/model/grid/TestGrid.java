package com.visentin.sudoku.model.grid;

import com.visentin.sudoku.model.grid.house.TestHouse;

import java.util.ArrayList;
import java.util.List;

import static com.visentin.sudoku.model.grid.house.TestHouse.createTestHouse;

public class TestGrid extends GridBase<TestHouse> {

    TestGrid(List<TestHouse> rows, List<TestHouse> columns, List<TestHouse> boxes) {
        super(rows, columns, boxes);
    }

    static TestGrid createTestGrid() {
        List<TestHouse> rows = new ArrayList<>(9);
        List<TestHouse> columns = new ArrayList<>(9);
        List<TestHouse> boxes = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            rows.add(createTestHouse());
            columns.add(createTestHouse());
            boxes.add(createTestHouse());
        }
        return new TestGrid(rows, columns, boxes);
    }

    static List<TestHouse> createTestHouseList() {
        List<TestHouse> houses = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            houses.add(createTestHouse());
        }
        return houses;
    }
}
