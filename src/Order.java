import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderCounter = 1;
    private String orderId;
    private String customer;
    private Map<String, Integer> items;
    private Restaurant restaurant;
    private int cost;

    public Order(String customer, Map<String, Integer> items, Restaurant restaurant, int cost) {
        this.orderId = "order" + orderCounter++;
        this.customer = customer;
        this.items = new HashMap<>(items);
        this.restaurant = restaurant;
        this.cost = cost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void markAsDelivered() {
        restaurant.markOrderAsDelivered(items.values().stream().mapToInt(Integer::intValue).sum());
    }

    @Override
    public String toString() {
        return String.format("{ 'order_id': '%s', 'customer': '%s', 'items': %s, 'restaurant': '%s', 'cost': %d }",
                orderId, customer, items, restaurant.getName(), cost);
    }
}
