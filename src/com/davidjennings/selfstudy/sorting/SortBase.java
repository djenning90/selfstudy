package com.davidjennings.selfstudy.sorting;

import java.util.Arrays;

public abstract class SortBase
{
    private int numSwaps, numCompares;

    abstract int[] sort(int[] input);

    void swap(int[] array, int i1, int i2)
    {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
        numSwaps++;
    }

    boolean isLessThan(int[] array, int i1, int i2)
    {
        numCompares++;
        return array[i1] < array[i2];
    }

    boolean isGreaterThan(int[] array, int i1, int i2)
    {
        numCompares++;
        return array[i1] > array[i2];
    }

    private String toString(int[] input)
    {
        StringBuilder result = new StringBuilder("[");
        for (int i=0; i< input.length; i++)
        {
            if (i != input.length-1)
                result.append(input[i]).append(", ");
            else
                result.append(input[i]).append("]");
        }

        return result.toString();
    }

    public void tryTestCase(int[] input, String msg)
    {
        numSwaps = 0;
        numCompares = 0;

        System.out.println(String.format("==> %s", msg));
        int[] check = Arrays.copyOf(input, input.length);
        Arrays.sort(check);

        System.out.println(String.format("Input:   %s size: %d", toString(input), input.length));

        int[] sorted = sort(input);
        System.out.println(String.format("Sorted:  %s", toString(sorted)));

        if (Arrays.equals(check, sorted))
            System.out.println(String.format("Correct! (%d compares, %d swaps)", numCompares, numSwaps));
        else
            System.out.println("FAIL! It's not sorted.");

        System.out.println();
    }

    public void tryTestCases()
    {
        tryTestCase(new int[]{17, 4, 5, 1, 22, 3, 8, 4, 21}, "Mixed data");
        tryTestCase(new int[]{1, 3, 4, 4, 5, 8, 17, 21, 22}, "Already sorted data");
    }
}
