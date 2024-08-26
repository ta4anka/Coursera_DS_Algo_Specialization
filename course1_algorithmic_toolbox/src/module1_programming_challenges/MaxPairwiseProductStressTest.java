package module1_programming_challenges;

import java.util.Arrays;
import java.util.Random;

import static module1_programming_challenges.MaxPairwiseProduct.getMaxPairwiseProduct;
import static module1_programming_challenges.MaxPairwiseProduct.getMaxPairwiseProductFast;

public class MaxPairwiseProductStressTest {
    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            int arraySize = random.nextInt(9) + 2;
            System.out.println("Size of array: " + arraySize);

            int[] list = random.ints(arraySize, 0, 10001).toArray(); // [7718, 455, 7348, 2505, 2805, 704]
            System.out.println(Arrays.toString(list));

            double result1 = getMaxPairwiseProduct(list);
            double result2 = getMaxPairwiseProductFast(list);

            if (result1 != result2) {
                System.out.println("Mismatch! Control: " + result1 + " Solution: " + result2);
                break;
            } else {
                System.out.println("OK");
            }
        }
    }
}
