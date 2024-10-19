import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LowestPriceStrategy implements Strategy {
    @Override
    public List<Restaurant> selectRestaurant(Map<String, Integer> items, List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(r -> r.canAcceptOrder(items.values().stream().mapToInt(Integer::intValue).sum()))
                .sorted(Comparator.comparingInt(r -> calculateTotalCost(r, items)))
                .collect(Collectors.toList());
    }

    private int calculateTotalCost(Restaurant restaurant, Map<String, Integer> items) {
        return items.entrySet().stream()
                .mapToInt(entry -> restaurant.getMenu().getOrDefault(entry.getKey(), Integer.MAX_VALUE) * entry.getValue())
                .sum();
    }
}
