package com.davidjennings.selfstudy.ik.week4_graphs.practice;

import java.util.ArrayDeque;

/**
 * Knight's Tour On A Chess Board
 *
 *
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * You are given a rows * cols chessboard and a knight that moves like in normal chess.
 *
 *
 *
 * Currently knight is at starting position denoted by start_row th row and start_col th col, and want to reach at
 * ending position denoted by end_row th row and end_col th col.
 *
 *
 *
 * The goal is to calculate the minimum number of moves that the knight needs to take to get from starting position
 * to ending position.
 *
 *
 *
 * start_row, start_col, end_row and end_col are 0-indexed.
 *
 *
 *
 * Input Format:
 *
 *
 *
 * There are six arguments. First is an integer denoting rows, second is an integer denoting cols, third is an
 * integer denoting start_row, fourth is an integer denoting start_col, fifth is an integer denoting end_row and
 * sixth is an integer denoting end_col.
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Return an integer.
 *
 *
 *
 * If it is possible to reach from starting position to ending position then return minimum number of moves that the
 * knight needs to take to get from starting position to ending position.
 *
 *
 *
 * If it is not possible to reach from starting position to ending position then return -1.
 *
 *
 *
 * Constraints:
 *
 *
 *
 * 1 <= rows * cols <= 10^5
 * 0 <= start_row, end_row < rows
 * 0 <= start_col, end_col < cols
 *
 *
 * Sample Test Case:
 *
 *
 *
 * Sample Input:
 *
 *
 *
 * rows = 5
 *
 * cols = 5
 *
 * start_row = 0
 *
 * start_col = 0
 *
 * end_row = 4
 *
 * end_col = 1
 *
 *
 *
 * Sample Output:
 *
 *
 *
 * 3
 *
 *
 *
 * Explanation:
 *
 *
 *
 * 3 moves to reach from (0, 0) to (4, 1):
 *
 *
 *
 * (0, 0) -> (1, 2) -> (3, 3) -> (4, 1).
 */
public class KnightsTourOnChessboard
{
    private static class Location
    {
        final int row;
        final int col;
        final Location parent;

        Location(int row, int col, Location parent)
        {
            this.row = row;
            this.col = col;
            this.parent = parent;
        }

        Location(Location loc, int offsetRow, int offsetCol)
        {
            this(loc.row + offsetRow, loc.col + offsetCol, loc);
        }
    }

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col)
    {
        // Create visited flags in shape of board.
        boolean visited[][] = new boolean[rows][];
        for (int row=0; row< rows; row++)
            visited[row] = new boolean[cols];

        boolean foundSolution = false;

        Location loc = new Location(start_row, start_col, null);
        ArrayDeque<Location> q = new ArrayDeque<>();
        q.addFirst(loc);
        while (!q.isEmpty())
        {
            // Examine a position
            loc = q.removeLast();

            // Make sure position is valid
            if (loc.row < 0 || loc.row >= rows
             || loc.col < 0 || loc.col >= cols)
                continue;

            if (!visited[loc.row][loc.col])
            {
                // Consider this position visited
                visited[loc.row][loc.col] = true;

                // See if we have arrived at the solution
                if (loc.row == end_row && loc.col == end_col)
                {
                    foundSolution = true;
                    break;
                }

                // Try all possible moves from this position.
                q.addFirst(new Location(loc, 2, 1));
                q.addFirst(new Location(loc, 2, -1));
                q.addFirst(new Location(loc, -2, 1));
                q.addFirst(new Location(loc, -2, -1));
                q.addFirst(new Location(loc, 1, 2));
                q.addFirst(new Location(loc, 1, -2));
                q.addFirst(new Location(loc, -1, 2));
                q.addFirst(new Location(loc, -1, -2));
            }
        }

        int moves = 0;
        while (loc.parent != null)
        {
            moves++;
            loc = loc.parent;
        }
        return foundSolution ? moves : -1;
    }

    public static void main(String[] args)
    {
        int m = find_minimum_number_of_moves(5,5, 0,0, 4,1);

        int x = 0;
    }
}
