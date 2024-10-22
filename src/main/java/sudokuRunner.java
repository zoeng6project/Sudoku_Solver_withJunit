public class sudokuRunner {
    public static void main(String[] args) {

        int[][] boardForSolver1 = {
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

        int[][] boardForSolver2 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(boardForSolver1[i], 0, boardForSolver2[i], 0, 9);
        }

// Solver 1 using Backtrack
        SudokuSolverBacktrack solver1 = new SudokuSolverBacktrack();
        long start_time = System.currentTimeMillis();

        solver1.printBoard(boardForSolver1);
        if (solver1.solveSudoku(boardForSolver1)) {
            System.out.println("Output: using backtracking algorithm");
            solver1.printBoard(boardForSolver1);
        } else {
            System.out.println("No solution exists.");
        }

        long end_time = System.currentTimeMillis();
        long duration = end_time - start_time;
        System.out.printf("Duration: %d ms%n", duration);

// Solver 2 using CSP
        SudokuSolverCSP solver2 = new SudokuSolverCSP();
        start_time = System.currentTimeMillis();

        if (solver2.solveSudoku(boardForSolver2)) {
            System.out.println("Output: using CSP algorithm");
            solver2.printBoard(SudokuSolverCSP.cellSudoku);

        } else {
            System.out.println("No solution exists.");
        }

        end_time = System.currentTimeMillis();
        duration = end_time - start_time;
        System.out.printf("Duration : %d ms%n", duration);
    }
}
