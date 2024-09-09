public interface Shopper {
    String lists();
    String displayList(GroceryList g);
    boolean createGroceryList(String name);
    GroceryList getGroceryList(String name);
    String getTotal(GroceryList g);
}
