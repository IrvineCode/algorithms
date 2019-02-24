import java.util.Arrays;

class UnionFind {
    public static void main(String[] args) {
        Edge[] graph = new Edge[3];
        graph[0] = new Edge(0, 1);
        graph[1] = new Edge(0, 2);
        graph[2] = new Edge(0, 3);
        // graph[3] = new Edge(2, 1);

        UnionFind uf = new UnionFind(graph, 4);
        System.out.println(uf.isCycle());
    }

    int[] parent;
    int[] setSize;
    int n;
    Edge[] graph;

    public UnionFind(Edge[] graph, int n) {
        this.n = n;
        parent = new int[n];
        Arrays.fill(parent, -1);
        this.graph = graph;
    }

    boolean isCycle() {
        for (Edge e : graph) {
            if (find(e.a) == find(e.b))
                return false;
            union(e.a, e.b);
        }
        return true;
    }

    // find the parent of a
    int find(int a) {
        if (parent[a] == -1)
            return a;
        return find(parent[a]);
    }

    // not using recursion (and with optimization)
    int find2(int a) {
        int root = a;
        while(parent[root] != -1) {
            root = parent[root];
        }
        int ret = root;

        // with compression
        root = a;
        while(parent[root] != -1) {
            int next = parent[root];
            parent[root] = ret;
            root = next;
        }

        return ret;
    }

    // TODO use size
    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parent[pa] = pb;
    }

}
 