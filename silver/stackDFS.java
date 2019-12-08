import java.util.*;

/*

Graph

0 -- 1 -- 2 -- 3
    |
    4 -- 5

*/

class stackDFS {
  public static void main(String[] args) {
    int[][] input = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 4 }, { 4, 5 } };
    int NV = 6; // number of vertices

    // construct adjacency list
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < NV; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] i : input) {
      adj.get(i[0]).add(i[1]);
      adj.get(i[1]).add(i[0]);
    }

    System.out.println("adj: " + adj);

    // find if 0 is connected to 5
    boolean[] visited = new boolean[NV];
    Deque<Integer> q = new LinkedList<>();
    visited[0] = true;
    q.add(0);

    while (!q.isEmpty()) {
      int u = q.removeFirst(); // removeLast() -- DFS, removeFirst() -- BFS
      System.out.println("visiting " + u);
      if (u == 5) {
        System.out.println("arrived");
        return;
      }

      for (int v : adj.get(u)) {
        if (!visited[v]) {
          visited[v] = true;
          q.add(v);
        }
      }
    }

  }

}
