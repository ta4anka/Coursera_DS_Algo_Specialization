package module3_greedy_algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Covering Segments by Points Problem
 * <p>
 * You are responsible for collecting signatures from all tenants in a building.
 * For each tenant, you know a period of time when he or she is at home.
 * You would like to collect all signatures by visiting the building as few times
 * as possible. For simplicity, we assume that when you enter the building,
 * you instantly collect the signatures of all tenants that are in the building at
 * that time.
 * <p>
 * Find the minimum number of points needed to cover all given segments on a line.
 * <p>
 * Input format. The first line of the input contains the number n of segments.
 * Each of the following n lines contains two integers l_i and r_i
 * (separated by a space) defining the coordinates of endpoints of the i-th segment.
 * <p>
 * Output format. The minimum number k of points on the first line and the
 * integer coordinates of k points (separated by spaces) on the second
 * line. You can output the points in any order. If there are multiple
 * such sets of points, you can output any of them.
 * <p>
 * Constraints. 1 ≤ n ≤ 100; 0 ≤ l_i ≤ r_i ≤ 10^9 for all i.
 * <p>
 * Sample
 * Input:
 * 4
 * 4 7
 * 1 3
 * 2 5
 * 5 6
 * Output:
 * 2
 * 3 6
 * The second and the third segments contain the point with coordinate 3 while the first and the fourth segments contain the point with
 * coordinate 6. All segments cannot be covered by a single point, since
 * the segments [1,3] and [5,6] do not overlap. Another valid solution
 * in this case is the set of points 2 and 5.
 */
public class CoveringSegments {

    /**
     * Calculates the minimum number of points needed to cover all segments.
     * <p>
     * The approach is greedy:
     * 1. Sort the segments by their ending points (in increasing order).
     * 2. Always choose the point at the end of the first uncovered segment to maximize coverage.
     * 3. Continue picking points until all segments are covered.
     *
     * <p>
     * Time Complexity: O(n log n) where n is the number of segments, because of the sorting step.
     * Space Complexity: O(n) for storing the input segments and resulting points.
     * </p>
     *
     * @param segments an array of Segment objects representing the intervals.
     * @return an array of integers representing the optimal set of points to cover all segments.
     */
    private static int[] optimalPoints(Segment[] segments) {
        // Sort segments by their end points
        Arrays.sort(segments, Comparator.comparingInt(s -> s.end));

        // List to store the optimal points
        int[] points = new int[segments.length];
        int pointCount = 0;
        int lastPoint = -1;

        for (Segment segment : segments) {
            // If the current segment is not covered by the last chosen point
            if (lastPoint < segment.start) {
                // Choose the endpoint of this segment
                lastPoint = segment.end;
                points[pointCount++] = lastPoint;
            }
        }

        // Return only the relevant portion of the points array
        return Arrays.copyOf(points, pointCount);
    }

    /**
     * Segment class to represent the intervals with start and end points.
     */
    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }

        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}

