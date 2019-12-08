package com.davidjennings.selfstudy.ik.week3_trees.tests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BstFromSortedArray
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

    public static TreeNode build_balanced_bst(int[] a)
    {
        return build_balanced_bst(a, 0, a.length-1);
    }

    public static TreeNode build_balanced_bst(int[] a, int start, int end)
    {
        // Base case
        if (start > end)
        {
            return null;
        }
        // Recursion case
        else
        {
            int mid = (start+end)/2;
            TreeNode node = new TreeNode(a[mid]);
            node.left_ptr = build_balanced_bst(a, start, mid-1);
            node.right_ptr = build_balanced_bst(a, mid+1, end);
            return node;
        }
    }


    static boolean check_balanced_bst(TreeNode root, int l, int r, int[] a)
    {
        if (l > r && root == null)                              // If no value in [l, r] and tree is also empty.
        {
            return true;
        }
        else if (l > r && root != null)                         // If no value in [l, r] but tree contains something.
        {
            return false;
        }
        if (root == null)                                       // If some values in [l, r] but tree is empty.
        {
            return false;
        }

        int mid1 = l + (r - l) / 2;
        int mid2 = l + (r - l + 1) / 2;

        boolean valid1 = (root.val == a[mid1] && check_balanced_bst(root.left_ptr, l, mid1 - 1, a) && check_balanced_bst(root.right_ptr, mid1 + 1, r, a));
        if (valid1)                                             // actually we are doing valid1 || valid2 but when valid1 is true then no need to find valid2
        {
            return true;
        }
        if (mid1 == mid2)                                       // when odd no of elements in [l, r] then mid1 = mid2 so valid1 = valid2 and no need to find valid2.
        {
            return false;
        }
        return (root.val == a[mid2] && check_balanced_bst(root.left_ptr, l, mid2 - 1, a) && check_balanced_bst(root.right_ptr, mid2 + 1, r, a));
    }


    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a_size = 0;
        a_size = Integer.parseInt(in.nextLine().trim());

        int[] a = new int[a_size];
        for(int i = 0; i < a_size; i++) {
            int a_item;
            a_item = Integer.parseInt(in.nextLine().trim());
            a[i] = a_item;
        }

        TreeNode root = build_balanced_bst(a);

        if (check_balanced_bst(root, 0, a_size - 1, a))
        {
            bw.write("Valid Balanced BST");
        }
        else
        {
            bw.write("Invalid Balanced BST");
        }
        bw.newLine();

        bw.close();
    }}
