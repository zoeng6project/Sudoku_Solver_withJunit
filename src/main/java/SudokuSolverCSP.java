import java.util.ArrayList;

public class SudokuSolverCSP {
    public static Cell[][] cellSudoku = new Cell[9][9];

    public static final int SIZE = 9;

    public boolean solveSudoku(int[][] arrSudoku) {

        //Create cell based sudoku
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (arrSudoku[row][col] == 0) {
                    // possible num
                    ArrayList<Integer> numbers = new ArrayList<>();
                    numbers.add(1);
                    numbers.add(2);
                    numbers.add(3);
                    numbers.add(4);
                    numbers.add(5);
                    numbers.add(6);
                    numbers.add(7);
                    numbers.add(8);
                    numbers.add(9);

                    Cell temp = new Cell(false, row, col, false, 0, numbers);
                    cellSudoku[row][col] = temp;
                }
                //if there is value
                else {
                    ArrayList<Integer> emptyPossibleNum = new ArrayList<>();
                    Cell temp = new Cell(false, row, col, true, arrSudoku[row][col], emptyPossibleNum);
                    cellSudoku[row][col] = temp;
                }

            }
        }

        printBoard(cellSudoku);

        int cycleCount = 0 ;
        while (!isOver(cellSudoku)) {

            eliminateNum(cellSudoku);
            uniqueCandidates(cellSudoku);
            eliminateNum(cellSudoku);

            uniquePositions_row(cellSudoku);

            eliminateNum(cellSudoku);
            uniqueCandidates(cellSudoku);
            eliminateNum(cellSudoku);

            uniquePositions_col(cellSudoku);

            eliminateNum(cellSudoku);
            uniqueCandidates(cellSudoku);
            eliminateNum(cellSudoku);

            uniquePositions_box(cellSudoku);

            cycleCount++;
            if (cycleCount > 1000) {
                return false;
            }

        }

        return true;
    }


    public void printBoard(Cell[][] board) {
        System.out.println("---------------------------------------------");
        for (int i=0;i<SIZE;i++){
            for (int j=0; j<SIZE;j++){
                if(cellSudoku[i][j].value == 0){
                    System.out.print("."+" " );
                } else {
                    System.out.print(cellSudoku[i][j].value + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------");


    }

    // ---------------- 1 POSSIBLE NUMBER ----------------
    public static Cell[][] uniqueCandidates (Cell[][] cellSudoku){

        for (int i=0; i< SIZE; i++){
            for (int j=0;j< SIZE;j++){
                if (cellSudoku[i][j].possibleNumbers.size() == 1){
                    cellSudoku[i][j].found = true;
                    cellSudoku[i][j].value = cellSudoku[i][j].possibleNumbers.get(0);
                    cellSudoku[i][j].possibleNumbers.clear();
                }
            }
        }
        return cellSudoku;

    }


    // ---------------- Eliminate Impossible Numbers ----------------
    public static Cell[][] eliminateNum (Cell[][] cellSudoku){

        for (int row=0; row< SIZE;row++){
            for (int col = 0; col< SIZE ; col++){


                //if this cell is not yet have a fix number
                if (cellSudoku[row][col].found == false){

                    //add all nonPossible numbers to this arraylist
                    ArrayList<Integer> foundNums = new ArrayList<>();

                    //add from ROW
                    for (int i=0;i<SIZE;i++){
                        if (cellSudoku[row][i].found == true){
                            //If it is in the arraylist, do not add it again
                            if (!foundNums.contains(cellSudoku[row][i].value)){
                                foundNums.add(cellSudoku[row][i].value);
                            }
                        }
                    }

                    //add from COLUMN
                    for (int i=0;i<SIZE;i++){
                        if (cellSudoku[i][col].found == true){
                            if (!foundNums.contains(cellSudoku[i][col].value)){
                                foundNums.add(cellSudoku[i][col].value);
                            }
                        }
                    }

                    //add form 3x3
                    int r = row - row % 3;
                    int c = col - col % 3;

                    for (int i = r; i < r + 3; i++){
                        for (int j = c; j < c + 3; j++){
                            if (cellSudoku[i][j].found == true){
                                if (!foundNums.contains(cellSudoku[i][j].value)){
                                    foundNums.add(cellSudoku[i][j].value);
                                }
                            }
                        }
                    }


                    //Remove impossible numbers from this cell according to the foundNums array
                    for (int i =0; i<foundNums.size();i++){
                        if (cellSudoku[row][col].possibleNumbers.contains(foundNums.get(i))){
                            cellSudoku[row][col].possibleNumbers.remove(cellSudoku[row][col].possibleNumbers.indexOf(foundNums.get(i)));
                        }
                    }

                    foundNums.clear();
                }
            }
        }

        return cellSudoku;

    }

    // ---------------- Select Only Suitable number in 3x3 BOX ----------------
    public static Cell[][] uniquePositions_box (Cell[][] cellSudoku){
        for (int row=0; row<SIZE ; row++){
            for (int col=0; col<SIZE ; col++){
                boolean shouldContinue = true;
                //go every single nonFound
                if (cellSudoku[row][col].found == false){
                    cellSudoku[row][col].current = true;

                    for (int i =0; i< cellSudoku[row][col].possibleNumbers.size(); i++){

                        if (shouldContinue){
                            //will look every cell`s possibleNums in 3x3 box, whether found&current
                            int r = row - row % 3;
                            int c = col - col % 3;
                            boolean shouldContinue_box = true;
                            for (int row_box = r; row_box< r+3; row_box++){
                                for (int col_box = c; col_box< c+3; col_box++) {

                                    if (shouldContinue_box) {
                                        if (cellSudoku[row_box][col_box].found == false) {
                                            if (cellSudoku[row_box][col_box].current == false) {
                                                if (cellSudoku[row_box][col_box].possibleNumbers.contains(cellSudoku[row][col].possibleNumbers.get(i))) {
                                                    shouldContinue_box = false;
                                                    break;
                                                }}}

                                        //if it is in the last cell of box(end) and not found any common number
                                        if (row_box == r + 2 && col_box == c + 2) {
                                            cellSudoku[row][col].current = false;
                                            cellSudoku[row][col].found = true;
                                            cellSudoku[row][col].value = cellSudoku[row][col].possibleNumbers.get(i);
                                            cellSudoku[row][col].possibleNumbers.clear();
                                            shouldContinue = false;
                                        }}}}
                        }else {
                            break;
                        }
                    }
                    //work finished on this cell
                    cellSudoku[row][col].current = false;
                }
            }
        }
        return cellSudoku;
    }

    // ---------------- Select Only Suitable number in  COLUMN ----------------
    public static Cell[][] uniquePositions_col(Cell[][] cellSudoku){

        for (int row=0; row<SIZE;row++){
            for (int col =0; col< SIZE; col++){
                boolean shouldContinue = true;
                //go every single nonFound cell
                if (cellSudoku[row][col].found == false){
                    //make this cell currentWorking cell (for ignoring duplicate)
                    cellSudoku[row][col].current = true;

                    for (int i =0; i< cellSudoku[row][col].possibleNumbers.size(); i++){
                        if (shouldContinue){
                            //will look every cell`s possibleNums in this column,
                            // whether found&current is false in same time
                            for (int c = 0; c<SIZE; c++){
                                if (cellSudoku[c][col].found == false){
                                    if (cellSudoku[c][col].current == false){
                                        //if any cell in this column contain this number as possible number, break
                                        if (cellSudoku[c][col].possibleNumbers.contains(cellSudoku[row][col].possibleNumbers.get(i))){
                                            break;
                                        }
                                    }
                                }
                                //if in the last cell of this column and not found any common possibleNumber
                                if (c == 8){
                                    cellSudoku[row][col].current = false;
                                    cellSudoku[row][col].found = true;
                                    cellSudoku[row][col].value = cellSudoku[row][col].possibleNumbers.get(i);
                                    cellSudoku[row][col].possibleNumbers.clear();
                                    shouldContinue = false;
                                }
                            }
                        }else {
                            break;
                        }
                    }
                    //work finished on this cell
                    cellSudoku[row][col].current = false;
                }
            }
        }
        return cellSudoku;
    }

    // ---------------- Select Only Suitable number in  ROW ----------------
    public static Cell[][] uniquePositions_row(Cell[][] cellSudoku){

        for (int row=0; row < SIZE; row++){
            for (int col =0; col < SIZE; col++){
                boolean shouldContinue = true;
                //go every single nonFound
                if (cellSudoku[row][col].found == false){
                    //make this cell currentWorking cell (for ignoring duplicate)
                    cellSudoku[row][col].current = true;

                    for (int i=0;i< cellSudoku[row][col].possibleNumbers.size();i++){

                        if (shouldContinue){

                            //will look every cell`s possibleNums in this row, whether found&current is false in same time
                            for (int r = 0; r<SIZE; r++){
                                if (cellSudoku[row][r].found == false){
                                    if (cellSudoku[row][r].current == false){
                                        //if any cell in this row contain this number as possible number, break for changing temporary possible number
                                        if (cellSudoku[row][r].possibleNumbers.contains(cellSudoku[row][col].possibleNumbers.get(i))){
                                            break;
                                        }
                                    }
                                }
                                //if in the last cell of this row and not found any common possibleNumber
                                if (r == 8){
                                    cellSudoku[row][col].current = false;
                                    cellSudoku[row][col].found = true;
                                    cellSudoku[row][col].value = cellSudoku[row][col].possibleNumbers.get(i);
                                    cellSudoku[row][col].possibleNumbers.clear();
                                    shouldContinue = false;
                                }
                            }
                        }else {
                            break;
                        }
                    }
                    //work finished on this cell
                    cellSudoku[row][col].current = false;
                }
            }
        }
        return cellSudoku;
    }


    public static boolean isOver(Cell[][] cellSudoku){

        for (int i=0;i<SIZE;i++){
            for (int j =0; j<SIZE; j++){
                if (cellSudoku[i][j].found == false){
                    return false;
                }
            }
        }
        return true;

    }





}
