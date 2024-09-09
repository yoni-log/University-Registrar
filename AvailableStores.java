import java.util.Set;
import java.util.HashSet;

public class AvailableStores {
    private Set<GroceryStore> stores = new HashSet<>();
    public AvailableStores() {}

    public void recordStore(String name) {
        GroceryStore store = new GroceryStore(name);
        stores.add(store);
    }

    public void addItem(String item, double price, String location) {
        for (GroceryStore groceryStore : stores) {
            groceryStore.addItem(item, price, location);
        }
    }

    public GroceryStore find(String store) {
        for (GroceryStore thatStore : stores) {
            if (store.equals(thatStore.getName())) {
                return thatStore;
            }
        }
        return null;
    }

    public Set<GroceryStore> getStores() {
        return stores;
    }

    // added count for ui improvement in the future if a selection system on an integer is desired
    public String toString() {
        String storeList = "Available Store(s): ";
        int count = 0;
        for (GroceryStore store : stores) {
            count++;
            storeList += "\nStore " + count + ". : " + store.getName();
        }
        return storeList;
    }
}
