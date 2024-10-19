import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private Strategy strategy = new LowestPriceStrategy(); // Default strategy

    public void addRestaurant(String name, Map<String, Integer> menu, int capacity) {
        restaurants.add(new Restaurant(name, menu, capacity));
    }

    public void updateMenu(String restaurantName, Map<String, Integer> menu) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.updateMenu(menu);
                return;
            }
        }
        throw new RuntimeException("Restaurant not found.");
    }

    public Order bookOrder(String customerName, Map<String, Integer> items) {
        List<Restaurant> selectedRestaurants = strategy.selectRestaurant(items, restaurants);
        if (selectedRestaurants.isEmpty()) {
            throw new RuntimeException("Order can't be fulfilled.");
        }
        Restaurant selected = selectedRestaurants.get(0);
        int totalCost = calculateTotalCost(selected, items);
        selected.processOrder(items.values().stream().mapToInt(Integer::intValue).sum());
        Order order = new Order(customerName, items, selected, totalCost);
        orders.add(order);
        return order;
    }

    public void markOrderAsDelivered(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.markAsDelivered();
                return;
            }
        }
        throw new RuntimeException("Order not found.");
    }

    private int calculateTotalCost(Restaurant restaurant, Map<String, Integer> items) {
        return items.entrySet().stream()
                .mapToInt(entry -> restaurant.getMenu().get(entry.getKey()) * entry.getValue())
                .sum();
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
