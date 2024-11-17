package module1_basic_data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem Description
 * <p>
 * Task. You are given a description of a rooted tree. Your task is to compute and output its height. Recall
 * that the height of a (rooted) tree is the maximum depth of a node, or the maximum distance from a
 * leaf to the root. You are given an arbitrary tree, not necessarily a binary tree.
 * <p>
 * Input Format. The first line contains the number of nodes 𝑛. The second line contains 𝑛 integer numbers
 * from −1 to 𝑛−1 — parents of nodes. If the 𝑖-th one of them (0 ≤ 𝑖 ≤ 𝑛−1) is −1, node 𝑖 is the root,
 * otherwise it’s 0-based index of the parent of 𝑖-th node. It is guaranteed that there is exactly one root.
 * It is guaranteed that the input represents a tree.
 * <p>
 * Constraints. 1 ≤ 𝑛 ≤ 10^5.
 * Output Format. Output the height of the tree.
 * <p>
 * Sample
 * Input:
 * 5
 * 4 -1 4 1 1
 * Output:
 * 3
 * Explanation
 * The input means that there are 5 nodes with numbers from 0 to 4, node 0 is a child of node 4, node 1
 * is the root, node 2 is a child of node 4, node 3 is a child of node 1 and node 4 is a child of node 1. To
 * see this, let us write numbers of nodes from 0 to 4 in one line and the numbers given in the input in
 * the second line underneath:
 * 0 1 2 3 4
 * 4 -1 4 1 1
 * Now we can see that the node number 1 is the root, because −1 corresponds to it in the second line.
 * Also, we know that the nodes number 3 and number 4 are children of the root node 1. Also, we know
 * that the nodes number 0 and number 2 are children of the node 4
 */
public class ComputeTreeHeight {

    // Helper class for fast input handling
    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        // Reads the next token as a string
        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        // Reads the next token as an integer
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static class TreeHeight {
        int n;            // Number of nodes in the tree
        int parent[];     // Parent array representing the tree
        int[] heights;    // Array to store precomputed heights of nodes

        // Reads input data
        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt(); // Number of nodes
            parent = new int[n];
            heights = new int[n];
            // Initialize heights array with -1 to indicate uncomputed heights
            Arrays.fill(heights, -1);
            // Read parent relationships for all nodes
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        /**
         * Computes the height of the tree using memoization to optimize repeated calculations.
         *
         * @return The height of the tree
         * <p>
         * Time Complexity: O(n)
         * Each node's height is computed once and stored for reuse. Iterating over all nodes
         * ensures that the computation is linear with respect to the number of nodes.
         * <p>
         * Space Complexity: O(n)
         * - The heights array stores one value per node: O(n)
         * - Recursive calls stack up to the maximum depth (tree height): O(h), where h ≤ n.
         */
        int computeHeight() {
            int maxHeight = 0; // Initialize max height

            // Iterate through all nodes to compute their height
            for (int vertex = 0; vertex < n; vertex++) {
                maxHeight = Math.max(maxHeight, computeNodeHeight(vertex));
            }

            return maxHeight;
        }

        /**
         * Recursively computes the height of a given node.
         *
         * @param node The index of the node
         * @return The height of the subtree rooted at the given node
         * <p>
         * Time Complexity: O(1) per node (amortized due to memoization)
         * Each node's height is computed only once and then reused.
         * <p>
         * Space Complexity: O(1) additional per call, with O(h) stack usage due to recursion.
         */
        int computeNodeHeight(int node) {
            // If height is already computed, return it
            if (heights[node] != -1) {
                return heights[node];
            }

            // If the node is the root, its height is 1
            if (parent[node] == -1) {
                heights[node] = 1;
            } else {
                // Otherwise, compute height as 1 + height of its parent
                heights[node] = 1 + computeNodeHeight(parent[node]);
            }

            return heights[node]; // Return the computed height
        }
    }

    static public void main(String[] args) throws IOException {
        // Run the program in a separate thread with increased stack size to handle deep recursion
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ComputeTreeHeight().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight(); // Create TreeHeight instance
        tree.read(); // Read input data
        System.out.println(tree.computeHeight()); // Compute and output the height of the tree
    }
}


