package com.davidjennings.selfstudy.ik.week3_trees.tests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * Kth Smallest Element Of BST
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * Given a BST (binary search tree), of size N, containing integer elements, and an integer k, you have to find kth
 * smallest element of given BST.
 *
 *
 *
 * Input/Output Format For The Function:
 *
 *
 *
 * Input Format:
 *
 *
 *
 * There are two arguments in the input. First one is the root of the BST and second one is an integer k.
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Return an integer kmin, denoting kth smallest element of BST.
 *
 *
 *
 * Input/Output Format For The Custom Input:
 *
 *
 *
 * Input Format:
 *
 *
 *
 * The first line of input should contain an integer N, denoting size of input array arr. In next N lines, ith line
 * should contain an integer arr[i], denoting a value at index i of arr.
 *
 * Input BST will be constructed by inserting elements of array arr to an empty BST in order arr[0], arr[1], …,
 * arr[N-1].
 *
 *
 *
 * If n = 3, k = 3 and arr = [1, 2, 3], then input should be:
 *
 *
 *
 * 3
 *
 * 2
 *
 * 3
 *
 * 1
 *
 * 3
 *
 *
 *
 * Output Format:
 *
 *
 *
 * There will be one line of output, containing an integer kmin, denoting the result returned by solution function.
 *
 *
 *
 * For input n = 3, k = 3 and arr = [1, 2, 3], output will be:
 *
 *
 *
 * 3
 *
 *
 *
 * Constraints:
 *
 * 1 <= N <= 6000
 * 1 <= k <= N
 * -2 * 10^9 <= value stored in any node <= 2 * 10^9
 * BST need not to be balanced.
 * You are not allowed to alter the structure or data inside the given BST.
 *
 *
 * Sample Test Case:
 *
 *
 *
 * Sample Input:
 *
 *
 *
 * BST =
 *
 * 2 is the root node.
 *
 * 1 is 2's left child.
 *
 * 3 is 2's right child.
 *
 *
 *
 * k = 3
 *
 *
 *
 * Sample Output:
 *
 *
 *
 * 3
 *
 *
 *
 * Explanation:
 *
 *
 *
 * 3rd smallest element is 3.
 *
 *
 *
 * Notes:
 *
 * Maximum time allowed in interview: 20 Minutes.
 */
class Solution
{
    static class TreeNode
    {
        int val;
        TreeNode left_ptr;
        TreeNode right_ptr;

        TreeNode(int _val)
        {
            val = _val;
            left_ptr = null;
            right_ptr = null;
        }
    }


    enum Order
    {
        PRE,
        IN,
        POST
    }

    enum From
    {
        LEFT,
        RIGHT
    }

    static int kth_smallest_element(TreeNode root, int k)
    {
        int count = 0;

        Stack<TreeNode> parents = new Stack<>();
        Stack<From> froms = new Stack<>();

        TreeNode node = root;
        Order order = Order.PRE;

        while (node != null)
        {
            switch (order)
            {
                case PRE:
                    // If no left child, swing around.
                    if (node.left_ptr == null)
                    {
                        order = Order.IN;
                    }
                    // Otherwise, descend into left child.
                    else
                    {
                        parents.push(node);
                        froms.push(From.LEFT);
                        node = node.left_ptr;
                    }
                    break;

                case IN:
                    count++;
                    if (count == k)
                        return node.val;

                    // If no right child, swing up.
                    if (node.right_ptr == null)
                    {
                        order = Order.POST;
                    }
                    // Otherwise, descend into the right child.
                    else
                    {
                        parents.push(node);
                        froms.push(From.RIGHT);
                        node = node.right_ptr;
                        order = Order.PRE;
                    }
                    break;

                case POST:
                    if (parents.empty())
                    {
                        node = null;
                    }
                    else
                    {
                        node = parents.pop();
                        From from = froms.pop();

                        if (from == From.LEFT)
                            order = Order.IN;
                        else
                            order = Order.POST;
                    }
                    break;
            }
        }

        return -1;
    }


    static TreeNode bst_insert(TreeNode root, int val)
    {
        if (root == null)                                                // destination.
        {
            return new TreeNode(val);
        }
        if (val <= root.val)                                            // element is <= hence insert it in left
            // subtree.
        {
            root.left_ptr = bst_insert(root.left_ptr, val);                  // if root->left_ptr is null then new
            // TreeNode will be created and root->left_ptr is assigned its address else it will be assigned to the
            // same value as previouly stored.
        }
        else                                                            // element is > hence insert it in right
        // subtree.
        {
            root.right_ptr = bst_insert(root.right_ptr, val);            // if root->right_ptr is null then new
            // TreeNode will be created and root->right_ptr is assigned its address else it will be assigned to the
            // same value as previouly stored.
        }
        return root;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.nextLine().trim());

        TreeNode root = null;

        for (int i = 0; i < N; i++)
        {
            int data = Integer.parseInt(in.nextLine().trim());
            root = bst_insert(root, data);
        }

        int k = Integer.parseInt(in.nextLine().trim());

        int ans = kth_smallest_element(root, k);

        bw.write(String.valueOf(ans));
        bw.newLine();

        bw.close();
    }
}