package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate
 * a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 *
 * Output: [
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
 *
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class Problem51
{
    public List<List<String>> solveNQueens(int n)
    {
        List<List<String>> results = new ArrayList<>();

        // Because a row can only have one queen, we can represent the board
        // as a one-dimensional array of horizontal strips, where each strip
        // is just the column index of a queen.
        int[] theBoard = new int[n];

        solveSubproblem(n, 0, theBoard, results);

        return results;
    }

    /**
     * @param n = fixed size of the board
     * @param row = represents the subproblem to solve: try starting with placing a queen on some column of given row
     * @param theBoard = partial result, where permutations of queens are tried
     * @param results = holds all valid solutions found
     */
    void solveSubproblem(int n, int row, int[] theBoard, List<List<String>> results)
    {
        // Base cases
        // If board is complete, add as a found result.
        if (row == n)
        {
            captureResult(n, theBoard, results);
            return;
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
                solveSubproblem(n, row+1, theBoard, results);
            }
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

    void captureResult(int n, int[] theBoard, List<List<String>> results)
    {
        results.add(boardToStringList(n, theBoard));
    }

    List<String> boardToStringList(int n, int[] theBoard)
    {
        List<String> resultBoard = new ArrayList<>(n);

        for (int row = 0; row < n; row++)
        {
            StringBuilder theRow = new StringBuilder(n);
            for (int col = 0; col < n; col++)
            {
                if (col == theBoard[row])
                    theRow.append('Q');
                else
                    theRow.append('.');
            }

            resultBoard.add(theRow.toString());
        }

        return resultBoard;
    }
    
    public static void main(String[] args)
    {
        Problem51 p = new Problem51();
        
        List<List<String>> results = p.solveNQueens(8);
        
        int solution = 1;
        for (List<String> board : results)
        {
            System.out.println(String.format("Solution %d", solution++));
            for (String row : board)
            {
                System.out.println(row);
            }
            System.out.println();
        }
    }    
}
