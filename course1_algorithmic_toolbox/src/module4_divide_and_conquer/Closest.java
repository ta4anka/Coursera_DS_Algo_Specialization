package module4_divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


/**
 * Closest Points Problem
 * Find the closest pair of points in a set of points on a plane.
 * Input: A list of n points on a plane.
 * Output: The minimum distance between a pair of these points.
 * <p>
 * Input format. The first line contains the number of points n.
 * Each of the following n lines defines a point (x_i,y_i).
 * <p>
 * Output format. The minimum distance. Recall that the distance between
 * points (x_1,y_1) and (x_2,y_2) is equal to √((x₁ − x₂)² + (y₁ − y₂)²)
 * Thus,while the Input contains only integers, the Output is not necessarily integer,
 * and you have to pay attention to precision when you report it.
 * The absolute value of the difference between the answer of your
 * program and the optimal value should be at most 10^−3.
 * To ensure this, output your answer with at least four digits after the decimal
 * point (otherwise even correctly computed answer may fail to pass our grader because of the rounding errors)
 * <p>
 * Constraints. 2 ≤ n ≤ 10^5; −10^9 ≤ x_i,y_i ≤ 10^9 are integers.
 */
public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        /**
         * Constructs a Point object with given x and y coordinates.
         *
         * @param x the x-coordinate of the point
         * @param y the y-coordinate of the point
         */
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(this.x, o.x);
        }
    }

    /**
     * Finds the minimum distance between any pair of points in a set of points on a 2D plane.
     * <p>
     * This method applies a divide-and-conquer approach to find the closest pair of points, achieving
     * a time complexity of O(n log n). It sorts the points by x-coordinates and recursively splits
     * the points into halves, calculating minimum distances in each half and then merging results.
     * <p>
     * Time Complexity: O(n log n), where n is the number of points.
     * Space Complexity: O(n), due to recursive calls and auxiliary storage for strip arrays.
     *
     * @param x an array of x-coordinates of the points
     * @param y an array of y-coordinates of the points
     * @return the minimum distance between any two points, formatted to at least four decimal places
     */
    static double minimalDistance(int[] x, int[] y) {
        int n = x.length;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        Arrays.sort(points); // Sort points by x-coordinate
        return findClosest(points, 0, n - 1);
    }

    /**
     * Recursively finds the minimum distance between points within a range by dividing and merging.
     *
     * @param points the array of points, sorted by x-coordinate
     * @param left   the starting index of the range
     * @param right  the ending index of the range
     * @return the minimum distance found in the specified range
     */
    private static double findClosest(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = left + (right - left) / 2;
        Point midPoint = points[mid];

        double dLeft = findClosest(points, left, mid);
        double dRight = findClosest(points, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        return Math.min(d, stripClosest(points, left, right, midPoint, d));
    }

    /**
     * Calculates the minimum distance between points in the strip near the midpoint.
     * <p>
     * Only points within a distance 'd' of the midpoint are considered to minimize unnecessary comparisons.
     * Points in the strip are sorted by y-coordinate to reduce comparisons in the y-axis, optimizing
     * the search for closest pairs within the strip.
     *
     * @param points   the array of points
     * @param left     the left boundary of the range
     * @param right    the right boundary of the range
     * @param midPoint the midpoint used to determine the strip area
     * @param d        the minimum distance found so far
     * @return the minimum distance found within the strip region
     */
    private static double stripClosest(Point[] points, int left, int right, Point midPoint, double d) {
        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        Arrays.sort(strip, 0, j, Comparator.comparingLong(p -> p.y));
        double minDist = d;

        for (int i = 0; i < j; ++i) {
            for (int k = i + 1; k < j && (strip[k].y - strip[i].y) < minDist; ++k) {
                minDist = Math.min(minDist, distance(strip[i], strip[k]));
            }
        }

        return minDist;
    }

    /**
     * Calculates the minimum distance between points using a brute-force approach.
     * <p>
     * This method is used for small sets of points (3 or fewer), where brute-force is more efficient
     * than recursive divide-and-conquer.
     *
     * @param points the array of points
     * @param left   the starting index of the range
     * @param right  the ending index of the range
     * @return the minimum distance between any two points in the range
     */
    private static double bruteForce(Point[] points, int left, int right) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        }
        return minDist;
    }

    /**
     * Computes the Euclidean distance between two points.
     * <p>
     * The distance is calculated as √((x₁ − x₂)² + (y₁ − y₂)²).
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the Euclidean distance between p1 and p2
     */
    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.printf("%.4f%n", minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");

    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    /**
     * Reads the next integer from the input.
     *
     * @return the next integer in the input stream
     */
    static int nextInt() {
        return Integer.parseInt(next());
    }
}

