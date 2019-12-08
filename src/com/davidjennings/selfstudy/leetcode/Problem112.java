package com.davidjennings.selfstudy.leetcode;

public class Problem112
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum)
    {
        // Base case
        if (root == null)
            return false;
        return helper(root, sum);
    }

    public boolean helper(TreeNode node, int sum)
    {
        // base cases
        // leaf node
        if (node.left == null && node.right == null)
        {
            return node.val == sum;
        }
        // recursion case
        else
        {
            boolean ok;
            if (node.left != null)
            {
                ok = helper(node.left, sum - node.val);
                if (ok)
                    return true;
            }

            if (node.right != null)
            {
                ok = helper(node.right, sum - node.val);
                if (ok)
                    return true;
            }
        }

        return false;
    }
}
