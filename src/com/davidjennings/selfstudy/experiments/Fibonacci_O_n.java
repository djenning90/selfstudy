package com.davidjennings.selfstudy.experiments;

public class Fibonacci_O_n
{
    static int addseq(int n, int b1, int b2)
    {
        int res;
        if (n == 0)
            res = b1;
        else
            res = addseq(n-1, b2, b1 + b2);

        System.out.println(String.format("%d,%d,%d -? %d", n, b1, b2, res));

        return res;
    }

    public static void main(String[] args)
    {
        int res = addseq(7, 0, 1);
    }
}
