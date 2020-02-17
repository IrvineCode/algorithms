class Lesson1 {
  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) {
    System.out.println("// --------------------------------- orientation");
    Pt p1 = new Pt(0, 0);
    Pt p2 = new Pt(4, 4);
    Pt p3 = new Pt(8, 8);
    Pt p4 = new Pt(1, 2);
    Pt p5 = new Pt(2, 1);

    System.out.println(orientation(p1, p2, p3)); // 0
    System.out.println(orientation(p1, p2, p4)); // 2
    System.out.println(orientation(p1, p2, p5)); // 1

    System.out.println("// --------------------------------- intersect");
    Pt a = new Pt(1, 1);
    Pt b = new Pt(10, 1);
    Pt c = new Pt(1, 2);
    Pt d = new Pt(10, 2);
    System.out.println(doIntersect(a, b, c, d)); // false

    a = new Pt(10, 1);
    b = new Pt(0, 10);
    c = new Pt(0, 0);
    d = new Pt(10, 10);
    System.out.println(doIntersect(a, b, c, d)); // true

    a = new Pt(-5, -5);
    b = new Pt(0, 0);
    c = new Pt(1, 1);
    d = new Pt(10, 10);
    System.out.println(doIntersect(a, b, c, d)); // false

    System.out.println("// ---------------------------------- point in polygon");
    Pt polygon[] = { new Pt(0, 0), new Pt(10, 0), new Pt(10, 10), new Pt(0, 10) };

    System.out.println(isInside(polygon, new Pt(5, 5)));
    System.out.println(isInside(polygon, new Pt(15, 5)));
    System.out.println(isInside(polygon, new Pt(-5, 6)));

  }

  static boolean doIntersect(Pt a, Pt b, Pt c, Pt d) {
    int o1 = orientation(a, b, c);
    int o2 = orientation(a, b, d);
    int o3 = orientation(c, d, a);
    int o4 = orientation(c, d, b);

    if (o1 != o2 && o3 != o4)
      return true;

    if (o1 == 0 && onLine(a, b, c))
      return true;
    if (o2 == 0 && onLine(a, b, d))
      return true;
    if (o3 == 0 && onLine(c, d, a))
      return true;
    if (o4 == 0 && onLine(c, d, b))
      return true;

    return false;
  }

  static boolean onLine(Pt a, Pt b, Pt c) {
    return Math.min(a.x, b.x) <= c.x && c.x <= Math.max(a.x, b.x) && Math.min(a.y, b.y) <= c.y
        && c.y <= Math.max(a.y, b.y);
  }

  static int orientation(Pt a, Pt b, Pt c) {
    int dy = b.y - a.y;
    int dx = b.x - a.x;
    int dyy = c.y - b.y;
    int dxx = c.x - b.x;

    int comp = dy * dxx - dyy * dx;

    if (comp == 0)
      return 0; // linear
    else if (comp > 0)
      return 1; // clockwise
    else
      return 2; // counter-clockwise
  }

  static boolean isInside(Pt[] polygon, Pt p) {
    if (polygon.length < 3)
      return false;

    Pt pEnd = new Pt(INF, p.y);

    int count = 0;

    for (int i = 0; i < polygon.length; i++) {
      Pt p1 = polygon[i];
      Pt p2 = polygon[(i + 1) % polygon.length];

      if (doIntersect(p1, p2, p, pEnd)) {
        count++;

        // lies on the line
        if (orientation(p1, p2, p) == 0)
          return true;
      }
    }

    return count % 2 == 1;
  }

}

class Pt {
  int x, y;

  Pt(int x, int y) {
    this.x = x;
    this.y = y;
  }
}