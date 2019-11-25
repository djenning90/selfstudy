package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Problem77
{
    /**
     * 77. Combinations
     *
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * Example:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    public List<List<Integer>> combine(int n, int k)
    {
        List<List<Integer>> results = new ArrayList<>();

        // Materialize the specified input
        int input[] = new int[n];
        for (int i=0; i<n; i++)
            input[i] = i+1;

        subproblem(input, 0, k, new ArrayList<>(), results);

        return results;
    }

    public void subproblem(int[] input, int i, int k, List<Integer> partialResult, List<List<Integer>> results)
    {
        // Base case:
        // Subproblem is a complete result, so save it.
        if (partialResult.size() == k)
        {
            // Add a copy because we're still permuting it.
            results.add(new ArrayList<>(partialResult));
        }
        // All input has been exhausted.
        else if (i == input.length)
        {
            return;
        }
        // Recursive case:
        // Try remainder of input both excluding and including i
        else
        {
            // Exclude i
            subproblem(input, i+1, k, partialResult, results);

            // Include i
            partialResult.add(input[i]);
            subproblem(input, i+1, k, partialResult, results);
            partialResult.remove(partialResult.size() - 1);
        }
    }

    public static void main(String[] args)
    {

        Problem77 p = new Problem77();

        List<List<Integer>> results = p.combine(4, 2);

        for (List<Integer> result : results)
        {
            Iterator<Integer> iter = result.iterator();
            System.out.print("[");
            while (iter.hasNext())
            {
                System.out.print(iter.next().intValue());
                if (iter.hasNext())
                    System.out.print(",");
            }
            System.out.println("]");
        }
    }
}
