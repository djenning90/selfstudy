package com.davidjennings.selfstudy.leetcode;

import java.util.*;

/**
 * 323. Number of Connected Components in an Undirected Graph
 * Medium
 *
 * 533
 *
 * 18
 *
 * Favorite
 *
 * Share
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a
 * function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 * Example 2:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Output:  1
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 */
public class Problem323
{
    public int countComponents(int n, int[][] edges)
    {
        int components = 0;
        //Set<Integer> captured = new HashSet<>(n);
        Set<Integer> visited = new HashSet<>();

        // Convert edge list to vertex list
        Map<Integer, List<Integer>> nodeNeighbors = new HashMap<>();
        for (int i=0; i<edges.length; i++)
        {
            int v1 = edges[i][0];
            int v2 = edges[i][1];

            // Hook v1 to v2
            List<Integer> neighbors = nodeNeighbors.get(v1);
            if (neighbors == null)
            {
                neighbors = new ArrayList<>(1);
                nodeNeighbors.put(v1, neighbors);
            }
            neighbors.add(v2);

            // Hook v2 to v1
            neighbors = nodeNeighbors.get(v2);
            if (neighbors == null)
            {
                neighbors = new ArrayList<>(1);
                nodeNeighbors.put(v2, neighbors);
            }
            neighbors.add(v1);
        }

        // Add edgeless nodes
        for (int i=0; i<n; i++)
        {
            if (!nodeNeighbors.containsKey(i))
            {
                nodeNeighbors.put(i, new ArrayList<>());
            }
        }

        // Walk the graph, discovering nodes and marking as visited
        for (int start : nodeNeighbors.keySet())
        {
            if (!visited.contains(start))
            {
                components++;

                ArrayDeque<Integer> q = new ArrayDeque<>();
                q.push(start);

                while (!q.isEmpty())
                {
                    int node = q.pop();
                    for (int neighbor : nodeNeighbors.get(node))
                    {
                        if (!visited.contains(neighbor))
                        {
                            visited.add(neighbor);
                            q.push(neighbor);
                        }
                    }
                }
            }
        }

        return components;
    }

    public static void main(String[] args)
    {
        Problem323 p = new Problem323();
        int[][] edges;
        int result;

        edges = new int[3][];
        edges[0] = new int[] {0, 1};
        edges[1] = new int[] {1, 2};
        edges[2] = new int[] {3, 4};
        result = p.countComponents(5, edges);
        assert (result == 2);

        edges = new int[4][];
        edges[0] = new int[] {0, 1};
        edges[1] = new int[] {1, 2};
        edges[2] = new int[] {2, 3};
        edges[3] = new int[] {3, 4};
        result = p.countComponents(5, edges);
        assert (result == 1);

        edges = new int[3][];
        edges[0] = new int[] {2, 3};
        edges[1] = new int[] {1, 2};
        edges[2] = new int[] {1, 3};
        result = p.countComponents(4, edges);
        assert (result == 2);

        int x = 0;
    }
}
