Project Description: Sudoku Solvers
This project consists of two implementations of a Sudoku solver, each utilizing different algorithms:

Sudoku Solver 1: Backtracking Method
Description: This implementation uses the backtracking algorithm to solve Sudoku puzzles. It systematically explores possible placements of numbers and backtracks whenever it encounters a conflict.
Performance: On average, this solver resolves Sudoku puzzles in approximately 15 milliseconds.

Sudoku Solver 2: Constraint Satisfaction Problem (CSP) Method
Description: This implementation leverages the Constraint Satisfaction Problem approach, which applies constraints to narrow down the possible values for each cell more efficiently.
Performance: This solver achieves a faster resolution time, solving Sudoku puzzles in about 5 milliseconds on average.

Testing
A JUnit test suite has been developed for the backtracking method to ensure its correctness and robustness. Tests cover easy, medium, and hard Sudoku puzzles to ensure the solvers can handle a range of complexities.

Conclusion
Both solvers demonstrate effective strategies for tackling Sudoku puzzles, with the CSP method significantly outperforming the backtracking method in terms of speed. The project highlights the importance of choosing the right algorithm based on performance requirements.

PowerPoint 
https://docs.google.com/presentation/d/1aP0ql6rkuG6pDccTmBBe9Y49pxuF64JxsigAhC4g-50/edit?usp=sharing
