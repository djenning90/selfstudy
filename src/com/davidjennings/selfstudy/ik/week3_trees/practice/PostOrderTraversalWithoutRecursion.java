package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Post-order Traversal of a Tree Without Recursion
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * Write a function to traverse a binary tree Post-order, without using recursion.
 *
 *
 *
 * e.g. for a tree that's
 *
 * ../../../../../Desktop/Screen%20Shot%202019-01-20%20at%2010.4
 *
 *
 *
 * Return:
 *
 * [4, 5, 2, 6, 7, 3, 1]
 *
 *
 *
 * Input/Output Format For The Function:
 *
 *
 *
 * Input format:
 *
 *
 *
 * There is only one argument named root denoting the root of the input tree.
 *
 *
 *
 * Output format:
 *
 *
 *
 * Return an array containing the node values in post-order traversal of the tree.
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
 * line number 1: <noOfNodes> denoting number of nodes of the tree.
 *
 * line number 2: <noOfNdoes space seprated integers> denoting the values of the nodes. Please make sure there are
 * not leading or trailing spaces and between two integers there must be a single space.
 *
 * line number 3: <rootIndex> denoting the root of the tree. value of rootIndex must be between -1 to noOfNodes-1
 *
 * line number 4: <noOfEdges> denoting the number of edges of the tree
 *
 * next noOfEdges line: <parentNodeIndex><space><childNodeIndex><space><leftOrRightFlag>
 *
 * Here <parentNodeIndex> and <childNodeIndex> are the node indexes ranging from 0 to noOfNodes-1. <leftOrRighFlag>
 *     is either "L" or "R" (without quotes) denoting the left or right child where "L" stands for left child and "R"
 *     stands for right child.
 *
 *
 *
 * Raw input of sample:
 *
 * 5
 *
 * 1 2 3 4 5
 *
 * 0
 *
 * 4
 *
 * 0 1 L
 *
 * 0 2 R
 *
 * 1 3 L
 *
 * 1 4 R
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Space separated integer denoting the values in postorder traversal.
 *
 *
 *
 * Raw output of sample:
 *
 * 4 5 2 3 1
 *
 *
 *
 *
 *
 * Constraints:
 *
 *
 *
 * 0 <= number of nodes <= 100000
 * 1 <= values stored in the nodes <= 10^9
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
 * ../../../../../Desktop/Screen%20Shot%202019-01-20%20at%2010.5
 *
 *
 *
 * Sample Output:
 *
 *
 *
 * 4 5 2 3 1
 *
 *
 *
 * Explanation:
 *
 *
 *
 * There are 5 nodes in the tree. As post-order traversal sequence is left_node -> right_node -> parent_node, so from
 * root (1) it goes to left node (2). Left node has two children. So, it goes to left (4) again which is a leaf node.
 * Print the content of this node. Go to parent’s right (5) node which is also a leaf node. Print the content and go
 * back to parent (2) node, print the content and go back to its parent (1). Repeat this process for right side and
 * print the root node (1) at last.
 *
 *
 *
 * Tree node structure:
 *
 *
 *
 * Class TreeNode {
 *
 * int val;
 *
 * TreeNode left_ptr;
 *
 * TreeNode right_ptr;
 *
 * }
 */
public class PostOrderTraversalWithoutRecursion
{
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

    static int[] postorderTraversal(TreeNode root)
    {
        ArrayList<Integer> results = new ArrayList<>();

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
                    // Record the post-order node and exit to parent.
                    results.add(node.val);
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

        int[] r = new int[results.size()];
        for (int i=0; i<results.size(); i++)
            r[i] = results.get(i);
        return r;
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

    public static void printArray(int[] result)
    {
        if (result == null)
        {
            System.out.println();
            return;
        }
        for (int i = 0; i < result.length; i++)
        {
            if (i > 0)
            {
                System.out.print(" ");
            }
            System.out.print(result[i]);
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        try
        {
            TreeNode root = readBinaryTree();
            int[] result = postorderTraversal(root);
            printArray(result);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    static InputStream getIn()
    {
        String INPUT =
            "5\n" +
            "\n" +
            "1 2 3 4 5\n" +
            "\n" +
            "0\n" +
            "\n" +
            "4\n" +
            "\n" +
            "0 1 L\n" +
            "\n" +
            "0 2 R\n" +
            "\n" +
            "1 3 L\n" +
            "\n" +
            "1 4 R";

//        INPUT =
//            "4\n" +
//                "1 2 3 4\n" +
//                "0\n" +
//                "3\n" +
//                "0 1 L\n" +
//                "1 2 R\n" +
//                "2 3 L";

        return new ByteArrayInputStream(INPUT.getBytes(Charset.defaultCharset()));
    }
}
