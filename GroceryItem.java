public class GroceryItem implements Comparable<GroceryItem> {
    private String name;
    private double price;
    private String location;

    public GroceryItem(String name, double price, String location) {
        this.name = name;
        this.price = price;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    // return -1 if object is less than 'that'
    // return 1 if object is greater than 'that'
    // compare one object to another
    public int compareTo(GroceryItem that) {
        int minimum = Math.min(name.length(), that.getName().length());
        for (int i = 0; i < minimum; i++) {
            if (name.charAt(i) < that.getName().charAt(i)) {
                return -1;
            } else if (name.charAt(i) > that.getName().charAt(i)) {
                return 1;
            }
        }
        // if same return 0
        return 0;
    }

    public String toString() {
        return "Item Name: " + name + ", Item Price: " + price + ", Item Location: " + location;
    }
}
