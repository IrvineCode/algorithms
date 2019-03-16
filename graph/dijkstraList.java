import java.util.*;

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

        int[] dist = Dijkstra.dijkstra(0, g.g);  // 3 - start point

        System.out.println(Arrays.toString(dist));
    }
}

class Graph {
    List<int[]> edges;
     final int V;

    Graph(int v) {
        V = v;
        g = new int[V][V];
        for(int[] row : g) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
    }
    void addEdge(int u, int v, int w) {
        g[u][v] = g[v][u] = w;
    }
}

class Dijkstra {

    static int findU(int[] dist, boolean[] completedSet) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;

        for(int i=0; i < dist.length; i++) {
            if (dist[i] < min && !completedSet[i]) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static int[] dijkstra(int start, int[][] g) {
        boolean[] completedSet;
        int[] dist;
        int V = g.length;

        completedSet = new boolean[V];
        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < V; i++) {

            int u = findU(dist, completedSet);
            if (u < 0) break;
            completedSet[u] = true;

            for(int v=0; v<g[0].length; v++) {
                if (
                    g[u][v] != Integer.MAX_VALUE &&
                            !completedSet[v] &&
                            dist[u] < Integer.MAX_VALUE &&

                            dist[v] > dist[u] + g[u][v]
                ) {
                    dist[v] = dist[u] + g[u][v];
                }
            }
        }

        return dist;
    }
}