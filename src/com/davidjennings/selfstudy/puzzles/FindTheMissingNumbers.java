package com.davidjennings.selfstudy.puzzles;

import java.util.HashSet;
import java.util.Set;

public class FindTheMissingNumbers
{
    static void printMissingNumbers(int[] vals, int loval, int hival)
    {
        Set<Integer> valset = new HashSet<Integer>();
        for (int i=0; i<vals.length; i++)
        {
            valset.add(vals[i]);
        }

        int rs = -1;
        for (int val=loval; val<=hival; val++)
        {
            if (!valset.contains(val))
            {
                if (rs == -1)
                    rs = val;
            }
            else
            {
                if (rs != -1)
                {
                    printRange(rs, val-1);
                    rs = -1;
                }
            }
        }

        if (rs != -1)
            printRange(rs, hival);
    }

    public static void printRange(int start, int end)
    {
        if (start == end)
            System.out.println(start);
        else
            System.out.println(String.format("%d-%d", start, end));
    }


	public static void main(String[] args)
	{
		// write your code here
        printMissingNumbers(new int[] {6,7,8,10,12,13}, 1, 100);
	}
}
