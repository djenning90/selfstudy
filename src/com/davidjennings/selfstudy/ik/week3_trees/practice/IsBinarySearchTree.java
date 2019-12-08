package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class IsBinarySearchTree
{
    /*
        private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;
        }
    */

    /*
        Complete the function below
    */
    static boolean isBST(TreeNode root)
    {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean isBST(TreeNode node, int min, int max)
    {
        // base cases
        if (node == null)
            return true;
        if (node.val < min || node.val > max)
            return false;

        // recursive cases
        if (!isBST(node.left_ptr, min, node.val))
            return false;
        if (!isBST(node.right_ptr, node.val, max))
            return false;
        return true;
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
            Scanner scan = new Scanner(getIn());

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

    public static void main(String[] args)
    {
        try
        {
            TreeNode root = readBinaryTree();
            boolean result = isBST(root);
            System.out.println(result ? "1" : "0");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    static InputStream getIn()
    {
        String INPUT = "8\n" +
            "100 20 130 140 135 132 150 145\n" +
            "0\n" +
            "7\n" +
            "0 1 L\n" +
            "0 2 R\n" +
            "2 3 R\n" +
            "3 4 L\n" +
            "4 5 L\n" +
            "3 6 R\n" +
            "6 7 L";
        return new ByteArrayInputStream(INPUT.getBytes(Charset.defaultCharset()));
    }
}

