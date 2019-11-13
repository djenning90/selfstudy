package com.davidjennings.selfstudy.sorting;

public class SelectionSort extends SortBase
{
	int[] sort(int[] input)
	{
		if (input.length <=1) return input;
		int n = input.length;

		// Divide the array of n elements into a sorted part on the left and an unsorted part on the right.
		// Move through the first Go through all elements but the last one (always leaving an unsorted array to the right).
		for (int i=0; i<n-1; i++)
		{
			// Scan over elements from i+1 on, looking for the smallest value less than the value at i.
			int min = i;
			for (int k = i + 1; k < n; k++)
			{
				if (isLessThan(input, k, min))
					min = k;
			}

			// If we don't already have the smallest value, move new smallest value from the right side to the left.
			if (min != i)
				swap(input, i, min);
		}
		return input;
	}

	public static void main(String[] args)
	{
		new SelectionSort().tryTestCases();
	}
}
