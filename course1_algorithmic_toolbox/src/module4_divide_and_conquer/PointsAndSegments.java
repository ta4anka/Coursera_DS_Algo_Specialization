package module4_divide_and_conquer;

import java.util.*;


/**
 * Points and Segments Problem
 * <p>
 * Given a set of points and a set of segments on a line, compute, for each point, the number of
 * segments it is contained in.
 * <p>
 * Input: A list of segments and a list of points.
 * Output: The number of segments containing each point.
 * <p>
 * Input format. The first line contains two non-negative integers n and m
 * defining the number of segments and the number of points on a line, respectively.
 * The next n lines contain two integers li,ri defining the i-th segment [li,ri].
 * The next line contains m integers defining points p_1,...,p_m.
 * Output format. m non-negative integers k1,...,kp where ki is the number of segments that contain pi.
 * Constraints.
 * 1 ≤ n,m ≤ 50000;
 * −10^8 ≤ l_i ≤ r_i ≤ 10^8 for all 1 ≤ i ≤ n;
 * −1068 ≤ p_j ≤ 10^8 for all 1 ≤ j ≤ m.
 * <p>
 * Sample
 * Input:
 * 2 3
 * 0 5
 * 7 10
 * 1 6 11
 * Output:
 * 1 0 0
 * Explanation
 * We have two segments and three points. The first point lies only in the first segment
 * while the remaining two points are outside all segments.
 */
public class PointsAndSegments {

    /**
     * Counts the number of segments containing each point using a fast, sorted approach.
     * This method leverages sorting and binary search to achieve efficient counting of segments.
     * </p>
     * Time Complexity: O((n + m) log (n + m)), where n is the number of segments and m is the number of points.
     * This complexity arises from sorting the events and iterating through them.
     * <p>
     * Space Complexity: O(n + m) due to the storage of events in the array list.
     * </p>
     *
     * @param starts the start points of the segments
     * @param ends   the end points of the segments
     * @param points the points for which we want to count the containing segments
     * @return an array where each element indicates the number of segments containing the corresponding point
     */
    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        // Create a list to hold all events
        List<Event> events = new ArrayList<>();

        // Add start of segments as events of type 'L'
        for (int start : starts) {
            events.add(new Event(start, 'L'));
        }

        // Add end of segments as events of type 'R'
        for (int end : ends) {
            events.add(new Event(end, 'R'));
        }

        // Add points as events of type 'P' and track their original indexes
        for (int i = 0; i < points.length; i++) {
            events.add(new Event(points[i], 'P', i));
        }

        // Sort all events
        events.sort((e1, e2) -> {
            if (e1.value != e2.value) return Integer.compare(e1.value, e2.value);
            return Character.compare(e1.type, e2.type);
        });

        // Sweep line approach to count segment overlaps
        int currentSegments = 0;
        for (Event event : events) {
            if (event.type == 'L') {
                currentSegments++;
            } else if (event.type == 'R') {
                currentSegments--;
            } else { // 'P' type
                cnt[event.index] = currentSegments;
            }
        }

        return cnt;
    }

    /**
     * Naive method to count the number of segments containing each point.
     * <p>
     * Time Complexity: O(n * m), where n is the number of segments and m is the number of points.
     * This complexity arises from the nested loops checking each point against each segment.
     * <p>
     * Space Complexity: O(m) for the output count array.
     * </p>
     *
     * @param starts the start points of the segments
     * @param ends   the end points of the segments
     * @param points the points for which we want to count the containing segments
     * @return an array where each element indicates the number of segments containing the corresponding point
     */
    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    // Helper class to represent events (segment start, segment end, or point)
    private static class Event {
        int value;       // The position on the line
        char type;       // 'L' for start of segment, 'R' for end of segment, 'P' for point
        int index;       // Index of the point in the original points array (only used for points)

        Event(int value, char type) {
            this.value = value;
            this.type = type;
        }

        Event(int value, char type, int index) {
            this.value = value;
            this.type = type;
            this.index = index;
        }
    }
}
