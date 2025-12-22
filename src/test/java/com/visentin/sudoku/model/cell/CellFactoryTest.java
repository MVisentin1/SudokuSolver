package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.model.grid.house.TestHouse;
import com.visentin.sudoku.util.dataStructures.SudokuSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellFactoryTest {
    private CellFactory<TestCell, TestCandidate, TestHouse> cellFactory;

    @BeforeEach
    void setUp() {
        cellFactory = new CellFactory<>(TestCell::new, TestCandidate::new);
    }

    @Nested
    @DisplayName("Solved Cell Creation")
    class CreateSolvedCell {

        @Test
        @DisplayName("Should throw on invalid value")
        void shouldThrowInvalidValue(){
            assertThrows(IllegalArgumentException.class, () -> cellFactory.createSolvedCell(0));
            assertThrows(IllegalArgumentException.class, () -> cellFactory.createSolvedCell(11));
        }

        @Test
        @DisplayName("Should initialize a solved cell correctly")
        void shouldCreateCell(){
            int value = 2;
            TestCell cell = cellFactory.createSolvedCell(value);

            assertTrue(cell.isSolved());
            assertEquals(value, cell.getValue());
            assertEquals(9, cell.getCandidateList().size());
            assertTrue(cell.isFixed());
            for (int i = 0; i < 9; i++) {
                TestCandidate testCandidate = cell.getCandidateList().get(i);
                if (i == value-1) {
                    assertFalse(testCandidate.isEliminated());
                } else {
                    assertTrue(testCandidate.isEliminated());
                }
                assertEquals(i + 1, testCandidate.getNumber());
                assertEquals(cell, testCandidate.getCell());
            }
        }

        @Test
        @DisplayName("Should mark all other candidates as eliminated except the correct one")
        void shouldEliminateAllExceptCorrect() {
            int value = 5;
            TestCell cell = cellFactory.createSolvedCell(value);

            assertTrue(cell.isSolved());
            for (int i = 0; i < 9; i++) {
                TestCandidate testCandidate = cell.getCandidateList().get(i);
                if (i == value - 1) {
                    assertFalse(testCandidate.isEliminated());
                } else {
                    assertTrue(testCandidate.isEliminated());
                }
            }
        }
    }

    @Nested
    @DisplayName("Unsolved Cell Creation")
    class CreateUnsolvedCell {

        @Test
        @DisplayName("Should initialize an unsolved cell correctly")
        void shouldCreateCell(){
            SudokuSet firstThreeEliminated = SudokuSet.emptySet();
            firstThreeEliminated.add(1);
            firstThreeEliminated.add(2);
            firstThreeEliminated.add(3);
            TestCell cell = cellFactory.createUnsolvedCell(firstThreeEliminated);

            assertFalse(cell.isSolved());
            assertFalse(cell.isFixed());
            assertEquals(9, cell.getCandidateList().size());
            for (int i = 0; i < 9; i++) {
                TestCandidate testCandidate = cell.getCandidateList().get(i);
                assertEquals(i + 1, testCandidate.getNumber());
                assertEquals(cell, testCandidate.getCell());
                assertEquals(!firstThreeEliminated.contains(i+1), testCandidate.isEliminated());
            }
        }
    }

    @Nested
    class Idempotency {
        @Test
        @DisplayName("Should create different candidates on different calls")
        void shouldCreateDifferentCandidates(){
            TestCell cell = cellFactory.createUnsolvedCell(SudokuSet.emptySet());
            TestCell cell2 = cellFactory.createUnsolvedCell(SudokuSet.emptySet());

            for (int i = 0; i < 9; i++) {
                assertNotEquals(cell.getCandidateList().get(i), cell2.getCandidateList().get(i));
            }
        }

    }
}
