import java.util.Arrays;

class UnionFind {
  public static void main(String[] args) {
    Edge[] graph = new Edge[4];
    graph[0] = new Edge(0, 1);
    graph[1] = new Edge(0, 2);
    graph[2] = new Edge(0, 3);
    graph[3] = new Edge(2, 1);
    System.out.println(new UnionFind(graph, 4).isCycle());
  }

  int[] parent;
  int size;

  

  boolean isCycle(Edge[] graph, int size) {
    int[] parent = new int[size];
    Arrays.fill(parent, -1);

    for(Edge e : graph) {
      if (find(e.a) == find(e.b))
        return false;
      union()
    }
    return true;
  }

  static class Edge {
    int a, b;

    Edge(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}
