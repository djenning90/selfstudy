package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem46
{
    /**
     * Given a collection of distinct integers, return all possible permutations.
     *
     * Example:
     *
     * Input: [1,2,3]
     * Output:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    public List<List<Integer>> permuteUnique(int[] nums)
    {
        List<List<Integer>> results = new ArrayList<>();

        permute(nums, 0, new ArrayList<>(), results);

        return results;
    }

    private void permute(int[] input, int i, List<Integer> partialResult, List<List<Integer>> results)
    {
        // Base case: if partial result is complete
        if (i == input.length)
            results.add(new ArrayList<Integer>(partialResult));     // add copy of complete result
            // Else split up the input.
        else
        {
            // Because of the distinct requirement, we keep track of individual values seen in loop.
            Set<Integer> seen = new HashSet<>();

            // Consider subproblems defined by input starting at i
            // Which is simply each value from i to the end of the string + the remaining values
            for (int k=i; k<input.length; k++)
            {
                int val = input[k];
                // Prevent duplicates by bypassing generating combinations on an already seen value.
                if (!seen.contains(val))
                {
                    seen.add(val);
                    // Consider a variant where number k comes first. Do by swapping!
                    swap(input, i, k);      // make element k come first (preserve current value)
                    // Handle subproblem
                    partialResult.add(val);
                    permute(input, i + 1, partialResult, results);    // use bigger partialResults
                    partialResult.remove(partialResult.size() - 1);      // put things back
                    swap(input, i, k);      // put things back
                }
            }
        }
    }

    private void swap(int[] array, int i1, int i2)
    {
        int val = array[i1];
        array[i1] = array[i2];
        array[i2] = val;
    }
}
