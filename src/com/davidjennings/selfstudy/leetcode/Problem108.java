package com.davidjennings.selfstudy.leetcode;

public class Problem108
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums)
    {
        return helper(nums, 0, nums.length-1);
    }

    public TreeNode helper(int[] nums, int start, int end)
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
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(nums, start, mid-1);
            node.right = helper(nums, mid+1, end);
            return node;
        }
    }

    public static void main(String[] args)
    {
        Problem108 p = new Problem108();

        TreeNode result = p.sortedArrayToBST(new int[] {-10,-3,0,5,9});

        int x = 0;
    }
}
