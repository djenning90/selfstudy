package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

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
