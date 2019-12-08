package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllPathsOfTree
{
    static List<List<Integer>> allPathsOfABinaryTree(TreeNode root)
    {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root != null)
        {
            List<Integer> basePath = new ArrayList<>();
            basePath.add(root.val);
            partialSolution(root, basePath, results);
        }

        return results;
    }

    static void partialSolution(TreeNode node, List<Integer> partialSolution, List<List<Integer>> results)
    {
        // base case
        if (node.left_ptr == null && node.right_ptr == null)
        {
            results.add(new ArrayList<Integer>(partialSolution));
        }
        else
        {
            if (node.left_ptr != null)
            {
                partialSolution.add(node.left_ptr.val);
                partialSolution(node.left_ptr, partialSolution, results);
                partialSolution.remove(partialSolution.size()-1);
            }
            if (node.right_ptr != null)
            {
                partialSolution.add(node.right_ptr.val);
                partialSolution(node.right_ptr, partialSolution, results);
                partialSolution.remove(partialSolution.size()-1);
            }
        }
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

    public static void printPaths(List<List<Integer>> allPaths)
    {
        if (allPaths == null)
        {
            return;
        }
        int pathCount = allPaths.size();
        if (pathCount == 0)
        {
            return;
        }
        for (int i = 0; i < pathCount; i++)
        {
            List<Integer> currentPathValues = allPaths.get(i);
            for (int j = 0; j < currentPathValues.size(); j++)
            {
                if (j > 0) System.out.print(" ");
                System.out.print(currentPathValues.get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            TreeNode root = readBinaryTree();
            List<List<Integer>> result = allPathsOfABinaryTree(root);
            printPaths(result);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    static InputStream getIn()
    {
        String INPUT = "0\n" +
            "-1\n" +
            "0";
        return new ByteArrayInputStream(INPUT.getBytes(Charset.defaultCharset()));
    }}
