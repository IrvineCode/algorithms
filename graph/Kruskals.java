import java.util.*;
import java.lang.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int src, dst, weight;

    public Edge(int src, int dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }

    @Override
    public String toString() {
        return String.format("%d-%d (%d)", src, dst, weight);
    }
};

class Kruskals {
    public static void main(String[] args) {
        Edge[] edges = { new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(2, 3, 4), new Edge(1, 3, 15),
                new Edge(0, 3, 5) };

        Kruskals k = new Kruskals(4, edges);
    }

    class Subset {
        int parent, rank;

        Subset(int p, int r) {
            parent = p;
            rank = r;
        }
    };

    int V; // no. of vertices
    Edge edge[]; // all edges
    Edge result[]; // selected edges
    Subset subsets[]; // all parent/rank information

    // find + path compression
    int find(int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    void Union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    Kruskals(int V, Edge[] edges) {
        this.V = V;
        this.edge = edges;
        this.result = new Edge[V-1];
        this.subsets = new Subset[V];

        for (int i = 0; i < V; ++i)
            subsets[i] = new Subset(i, 0);

        solve();
    }

    void solve() {
        int i = 0; // index for next edge;

        // 1. sort all edges. Be careful not to sort the "input" array!
        Arrays.sort(edge);

        for (int r = 0; r < V - 1;) {
            // Step 2: Pick the smallest edge.
            // And increment the index for next iteration
            Edge next_edge = edge[i++];

            int x = find(next_edge.src);
            int y = find(next_edge.dst);

            // If non-cycle,
            if (x != y) {
                // union!
                result[r++] = next_edge;
                Union(x, y);
            }
        }

        // print or return
        for (Edge e : result)
            System.out.println(e);
    }

}