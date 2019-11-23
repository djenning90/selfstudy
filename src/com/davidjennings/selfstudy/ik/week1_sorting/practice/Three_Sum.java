package com.davidjennings.selfstudy.ik.week1_sorting.practice;

import java.util.*;

public class Three_Sum
{
    /**
     * 3 Sum
     *
     * Problem Statement:
     *
     * Given an integer array arr of size N, find all magical triplets in it.
     *
     * Magical triplet is the group of 3 integers, whose sum is zero.
     *
     * Note that magical triplets may or may not be made of consecutive numbers in arr.
     */
    static String[] findZeroSum(int[] arr)
    {
        Arrays.sort(arr);
        Set<Triplet> results = new HashSet<>();

        for (int i = 0; i < arr.length; i++)
        {
            int val = arr[i];
            List<int[]> pairs = pairsSummingTo(-val, arr, i);
            for (int[] pair : pairs)
            {
                results.add(new Triplet(val, arr[pair[0]], arr[pair[1]]));
            }
        }

        String[] res = new String[results.size()];
        int i = 0;
        for (Triplet t : results)
        {
            res[i++] = t.a + "," + t.b + "," + t.c;
        }

        Arrays.sort(res);
        return res;
    }

    static List<int[]> pairsSummingTo(int val, int[] arr, int skipIndex)
    {
        List<int[]> results = new ArrayList<>();
        int n = arr.length;
        int left = skipIndex == 0 ? 1 : 0;
        int right = skipIndex == n - 1 ? n - 2 : n - 1;

        while (left < right)
        {
            if (arr[left] + arr[right] < val)
            {
                left++;
                if (left == skipIndex)
                    left++;
            }
            else if (arr[left] + arr[right] > val)
            {
                right--;
                if (right == skipIndex)
                    right--;
            }
            else
            {
                results.add(new int[]{left, right});
            }
        }

        return results;
    }

    static class Triplet
    {
        int a, b, c;

        Triplet(int a, int b, int c)
        {
            int[] vals = new int[]{a, b, c};
            Arrays.sort(vals);
            this.a = vals[0];
            this.b = vals[1];
            this.c = vals[2];
        }

        public boolean equals(Object other)
        {
            Triplet o = (Triplet) other;
            return (a == o.a && b == o.b && c == o.c);
        }

        public int hashCode()
        {
            // Values are all between -1000 and 1000. This creates guaranteed unique hash code.
            return (a + 1000) + (b + 1000) << 10 + (c + 1000) << 20;
        }
    }

    public static void main(String[] args)
    {
        String[] res = findZeroSum(new int[]{6, 10, 3, -4, 1, -6, 9});
        int i = 0;
    }
}
