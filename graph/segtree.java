import java.util.Arrays;

//[2, 5, 1, 4, 9, 3]
//[1, 1, 3, 2, 1, 4, 3, 2, 5, x, x, 4, 9, x, x]
//[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

class segtree {
  int[] tree;

  segtree(int[] ar) {
    int n = ar.length;
    int height = (int) Math.ceil(Math.log(n) / Math.log(2));
    int max_size = (int) Math.pow(2, height + 1) - 1;
    tree = new int[max_size];
    
    makeSegTree(ar, 0, n-1, 0);
    System.out.println(Arrays.toString(tree));
  }

  int makeSegTree(int ar[], int a, int b, int i) {
    // exit condition
    if (a == b)
      return tree[i] = ar[a];

    // recursion
    int mid = a + (b - a) / 2;
    return tree[i] = Math.min(
      makeSegTree(ar, a, mid, i * 2 + 1), 
      makeSegTree(ar, mid+1, b, i * 2 + 2));
  }

  int queryMin(int a, int b, int aa, int bb, int ti) {
    if (bb < a || aa > b) {
      return Integer.MAX_VALUE;
    }
    if (a <= aa && bb <= b) {
      return tree[ti];
    }

    int mid = aa + (bb - aa) / 2;

    return Math.min(
      queryMin(a, b, aa, mid, ti * 2 + 1),
      queryMin(a, b, mid+1, bb, ti * 2 + 2)
    );
  }

  int querySum(int a, int b, int aa, int bb, int ti) {
    if (bb < a || aa > b) {
      return 0;
    }
    if (a <= aa && bb <= b) {
      return tree[ti];
    }

    int mid = aa + (bb - aa) / 2;

    return 
      querySum(a, b, aa, mid, ti * 2 + 1) +
      querySum(a, b, mid+1, bb, ti * 2 + 2);
  }

  public static void main(String[] args) {
    int[] ar = { 2, 5, 1, 4, 9, 3 };

    segtree s = new segtree(ar);
    int res = s.querySum(2, 5, 0, ar.length-1, 0);
    System.out.println(res);
  }

}
