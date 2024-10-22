public class SudokuSolverBacktrack {

    private static final int SIZE = 9;

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        //Check any same number at below 4 constraint
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            // Recursion
                            if (solveSudoku(board)) {
                                return true;
                            }
                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    // Trigger backtracking
                    return false;
                }
            }
        }
        return true; // Sudoku solved with solution
    }

    public boolean isSafe(int[][] board, int row, int col, int num) {
        // Check the row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }
        // Check the column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }
        // Check the 3x3 box
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + boxRowStart][j + boxColStart] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard(int[][] board) {
        System.out.println("---------------------------------------------");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0 ){
                    System.out.print("." + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------");
    }
}
