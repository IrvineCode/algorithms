class dsegtree {
  public static void main(String[] args) {
    int[] ar = { 2, 5, 1, 4, 9, 3 };

    dseg s = new dseg(ar);
    System.out.println(Arrays.toString(s.tree));

    for (int i = 0; i < ar.length - 2; i++) {
      System.out.println(ar[i] + "~" + ar[i + 2] + "=" + s.queryMin(i, i + 2));
    }

    s.update(4, 2);
    System.out.println(Arrays.toString(s.tree));

    for (int i = 0; i < ar.length - 2; i++) {
      System.out.println(ar[i] + "~" + ar[i + 2] + "=" + s.queryMin(i, i + 2));
    }
  }
}

// copy paste this
class dseg {
  
}