package com.davidjennings.selfstudy.sorting;

public class BubbleSort extends SortBase
{
    int[] sort(int[] input)
    {
        if (input.length <=1) return input;
        int n = input.length;

        // Outer loop over all elements in the input. The right half of the array will hold the
        // highest sorted values, growing to the left until all are sorted.
        for (int i=1; i < n; i++)
        {
            boolean swapped = false;

            // The inner "bubbling" loop walks all elements from first and up to but not including
            // first sorted element, swapping out of order values along the way. Final result of each
            // inner loop is the highest value in the elements up to n-1 ends up stored at n-1.
            for (int j=0; j<n-1; j++)
            {
                if (isGreaterThan(input, j, j+1))
                {
                    swapped = true;
                    swap(input, j, j + 1);
                }
            }

            // Optimization to end if any full pass discovers the list is fully sorted. This gives
            // the algorithm O(N) time complexity when the list is already sorted.
            if (!swapped)
                break;
        }

        return input;
    }

    public static void main(String[] args)
    {
        new BubbleSort().tryTestCases();
    }
}
