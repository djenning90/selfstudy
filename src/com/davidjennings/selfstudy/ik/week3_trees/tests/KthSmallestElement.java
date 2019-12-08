package com.davidjennings.selfstudy.ik.week3_trees.tests;

import com.davidjennings.selfstudy.ik.week3_trees.practice.PostOrderTraversalWithoutRecursion;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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