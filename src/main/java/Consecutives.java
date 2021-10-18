import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consecutives {


        public static List<Integer> sumConsecutives(List<Integer> s) {
            int previous = Integer.MAX_VALUE;
            LinkedList<Integer> l = new LinkedList<>();
            for (Integer v: s){
                l.add(v == previous? l.pollLast() + v : v);
                previous = v;
            }
            return l;
        }
    public static String high(String s) {
        // Your code here...
        return Arrays.stream(s.split(" "))
                .max(Comparator.comparingInt(word -> word.chars().map(letter -> letter -96).sum())).get();
    }
    }
