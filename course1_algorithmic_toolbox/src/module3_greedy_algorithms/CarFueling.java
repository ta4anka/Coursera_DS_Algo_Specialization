package module3_greedy_algorithms;

import java.util.Scanner;

/**
 * Car Fueling Problem
 * Compute the minimum number of gas tank refills to get from one city to another.
 * <p>
 * Input: Integers d (total distance to the destination) and m (maximum distance the car can travel on a full tank),
 * as well as a sequence of integers stop_1 < stop_2 < ··· < stop_n.
 * <p>
 * Output: The minimum number of refills to get from one city to another if a car can travel at most
 * m miles on a full tank. The distance between the cities is d miles and there are gas stations at distances
 * stop_1,stop_2,...,stop_n along the way.
 * We assume that a car starts with a full tank.
 * <p>
 * Input format. The first line contains an integer d.
 * The second line contains an integer m.
 * The third line specifies an integer n.
 * Finally, the last line contains integers stop_1,stop_2,...,stop_n.
 * Output format. The minimum number of refills needed. If it is not possible to reach the destination, output −1.
 * Constraints. 1 ≤ d ≤ 10^5. 1 ≤ m ≤ 400. 1 ≤ n ≤ 300. 0 < stop_1 < stop_2 < ··· < stop_n < d.
 * <p>
 * Sample
 * Input:
 * 950
 * 400
 * 4
 * 200 375 550 750
 * Output:
 * 2
 * The distance between the cities is 950, the car can travel at most 400 miles on a full tank.
 * It suffices to make two refills: at distance 375 and 750.
 * This is the minimum number of refills as with a single refill one would only be able to travel at most 800 miles.
 */

public class CarFueling {

    /**
     * Computes the minimum number of refills needed to reach the destination.
     * <p>
     * Time Complexity: O(n) - The algorithm loops through the list of stops.
     * Space Complexity: O(n) - An additional array is created to hold stops and the destination.
     *
     * @param dist  the total distance to the destination
     * @param tank  the maximum distance the car can travel on a full tank
     * @param stops the array of gas station positions (distances from the starting point)
     * @return the minimum number of refills required to reach the destination, or -1 if not possible
     */
    static int computeMinRefills(int dist, int tank, int[] stops) {
        // Start by adding a final "stop" at the destination
        int n = stops.length;
        int[] allStops = new int[n + 2]; // Include starting point and destination
        allStops[0] = 0;                 // Start point at 0
        // Copy stops into allStops, offset by 1
        System.arraycopy(stops, 0, allStops, 1, n);
        allStops[n + 1] = dist;          // Add destination as the final stop
        int numRefills = 0;  // Count of refills made
        int currentStop = 0; // Index of the current stop (start at the beginning)
        // Greedy approach to calculate the minimum number of refills
        while (currentStop <= n) {
            int lastStop = currentStop;
            // Move to the farthest reachable stop
            while (currentStop <= n && allStops[currentStop + 1] - allStops[lastStop] <= tank) {
                currentStop++;
            }
            // If we can't move forward, return -1 (impossible to reach)
            if (currentStop == lastStop) {
                return -1;
            }
            // If we haven't reached the destination, increment the refill count
            if (currentStop <= n) {
                numRefills++;
            }
        }
        return numRefills;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the total distance, tank capacity, and number of stops
        int dist = scanner.nextInt(); // Distance to the destination
        int tank = scanner.nextInt(); // Maximum distance the car can travel on a full tank
        int n = scanner.nextInt();    // Number of gas stations along the way
        // Read the distances of each gas station
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}


