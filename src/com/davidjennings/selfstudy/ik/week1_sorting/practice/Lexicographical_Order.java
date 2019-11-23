package com.davidjennings.selfstudy.ik.week1_sorting.practice;

import java.util.*;

public class Lexicographical_Order
{
    /**
     * Lexicographical Order
     *
     * Problem Statement:
     *
     * You are given a string array named arr, of size N, containing KEYS and VALUES separated by a space.
     *
     * Your task is to find, for each unique KEY, the number of VALUES with that key and the VALUE with the highest
     * lexicographical order (also called alphabetical order OR dictionary order).
     *
     * (Have a look at the sample test cases for more clarity.)
     */
    static String[] solve(String[] arr)
    {
        Map<String, Entry> map = new HashMap<>();

        for (String input : arr)
        {
            // Separate key and value for this input
            String[] pair = input.split(" ");
            String key = pair[0];
            String val = pair[1];

            // If no entry exists, create first entry for val with count 1
            Entry entry = map.get(key);
            if (entry == null)
            {
                map.put(key, new Entry(val));
            }
            // Otherwise, update count in existing entry, and replace val if ours is greater
            else
            {
                entry.count++;
                if (val.compareTo(entry.hiLexVal) > 0)
                    entry.hiLexVal = val;
            }
        }

        String results[] = new String[map.keySet().size()];
        int i = 0;
        for (String key : map.keySet())
        {
            Entry entry = map.get(key);
            results[i++] = key + ":" + entry.count + "," + entry.hiLexVal;
        }

        return results;
    }

    static class Entry
    {
        int count = 1;
        String hiLexVal;

        Entry(String hiLexVal)
        {
            this.hiLexVal = hiLexVal;
        }
    }
}
