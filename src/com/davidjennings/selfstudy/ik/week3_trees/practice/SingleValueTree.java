package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.util.ArrayList;
import java.util.Scanner;

public class SingleValueTree
{

    /*
        private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;
        }
    */

    private static int count;

    /*
        Finds the number of trees that are single-value (all nodes have same value).
    */
    static int findSingleValueTrees(TreeNode root)
    {
        count = 0;

        isSingleValueSubtree_withCount(root);

        return count;
    }

    static boolean isSingleValueSubtree_withCount(TreeNode root)
    {
        // Base cases
        if (root == null)
            return false;
        else if (root.left_ptr == null && root.right_ptr == null)
        {
            // Single node tree is single value.
            count++;
            return true;
        }
        // Recursive case (bottom-up DFS) to check children
        else
        {
            boolean leftIsSingleValue =  root.left_ptr  == null ? true : isSingleValueSubtree_withCount(root.left_ptr) && root.left_ptr.val == root.val;
            boolean rightIsSingleValue = root.right_ptr == null ? true : isSingleValueSubtree_withCount(root.right_ptr) && root.right_ptr.val == root.val;
            if (leftIsSingleValue && rightIsSingleValue)
            {
                count++;
                return true;
            }
        }

        return false;
    }


    private static class TreeNode
    {
        public int val;
        public TreeNode left_ptr;
        public TreeNode right_ptr;

        public TreeNode()
        {
            this.left_ptr = null;
            this.right_ptr = null;
        }

        public TreeNode(int val)
        {
            this.val = val;
            this.left_ptr = null;
            this.right_ptr = null;
        }
    }

    private static class BinaryTree
    {
        public class Edge
        {
            public int parentNodeIndex;
            public int childNodeIndex;
            public String leftRightFlag;

            public Edge() {}

            public Edge(int parentNodeIndex, int childNodeIndex, String leftRightFlag)
            {
                this.parentNodeIndex = parentNodeIndex;
                this.childNodeIndex = childNodeIndex;
                this.leftRightFlag = leftRightFlag;
            }
        }

        public int noOfNodes;
        public ArrayList<Integer> nodeValues;
        public int rootIndex;
        public int noOfEdges;
        public ArrayList<Edge> edges;
        public TreeNode root;

        public BinaryTree()
        {
            noOfNodes = 0;
            rootIndex = -1;
            noOfEdges = 0;
            nodeValues = new ArrayList();
            edges = new ArrayList();
            root = null;
        }

        public void readRawValues()
        {
            Scanner scan = new Scanner(System.in);

            noOfNodes = scan.nextInt();
            for (int i = 0; i < noOfNodes; i++)
            {
                int nodeVal = scan.nextInt();
                nodeValues.add(nodeVal);
            }

            rootIndex = scan.nextInt();

            noOfEdges = scan.nextInt();
            for (int i = 0; i < noOfEdges; i++)
            {
                int parentNodeIndex = scan.nextInt();
                int childNodeIndex = scan.nextInt();
                String leftRightFlag = scan.next();
                edges.add(new Edge(parentNodeIndex, childNodeIndex, leftRightFlag));
            }
        }

        public void buildFromRawValues()
        {
            if (noOfNodes == 0)
            {
                root = null;
                return;
            }

            ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
            for (int i = 0; i < noOfNodes; i++)
            {
                nodes.add(new TreeNode(nodeValues.get(i)));
            }

            for (int i = 0; i < noOfEdges; i++)
            {
                if (edges.get(i).leftRightFlag.equals("L"))
                {
                    nodes.get(edges.get(i).parentNodeIndex).left_ptr
                        = nodes.get(edges.get(i).childNodeIndex);
                }
                else
                {
                    nodes.get(edges.get(i).parentNodeIndex).right_ptr = nodes.get(edges.get(i).childNodeIndex);
                }
            }

            root = nodes.get(rootIndex);
            return;
        }
    }

    public static TreeNode readBinaryTree()
    {
        BinaryTree inputBinaryTree = new BinaryTree();
        inputBinaryTree.readRawValues();
        inputBinaryTree.buildFromRawValues();
        return inputBinaryTree.root;
    }


    public static void main(String args[])
    {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        new Thread(null, new Runnable()
        {
            public void run()
            {
                try
                {
                    solve();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static void solve()
    {
        try
        {
            TreeNode root = readBinaryTree();
            int result = findSingleValueTrees(root);
            System.out.println(result);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
