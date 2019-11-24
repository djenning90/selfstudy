package com.davidjennings.selfstudy.ik.week2_recursion.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Find All Well Formed Brackets
 *
 * Problem Statement:
 *
 * Given a positive integer n, find ALL well formed round brackets string of length 2*n.
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
 * Return array of strings res, containing all possible well formed round brackets string. (Length of each string
 * will be 2*n).
 *
 * You need not to worry about the order of strings in res.
 * E.g. For n = 2, ["(())", "()()"] or ["()()", "(())"], both will be accepted.
 *
 * Input/Output Format For The Custom Input:
 *
 * Input Format:
 *
 * There should be one line for input, containing an integer n.
 *
 * If n = 3, then input should be:
 *
 * 3
 *
 * Output Format:
 *
 * Let’s denote the size of res is m, where res is the resultant array of string returned by the solution function.
 * Then, there will be m lines of output, where ith line contains res[i], denoting string at index i of res.
 *
 * For input n = 3, output will be:
 *
 * ((()))
 * (()())
 * (())()
 * ()(())
 * ()()()
 *
 * Constraints:
 *
 * 1 <= n <= 13
 * Only use round brackets. '(' and ')'.
 *
 * Sample Test Case:
 *
 * Sample Input:
 *
 * 3
 *
 * Sample Output:
 *
 * [
 *    "((()))",
 *    "(()())",
 *    "(())()",
 *    "()(())",
 *    "()()()"
 * ]
 *
 * (This is one of the possible outputs. Array containing these five string in any order, will be accepted.)
 */
public class FindWellFormedBrackets
{
    static String[] find_all_well_formed_brackets(int n)
    {
        List<String> results = new ArrayList<>();

        handleSubproblem(n*2, n, n, new char[n*2], 0, results);

        int i=0;
        String[] r = new String[results.size()];
        for (String result : results)
            r[i++] = result;

        return r;
    }

    static void handleSubproblem(int length, int leftRemaining, int rightRemaining, char[] partialResults, int partialResultsLen, List<String> results)
    {
        // base cases:
        // If it would be imbalanced, leave
        if (leftRemaining > rightRemaining)
        {
            return;
        }
        // If balanced and complete, add result.
        if (leftRemaining == 0 && rightRemaining == 0)
        {
            results.add(new String(partialResults));
        }
        // If reached the end and didn't find a result, exit.
        else if (partialResultsLen == length)
        {
            return;
        }
        else
        {
            if (leftRemaining > 0)
            {
                partialResults[partialResultsLen] = '(';
                handleSubproblem(length, leftRemaining - 1, rightRemaining, partialResults, partialResultsLen+1, results);
            }
            if (rightRemaining > 0)
            {
                partialResults[partialResultsLen] = ')';
                handleSubproblem(length, leftRemaining, rightRemaining - 1, partialResults, partialResultsLen+1, results);
            }
        }
    }

    public static void main(String[] args)
    {
        String[] results = find_all_well_formed_brackets(3);

        for (String result : results)
        {
            System.out.println(result);
        }
    }
}
