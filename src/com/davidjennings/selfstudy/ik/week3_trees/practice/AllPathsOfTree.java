package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Print All Paths of a Tree
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * Given a binary tree. Returns all the paths from root to leaf of the tree. Each path will consist of a list of
 * integer values denoting nodes traversed for that path. e.g. for the tree,
 *
 *
 *
 *
 *
 *
 *
 * Return a list which contains the following paths:
 *
 * 1 2 4
 *
 * 1 2 5
 *
 * 1 3 6
 *
 * 1 3 7
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
 * Return a list of integer lists.
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
 * → line number 1: noOfNodes denoting number of nodes of the tree.
 *
 *
 *
 * → line number 2: noOfNodes space separated integers denoting the values of the nodes. Please make sure there are
 * no leading or trailing spaces and between two integers there must be a single space.
 *
 *
 *
 * → line number 3: rootIndex denoting the root of the tree. value of rootIndex must be between -1 to noOfNodes-1
 *
 *
 *
 * → line number 4: noOfEdges denoting the number of edges of the tree
 *
 *
 *
 * → next noOfEdges line: parentNodeIndex<space>childNodeIndex<space>leftOrRightFlag. Here parentNodeIndex and
 * childNodeIndex are the node indexes ranging from 0 to noOfNodes-1. leftOrRightFlag is either "L" or "R" (without
 * quotes) denoting the left or right child where "L" stands for left child and "R" stands for right child.
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Space separated integer denoting the node values in a path. If there are K paths, print K lines.
 *
 *
 *
 * Constraints:
 *
 *
 *
 * 0 <= noOfNodes <= 10^5
 * noOfEdges = noOfNodes - 1
 * -10^9 <= values stored in the nodes <= 10^9
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
 * For the function:
 *
 *
 *
 *
 *
 *
 *
 * For the custom input:
 *
 *
 *
 * 1
 *
 * 5
 *
 * 10 20 30 40 50
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
 * Sample Output:
 *
 *
 *
 * For the function:
 *
 *
 *
 * ret = [ [10,20,40], [10,20,50], [10,30] ]
 *
 *
 *
 * For the custom output:
 *
 *
 *
 * 10 20 40
 *
 * 10 20 50
 *
 * 10 30
 *
 *
 *
 * Explanation:
 *
 *
 *
 * There are 3 paths in the tree.
 *
 * The leftmost path contains values: 10 -> 20 -> 40
 *
 * The rightmost path contains values: 10 -> 30
 *
 * The other path contains values: 10 -> 20 -> 50
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
 *
 *
 *
 * Notes:
 *
 *
 *
 * → (Order of paths in output does not matter. Hence for sample input,
 *
 * 10 30
 *
 * 10 20 50
 *
 * 10 20 40
 *
 * will also be considered as correct answer.
 */
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
