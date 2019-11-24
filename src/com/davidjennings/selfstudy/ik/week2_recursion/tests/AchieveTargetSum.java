package com.davidjennings.selfstudy.ik.week2_recursion.tests;

/**
 * Possible To Achieve Target Sum?
 *
 * Problem Statement:
 *
 * Given an array of integers arr and a target integer value k, determine whether there exists a non-empty group of
 * numbers in arr such that their sum equals k. Numbers in the group need not be contiguous elements of arr.
 *
 * The purpose of this problem is to learn recursion and not DP. Write at least one recursive solution. Feel free to
 * write a DP solution after that.
 *
 * Input Format:
 *
 * Function has two arguments: arr and k.
 *
 * Output Format:
 *
 * Function must return a boolean value.
 *
 * Constraints:
 * 1 <= size of arr <= 18
 * -10^17 <= arr[i], k <= 10^17
 *
 * Sample Input 1:
 *
 * arr = [2 4 8]
 * k = 6
 *
 * Sample Output 1:
 *
 * True
 *
 * Because arr[0] + arr[1] = 6.
 *
 *
 * Sample Input 2:
 *
 * arr = [2 4 6]
 * k = 5
 *
 * Sample Output 2:
 *
 * False
 *
 * Because no group of numbers in arr sums up to 5.
 *
 *
 * Custom Input Format:
 *
 * First line of input contains integer n, size of arr.
 * Next n lines contain integer elements of arr.
 * Next line contains integer k.
 *
 * If arr = [2, 4, 8] and k = 6, custom input would be:
 *
 * 3
 * 2
 * 4
 * 8
 * 6
 *
 * Custom Output Format:
 *
 * Valid output consists of a single character on a single line: 0 for False or 1 for True.
 *
 * For input arr = [2, 4, 8] and k = 6, correct output would be:
 *
 * 1
 *
 * Notes: Maximum time allowed in interview: 20 Minutes.
 */
public class AchieveTargetSum
{
    static boolean result;
    static boolean check_if_sum_possible(long[] arr, long k)
    {
        result = false;
        handleSubproblem(arr, 0, 0, 0, k);
        return result;
    }

    static void handleSubproblem(long[] input, int i, long runningTotal, int numNums, long target)
    {
        // Base case
        if (runningTotal == target && numNums > 0)
        {
            result = true;
        }
        // At end
        else if (i == input.length)
        {
            return;
        }
        // Recursion case
        else
        {
            // Exclusion
            handleSubproblem(input, i+1, runningTotal, numNums, target);
            // Inclusion
            handleSubproblem(input, i+1, runningTotal + input[i], numNums+1, target);
        }
    }

    public static void main(String[] args)
    {
        Boolean result = check_if_sum_possible(new long[]{-2,-3, 1}, -4);
        System.out.println(result.toString());
    }
}
