package com.davidjennings.selfstudy.ik.week2_recursion.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * n Queen Problem
 *
 * Problem Statement:
 *
 * The n-queen problem is the problem of placing n chess queens on an n * n chessboard, so that no two queens attack
 * each other.
 *
 * Your task is to find ALL possible arrangements for the n-queen problem.
 *
 * You have to solve this problem using recursion. (There may be other ways of solving this problem, but for the
 * purpose of this exercise, please use recursion only).
 *
 * A queen can move horizontally, vertically, or diagonally.
 *
 * The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution.
 * After that, you can write a DP solution if you want.
 *
 * Input/Output Format For The Function:
 *
 * Input Format:
 *
 * There is only one argument denoting integer n.
 *
 * Output Format:
 *
 * Return 2D string array res, of size m * n, where length of each string is n and m is the total no. of distinct
 * arrangements possible.
 *
 * Each character in res[i][j] describes a square on chessboard. So, any character in string should be one of {'q',
 * '-'}. Character 'q' means queen is present on it and character '-' means it is empty.
 *
 * To be precise, character at kth position of string res[i][j] describes content of square in kth column of jth row
 * of chessboard in ith arrangement.
 *
 * (To be more clear about the output, look at the sample test case.)
 *
 * Input/Output Format For The Custom Input:
 *
 * Input Format:
 *
 * The first and only line of input should contain an integer n.
 *
 * If n = 4, then input should be:
 *
 * 4
 *
 * Output Format:
 *
 * There will be (m*(n+1)) lines of output, describing m different possible arrangements. Order of printing of
 * arrangements will be as per order in res array, i.e. res[0], res[1], …, res[m-1].
 *
 * For ith arrangement (described by res[i]):
 *
 * There will be total n + 1 lines. In first n lines, jth line contains a string res[i][j], denoting string at index
 * j of res[i]. Last line will be an empty line.
 *
 * For input n = 4, output will be:
 *
 * -q--
 * ---q
 * q---
 * --q-
 *
 * --q-
 * q---
 * ---q
 * -q--
 *
 * Constraints:
 *
 * 1 <= n <= 12
 *
 * Sample Test Case:
 *
 * Sample Input:
 *
 * 4
 *
 * Sample Output:
 *
 * Suppose name of the returned array is ret.
 *
 * ret [0] =
 * [
 * "--q-",
 * "q---",
 * "---q",
 * "-q--"
 * ]
 *
 * ret [1] =
 * [
 * "-q--",
 * "---q",
 * "q---",
 * "--q-"
 * ]
 *
 * Explanation:
 *
 * There are 2 possible solutions for 4 queen problem, hence size of ret is 2 * 4, and length of each string is 4.
 *
 * ret [i] will denote ith arrangement.
 * ret [i][j] will denote jth row of ith arrangement.
 * ret [i][j][k] will denote kth character (if it is a queen or empty cell) of jth row in ith arrangement.
 *
 * You need not to worry about the order of arrangements in your returned. So output
 *
 * ret [0] =
 * [
 * "-q--",
 * "---q",
 * "q---",
 * "--q-"
 * ]
 *
 * ret [1] =
 * [
 * "--q-",
 * "q---",
 * "---q",
 * "-q--"
 * ]
 *
 * will also be considered as a valid answer.
 *
 * Notes:
 *
 * Suggested time in interview: 30 minutes; this is only a variation of permutations problems.
 *
 * The “Suggested Time” is the time expected to complete this question during a real-life interview, not now in
 * homework i.e. For the first attempt of a given homework problem, the focus should be to understand what the
 * problem is asking, what approach you are using, coding it, as well as identifying any gaps that you can discuss
 * during a TC session. Take your time, but limit yourself to 2 one hour sessions for most problems.
 */
public class NQueens
{
    static char[][] board;
    static int n;
    static List<String[]> results = new ArrayList<>();

    static String[][] find_all_arrangements(int size)
    {
        n = size;

        // Set up the board as board[rows][cols]
        board = new char[n][];
        for (int row=0; row<n; row++)
        {
            board[row] = new char[n];
            for (int col=0; col<n; col++)
                board[row][col] = '-';
        }

        findArrangementsAtRow(0);

        String[][] r = new String[results.size()][];
        for (int i=0; i<results.size(); i++)
            r[i] = results.get(i);
        return r;
    }

    static void findArrangementsAtRow(int row)
    {
        // Base case
        if (row == n)
        {
            captureResult();
        }
        // Recursively try column permutations below this row.
        else
        {
            for (int col=0; col<n; col++)
            {
                if (!conflictsWithBoard(row, col))
                {
                    // Place queen here
                    board[row][col] = 'q';
                    findArrangementsAtRow(row + 1);
                    // Remove queen and continue
                    board[row][col] = '-';
                }
            }
        }
    }

    static boolean conflictsWithBoard(int newRow, int newCol)
    {
        for (int existingRow=0; existingRow<newRow; existingRow++)
        {
            int existingCol;
            for (existingCol=0; existingCol<n; existingCol++)
            {
                if (board[existingRow][existingCol] == 'q')
                    break;
            }

            // New queen in same col as existing queens
            if (newCol == existingCol)
                return true;
            // New queen can attack along diagonals
            int deltaRow = newRow - existingRow;
            int deltaCol = newCol - existingCol;
            if (deltaRow == deltaCol || deltaRow == -deltaCol)
                return true;
        }

        return false;
    }

    static void captureResult()
    {
        String[] result = new String[n];
        for (int row=0; row<n; row++)
        {
            result[row] = new String(board[row]);
        }

        results.add(result);
    }

    public static void main(String[] args)
    {
        String[][] results = find_all_arrangements(8);

        int solution = 1;
        for (String[] board : results)
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
