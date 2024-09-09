import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GroceryList {
    private String listName;
    private Map<GroceryItem, Integer> cart = new HashMap<>();

    public GroceryList(String listName) {
        this.listName = listName;
    }

    public String getName() {
        return listName;
    }

    public int getQuantity(GroceryItem item) {
        if (cart.containsKey(item)) {
            return cart.get(item);
        }
        return 0;
    }

    public void addItem(GroceryItem item, int quantity) {
        if (cart.containsKey(item)) {
            int currentQuantity = cart.get(item);
            cart.put(item, currentQuantity + quantity);
        } else {
            cart.put(item, quantity);
        }
    }

    public void removeItem(GroceryItem item, int quantity) {
        if (quantity == 0) return;
        if (cart.containsKey(item)) {
            if (cart.get(item) > quantity) {
                cart.put(item, cart.get(item) - quantity);
            } else {
                cart.put(item, 0);
            }
        }
    }

    public Map<GroceryItem, Integer> getCart() {
        return cart;
    }

    public String toString() {
        String description = "Items in grocery list " + listName;
        List<GroceryItem> sortedItems = new ArrayList<>();
        for (GroceryItem item : cart.keySet()) sortedItems.add(item);
        Collections.sort(sortedItems);

        for (GroceryItem item : sortedItems) {
            description += "\n ►" + item.getName() + ", Price: " + item.getPrice() + ", Quantity: " + cart.get(item);
        }
        return description;
    }
}
