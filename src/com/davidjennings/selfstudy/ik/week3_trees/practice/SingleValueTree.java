package com.davidjennings.selfstudy.ik.week3_trees.practice;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Single Value Tree
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * Given a binary tree, find the number of unival subtrees (the unival tree is a tree which has the same value for
 * every node in it).
 *
 *
 *
 * Input/Output Format For The Function:
 *
 *
 *
 * Input Format:
 *
 *
 *
 * There is only one argument named root denoting the root of the input tree.
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Return an integer denoting the number of unival subtrees in a given tree.
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
 * The first line of the input contains an integer noOfNodes, denoting the number of nodes of the tree.
 *
 * In the second line of the input, it contains noOfNodes integers, denoting the values of the nodes. Please make
 * sure there are no leading or trailing spaces and between two integers there must be a single space.
 *
 * In the third line of the input, it contains a single integer rootIndex, denoting the root of the tree. value of
 * rootIndex must be between 0 to noOfNodes-1.
 *
 * In the fourth line of the input, it contains a single integer noOfEdges, denoting the number of edges of the tree.
 *
 * Next noOfNodes-1 lines, each line contains parentNodeIndex, childNodeIndex, leftOrRightFlag separated by space.
 * Here parentNodeIndex and childNodeIndex are the node indexes ranging from 0 to noOfNodes-1. leftOrRighFlag is
 * either "L" or "R" (without quotes) denoting the relationship between parent and child, the left or right child
 * where "L" stands for left child and "R" stands for the right child.
 *
 *
 *
 * If noOfNodes= 6, values = [5, 5, 5, 5, 5, 5], root_index = 0, noOfEdges=5, tree = [ {Node: 0, LeftChild: 1,
 * RightChild: 2}, {Node: 1, LeftChild: 3, RightChild: 4}, {Node: 2, RightChild: 5}]:
 *
 *
 *
 * 6
 *
 * 5 5 5 5 5 5
 *
 * 0
 *
 * 5
 *
 * 0 1 L
 *
 * 0 2 R
 *
 * 1 3 L
 *
 * 1 4 R
 *
 * 2 5 R
 *
 *
 *
 * Output Format:
 *
 *
 *
 * A single integer denoting answer of the problem i.e. the number of unival subtrees in a given tree.
 *
 *
 *
 * For input, noOfNodes= 6, values = [5, 5, 5, 5, 5, 5], root_index = 0, noOfEdges=5, tree = [ {Node: 0, LeftChild:
 * 1, RightChild: 2}, {Node: 1, LeftChild: 3, RightChild: 4}, {Node: 2, RightChild: 5}], output will be:
 *
 *
 *
 * 6
 *
 *
 *
 * Constraints:
 *
 * 0 <= n <= 100000, where n denotes number of nodes of tree.
 * -1000000000 <= value of node <= 1000000000
 * 0 <= root_index <= n-1 where n denotes number of nodes of tree and root_index denotes root index of tree.
 * The solution should use only constant extra space.
 *
 *
 * Sample Test Cases:
 *
 *
 *
 * Sample Input 1:
 *
 *
 *
 * 6
 *
 * 5 5 5 5 5 5
 *
 * 0
 *
 * 5
 *
 * 0 1 L
 *
 * 0 2 R
 *
 * 1 3 L
 *
 * 1 4 R
 *
 * 2 5 R
 *
 *
 *
 * Sample Output 1:
 *
 *
 *
 * 6
 *
 *
 *
 * Explanation 1:
 *
 *
 *
 *
 *
 * ../../../../Desktop/Screen%20Shot%202019-06-26%20at%2010.48.5
 *
 *
 *
 * There are 6 nodes i.e. 6 subtrees and for each subtree, value is 5 for each node. Means each subtree of this tree
 * is a unival tree hence answer will be 6.
 *
 *
 *
 * Sample Input 2:
 *
 *
 *
 * 7
 *
 * 5 5 5 5 5 4 5
 *
 * 0
 *
 * 6
 *
 * 0 1 L
 *
 * 0 2 R
 *
 * 1 3 L
 *
 * 1 4 R
 *
 * 2 5 L
 *
 * 2 6 R
 *
 *
 *
 * Sample Output 2:
 *
 *
 *
 * 5
 *
 *
 *
 * Explanation 2:
 *
 *
 *
 *
 *
 * ../../../Desktop/Screen%20Shot%202019-06-25%20at%2011.11.55%2
 *
 *
 *
 * There are 7 nodes i.e. 7 subtrees. Left subtree has 3 nodes and all nodes values are identical. So, there is 3
 * single value tree in the left subtree of the given tree. Right subtree has also 3 nodes. But all values are not
 * identical. There are two leaves. So, 2 single value tree in the right subtree. As right subtree is not a single
 * value tee, the whole tree is not a single value tree either.
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
