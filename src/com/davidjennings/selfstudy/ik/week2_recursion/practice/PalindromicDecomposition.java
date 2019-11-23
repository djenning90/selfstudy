package com.davidjennings.selfstudy.ik.week2_recursion.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Palindromic Decomposition Of A String
 *
 * Problem Statement:
 *
 * A palindromic decomposition of string is a decomposition of the string into substrings, such that all those
 * substrings are valid palindromes.
 *
 * Given a string s, you have to find ALL possible palindromic decompositions of it.
 *
 * Note that string s itself is also a substring of s.
 *
 * The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution.
 * After that, you can write a DP solution if you want.
 */
public class PalindromicDecomposition
{
    static String[] generate_palindromic_decompositions(String s)
    {
        List<String> results = new ArrayList<>();

        subproblem(s, 0, new ArrayList<>(), results);

        String[] r = new String[results.size()];
        for (int i=0; i<results.size(); i++)
        {
            r[i] = results.get(i);
        }

        return r;
    }

    static void subproblem(String input, int i, List<String> partialResult, List<String> results)
    {
        int len = 0;
        for (String fragment : partialResult)
        {
            len += fragment.length();
        }

        // Base case: complete result
        if (len == input.length())
        {
            StringBuilder result = new StringBuilder();
            Iterator<String> iter = partialResult.iterator();
            while (iter.hasNext())
            {
                result.append(iter.next());
                if (iter.hasNext())
                    result.append("|");
            }
            results.add(result.toString());
        }
        // Recursive case
        else
        {
            // Look for biggest palindrome at start of string (or possibly whole string)
            int tryLen = 1;
            while (i + tryLen - 1< input.length())
            {
                if (isPalindrome(input,i, tryLen))
                {
                    partialResult.add(input.substring(i, i + tryLen));
                    subproblem(input, i + tryLen, partialResult, results);
                    partialResult.remove(partialResult.size() - 1);
                }
                tryLen++;
            }
        }
    }

    static boolean isPalindrome(String s, int i, int len)
    {
        if (s.length() == 0)
            return true;

        int p1 = i;
        int p2 = i + len - 1;
        while (p1 < p2)
        {
            if (s.charAt(p1) != s.charAt(p2))
                return false;
            p1++;
            p2--;
        }

        return true;
    }

    public static void main(String[] args)
    {
        String[] results = generate_palindromic_decompositions("abracadabra");

        for (String result : results)
        {
            System.out.println(result);
        }
    }
}
