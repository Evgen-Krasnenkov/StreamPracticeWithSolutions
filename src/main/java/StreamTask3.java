
import java.util.Arrays;

public class StreamTask3 {

    /**
     * Find the average of array elements
     * 
     */ 
    public static double getAvg(int [] array) {
        //write your code here
        double asDouble = Arrays.stream(array)
                .average().orElse(0d);
        return asDouble;
    }

    public static void main(String[] args) {
        test(getAvg(new int[]{}), 0, "emptyArray");
        test(getAvg(new int[]{1}), 1, "singleElement");
        test(getAvg(new int[]{1, 2, 3}), 2, "usualCase");
        test(getAvg(new int[]{-1, -2, -3}), -2, "negativeNumbers");
        test(getAvg(new int[]{4, 4, 4}), 4, "allTheSame");
    }

    private static void test(double actual, double expected, String testName) {
        if (actual != expected) {
            String errorMessage = String.format(
                    "Test %s failed: %s is not equal to expected %s",
                    testName,
                    actual,
                    expected);
            System.out.println("ERROR: " + errorMessage);
        } else {
            System.out.println("SUCCESS: " + testName + " passed");
        }
    }
}
