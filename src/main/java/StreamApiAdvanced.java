import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamApiAdvanced {
    /**
     * Given array of numbers, your task is to sort them in the reverse order and return only those
     * numbers that can be divided by 5 without a remainder.
     */
    public List<Integer> filterAndReverse(int[] inputNumbers) {

        List<Integer> collect = Arrays.stream(inputNumbers)
                .filter(i -> i % 5 == 0)
                .boxed()
                //.sorted(Collections.reverseOrder())
                .sorted(Comparator.reverseOrder())
                .collect(toList());

        return collect;

        //.collect(Collectors.toList());
        //return sorted;
    }

    /**
     * We want to gather some statistics: we have list of people and we want to know
     * distribution among the age of women who have cats and are older than 18
     * Result should have the following view: Map.of(19 - List.of(person1, person2, ...),
     * 21 - List.of(person3, person7, ...);
     */
    public Map<Integer, List<PersonAdvanced>> groupByAge(List<PersonAdvanced> people) {

        Map<Integer, List<PersonAdvanced>> collect1 = people.stream()
                .filter(p -> p.getSex().equals(PersonAdvanced.Sex.WOMAN)
                        && p.getAge() > 18 && !p.getCatList().isEmpty())
                .collect(groupingBy(PersonAdvanced::getAge));


        Map<Integer, List<PersonAdvanced>> collect = people.stream()
                .filter(p -> p.getAge() > 18
                        && p.getSex().equals(PersonAdvanced.Sex.WOMAN)
                        && p.getCatList() != null)
                .collect(groupingBy(p -> p.getAge()));

        return collect;
    }

    /**
     * Given a list of random strings, group all of them by the last letter from the
     * string. If last char is a number or punctuation - skip the word.
     */
    public Map<Character, List<String>> groupWordsByLastChar(List<String> words) {
        Map<Character, List<String>> collect = words.stream()
                .filter(w -> Character.isAlphabetic(w.charAt(w.length() - 1)))
                .collect(groupingBy(w -> Character.valueOf(w.charAt(w.length() - 1))));
  /*      Map<Integer, List<String>> collect = words.stream()
                .filter(w -> (w.charAt(w.length() - 1) >= 65 && w.charAt(w.length() - 1) <= 90)
                        || (w.charAt(w.length() - 1) >= 97 && w.charAt(w.length() - 1) <= 122))
                .collect(Collectors.groupingBy(w -> w.length));

        Map<Character, List<String>> collect2 = new HashMap<>();
        for (String w : words) {
            if ((w.charAt(w.length() - 1) >= 65 && w.charAt(w.length() - 1) <= 90)
                    || (w.charAt(w.length() - 1) >= 97 && w.charAt(w.length() - 1) <= 122)) {
                collect2.computeIfAbsent(w.charAt(w.length() - 1), k -> new ArrayList<>()).add(w);
            }
        }
*/
        return words.stream()
                .filter(w -> (w.charAt(w.length() - 1) >= 65 && w.charAt(w.length() - 1) <= 90)
                        || (w.charAt(w.length() - 1) >= 97 && w.charAt(w.length() - 1) <= 122))
                .collect(Collectors.groupingBy(w -> w.charAt(w.length() - 1)));

    }
}
