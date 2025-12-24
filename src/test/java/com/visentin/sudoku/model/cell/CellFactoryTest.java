package com.visentin.sudoku.model.cell;

import com.visentin.sudoku.util.dataStructures.SudokuSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellFactoryTest {

    @Nested
    @DisplayName("Solved User Cell Creation")
    class CreateSolvedUserCell {

        @Test
        @DisplayName("Should throw on invalid value")
        void shouldThrowOnInvalidValue(){
            assertThrows(IllegalArgumentException.class, () -> CellFactory.createSolvedUserCell(0));
            assertThrows(IllegalArgumentException.class, () -> CellFactory.createSolvedUserCell(10));
        }

        @Test
        @DisplayName("Should create user cell with correctly initialized eliminated candidates")
        void shouldCreateCell(){
            int value = 2;
            UserCell cell = CellFactory.createSolvedUserCell(value);
            assertEquals(value, cell.getValue());
            assertTrue(cell.isSolved());
            assertNotNull(cell.candidates);
            assertEquals(10, cell.candidates.length);
            for (int i = 1; i < 10; i++){
                UserCandidate candidate = cell.candidates[i];
                assertNotNull(candidate);
                assertEquals(i, candidate.getNumber());
                assertEquals(cell, candidate.getCell());
                assertTrue(candidate.isEliminated());
            }
            assertTrue(cell.isFixed());
        }
    }

    @Nested
    @DisplayName("Unsolved Cell Creation")
    class CreateUnsolvedCell {

        @Test
        @DisplayName("Should throw on null set")
        void shouldThrowOnNullSet(){
            assertThrows(NullPointerException.class, () -> CellFactory.createUnsolvedUserCell(null));
        }

        @Test
        @DisplayName("Should initialize an unsolved cell with according eliminated candidates")
        void shouldCreateCell(){
            // Creates a cell where 1, 2, 3 are eliminated, other digits there
            SudokuSet active = SudokuSet.emptySet().add(1).add(2).add(3).negate();
            UserCell cell = CellFactory.createUnsolvedUserCell(active.negate());
            assertFalse(cell.isSolved());
            assertFalse(cell.isFixed());
            assertNotNull(cell.candidates);
            assertEquals(10, cell.candidates.length);
            for (int i = 1; i < 10; i++){
                UserCandidate candidate = cell.candidates[i];
                assertNotNull(candidate);
                assertEquals(i, candidate.getNumber());
                assertEquals(cell, candidate.getCell());
                assertEquals(active.contains(i), !candidate.isEliminated());
            }
        }

    }

    @Nested
    class Idempotency {
        @Test
        @DisplayName("Should instantiate different candidate objects across calls")
        void shouldCreateDifferentCandidates(){
            SudokuSet set = SudokuSet.emptySet();
            UserCell cell = CellFactory.createUnsolvedUserCell(set);
            UserCell cell2 = CellFactory.createUnsolvedUserCell(set);

            for (int i = 1; i < 10; i++) {
                assertNotSame(cell.candidates[i], cell2.candidates[i]);
            }
        }

    }
}
