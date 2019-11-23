package com.davidjennings.selfstudy.puzzles;

import java.util.Stack;

public class TowersOfHanoi
{
    static int step = 1;

    public static void moveTopDisc(Stack<Integer>[] spindles, int src, int dst)
    {
        // Get disc to move
        int disc = spindles[src].pop();

        // Make sure the move is valid (disc below, if any, must be larger).
        if (!spindles[dst].empty())
            assert(disc < spindles[dst].peek().intValue());

        // Move the disc.
        spindles[dst].push(disc);

        print(spindles);
    }

    public static void moveDiscs(Stack<Integer>[] spindles, int numToMove, int src, int dst, int spare)
    {
        // Case where there's nothing to do
        if (numToMove == 0)
            return;
        // Here's how to move one disc
        else if (numToMove == 1)
            moveTopDisc(spindles, src, dst);
        // Here's how to move all discs
        else
        {
            // Move all discs on top of this one to spare, using dst as the spare.
            moveDiscs(spindles, numToMove - 1, src, spare, dst);
            // Move the bottom disc from src to dst, ignoring spare.
            moveTopDisc(spindles, src, dst);
            // Move the discs we put on spare back to dst, using src as the spare.
            moveDiscs(spindles, numToMove - 1, spare, dst, src);
        }
    }


    static void print(Stack<Integer>[] spindles)
    {
        System.out.println(String.format("Step %d", step++));

        for (int i=0; i<spindles.length; i++)
        {
            System.out.print("-");

            for (Integer disc : spindles[i])
            {
                System.out.print(String.format("%d-", disc));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int n = 6;
        Stack<Integer>[] spindles = new Stack[3];
        for (int i=0; i<3; i++)
            spindles[i] = new Stack();

        // Set up problem with all discs on spindle 0.
        for (int i=n; i>0; i--)
            spindles[0].push(i);

        print(spindles);

        // Move all discs to spindle 1, using spindle 2 as spare.
        moveDiscs(spindles, n, 0, 1, 2);
    }
}
