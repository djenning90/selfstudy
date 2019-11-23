package com.davidjennings.selfstudy.ik.week1_sorting.tests;

public class Merge_Array1_into_Array2
{
    /**
     * Merge First Sorted Array Into Second Sorted Array
     *
     * Problem Statement:
     *
     * Given two arrays:
     *
     * 1) arr1 of size n, which contains n positive integers sorted in increasing order.
     *
     * 2) arr2 of size (2*n) (twice the size of first), which contains n positive integers sorted in increasing order
     *    in its first half. Second half of this array arr2 is empty. (Empty elements are marked by 0).
     *
     * Write a function that takes these two arrays, and merges the first one into second one, resulting in an
     * increasingly sorted array of (2*n) positive integers.
     */
    static void merge_first_into_second(int[] arr1, int[] arr2)
    {
        assert (arr2.length == arr1.length * 2);

        int src1 = arr1.length - 1, src2 = src1;
        int dest = arr2.length - 1;
        while (src1 >= 0 && src2 >= 0)
        {
            if (arr1[src1] > arr2[src2])
                arr2[dest--] = arr1[src1--];
            else
                arr2[dest--] = arr2[src2--];
        }

        while (src1 >= 0)
            arr2[dest--] = arr1[src1--];
        while (src2 >= 0)
            arr2[dest--] = arr2[src2--];
    }
}
