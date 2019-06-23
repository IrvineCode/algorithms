import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class knapsack {
  public static void main(String[] args) {
    int W = 7;

    int[] val = { 1, 4, 5, 5, 5, 7, 7, 7 };
    int[] wt = { 1, 3, 5, 5, 5, 5, 5, 5 };
    int n = val.length;

    System.out.println(knapsack1(W, wt, val, n));
    System.out.println("-------");
    System.out.println(knapsack2(W, wt, val, n));
    System.out.println("count: " + count2);
    System.out.println("-------");
    System.out.println(knapsack3(W, wt, val, n));
    System.out.println("count: " + count3);
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

    return t[n - 1][W];
  }

  // naive recursive
  static int count2 = 0;

  static int knapsack2(int W, int[] wt, int[] val, int n) {
    if (W == 0 || n == 0)
      return 0;

    count2++;
    if (wt[n - 1] > W) {
      return knapsack2(W, wt, val, n - 1);
    } else {
      return Math.max(val[n - 1] + knapsack2(W - wt[n - 1], wt, val, n - 1), knapsack2(W, wt, val, n - 1));
    }
  }

  // recursive with DP
  static int count3 = 0;
  static Map<Index, Integer> dp = new HashMap<>();

  static int knapsack3(int W, int[] wt, int[] val, int n) {
    if (W == 0 || n == 0)
      return 0;

    count3++;
    System.out.println(W + "," + n);
    Index idx = new Index(W, n);
    if (dp.containsKey(idx))
      return dp.get(idx);

    int ret = 0;
    if (wt[n - 1] > W) {
      ret = knapsack3(W, wt, val, n - 1);
    } else {
      ret = Math.max(val[n - 1] + knapsack3(W - wt[n - 1], wt, val, n - 1), knapsack3(W, wt, val, n - 1));
    }

    dp.put(idx, ret);
    return ret;
  }

}

class Index {
  int remWt, remI;

  public Index(int w, int i) {
    remWt = w;
    remI = i;
  }

  @Override
  public boolean equals(Object obj) {
    Index o = (Index) obj;
    return (remWt == remWt && remI == remI);
  }

  @Override
  public int hashCode() {
    return remWt * 100 + remI;
  }
}