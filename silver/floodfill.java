class floodfill {
  public static void main(String[] args) {
    char[][] ar = { ".#.#.".toCharArray(), ".#.#.".toCharArray(), ".#.#.".toCharArray(), "...#.".toCharArray(),
        ".###.".toCharArray(), ".#...".toCharArray(), };

    ff(0, 0, ar);

    // print result
    for (char[] row : ar) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  static void ff(int r, int c, char[][] ar) {
    if (r < 0 || c < 0 || r == ar.length || c == ar[r].length)
      return;
    if (ar[r][c] != '.')
      return;

    ar[r][c] = '#';

    ff(r - 1, c, ar);
    ff(r + 1, c, ar);
    ff(r, c + 1, ar);
    ff(r, c - 1, ar);
  }
}