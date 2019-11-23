package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Probllem47
{
    /**
     * 47. Permutations II
     *
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     *
     * Example:
     *
     * Input: [1,1,2]
     * Output:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums)
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
            // Consider subproblems defined by input starting at pos
            // Which is simply each value from pos to the end of the string + the remaining values
            for (int k=i; k<input.length; k++)
            {
                int val = input[k];
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

    private void swap(int[] array, int i1, int i2)
    {
        int val = array[i1];
        array[i1] = array[i2];
        array[i2] = val;
    }
}
