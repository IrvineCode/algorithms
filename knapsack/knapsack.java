import java.util.Arrays;

class knapsack {
  public static void main(String[] args) {
    int W = 7;
    int n = 4;
    int[] val = { 1, 4, 5, 7 };
    int[] wt = { 1, 3, 4, 5 };

    System.out.println(knapsack1(W, wt, val, n));
    System.out.println("-------");
    System.out.println(knapsack2(W, wt, val, n));
    System.out.println("-------");
  }

  // iterative
  static int knapsack1(int W, int[] wt, int[] val, int n) {
    int[][] t = new int[n][W + 1];

    // first row
    for (int j = 0; j <= W; j++) {
      if (wt[0] > j)
        t[0][j] = 0;
      else
        t[0][j] = val[0];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= W; j++) {
        if (wt[i] > j)
          t[i][j] = t[i - 1][j];
        else
          t[i][j] = Math.max(val[i] + t[i - 1][j - wt[i]], t[i - 1][j]);
      }
    }

    for (int[] row : t)
      System.out.println(Arrays.toString(row));

    return t[n - 1][Wi];
  }

  // naive recursive
  static int knapsack2(int W, int[] wt, int[] val, int n) {
    if (W == 0 || n == 0)
      return 0;

    if (wt[n - 1] > W) {
      return knapsack2(W, wt, val, n - 1);
    } else {
      return Math.max(val[n - 1] + knapsack2(W - wt[n - 1], wt, val, n - 1), knapsack2(W, wt, val, n - 1));
    }
  }

}