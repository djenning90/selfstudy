package com.davidjennings.selfstudy.ik.week1_sorting.practice;

public class Group_the_numbers
{
    /**
     * Group the numbers
     *
     * Problem Statement:
     *
     * You are given an integer array arr, of size n. Group and rearrange them (in-place) into evens and odds in such
     * a way that group of all even integers appears on the left side and group of all odd integers appears on the
     * right side.
     */
    static int[] solve(int[] arr)
    {
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        while (i < j)
        {
            // Skip cases
            if (isEven(arr[i]))
            {
                i++;
            }
            else if (isOdd(arr[j]))
            {
                j--;
            }
            // Swap case
            else if (isOdd(arr[i]) && isEven(arr[j]))
            {
                swap(arr, i, j);
                i++;
                j--;
            }
            // Already in place case
            else
            {
                i++;
                j--;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean isOdd(int n) { return n % 2 == 1; }

    private static boolean isEven(int n) { return n % 2 == 0; }
}

