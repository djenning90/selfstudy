package com.davidjennings.selfstudy.ik.week2_recursion.practice;

/**
 * How Many Binary Search Trees With n Nodes?
 *
 * Problem Statement:
 *
 * Write a function that will return the number of binary search trees that can be constructed with n nodes.
 *
 * There may be other iterative solutions, but for the purpose of this exercise, please use recursive solution.
 *
 * The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution.
 * After that, you can write a DP solution if you want.
 *
 * Examples
 * 1 -> 1
 * 2 -> 2
 * 3 -> 5
 *
 * Explanation 3:
 * 1) root (node val = 3), root->left (node val = 2), root->left->left (node val = 1)
 * 2) root (node val = 3), root->left (node val = 1), root->left->right (node val = 2)
 * 3) root (node val = 1), root->right (node val = 2), root->right->right (node val = 3)
 * 4) root (node val = 1), root->right (node val = 3), root->right->left (node val = 2)
 * 5) root (node val = 2), root->left (node val = 1), root->right (node val = 3)
 *
 * If you keep doing this, it will form a series called Catalan numbers. One can simply lookup the formula for
 * Catalan numbers and write code for it. But that's not how we want to do this. We want to do this by understanding
 * the underlying recursion. The recursion is based on tree-topology only, as you can see by examples above, contents
 * of the nodes of the tree do not matter.
 */
public class HowManyBinarySearchTrees
{
    public static long how_many_BSTs(int n)
    {
        if (n <= 1)
        {
            return (1);
        }
        else
        {
            long sum = 0;
            long left, right;

            // there will be one value at the root, with whatever remains
            // on the left and right each forming their own subtrees.
            // Iterate through all the values that could be the root...
            for (int nRoot = 1; nRoot <= n; nRoot++)
            {
                // Left side has trees formed from all values before root
                left = how_many_BSTs(nRoot - 1);
                // Right side has trees formed from all values after root
                right = how_many_BSTs(n - nRoot);

                // number of possible trees with this root == left*right
                sum += left * right;
            }

            return sum;
        }
    }

    public static void main(String[] args)
    {
        long result = how_many_BSTs(3);

        System.out.println(result);
    }
}
