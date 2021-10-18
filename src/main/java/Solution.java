import java.util.stream.Stream;

public class Solution {
    public static String solve(final String str) {
        long countUpper = str.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> c.isUpperCase(c))
                .count();
        long countLower = str.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> c.isLowerCase(c))
                .count();


        return countLower >= countUpper ? str.toLowerCase() : str.toUpperCase();
    }
}