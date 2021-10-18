import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleLetterRemovalOne {
    public static String solve(String s, int k) {

        List<String> list = Arrays.asList(s.split("")).stream().collect(Collectors.toList());
        boolean a = list.add("a");
        int i = 0;

        Iterator<String> iterator = list.iterator();
        while (list.size() != s.length() - k && list.size() != 0) {
            int temp = list.size();
            Character character = Character.valueOf((char) ('a' + i));
            list.remove("" + character);
            if (temp == list.size()) {
                i++;
            }
        }
        return list.stream().collect(Collectors.joining(""));
    }
}