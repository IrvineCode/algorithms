import java.util.*;

class graham {
  static boolean debug = true;

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

  static int distSq(Pt a, Pt b) {
    return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
  }

  // assume all pts are unique...
  static LinkedList<Pt> convexHull(Pt[] pts) {

    // not a polygon
    if (pts.length < 3)
      return null;

    LinkedList<Pt> hull = new LinkedList<>();

    // most bottom point
    int b = 0;
    for (int i = 1; i < pts.length; i++) {
      if (pts[i].y < pts[b].y)
        b = i;
      else if (pts[i].y == pts[b].y && pts[i].x < pts[b].x)
        b = i;
    }
    Pt p0 = pts[b];
    pts[b] = pts[0];
    pts[0] = p0;

    // sort the rest by polar-angle from p0 (right -> left)
    Comparator polarComp = new Comparator<Pt>() {
      public int compare(Pt a, Pt b) {
        int ori = orientation(p0, a, b);
        switch (ori) {
        case 0:
          return Integer.compare(distSq(p0, a), distSq(p0, b));
        case 1:
          return 1;
        default: // case 2
          return -1;
        }
      }
    };
    Arrays.sort(pts, 1, pts.length, polarComp);

    // if multiple pts are collinear, remove all but the farthest
    int j = 1;
    for (int i = 1; i < pts.length; i++) {
      // skip duplicates
      while (i < pts.length - 1 && orientation(p0, pts[i], pts[i + 1]) == 0)
        i++;

      pts[j++] = pts[i];
    }

    if (debug) {
      for (int i = 0; i < j; i++) {
        System.out.print(pts[i] + ", ");
      }
      System.out.println();
    }

    // check if there are enough points
    if (j < 3)
      return null;

    // return value
    hull.addLast(pts[0]);
    hull.addLast(pts[1]);
    hull.addLast(pts[2]);

    // graham's algorithm starts here :|
    for (int i = 3; i < j; i++) {
      System.out.println("cur: " + hull);
      while (orientation(hull.get(hull.size() - 2), hull.getLast(), pts[i]) != 2) {
        if (debug)
          System.out.println("pop: " + hull.getLast());
        hull.removeLast();
      }
      hull.addLast(pts[i]);
    }

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
