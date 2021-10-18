import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
       // 10. Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category "Books"
        DoubleSummaryStatistics booksStats = dataGenerator.getProducts().stream()
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(p -> p.getPrice())
                .summaryStatistics();
        System.out.println(booksStats.toString());
        // 15. Get the most expensive product by category
        Map<String, Optional<Product>> collect12 = dataGenerator.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))));

        // 2. Obtain a list of order with products belong to category "Baby"

        List<Order> baby = dataGenerator.getOrders()
                .stream()
                .filter(o ->
                        o.getProducts()
                                .stream()
                                .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
                )
                .collect(Collectors.toList());
        //14. Obtain a data map with list of product name by category
        Map<String, List<String>> collect11 = dataGenerator.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        //13. Produce a data map with order record and product total sum
        Map<Order, Double> collect10 = dataGenerator.getOrders().stream()
                .collect(Collectors.toMap(o -> o, o -> o.getProducts().stream()
                        .map(p -> p.getPrice()).reduce(0d, (a, b) -> a + b)));
        Map<Order, Double> collect13 = dataGenerator.getOrders().stream()
                .collect(Collectors.toMap(Function.identity(), order -> order.getProducts().stream()
                        .mapToDouble(p -> p.getPrice()).sum()));

        //12. Produce a data map with order records grouped by customer
        Map<Customer, List<Order>> collect6 = dataGenerator.getOrders().stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        Map<Customer, List<Order>> collect8 = dataGenerator.getOrders().stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.toList()));

        Map<Customer, Set<Product>> collect7 = dataGenerator.getOrders().stream()
                .collect(Collectors.toMap(Order::getCustomer, Order::getProducts));
        List<Customer> collect9 = dataGenerator.getOrders().stream()
                .collect(Collectors.mapping(Order::getCustomer, Collectors.toList()));

        //9. Calculate order average payment placed on 14-Mar-2021
        double v = dataGenerator.getOrders().stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.parse("2021-03-15")))
                .mapToDouble(o -> o.getProducts().stream().map(p -> p.getPrice()).reduce(0D, (a, b) -> a + b))
                .average()
                .orElse(0D);
        Double result = dataGenerator.getOrders()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .average().getAsDouble();

        //7. Get a list of orders which were ordered on 15-Mar-2021,
        // log the order records to the console and then return its product list
        List<Order> collect5 = dataGenerator.getOrders().stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.parse("2021-03-15")))
                .collect(Collectors.toList());
        List<Set<Product>> collect4 = dataGenerator.getOrders().stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.parse("2021-03-15")))
                .map(o -> o.getProducts())
                .collect(Collectors.toList());

        //6. Get the 3 most recent placed order
        List<Order> collect3 = dataGenerator.getOrders().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        // 5. Get the cheapest products of "Books" category
        Product product = dataGenerator.getProducts().stream()
                .filter(p -> p.getCategory().equals("Books"))
                .min(Comparator.comparingDouble(Product::getPrice))
                .get();
        // 1. Obtain a list of products belongs to category "Books" with price > 100
        List<Product> books = dataGenerator.getProducts().stream()
                .filter(p -> p.getCategory().equals("Books")
                        && p.getPrice() > 100)
                .collect(Collectors.toList());
       // 3. Obtain a list of product with category = "Toys" and then apply 10% discount
        dataGenerator.getProducts().stream().forEach(System.out::println);
        dataGenerator.getProducts().stream()
                .filter(p -> p.getCategory().equals("Toys"))
                .forEach(p -> p.setPrice(p.getPrice() * 0.9d));
        dataGenerator.getProducts().stream().forEach(System.out::println);



        //Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        List<Product> collect1 = dataGenerator.getOrders().stream()
                .filter(order -> order.getCustomer().getTier() == 2
                        && order.getOrderDate().isAfter(LocalDate.parse("2021-02-01"))
                        && order.getOrderDate().isBefore(LocalDate.parse("2021-04-01")))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());


        //Calculate total sum of all orders placed in Feb 2021

        double sum1 = dataGenerator.getOrders().stream()
                .filter(o -> o.getOrderDate().isAfter(LocalDate.parse("2021-02-01"))
                        && o.getOrderDate().isBefore(LocalDate.parse("2021-03-01")))
                .mapToDouble(o -> o.getProducts().stream().mapToDouble(p -> p.getPrice()).sum())
                .sum();


        double sum = dataGenerator.getOrders().stream()
                .filter(o -> o.getOrderDate().getMonthValue() == LocalDate.parse("2021-02-01").getMonthValue())
                .mapToDouble(o -> o.getProducts().stream().mapToDouble(p -> p.getPrice()).sum())
                .sum();

        double sum2 = dataGenerator.getOrders().stream()
                .filter(order -> order.getOrderDate()
                        .isAfter(LocalDate.parse("2021-02-01")) && order.getOrderDate().isBefore(LocalDate.parse("2021-03-01")))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();


       // Obtain a data map with order id and order's product count
        List<Order> orders = dataGenerator.getOrders();


        Map<Long, Integer> collect = dataGenerator.getOrders().stream()
                .collect(Collectors.toMap(Order::getId, order -> order.getProducts().size()));


        Map<Long, Long> collect2 = orders.stream()
                .collect(Collectors.groupingBy(o -> o.getId(), Collectors.mapping(order -> order.getProducts().size(), Collectors.counting())));

        Map<Long, Long> map = dataGenerator.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
