package com.davidjennings.selfstudy.ik.week1_sorting.practice;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Merge_K_sorted_arrays
{
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Merge_K_sorted_arrays
     *
     * Problem Statement:
     *
     * This is a popular facebook problem.
     *
     * Given K sorted arrays arr, of size N each, merge them into a new array res, such that res is a sorted array.
     *
     * Assume N is very large compared to K. N may not even be known. The arrays could be just sorted streams, for instance, timestamp streams.
     *
     * All arrays might be sorted in increasing manner or decreasing manner. Sort all of them in the manner they appear in input.
     *
     * Note:
     * - Repeats are allowed.
     * - Negative numbers and zeros are allowed.
     * - Assume all arrays are sorted in the same order. Preserve that sort order in output.
     * - It is possible to find out the sort order from at least one of the arrays.
     */
    int[] mergeArrays_4(int[][] arr)
    {
        int K = arr.length;
        int N = arr[0].length;

        // Add nodes with first elem from each stream.
        PriorityQueue<Node> pq = getQueue(arr);
        for (int i = 0; i < K; i++)
        {
            // Node identifies both location and value
            pq.add(new Node(i, 0, arr[i][0]));
        }

        int[] result = new int[K * N];

        // Sweep across the streams, taking out the minmax value, which is guaranteed to be present.
        for (int r = 0; r < K * N; r++)
        {
            // Grab the minmax value and add to result
            Node minmax = pq.remove();
            result[r] = minmax.v;

            // Find next entry in same stream
            int k = minmax.k;
            int n = minmax.n + 1;

            // Queue next value from the same stream.
            if (n < N)
                pq.add(new Node(k, n, arr[k][n]));
        }

        return result;
    }

    PriorityQueue<Node> getQueue(int[][] arr)
    {
        int K = arr.length;
        int N = arr[0].length;

        boolean asc = true;
        for (int i = 0; i < K; i++)
        {
            if (arr[i][0] > arr[i][N - 1])
            {
                asc = false;
                break;
            }
        }

        if (asc)
            return new PriorityQueue<>(K * N);
        else
            return new PriorityQueue<>(K * N, Collections.reverseOrder());
    }

    static class Node implements Comparable<Node>
    {
        final int k;
        final int n;
        final int v;

        Node(int k, int n, int v)
        {
            this.k = k;
            this.n = n;
            this.v = v;
        }

        public int compareTo(Node other)
        {
            return this.v - other.v;
        }
    }

    static int[] mergeArrays_3(int[][] arr)
    {
        int K = arr.length;
        int N = arr[0].length;

        int sign = 1;
        for (int i = 0; i < K; i++)
        {
            if (arr[i][0] > arr[i][N - 1])
            {
                sign = -1;
                break;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(K * N);

        for (int n = 0; n < N; n++)
        {
            for (int k = 0; k < K; k++)
            {
                pq.add(sign * arr[k][n]);
            }
        }

        int[] result = new int[K * N];
        for (int r = 0; r < K * N; r++)
        {
            result[r] = sign * pq.remove();
        }

        return result;
    }

    static int[] mergeArrays_2(int[][] arr)
    {
        int K = arr.length;
        int N = arr[0].length;

        int result[] = new int[K * N];
        int r = 0;

        boolean asc = true;
        for (int i = 0; i < K; i++)
        {
            if (arr[i][0] > arr[i][N - 1])
            {
                asc = false;
                break;
            }
        }

        int minmax = asc ? 1000001 : -1000001;
        int[] ixs = new int[K];
        for (int i = 0; i < K; i++)
        {
            ixs[i] = 0;
        }

        while (r < K * N)
        {
            // Find best = current index with least value
            int best = -1;

            for (int i = 0; i < K; i++)
            {
                // Consider only remaining streams
                if (ixs[i] < N)
                {
                    // Find best value (max or min)
                    if (asc)
                    {
                        if (arr[i][ixs[i]] < minmax)
                        {
                            minmax = arr[i][ixs[i]];
                            best = i;
                        }
                    }
                    else
                    {
                        if (arr[i][ixs[i]] > minmax)
                        {
                            minmax = arr[i][ixs[i]];
                            best = i;
                        }
                    }

                    // Consume value, increment index
                    result[r++] = arr[best][ixs[best]];
                    ixs[best]++;
                }
            }
        }

        return result;
    }

    /*
     * Complete the mergeArrays function below.
     */
    static int[] mergeArrays(int[][] arr)
    {
        int K = arr.length;
        int N = arr[0].length;

        int result[] = new int[K * N];

        int sign = 1;
        for (int i = 0; i < K; i++)
        {
            if (arr[i][0] > arr[i][N - 1])
            {
                sign = -1;
                break;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(K);
        for (int i = 0; i < K; i++)
        {
            for (int j = 0; j < N; j++)
            {
                pq.add(sign * arr[i][j]);
            }
        }

        for (int i = 0; i < K * N; i++)
        {
            result[i] = sign * pq.remove();
        }

        return result;
    }
}

