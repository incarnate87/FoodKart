import java.util.List;
import java.util.Map;

public interface Strategy {
    List<Restaurant> selectRestaurant(Map<String, Integer> items, List<Restaurant> restaurants);
}
