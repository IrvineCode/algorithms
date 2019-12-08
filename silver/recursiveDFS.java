import java.util.*;

/*

Graph

0 -- 1 -- 2 -- 3
    |
    4 -- 5

*/

class recursiveDFS {
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
    System.out.println(dfs(adj, new boolean[NV], 0, 5));

  }

  static boolean dfs(List<List<Integer>> adj, boolean[] visited, int u, int dst) {
    if (u == dst)
      return true;

    for (int v : adj.get(u)) {
      if (!visited[v]) {
        visited[v] = true;
        if (dfs(adj, visited, v, dst))
          return true;
      }
    }
    return false;
  }
}
