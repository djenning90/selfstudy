package com.davidjennings.selfstudy.leetcode;

/**
 * 52. N-Queens II
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class Problem52
{
    public int totalNQueens(int n)
    {
        // Because a row can only have one queen, we can represent the board
        // as a one-dimensional array of horizontal strips, where each strip
        // is just the column index of a queen.
        int[] theBoard = new int[n];

        int numSolutions = solveSubproblem(n, 0, theBoard, 0);

        return numSolutions;
    }

    /**
     * @param n = fixed size of the board
     * @param row = represents the subproblem to solve: try starting with placing a queen on some column of given row
     * @param theBoard = partial result, where permutations of queens are tried
     * @param numSolutions = number of solutions found before now
     */
    int solveSubproblem(int n, int row, int[] theBoard, int numSolutions)
    {
        // Base cases
        // If board is complete, add as a found result.
        if (row == n)
        {
            return numSolutions + 1;
        }
        // Recursive case
        else
        {
            // Loop over columns for the current row, looking for one that fits the solution constraints.
            // We do this by spawning a subproblem for all n variations of the current board, trying a oolumn.
            for (int col=0; col<n; col++)
            {
                // Skip if proposed queen position clashes with the rest of the board (rows above).
                if (clashesWithRowsAbove(n, theBoard, row, col))
                    continue;

                theBoard[row] = col;
                // Try this board configuration, and rows below the current row.
                numSolutions = solveSubproblem(n, row+1, theBoard, numSolutions);
            }

            // No new solutions were found
            return numSolutions;
        }
    }

    boolean clashesWithRowsAbove(int n, int[] theBoard, int row, int col)
    {
        for (int i=0; i<row; i++)
        {
            // If any queen to the left is on this col, that's a clash
            if (theBoard[i] == col)
                return true;

            // If any queen to the left is on a diagonal to col,row, that's a clash
            // A diagonal is abs(deltaCol) == deltaRow
            int deltaRow = row-i;
            int deltaCol = theBoard[i] - col;
            if (deltaRow == deltaCol || deltaRow == -deltaCol)
                return true;
        }

        return false;
    }

    public static void main(String[] args)
    {
        Problem52 p = new Problem52();

        int numSolutions = p.totalNQueens(4);

        System.out.println(String.format("Solutions found: %d", numSolutions));
    }
}
