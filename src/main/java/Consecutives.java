import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consecutives {

    /*public static List<Integer> sumConsecutives(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        int sum = 0;
        int j = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            while (list.get(i + 1) == list.get(i)) {
                sum += list.get(i);
                i++;
            }
            if (sum == 0) {
                newList.add(j, list.get(i));
            } else {
                newList.add(j, sum + list.get(i));
            }
            sum = 0;
            j++;
        }
        if (list.get(list.size() - 1) == (list.get(list.size() - 2))) {
            newList.add(list.size() - 2, list.get(list.size() - 2) + list.get(list.size() - 2));
        } else {
            newList.add(list.get(list.size() - 1));
        }
        return newList;*/
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
