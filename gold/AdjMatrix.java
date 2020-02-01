// utility matrix. (weighted)
class AdjMatrix {
    int[][] adj;
    int n;
    boolean isDirected;

    AdjMatrix(int n, boolean isDirected) {
        this.n = n;
        this.isDirected = isDirected;
        adj = new int[n][n];
    }

    void addEdge(int a, int b) {
        addEdge(a, b, 1);
    }

    void addEdge(int a, int b, int weight) {
        adj[a][b] = weight;
        if (!isDirected)
            adj[b][a] = weight;
    }
}