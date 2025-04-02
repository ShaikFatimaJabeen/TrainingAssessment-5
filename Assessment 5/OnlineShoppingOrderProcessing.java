import java.time.*;
import java.util.*;
import java.util.stream.*;

class Order {
    private String orderId, customerName;
    private LocalDate orderDate;
    private double totalAmount;

    public Order(String orderId, LocalDate orderDate, String customerName, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "Order{" + orderId + ", " + orderDate + ", " + customerName + ", $" + totalAmount + "}";
    }
}

public class OnlineShoppingOrderProcessing {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
            new Order("ORD001", LocalDate.of(2025, 4, 1), "John Doe", 150.50),
            new Order("ORD002", LocalDate.of(2025, 4, 2), "Jane Smith", 220.75),
            new Order("ORD003", LocalDate.of(2025, 4, 3), "Alice Brown", 130.25),
            new Order("ORD004", LocalDate.of(2025, 4, 2), "Bob White", 75.00),
            new Order("ORD005", LocalDate.of(2025, 4, 1), "Charlie Black", 200.00),
            new Order("ORD006", LocalDate.of(2025, 4, 1), "David Green", 175.00)
        );

        
        orders.stream().filter(o -> o.getOrderDate().isEqual(LocalDate.of(2025, 4, 1)))
            .forEach(System.out::println);
        System.out.println("----------------------------");

        
        double totalAmount = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        System.out.println("Total order amount: $" + totalAmount);
        System.out.println("----------------------------");

        
        orders.stream().max(Comparator.comparingDouble(Order::getTotalAmount))
            .ifPresent(o -> System.out.println("Highest order: " + o));
        System.out.println("----------------------------");

        
        orders.stream().filter(o -> o.getCustomerName().equals("John Doe"))
            .forEach(System.out::println);
        System.out.println("----------------------------");

        
        orders.stream().filter(o -> !o.getOrderDate().isBefore(LocalDate.of(2025, 4, 1)) && !o.getOrderDate().isAfter(LocalDate.of(2025, 4, 2)))
            .forEach(System.out::println);
        System.out.println("----------------------------");

        
        orders.stream().sorted(Comparator.comparingDouble(Order::getTotalAmount).reversed())
            .forEach(System.out::println);
        System.out.println("----------------------------");

        
        orders.stream().collect(Collectors.groupingBy(Order::getOrderDate))
            .forEach((date, list) -> { System.out.println(date + ": " + list); });
        System.out.println("----------------------------");

        
        double avgAmount = orders.stream().mapToDouble(Order::getTotalAmount).average().orElse(0.0);
        System.out.println("Average order amount: $" + avgAmount);
        System.out.println("----------------------------");

        
        orders.stream().max(Comparator.comparing(Order::getOrderDate))
            .ifPresent(o -> System.out.println("Most recent order: " + o));
        System.out.println("----------------------------");

        
        Optional<Order> orderForCustomer = orders.stream().filter(o -> o.getCustomerName().equals("Jane Smith")).findFirst();
        orderForCustomer.ifPresentOrElse(
            o -> System.out.println("Order for Jane Smith: " + o),
            () -> System.out.println("No order found for Jane Smith")
        );
    }
}
