package com.davidjennings.selfstudy.ik.week2_recursion.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate All Subsets Of A Set
 *
 * Problem Statement:
 *
 * Given a set (in form of string s containing only distinct lowercase letters ('a' - 'z')), you have to generate ALL
 * possible subsets of it .
 *
 * Note that:
 *
 * empty set is always a subset of any set.
 * whole set s should also be considered as its subset here.
 *
 *
 * The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution.
 * After that, you can write a DP solution if you want.
 */
public class GenerateSubsetsOfSet
{
    static String[] generate_all_subsets(String s)
    {
        List<String> results = new ArrayList<>();

        handleSubproblem(s, 0, "", results);

        return results.toArray(new String[results.size()]);
    }

    static void handleSubproblem(String input, int i, String partialResult, List<String> results)
    {
        // Base case
        if (i == input.length())
        {
            results.add(partialResult);
        }
        // Recursion case: process one element of input at a time, either including or excluding it
        else
        {
            // Make variants that EXCLUDE the current input char i from partial result
            handleSubproblem(input, i+1, partialResult, results);
            // Make variants that INCLUDE the current input char i from partial result
            handleSubproblem(input, i+1, partialResult + input.charAt(i), results);
        }
    }

    public static void main(String[] args)
    {
        String[] results = generate_all_subsets("xy");

        for (String result : results)
        {
            System.out.println(String.format("[%s]", result));
        }
    }
}
