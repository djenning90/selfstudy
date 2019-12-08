package com.davidjennings.selfstudy.leetcode;


import java.util.*;

public class Problem102
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public Problem102()
    {
    }

    public List<List<Integer>> levelOrder(TreeNode root)
    {
        List<List<Integer>> results = new ArrayList<>();

        ArrayDeque<TreeNode> nodes = new ArrayDeque<TreeNode>();
        if (root != null)
            nodes.addLast(root);

        while (!nodes.isEmpty())
        {
            // We are always at the start of a level here, so get the level's size.
            int numNodesThisLevel = nodes.size();
            List<Integer> levelVals = new ArrayList<>(numNodesThisLevel);

            for (int i=0; i<numNodesThisLevel; i++)
            {
                TreeNode node = nodes.removeFirst();
                levelVals.add(node.val);

                if (node.left != null)
                    nodes.addLast(node.left);
                if (node.right != null)
                    nodes.addLast(node.right);
            }

            results.add(levelVals);
        }

        return results;
    }
}
