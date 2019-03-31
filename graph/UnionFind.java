import java.util.Arrays;

// moocast

class Edge {
    int u, v;
    int w; // weight

    public Edge(int a, int b, int w) {
        u = a;
        v = b;
        this.w = w;
    }

    @Override
    public String toString() {
        return u + " -> " + v;
    }
}

class UnionFind {
    int[] parent;
    int[] size;
    int V; // number of vertices

    public UnionFind(int v) {
        V = v;
        parent = new int[v];
        size = new int[v];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // find the parent of a
    int findRecursion(int a) {
        if (parent[a] == -1)
            return a;
        return find(parent[a]);
    }

    // no recursion + optimization
    int find(int a) {
        int cur = a;

        while (parent[cur] != cur) {
            cur = parent[cur];
        }
        int root = cur;

        // with compression
        cur = a;
        while (parent[cur] != root) {
            int next = parent[cur];
            parent[cur] = root;
            cur = next;
        }
        return root;
    }

    int sizeof(int a) {
        return size[find(a)];
    }

    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        // check cycle
        if (pa == pb)
            return false;

        if (size[pa] > size[pb]) {
            parent[pb] = pa;        
            size[pa]++;     // TODO this part could change!
            // System.out.println(pb + " -> " + pa);
        } else {
            parent[pa] = pb;
            size[pb]++;
            // System.out.println(pa + " -> " + pb);
        }

        return true;
    }

    public static void main(String[] args) {

        // Kruskals
        Edge[] graph = { new Edge(0, 1, 2), new Edge(0, 2, 1), new Edge(0, 3, 1), new Edge(2, 3, 1),
                new Edge(1, 2, 1) };
        int V = 4;
        Arrays.sort(graph, (a, b) -> a.w - b.w);
        // System.out.println(Arrays.toString(graph));

        UnionFind uf = new UnionFind(V);

        int numSets = V;
        for (Edge e : graph) {
            if (uf.union(e.u, e.v)) {
                // System.out.println("connecting " + e.u + " - " + e.v);
                numSets--;
            } else {
                // System.out.println("skipping " + e.u + " - " + e.v);
            }
            // System.out.println("---------");

            if (numSets == 1)
                break;
        }

        System.out.println("par: " + Arrays.toString(uf.parent));
        System.out.println("siz: " + Arrays.toString(uf.size));
    }
}
