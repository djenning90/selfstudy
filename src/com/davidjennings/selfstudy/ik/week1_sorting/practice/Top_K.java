package com.davidjennings.selfstudy.ik.week1_sorting.practice;

import java.util.Iterator;
import java.util.TreeSet;

public class Top_K
{
    /**
     * Top K
     *
     * Problem Statement:
     *
     * You are given an array of integers arr, of size n, which is analogous to a continuous stream of integers input
     * . Your task is to find K largest elements from a given stream of numbers.
     *
     * By definition, we don't know the size of the input stream. Hence, produce K largest elements seen so far, at
     * any given time. For repeated numbers, return them only once.
     *
     * If there are less than K distinct elements in arr, return all of them.
     *
     * Note:
     * - Don't rely on size of input array arr.
     * - Feel free to use built-in functions if you need a specific data-structure.
     */
    static int[] topK(int[] arr, int k)
    {
        int n = arr.length;

        // Add entries, keeping only the largest unique k entries by removing small ones past k.
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i=0; i<n; i++)
        {
            tree.add(arr[i]);
            if (tree.size() > k)
                tree.pollFirst();
        }

        int[] res = new int[tree.size()];
        int i = 0;
        Iterator<Integer> iter = tree.iterator();
        while (iter.hasNext())
        {
            res[i++] = iter.next();
        }

        return res;
    }
 }

