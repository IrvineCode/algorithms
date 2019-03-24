import java.util.*;

//[0, 4, 12, 19, 21, 11, 9, 8, 14]

public class dijkstraListPq {
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

        int[] dist = (new Dijkstra(10, g.edges)).solve(0); // 0 - start point

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

class Vertex {
    int v;
    int dist;

    Vertex(int v, int dist) {
        this.v = v;
        this.dist = dist;
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
    boolean[] completedSet;
    int[] dist;
    int V;
    PriorityQueue<Vertex> pq;
    List<Edge>[] adj;

    Dijkstra(int V, List<Edge>[] adj) {
        this.V = V;
        completedSet = new boolean[V];
        dist = new int[V];
        pq = new PriorityQueue<>(V, (a, b) -> a.dist - b.dist);
        this.adj = adj;
    }

    // static int findU(int[] dist, boolean[] completedSet) {
    //     int minIndex = -1;
    //     int min = Integer.MAX_VALUE;

    //     for (int i = 0; i < dist.length; i++) {
    //         if (dist[i] < min && !completedSet[i]) {
    //             min = dist[i];
    //             minIndex = i;
    //         }
    //     }

    //     return minIndex;
    // }

    int[] solve(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Vertex(start, 0));

        for (int i = 0; i < V; i++) {

            int u = pq.remove().v;
            completedSet[u] = true;
            process(u);

            // int u = pg.remove().u;
            // if (u < 0)
            //     break;
            // completedSet[u] = true;

            // if (edges[u] != null)
            //     for (Edge e : edges[u]) {
            //         if (!completedSet[e.v] && dist[e.v] > dist[u] + e.w) {
            //             dist[e.v] = dist[u] + e.w;
            //         }
            //     }
        }

        return dist;
    }

    void process(int u) {

        System.out.println("processing " + u + " @ " + dist[u]);

        for(Edge e : adj[u]) {
            int v = e.v;

            if (completedSet[v]) continue;

            if (dist[v] > dist[u] + e.w) {
                dist[v] = dist[u] + e.w;
            }

            pq.add(new Vertex(v, dist[v]));
        }
    }
}