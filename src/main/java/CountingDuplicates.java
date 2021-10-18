import java.util.Map;
import java.util.stream.Collectors;

public class CountingDuplicates {
  public static int duplicateCount(String text) {
    // Write your code here
    Map<Character, Long> collect = text.toLowerCase().chars()
            .mapToObj(aChar -> (char) aChar)
            .collect(Collectors.groupingBy(aChar -> aChar, Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    return  (int) text.chars()
            .mapToObj(aChar -> (char) aChar)
            .collect(Collectors.groupingBy(aChar -> aChar, Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .count();
  }
}