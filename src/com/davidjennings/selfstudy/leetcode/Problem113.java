package com.davidjennings.selfstudy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem113
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    List<List<Integer>> results;

    public List<List<Integer>> pathSum(TreeNode root, int sum)
    {
        results = new ArrayList<>();

        if (root != null)
            helper(root, sum, new ArrayList<>());

        return results;
    }

    public void helper(TreeNode node, int sum, List<Integer> partialResult)
    {
        // base cases
        // leaf node - partial result is complete if sum matches
        if (node.left == null && node.right == null)
        {
            if (sum == node.val)
            {
                List<Integer> result = new ArrayList<>(partialResult);
                result.add(node.val);
                results.add(result);
            }
        }
        // recursion case
        else
        {
            if (node.left != null)
            {
                partialResult.add(node.val);
                helper(node.left, sum - node.val, partialResult);
                partialResult.remove(partialResult.size()-1);
            }

            if (node.right != null)
            {
                partialResult.add(node.val);
                helper(node.right, sum - node.val, partialResult);
                partialResult.remove(partialResult.size()-1);
            }
        }
    }
}
