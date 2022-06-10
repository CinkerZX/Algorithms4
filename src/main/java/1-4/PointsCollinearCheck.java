import edu.princeton.cs.algs4.ThreeSum;

/**
 * The {@PointsCollinearCheck} class get the input of N distinct points (2D),
 * method findCollinearN returns the number of triples that falls on the same line.
 *
 * Idea: input (x1,y1), (x2,y2), ..., (xi,yi), ..., (xn,yn)
 * if (y2-y1)/(x2-x1) == (yi-yi)/(xi-x1), then (x1,y1), (x2,y2) and (xi,yi) are collinear
 * note: if x1 == x2, then just need to check if xi == x1
 *
 * It can also apply to 3-sum problem.
 * Proof: x1, x2, ..., xi, ..., xn => (x1, x1^3), (x2, x2^3), ..., (xn, xn^3)
 * let x1 = a, x2 = b, xi = c
 * (y2-y1)/(x2-x1) = (b^3-a^3)/(b-a)
 * (yi-yi)/(xi-x1) = (c^3-a^3)/(c-a)
 * Since b^3-a^3 = (b-a)(b^2+a^2+ab), (y2-y1)/(x2-x1) = (b^2+a^2+ab) = (b+a)^2-ab
 * Similar, (yi-yi)/(xi-x1) = (c+a)^2-ac
 * If collinear, then (y2-y1)/(x2-x1) == (yi-yi)/(xi-x1), that is (b+a)^2-ab == (c+a)^2-ac
 * After simplify, we have: (b+c+2a)(b-c) == a(b-c), a+b+c == 0 (b != c, since the points are distinct, x1 != x2 != x3)
 */
public class PointsCollinearCheck{
    private PointsCollinearCheck(){} // Construct do nothing

    public static int findCollinearN(Point2D[] points){
        // TODO: calculate the num of collinear points
        if (checkDuplicatePoints(points)){throw new IllegalArgumentException("There are duplicate points.");}
        int n = 0;
        double k;
        double r;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                k = calculateK(points[i], points[j]);
                for (int l = j+1; l < points.length; l++) {
                    r = calculateK(points[i], points[l]);
                    if (k == r){n += 1;}
                }
            }
        }
        return n;
    }

    public static double calculateK(Point2D p1, Point2D p2){
        return (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    }

    public static boolean checkDuplicatePoints(Point2D[] points){
        //TODO: check if the points are duplicated, return true if duplicated, else return false
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i].getX() == points[j].getX() && points[i].getY() == points[j].getY()){return true;}
            }
        }
        return false;
    }

    public static Point2D[] TransformDoubletoPoint(int[] set){
        Point2D[] result = new Point2D[set.length];
        Point2D point;
        if (ThreeSumFaster.containsDuplicates(set)){throw new IllegalArgumentException("Contains duplicate integers!");}
        for (int i = 0; i < set.length; i++) {
            result[i] = new Point2D(set[i],Math.pow(set[i],3));
        }
        return result;
    }

    public static int ThreeSumbyCollinear(int[] set){
        return findCollinearN(TransformDoubletoPoint(set));
    }
}














