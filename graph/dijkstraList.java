import java.util.*;

//[0, 4, 12, 19, 21, 11, 9, 8, 14]

public class dijkstraList {
    public static void main(String[] args) {
        Graph g = new Graph(9);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 8, 2);
        g.addEdge(7, 6, 1);
        g.addEdge(7, 8, 7);
        g.addEdge(6, 8, 6);
        g.addEdge(6, 5, 2);
        g.addEdge(3, 5, 14);
        g.addEdge(3, 4, 9);
        g.addEdge(4, 5, 10);

        int[] dist = Dijkstra.dijkstra(0, g.edges); // 3 - start point

        System.out.println(Arrays.toString(dist));
    }
}

class Edge {
    int v;
    int w;

    Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

class Graph {
    List<Edge>[] edges;

    Graph(int v) {
        edges = new ArrayList[v];
    }

    void addEdge(int u, int v, int w) {
        if (edges[u] == null)
            edges[u] = new ArrayList<Edge>();
        if (edges[v] == null)
            edges[v] = new ArrayList<Edge>();
        edges[u].add(new Edge(v, w));
        edges[v].add(new Edge(u, w));
    }
}

class Dijkstra {

    static int findU(int[] dist, boolean[] completedSet) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] < min && !completedSet[i]) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static int[] dijkstra(int start, List<Edge>[] edges) {
        boolean[] completedSet;
        int[] dist;
        int V = edges.length;

        completedSet = new boolean[V];
        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < V; i++) {

            int u = findU(dist, completedSet);
            if (u < 0)
                break;
            completedSet[u] = true;

            if (edges[u] != null)
                for (Edge e : edges[u]) {
                    if (!completedSet[e.v] && dist[e.v] > dist[u] + e.w) {
                        dist[e.v] = dist[u] + e.w;
                    }
                }
        }

        return dist;
    }
}