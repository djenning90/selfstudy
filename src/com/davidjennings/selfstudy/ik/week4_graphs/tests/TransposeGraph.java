package com.davidjennings.selfstudy.ik.week4_graphs.tests;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Given A Graph, Build A New One With Reversed Edges
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * Given a strongly connected directed graph G, containing n nodes and m edges, you have to build a new graph
 * containing n nodes, where edges are reversed than the original graph G.
 *
 *
 *
 * This is also called Transposing the graph.
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
 * There is only one argument pointing to a random node of the graph G.
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Return any node in the new graph.
 *
 *
 *
 * As input is a strongly connected graph, you will be able to visit all the nodes, given any random node.
 *
 *
 *
 * Also when we reverse all the edges of strongly connected graph it will remain strongly connected graph, hence
 * returning any one node is sufficient.
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
 * The first line of input should contain two space separated integers n and m, denoting no. of nodes and no. of
 * edges in input graph G. In next m lines, each line should contain two space separated integers fromNode and
 * toNode, denoting that there is an edge from fromNode to toNode in G.
 *
 *
 *
 * If n = 3, m = 3 and edges = [(1 -> 2), (2 -> 3), (3 -> 1)], then input should be:
 *
 *
 *
 * 3 3
 *
 * 1 2
 *
 * 2 3
 *
 * 3 1
 *
 *
 *
 * Output Format:
 *
 *
 *
 * There will be one line of output, containing a string "Wrong Answer!" OR "Correct Answer!", depending on the
 * evaluation of your solution’s output.
 *
 *
 *
 * For input n = 3, m = 3 and edges = [(1 -> 2), (2 -> 3), (3 -> 1)], if your solution is correct, output will be:
 *
 *
 *
 * Correct Answer!
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 315
 * Given graph does not contain multi edges (there will not be more than one edge connecting same pair of vertices in
 * the same direction) and self loops.
 * Each node contains distinct values.
 * 1 <= value stored in node <= n
 * You are not allowed to modify the given graph. Return completely new graph.
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
 * Any node of the graph where:
 *
 * For n = 3
 *
 * nodes = [1 2 3]
 *
 * edges = [(1 -> 2), (2 -> 3), (3 -> 1)]
 *
 *
 *
 * you could be given any node (1, 2 or 3) as input.
 *
 *
 *
 * Sample Output:
 *
 *
 *
 * Any node of the new graph where:
 *
 * For n = 3
 *
 * nodes = [1 2 3]
 *
 * edges = [(2 -> 1), (3 -> 2), (1 -> 3)]
 *
 *
 *
 * you could return any node as output.
 *
 *
 *
 * Notes:
 *
 * Maximum time allowed in interview: 20 Minutes.
 */
public class TransposeGraph
{
    /*
     * Complete the function below.
     */
    static Node build_other_graph(Node node)
    {
        Node origNode = node;
        boolean[] visited = new boolean[316];

        Node[] transposedNodes = new Node[316];

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(node);

        while (!q.isEmpty())
        {
            node = q.removeFirst();

            // Handle graph traversal
            if (!visited[node.val])
            {
                visited[node.val] = true;

                for (Node neighbor : node.neighbours)
                {
                    q.push(neighbor);
                }

                // Transpose node by adding new node edges in reverse.
                for (Node neighbor : node.neighbours)
                {
                    int newToVal = node.val;
                    int newFrVal = neighbor.val;

                    // Find or create transposed nodes represented by this edge.
                    if (transposedNodes[newToVal] == null)
                        transposedNodes[newToVal] = new Node(newToVal);
                    if (transposedNodes[newFrVal] == null)
                        transposedNodes[newFrVal] = new Node(newFrVal);

                    // Create transposed edge.
                    transposedNodes[newFrVal].neighbours.add(transposedNodes[newToVal]);
                }
            }
        }

        for (Node start : transposedNodes)
        {
            if (start != null)
                return start;
        }

        return new Node(origNode.val);
    }

    static class Node
    {
        Integer val;
        Vector<Node> neighbours = new Vector<Node>(0);

        Node(Integer _val)
        {
            val = _val;
            neighbours.clear();
        }
    }

    ;
    static HashMap<Integer, Node> reversed = new HashMap<Integer, Node>();

    static void helper_dfs(Node reversed_node)
    {
        reversed.put(reversed_node.val, reversed_node);
        int n = reversed_node.neighbours.size();
        for (int i = 0; i < n; i++)
        {
            if (reversed.containsKey(reversed_node.neighbours.get(i).val) == false)
            {
                helper_dfs(reversed_node.neighbours.get(i));
            }
        }
    }

    static HashMap<Integer, Node> helper_get_all_addresses_in_reversed_graph(Node reversed_node)
    {
        helper_dfs(reversed_node);
        return reversed;
    }

    static String helper(int graph_nodes, int[] graph_from, int[] graph_to)
    {

        int MAX_NODES = 315;

        HashMap<Integer, Node> original = new HashMap<Integer, Node>();
        for (int i = 1; i <= graph_nodes; i++)
        {
            original.put(i, new Node(i));
        }
        HashMap<Integer, Boolean> edges = new HashMap<Integer, Boolean>();
        int graph_edges = graph_from.length;
        for (int i = 0; i < graph_edges; i++)
        {
            original.get(graph_from[i]).neighbours.add(original.get(graph_to[i]));

            edges.put(MAX_NODES * (graph_from[i] - 1) + graph_to[i] - 1, true);
        }

        HashMap<Integer, Node> reversed =
            helper_get_all_addresses_in_reversed_graph(build_other_graph(original.get(1)));

        System.err.print("In returned graph: \n");
        for (Integer val : reversed.keySet())
        {
            System.err.print("Neighbours of node " + String.valueOf(val) + " = [");
            Node node = reversed.get(val);
            int n = node.neighbours.size();
            for (int i = 0; i < n; i++)
            {
                int _val = node.neighbours.get(i).val;
                System.err.print(String.valueOf(_val));
                if (i != n - 1)
                {
                    System.err.print(", ");
                }
            }
            System.err.print("]\n");
        }

        if (reversed.size() != graph_nodes)
        {
            System.err.print("Wrong answer because no of nodes in returned graph != no of nodes in original graph.\n");
            return "Wrong Answer!";
        }

        for (Integer val : reversed.keySet())
        {
            Node node = reversed.get(val);
            if (1 > val || val > graph_nodes)
            {
                System.err.print("Wrong answer because value of node is out of range.\n");
                return "Wrong Answer!";
            }
            if (original.get(val) == reversed.get(val))
            {
                System.err.print("Wrong answer because instead of creating new node, you have used node from original" +
                    " graph.\n");
                return "Wrong Answer!";
            }
            int n = node.neighbours.size();
            for (int i = 0; i < n; i++)
            {
                int _val = node.neighbours.get(i).val;
                int temp = MAX_NODES * (_val - 1) + val - 1;
                if (edges.containsKey(temp) == false)
                {
                    System.err.print("Wrong answer because returned graph contains edge that is not present in " +
                        "original graph.\n");
                    return "Wrong Answer!";
                }
                edges.remove(temp);
            }
        }
        if (edges.size() > 0)
        {
            System.err.print("Wrong answer because returned graph contains extra edge that is not present in original" +
                " graph\n");
            return "Wrong Answer!";
        }
        return "Correct Answer!";
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String res;
        String[] graph_nodesm = in.nextLine().split(" ");
        int graph_nodes = Integer.parseInt(graph_nodesm[0]);
        int graph_edges = Integer.parseInt(graph_nodesm[1]);

        int[] graph_from = new int[graph_edges];
        int[] graph_to = new int[graph_edges];

        for (int graph_i = 0; graph_i < graph_edges; graph_i++)
        {
            String[] graph_fromv = in.nextLine().split(" ");
            graph_from[graph_i] = Integer.parseInt(graph_fromv[0]);
            graph_to[graph_i] = Integer.parseInt(graph_fromv[1]);
        }

        res = helper(graph_nodes, graph_from, graph_to);
        bw.write(res);
        bw.newLine();

        bw.close();
    }
}
