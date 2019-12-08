package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Problem78
{
    public List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> results = new ArrayList<>();

        permuteSubproblem(nums, 0, new Stack<Integer>(), results);

        return results;
    }

    void permuteSubproblem(int[] input, int i, Stack<Integer> partialResult, List<List<Integer>> results)
    {
        // Base case
        if (i == input.length)
        {
            results.add(new ArrayList<>(partialResult));
        }
        else
        // Recursive case
        {
            // Consider all subsets not having this value.
            permuteSubproblem(input, i+1, partialResult, results);
            // Consider all subsets having this value.
            partialResult.push(input[i]);
            permuteSubproblem(input, i+1, partialResult, results);
            partialResult.pop();
        }
    }

    public static void main(String[] args)
    {
        Problem78 p = new Problem78();

        List<List<Integer>> results = p.subsets(new int[]{1, 2, 3});

        for (List<Integer> result : results)
        {
            Iterator<Integer> iter = result.iterator();
            System.out.print("(");
            while (iter.hasNext())
            {
                System.out.print(iter.next().intValue());
                if (iter.hasNext())
                    System.out.print(",");
            }
            System.out.println(")");
        }
    }
}
