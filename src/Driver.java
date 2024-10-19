import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        // Initialize OrderManager
        OrderManager orderManager = new OrderManager();

        // Add restaurants
        Map<String, Integer> menu1 = new HashMap<>();
        menu1.put("king_burger", 10);
        menu1.put("samosa_pizza", 20);
        menu1.put("alu_pasta", 19);
        orderManager.addRestaurant("resta1", menu1, 15);

        Map<String, Integer> menu2 = new HashMap<>();
        menu2.put("bendi_macaroni", 12);
        menu2.put("samosa_pizza", 25);
        menu2.put("alu_pasta", 17);
        orderManager.addRestaurant("resta2", menu2, 12);

        // Update menu for resta1
        Map<String, Integer> updatedMenu1 = new HashMap<>();
        updatedMenu1.put("bendi_macaroni", 8);
        updatedMenu1.put("king_burger", 15);
        orderManager.updateMenu("resta1", updatedMenu1);

        // Print restaurant details
        System.out.println(orderManager.getAllRestaurants());

        // Customer places an order
        Map<String, Integer> orderItems = new HashMap<>();
        orderItems.put("bendi_macaroni", 3);
        orderItems.put("samosa_pizza", 2);
        Order order = orderManager.bookOrder("cust1", orderItems);
        System.out.println("Order placed: " + order);

        // Print restaurant details after order
        List<Restaurant> allRestaurants = orderManager.getAllRestaurants();
        for(Restaurant r: allRestaurants)
          System.out.println(r);

        // Print all orders
        System.out.println(orderManager.getAllOrders());

        // Mark order as delivered
        orderManager.markOrderAsDelivered(order.getOrderId());

        // Print restaurant details after marking order as delivered
        System.out.println(orderManager.getAllRestaurants());
    }
}
