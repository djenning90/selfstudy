package com.davidjennings.selfstudy.sorting;

public class InsertionSort extends SortBase
{
	int[] sort(int[] input)
	{
		if (input.length <=1) return input;
		int n = input.length;

		// Sorted elements are on the left, unsorted elements are on the right.
		// Iterate over n input elements from 0 to n-1. For each element, remove it from the array,
		// find the location where it belongs on the left and insert it into the growing sorted array.
		for (int i=1; i < n; i++)
		{
			// Consider all values from i to the beginning moving leftward.
			int j = i;
			// If value to the right is smaller, swap with current.
			while (j > 0 && isLessThan(input, j, j-1))
			{
				swap(input, j, j-1);
				// Complete for all elements.
				j--;
			}
		}

		return input;
	}

	public static void main(String[] args)
	{
		new InsertionSort().tryTestCases();
	}
}
