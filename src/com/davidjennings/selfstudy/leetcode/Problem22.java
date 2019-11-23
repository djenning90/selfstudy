package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Problem22
{
    public List<String> generateParenthesis(int n)
    {
        List<String> results = new ArrayList<>();

        subproblem(n*2, n, n, new char[n*2], 0, results);

        return results;
    }

    void subproblem(int length, int numLeftRemaining, int numRightRemaining, char[] partialResult, int partialLen, List<String> results)
    {
        // Base cases
        // If result would be imbalanced, exit. Example: (()))
        if (numLeftRemaining > numRightRemaining)
        {
            return;
        }
        // When valid partial result contains all parens, add it as final result.
        else if (numLeftRemaining == 0 && numRightRemaining == 0)
        {
            results.add(new String(partialResult));
        }
        // If we reached max size without a result, exit
        else if (partialLen == length)
        {
            return;
        }
        // Recursive case
        else
        {
            // Try (
            if (numLeftRemaining > 0)
            {
                partialResult[partialLen] = '(';
                subproblem(length, numLeftRemaining-1, numRightRemaining, partialResult, partialLen+1, results);
            }
            // Try )
            if (numRightRemaining > 0)
            {
                partialResult[partialLen] = ')';
                subproblem(length, numLeftRemaining, numRightRemaining-1, partialResult, partialLen+1, results);
            }
        }
    }

    public static void main(String[] args)
    {
        Problem22 p = new Problem22();

        List<String> results = p.generateParenthesis(3);

        int count = 1;
        for (String result : results)
        {
            System.out.println(String.format("%d: %s", count++, result));
        }
    }
}
