class dsegtree {
  public static void main(String[] args) {
    int[] ar = { 2, 5, 1, 4, 9, 3 };
    // 2

    node seg = new node(0, 6);
    for (int i = 0; i < ar.length; i++) {
      System.out.println("adding " + ar[i]);
      seg.update(i, ar[i]);
    }

    for (int i = 0; i < ar.length - 2; i++) {
      System.out.println(ar[i] + "~" + ar[i + 2] + "=" + seg.getMax(i, i + 2));
    }

    seg.update(4, 2);
    System.out.println("-------");

    for (int i = 0; i < ar.length - 2; i++) {
      System.out.println(ar[i] + "~" + ar[i + 2] + "=" + seg.getMax(i, i + 2));
    }
  }
}

// copy paste this
class node {
  int lv, rv, max;
  node left, right;

  node(int l, int r) {
    lv = l;
    rv = r;
  }

  void extend() {
    if (lv == rv)
      return;
    if (left == null) {
      int m = (lv + rv) / 2;
      left = new node(lv, m);
      right = new node(m + 1, rv);
    }
  }

  int getMax(int l, int r) {
    if (r < lv || rv < l) {
      return 0;
    }
    if (l <= lv && rv <= r) {
      return max;
    }

    // extend();
    if (left == null)
      return 0;

    return Math.max(left.getMax(l, r), right.getMax(l, r));
  }

  void update(int p, int newVal) {
    if (lv == rv) {
      max = newVal;
      return;
    }
    extend();

    if (p <= left.rv)
      left.update(p, newVal);
    else
      right.update(p, newVal);

    max = Math.max(left.max, right.max);
  }

  @Override
  public String toString() {
    return String.format("[ %d ~ %d ]", lv, rv);
  }
}

// https://ideone.com/0QK4CX