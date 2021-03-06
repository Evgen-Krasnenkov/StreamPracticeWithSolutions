
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import model.Candidate;
import model.CandidateValidator;
import model.Cat;
import model.Person;

public class StreamPractice {
    /**
     * Given list of strings where each element contains 1+ numbers:
     * input = {"5,30,100", "0,22,7", ...}
     * return min integer value. One more thing - we're interested in even numbers.
     * If there is no needed data throw RuntimeException with message
     * "Can't get min value from list: < Here is our input 'numbers' >"
     */
    public int findMinEvenNumber(List<String> numbers) {

        numbers.stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .mapToInt(s -> Integer.parseInt(s))
                .boxed()
                .filter(i -> i % 2 == 0)
                .min(Integer::compare)
                .orElseThrow(() -> new NoSuchElementException("Can't get min value from list:"));

        return numbers.stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(s1 -> Integer.parseInt(s1))
                .filter(n -> n % 2 == 0)
                .min((x, y) -> Integer.compare(x, y))
                .orElseThrow(() -> new NoSuchElementException("Can't get min value from list:"));
    }
    /**
     * Given a List of Integer numbers,
     * return the average of all odd numbers from the list or throw NoSuchElementException.
     * But before that subtract 1 from each element on an odd position (having the odd index).
     */

    public Double getOddNumsAverage(List<Integer> numbers) {
        double asDouble = IntStream.range(0, numbers.size() - 1)
                .map(i -> i % 2 != 0 ? numbers.get(i) - 1 : numbers.get(i))
                .filter(n -> n % 2 != 0)
                .average()
                .getAsDouble();


        return IntStream.range(0, numbers.size())
                .map(index -> index % 2 != 0 ? numbers.get(index) - 1 : numbers.get(index))
                .filter(number -> (number % 2) != 0)
                .average()
                .getAsDouble();
    }
    /**
     * Given a List of `Person` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new Person(??Victor??, 16, Sex.MAN),
     * new Person(??Helen??, 42, Sex.WOMAN))`,
     * select from the List only men whose age is from `fromAge` to `toAge` inclusively.
     * <p>
     * Example: select men who can be recruited to army (from 18 to 27 years old inclusively).
     */

    public List<Person> selectMenByAge(List<Person> peopleList, int fromAge, int toAge) {
        List<Person> collect = peopleList.stream()
                .filter(p -> p.getSex().equals(Person.Sex.MAN)
                        && p.getAge() >= fromAge
                        && p.getAge() <= toAge)
                .collect(toList());


        Predicate<Person> filteredPerson = p -> p.getAge() >= fromAge
                && p.getAge() <= toAge
                && p.getSex() == Person.Sex.MAN;

        return peopleList.stream()
                .filter(filteredPerson)
                .collect(toList());
    }
    /**
     * Given a List of `Person` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new Person(??Victor??, 16, Sex.MAN),
     * new Person(??Helen??, 42, Sex.WOMAN))`,
     * select from the List only people whose age is from `fromAge` and to `maleToAge` (for men)
     * or to `femaleToAge` (for women) inclusively.
     * <p>
     * Example: select people of working age
     * (from 18 y.o. and to 60 y.o. for men and to 55 y.o. for women inclusively).
     */

    public List<Person> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<Person> peopleList) {

        List<Person> collect = peopleList.stream()
                .filter(p -> p.getAge() >= fromAge
                        && ((p.getSex().equals(Person.Sex.MAN) && p.getAge() <= maleToAge)
                        || (p.getSex().equals(Person.Sex.WOMAN) && p.getAge() <= femaleToAge))
                        )
                .collect(toList());


        Predicate<Person> filteredPerson =
                person -> person.getAge() >= fromAge
                        && ((person.getSex() == Person.Sex.MAN && person.getAge() <= maleToAge)
                        || (person.getSex() == Person.Sex.WOMAN && person.getAge() <= femaleToAge));

        List<Person> collect1 = peopleList.stream()
                .filter(filteredPerson)
                .collect(toList());
        return collect1;
    }
    /**
     * Given a List of `Person` instances (having `name`, `age`, `sex` and `cats` fields,
     * and each `Cat` having a `name` and `age`),
     * return the names of all cats whose owners are women from `femaleAge` years old inclusively.
     */

    public List<String> getCatsNames(List<Person> peopleList, int femaleAge) {
        List<String> collect = peopleList.stream()
                .filter(p -> p.getAge() >= femaleAge
                        && p.getSex().equals(Person.Sex.WOMAN)
                        && !p.getCats().isEmpty())
                .flatMap(p -> p.getCats().stream())
                .map(c -> c.getName())
                .collect(toList());


        List<String> collect1 = peopleList.stream()
                .filter(person -> person.getSex() == Person.Sex.WOMAN
                        && person.getAge() >= femaleAge)
                .flatMap(person -> person.getCats().stream())
                .map(Cat::getName)
                .collect(toList());
        return collect1;
    }
    /**
     * Your help with a election is needed. Given list of candidates, where each element
     * has Candidate.class type.
     * Check which candidates are eligible to apply for president position and return their
     * names sorted alphabetically.
     * The requirements are: person should be older than 35 years, should be allowed to vote,
     * have nationality - 'Ukrainian'
     * and live in Ukraine for 10 years. For the last requirement use field periodsInUkr,
     * which has following view: "2002-2015"
     * We want to reuse our validation in future, so let's write our own impl of Predicate
     * parametrized with Candidate in CandidateValidator.
     */

    public List<String> validateCandidates(List<Candidate> candidates) {
        CandidateValidator candidateValidator = new CandidateValidator();
        return candidates.stream()
                .filter(candidateValidator)
                .map(Candidate::getName)
                .sorted()
                .collect(toList());
    }
}
