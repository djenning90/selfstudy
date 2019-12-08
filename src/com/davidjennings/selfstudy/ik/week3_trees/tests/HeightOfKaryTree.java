package com.davidjennings.selfstudy.ik.week3_trees.tests;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HeightOfKaryTree
{
    static class TreeNode
    {
        //int val;														// To find height of tree, value stored in nodes does not matter. So in input also we are not given this field.
        Vector<TreeNode> children = new Vector<TreeNode>(0);
        TreeNode()
        {
            children.clear();
        }
    };


    static int maxHeight = 0;

    static int find_height(TreeNode root)
    {
        helper(root, 0);
        return maxHeight;
    }

    static void helper(TreeNode node, int heightSoFar)
    {
        // base case
        if (node.children == null || node.children.size() == 0)
        {
            if (heightSoFar > maxHeight)
                maxHeight = heightSoFar;
        }
        else
        {
            int maxThisHeight = 0;
            for (int i=0; i<node.children.size(); i++)
            {
                helper(node.children.get(i), heightSoFar+1);
            }
        }
    }


    static HashMap <Integer, TreeNode> address = new HashMap<Integer, TreeNode>();

    static TreeNode build_tree(int[] from, int[] to) {
        int N = from.length + 1;
        address.clear();												// Clear the variable.
        for (int i = 1; i <= N; i++)
        {
            address.put(i, new TreeNode());								// Create N nodes.
        }
        for (int i = 0; i < N - 1; i++)
        {
            address.get(from[i]).children.add(address.get(to[i]));		// Link the nodes. (Build the k-ary tree.)
        }
        return address.get(1);
    }

    HeightOfKaryTree() throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int res;
        int k;
        k = Integer.parseInt(in.nextLine().trim());

        int from_size = 0;
        from_size = Integer.parseInt(in.nextLine().trim());

        int[] from = new int[from_size];
        for(int i = 0; i < from_size; i++) {
            int from_item;
            from_item = Integer.parseInt(in.nextLine().trim());
            from[i] = from_item;
        }

        int to_size = 0;
        to_size = Integer.parseInt(in.nextLine().trim());

        int[] to = new int[to_size];
        for(int i = 0; i < to_size; i++) {
            int to_item;
            to_item = Integer.parseInt(in.nextLine().trim());
            to[i] = to_item;
        }

        TreeNode root = build_tree(from, to);

        res = find_height(root);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }

    public static void main(String[] args) throws IOException {

        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new HeightOfKaryTree();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }
}
