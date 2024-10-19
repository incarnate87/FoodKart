import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private String name;
    private Map<String, Integer> menu;
    private int totalCapacity;
    private int capacityInUse;

    public Restaurant(String name, Map<String, Integer> menu, int totalCapacity) {
        this.name = name;
        this.menu = new HashMap<>(menu);
        this.totalCapacity = totalCapacity;
        this.capacityInUse = 0;
    }

    public boolean canAcceptOrder(int itemCount) {
        return (capacityInUse + itemCount) <= totalCapacity;
    }

    public void processOrder(int itemCount) {
        if (canAcceptOrder(itemCount)) {
            capacityInUse += itemCount;
        } else {
            throw new RuntimeException("Capacity limit reached.");
        }
    }

    public void markOrderAsDelivered(int itemCount) {
        capacityInUse -= itemCount;
    }

    public void updateMenu(Map<String, Integer> newMenu) {
        menu.putAll(newMenu);
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getCapacityInUse() {
        return capacityInUse;
    }

    @Override
    public String toString() {
        return String.format("{'name': '%s', 'menu': %s, 'total_capacity': %d, 'capacity_in_use': %d}",
                name, menu.toString(), totalCapacity, capacityInUse);
    }
}
