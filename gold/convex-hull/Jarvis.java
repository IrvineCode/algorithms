import java.util.Deque;
import java.util.LinkedList;

class Jarvis {
  public static void main(String[] args) {
    Pt pts[] = new Pt[7];
    pts[0] = new Pt(0, 3);
    pts[1] = new Pt(2, 3);
    pts[2] = new Pt(1, 1);
    pts[3] = new Pt(2, 1);
    pts[4] = new Pt(3, 0);
    pts[5] = new Pt(0, 0);
    pts[6] = new Pt(3, 3);

    System.out.println(convexHull(pts));
  }

  static Deque<Pt> convexHull(Pt[] pts) {
    // not a polygon
    if (pts.length < 3)
      return null;

    Deque<Pt> hull = new LinkedList<>();

    // find left most point
    int l = 0;
    for (int i = 1; i < pts.length; i++) {
      if (pts[i].x < pts[l].x)
        l = i;
    }

    // start wrapping
    int p = l;
    do {
      hull.add(pts[p]);

      // find q
      int q = (p + 1) % pts.length;
      for (int i = 0; i < pts.length; i++) {
        if (orientation(pts[p], pts[i], pts[q]) == 2)
          q = i;
      }

      p = q;
    } while (p != l);

    return hull;
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
}

class Pt {
  int x, y;

  Pt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("(%d,%d)", x, y);
  }
}
