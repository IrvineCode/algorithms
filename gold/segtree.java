import java.util.Arrays;

//[2, 5, 1, 4, 9, 3]
//[1, 1, 3, 2, 1, 4, 3, 2, 5, x, x, 4, 9, x, x]
//[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

class minSeg {
  int[] tree;
  int n; // size of the original array

  minSeg(int n) {
    this.n = n;
    int height = (int) Math.ceil(Math.log(n) / Math.log(2));
    int max_size = (int) Math.pow(2, height + 1) - 1;
    tree = new int[max_size];
    Arrays.fill(tree, Integer.MAX_VALUE);
  }

  minSeg(int[] ar) {
    this(ar.length);
    makeSegTree(ar, 0, n - 1, 0);
  }

  // v = new value
  // i = index
  public void update(int i, int v) {
    update(i, v, 0, n - 1, 0);
  }

  private void update(int index, int value, int start, int end, int i) {
    if (start > index || end < index) {
      return;
    }

    int mid = (start + end) / 2;
    tree[i] = Math.min(tree[i], value);

    if (start != end) {
      update(index, value, start, mid, i * 2 + 1);
      update(index, value, mid + 1, end, i * 2 + 2);
    }
  }

  // [a, b]
  int makeSegTree(int ar[], int a, int b, int i) {
    // exit condition
    if (a == b)
      return tree[i] = ar[a];

    // recursion
    int mid = a + (b - a) / 2;
    return tree[i] = Math.min(makeSegTree(ar, a, mid, i * 2 + 1), makeSegTree(ar, mid + 1, b, i * 2 + 2));
  }

  public int queryMin(int a, int b) {
    return queryMin(a, b, 0, n - 1, 0);
  }

  // [a, b], [aa, bb]
  private int queryMin(int a, int b, int aa, int bb, int ti) {
    if (bb < a || aa > b) {
      return Integer.MAX_VALUE;
    }
    if (a <= aa && bb <= b) {
      return tree[ti];
    }

    int mid = aa + (bb - aa) / 2;

    return Math.min(queryMin(a, b, aa, mid, ti * 2 + 1), queryMin(a, b, mid + 1, bb, ti * 2 + 2));
  }
}

class maxSeg {
  int[] tree;
  int n; // size of the original array

  maxSeg(int n) {
    this.n = n;
    int height = (int) Math.ceil(Math.log(n) / Math.log(2));
    int max_size = (int) Math.pow(2, height + 1) - 1;
    tree = new int[max_size];
  }

  maxSeg(int[] ar) {
    this(ar.length);
    makeSegTree(ar, 0, n - 1, 0);
  }

  // v = new value
  // i = index
  public void update(int i, int v) {
    update(i, v, 0, n - 1, 0);
  }

  private void update(int index, int value, int start, int end, int i) {
    if (start > index || end < index) {
      return;
    }

    int mid = (start + end) / 2;
    tree[i] = Math.max(tree[i], value);

    if (start != end) {
      update(index, value, start, mid, i * 2 + 1);
      update(index, value, mid + 1, end, i * 2 + 2);
    }
  }

  // [a, b]
  int makeSegTree(int ar[], int a, int b, int i) {
    // exit condition
    if (a == b)
      return tree[i] = ar[a];

    // recursion
    int mid = a + (b - a) / 2;
    return tree[i] = Math.max(makeSegTree(ar, a, mid, i * 2 + 1), makeSegTree(ar, mid + 1, b, i * 2 + 2));
  }

  public int queryMax(int a, int b) {
    return queryMax(a, b, 0, n - 1, 0);
  }

  // [a, b], [aa, bb]
  private int queryMax(int a, int b, int aa, int bb, int ti) {
    if (bb < a || aa > b) {
      return -1;
    }
    if (a <= aa && bb <= b) {
      return tree[ti];
    }

    int mid = aa + (bb - aa) / 2;

    return Math.max(queryMax(a, b, aa, mid, ti * 2 + 1), queryMax(a, b, mid + 1, bb, ti * 2 + 2));
  }
}
