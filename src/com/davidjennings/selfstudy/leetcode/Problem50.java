package com.davidjennings.selfstudy.leetcode;

/**
 * 50. Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n(x^n).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class Problem50
{
    /**
     * We were asked to come up with an efficient recursive solution.
     */
    public double myPow(double x, int n)
    {
        boolean inverse = false;
        if (n < 0)
        {
            // Don't try to negate n here, because the calcPow() method will work just fine with negative n,
            // results will be treated the same as positive n. This lets us support the maximum negative int
            // value -2147483648, which cannot be negated (-n == n !).
            inverse = true;
        }

        double result = calcPow(x, n);

        if (inverse)
            return 1.0d/result;
        else
            return result;
    }

    /**
     * Recursively calculate x^n efficiently.
     */
    double calcPow(double x, int n)
    {
        // Fast exit cases for x
        if (x == 0.0d || x == 1.0d)
        return x;

        // Fast exit cases for n
        if (n == 0)
            return 1.0d;    // x^0 = 1.0
        if (n == 1 || n == -1)
            return x;       // x^1` = x (avoid needless recursion to both the odd and even cases)

        // For even powers, take advantage of x^2n = x^n * x^n (reduce problem size by half).
        if (n % 2 == 0)
        {
            double temp = calcPow(x, n >> 1);
            return temp * temp;
        }
        // For even powers, take advantage of x^n = x * x^(n-1) (reduce problem size by one).
        else
        {
            double temp = calcPow(x, n > 0 ? n-1 : n+1);
            return x * temp;
        }
    }

    public static void main(String[] args)
    {
        //double result = new Problem50().myPow(2.00000,-2147483648);
        double result = new Problem50().myPow(2.00000,-2);
        int x = 0;
    }
}

