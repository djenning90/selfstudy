package com.davidjennings.selfstudy.ik.week1_sorting.tests;

public class Dutch_National_Flag
{
    /**
     * Dutch National Flag
     *
     * Problem Statement:
     *
     * You are given n balls. Each of these balls are of one the three colors: Red, Green and Blue. They are arranged
     * randomly in a line. Your task is to rearrange them such that all balls of the same color are together and
     * their collective color groups are in this order: Red balls first, Green balls next and Blue balls last.
     *
     * This combination of colors is similar to the Dutch National Flag, hence the problem name.
     *
     * This is a popular sorting problem.
     */
    public static void dutch_flag_sort(char[] balls)
    {
        int n = balls.length;

        int swapRedHere = 0;
        int swapBluHere = n-1;

        int cur = 0;
        while (cur <= swapBluHere)
        {
            // If current ball is red, swap with swapRedHere. We will get a green so move current.
            if (balls[cur] == 'R')
            {
                swap(balls, cur, swapRedHere++);
                cur++;
            }
            // If current ball is blue, move it to swapBluHere. But don't move current because it now has unknown ball..
            else if (balls[cur] == 'B')
            {
                swap(balls, cur, swapBluHere--);
            }
            // Otherwise we have a green. Leave it there because it will get pulled out when swapRedHere reaches it.
            else    // 'G'
            {
                cur++;
            }
        }
    }

    static void swap(char[] array, int i1, int i2)
    {
        char tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    public static void main(String[] args)
    {
//        dutch_flag_sort(new char[]{'R','G'});
        dutch_flag_sort(new char[]{'G','B','G', 'G', 'R', 'B', 'R', 'G'});
    }
}
