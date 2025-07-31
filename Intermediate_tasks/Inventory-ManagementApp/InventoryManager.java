import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
    private final List<InventoryItem> items = new ArrayList<>();

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void deleteItem(String name) {
        items.removeIf(i -> i.getName().equalsIgnoreCase(name));
    }

    public void updateItem(String name, InventoryItem updated) {
        for (InventoryItem i : items) {
            if (i.getName().equalsIgnoreCase(name)) {
                i.setQuantity(updated.getQuantity());
                i.setPrice(updated.getPrice());
                break;
            }
        }
    }

    public List<InventoryItem> getItems() {
        return new ArrayList<>(items);
    }

    public List<InventoryItem> search(String keyword) {
        return items.stream()
                .filter(i -> i.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}