import java.util.*;

public class dijkstraListPq {
  public static void main(String[] args) {
    // input
    String[] input = { "0 1 2", "0 2 10", "1 2 1", "1 3 5", "2 3 1" };
    int V = 4;

    List<List<Node>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<Node>());
    }
    for (String edge : input) {
      StringTokenizer st = new StringTokenizer(edge);
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      adj.get(u).add(new Node(v, w));
      adj.get(v).add(new Node(u, w));
    }

    // data structures
    PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.w - b.w);
    HashSet<Integer> settled = new HashSet<>();
    int dist[] = new int[V];

    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0] = 0;
    q.add(new Node(0, 0));

    // START!
    while (!q.isEmpty() && settled.size() < V) {
      int u = q.remove().v;
      if (settled.contains(u))
        continue;
      settled.add(u);

      // this portion will be different based on graph structure (edge-list, adj-list,
      // adj-matrix)
      for (Node n : adj.get(u)) {
        int v = n.v;
        int w = n.w + dist[u];

        if (!settled.contains(v) && w < dist[v]) {
          dist[v] = w;
          q.add(new Node(v, w));
        }
      }
    }

    System.out.println(Arrays.toString(dist));

  }
}

// used for both queue "state" and "edge"
class Node {
  int v, w;

  Node(int v, int w) {
    this.v = v;
    this.w = w;
  }
}
