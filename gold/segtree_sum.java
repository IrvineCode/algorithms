class segtree {
    int[] tree;
    int n; // size of the original array

    segtree(int n) {
        this.n = n;
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int max_size = (int) Math.pow(2, height + 1) - 1;
        tree = new int[max_size];
    }

    segtree(int[] ar) {
        this(ar.length);
        makeSegTree(ar, 0, n - 1, 0);
    }

    // v = new value
    // i = index
    public void update(int i, int diff) {
        update(i, diff, 0, n - 1, 0);
    }

    private void update(int index, int diff, int start, int end, int i) {

        if (start > index || end < index) {
            return;
        }

        int mid = (start + end) / 2;
        tree[i] += diff;

        if (start != end) {
            update(index, diff, start, mid, i * 2 + 1);
            update(index, diff, mid + 1, end, i * 2 + 2);
        }
    }

    private int makeSegTree(int ar[], int a, int b, int i) {
        // exit condition
        if (a == b)
            return tree[i] = ar[a];

        // recursion
        int mid = a + (b - a) / 2;
        return tree[i] = makeSegTree(ar, a, mid, i * 2 + 1) + makeSegTree(ar, mid + 1, b, i * 2 + 2);
    }
    
    // [a, b]
    public int query(int a, int b) {
        return query(a, b, 0, n - 1, 0);
    }

    // [a, b], [aa, bb]
    private int query(int a, int b, int aa, int bb, int ti) {
        if (bb < a || aa > b) {
            return 0;
        }
        if (a <= aa && bb <= b) {
            return tree[ti];
        }

        int mid = aa + (bb - aa) / 2;

        return query(a, b, aa, mid, ti * 2 + 1) + query(a, b, mid + 1, bb, ti * 2 + 2);
    }

}
