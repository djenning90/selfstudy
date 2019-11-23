package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem784
{
    public List<String> letterCasePermutation(String S)
    {
        List<String> results = new ArrayList<>();

        permuteSubproblem(S, 0, new char[S.length()], results);

        return results;
    }

    void permuteSubproblem(String input, int i, char[] partialResult, List<String> results)
    {
        // Base case
        if (i == input.length())
        {
            results.add(new String(partialResult));
        }
        // Recursion case
        else
        {
            char c = input.charAt(i);

            if (Character.isDigit(c))
            {
                // Include digit, and permute starting with next char.
                partialResult[i] = c;
                permuteSubproblem(input, i+1, partialResult, results);
            }
            else
            {
                // Include as lowercase, and permute starting with next char.
                partialResult[i] = Character.toLowerCase(c);
                permuteSubproblem(input, i+1, partialResult, results);

                // Include as uppercase, and permute starting with next char.
                partialResult[i] = Character.toUpperCase(c);
                permuteSubproblem(input, i+1, partialResult, results);
            }
        }
    }

    public static void main(String[] args)
    {
        Problem784 p = new Problem784();

        List<String> results;

        results = p.letterCasePermutation("a1b2");
        for (String result : results)
        {
            System.out.println(result);
        }
    }
}
