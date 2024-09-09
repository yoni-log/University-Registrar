import java.text.DecimalFormat;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GroceryStore {
    private String name;
    private Set<GroceryItem> items;
    private Map<GroceryItem, Integer> itemsPurchased = new HashMap<GroceryItem, Integer>();

    public GroceryStore(String name) {
        this.name = name;
        this.items = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public GroceryItem getItem(String itemName) {
        for (GroceryItem groceryItem : items) {
            if (groceryItem.getName().equals(itemName)) {
                return groceryItem;
            }
        }
        return null;
    }

    public Set<GroceryItem> getItems() {
        return items;
    }

    public double getPrice(GroceryItem item) {
        for (GroceryItem groceryItem : items) {
            if (groceryItem == item) {
                return groceryItem.getPrice();
            }
        }
        return 0;
    }

    public void addItem(String name, double price, String location) {
        GroceryItem groceryItem = new GroceryItem(name, price, location);
        items.add(groceryItem);
    }

    public void updatePurchasedItems(Map<GroceryItem, Integer> map) {
        for (GroceryItem gi : map.keySet()) {
            if (itemsPurchased.containsKey(gi)) {
                itemsPurchased.put(gi, map.get(gi));
            } else {
                itemsPurchased.put(gi, map.get(gi));
            }
        }
    }

    public String getPurchasedItemInformation() {
        DecimalFormat df = new DecimalFormat("#.##");
        String s = "How many of each item was purchase at [" + name + "] :\n";
        double revenue = 0;
        List<GroceryItem> list = new ArrayList<>(itemsPurchased.keySet());
        Collections.sort(list);

        for (GroceryItem gi : list) {
            s += "|  ►Item: " + gi.getName() + "\n|     ↳Quantity Sold: " + itemsPurchased.get(gi) + " <=> Price per unit " + gi.getPrice() + "\n";
            double revPerItem = itemsPurchased.get(gi)*gi.getPrice();
            s += "|     ↳Revenue Earned From " + gi.getName() + " : " + revPerItem + "\n";
            revenue += revPerItem;
        }
        s+= "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\nTotal Revenue: $" + df.format(revenue);
        return s;
    }
    // used for displaying the items available in a store
    @Override
    public String toString() {
        String storeDescription = "Store Name: " + name + "\nItems in Stock:\n";
        for (GroceryItem item : items) {
            storeDescription += "\n" + item.getName() + " ⟺ $" + item.getPrice();
        }
        return storeDescription;
    }
}
