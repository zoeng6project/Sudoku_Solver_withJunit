import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverBacktrackTest {

    private SudokuSolverBacktrack solver;
    private int[][] board;
    private int[][] expectedSolution;

    @BeforeEach
    public void setUp() {
        solver = new SudokuSolverBacktrack();
    }

    @Test
    @DisplayName("Test for Easy Board")
    void solveSudokuTest_Easy(){
        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        expectedSolution = new int[][] {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        assertTrue(solver.solveSudoku(board));
        assertArrayEquals(expectedSolution, board);

    }

    @Test
    @DisplayName("Test for Mid Board")
    void solveSudokuTest_Mid(){
        board = new int[][]{
                {6, 5, 0, 9, 1, 2, 7, 0, 8},
                {0, 0, 7, 0, 0, 0, 0, 0, 0},
                {1, 4, 0, 0, 7, 8, 6, 2, 0},
                {0, 0, 0, 6, 4, 0, 0, 5, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 4},
                {0, 3, 0, 0, 2, 7, 0, 0, 0},
                {0, 8, 5, 2, 6, 0, 0, 7, 1},
                {0, 0, 0, 0, 0, 0, 5, 0, 0},
                {3, 0, 4, 7, 8, 5, 0, 6, 2}
        };

        expectedSolution = new int[][] {
                {6, 5, 3, 9, 1, 2, 7, 4, 8},
                {8, 2, 7, 4, 5, 6, 1, 3, 9},
                {1, 4, 9, 3, 7, 8, 6, 2, 5},
                {2, 9, 8, 6, 4, 1, 3, 5, 7},
                {5, 7, 6, 8, 3, 9, 2, 1, 4},
                {4, 3, 1, 5, 2, 7, 8, 9, 6},
                {9, 8, 5, 2, 6, 3, 4, 7, 1},
                {7, 6, 2, 1, 9, 4, 5, 8, 3},
                {3, 1, 4, 7, 8, 5, 9, 6, 2}
        };

        assertTrue(solver.solveSudoku(board));
        assertArrayEquals(expectedSolution, board);

    }


    @Test
    @DisplayName("Test for Hard Board")
    void solveSudokuTest_Hard(){
        board = new int[][]{
                {5, 0, 1, 0, 0, 0, 6, 0, 4},
                {0, 9, 0, 3, 0, 6, 0, 5, 0},
                {0, 0, 0, 0, 9, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 1, 0, 9, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 6},
                {0, 0, 0, 0, 2, 0, 0, 0, 0},
                {0, 8, 0, 5, 0, 7, 0, 6, 0},
                {1, 0, 3, 0, 0, 0, 7, 0, 2}
        };

        expectedSolution = new int[][] {
                {5, 3, 1, 7, 8, 2, 6, 9, 4},
                {2, 9, 4, 3, 1, 6, 8, 5, 7},
                {8, 7, 6, 4, 9, 5, 1, 2, 3},
                {4, 2, 8, 6, 7, 3, 5, 1, 9},
                {3, 6, 5, 1, 4, 9, 2, 7, 8},
                {7, 1, 9, 2, 5, 8, 3, 4, 6},
                {6, 4, 7, 8, 2, 1, 9, 3, 5},
                {9, 8, 2, 5, 3, 7, 4, 6, 1},
                {1, 5, 3, 9, 6, 4, 7, 8, 2}
        };

        assertTrue(solver.solveSudoku(board));
        assertArrayEquals(expectedSolution, board);

    }


    @ParameterizedTest
    @DisplayName("Test for isSafe method at r6 c2 return true")
    @ValueSource(ints = {1,3,4,5,7,9} )
//    {1,3,4,5,7,9} true
//    {2,6,8} false

    void isSafeTest_TrueR6C2(int num){
        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };


        assertTrue(solver.isSafe(board, 6,2,num));


    }


    @ParameterizedTest
    @DisplayName("Test for isSafe method at r6 c2 return false")
    @ValueSource(ints = {2,6,8} )
//    {1,3,4,5,7,9} true
//    {2,6,8} false

    void isSafeTest_FalseR6C2(int num){
        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };


        assertFalse(solver.isSafe(board, 6,2,num));


    }

    @ParameterizedTest
    @DisplayName("Test for isSafe method at r2 c6 return true")
    @ValueSource(ints = {1,3,4,5,7} )
//    {1,3,4,5,7} true
//    {2,6,8,9} false

    void isSafeTest_TrueR2C6(int num){
        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };


        assertTrue(solver.isSafe(board, 2,6,num));


    }


    @ParameterizedTest
    @DisplayName("Test for isSafe method at r6 c2 return false")
    @ValueSource(ints = {2,6,8,9}  )
//    {1,3,4,5,7} true
//    {2,6,8,9} false

    void isSafeTest_FalseR2C6(int num){
        board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };


        assertFalse(solver.isSafe(board, 2,6,num));


    }

}