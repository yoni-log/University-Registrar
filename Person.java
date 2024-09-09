import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Person implements Shopper {
    private String firstNames;
    private String lastName;
    final LocalDate dob;
    final double discount;
    ArrayList<GroceryList> groceryLists = new ArrayList<>();

    public Person(String ln, String fn, int day, int month, int year) {
        lastName = ln.toUpperCase();
        firstNames = fn.toUpperCase();
        dob = LocalDate.of(year,month,day);
        discount = 0;
    }

    public Person(String ln, String fn, int day, int month, int year,double discount) {
        lastName = ln.toUpperCase();
        firstNames = fn.toUpperCase();
        dob = LocalDate.of(year,month,day);
        this.discount = discount;
    }

    public boolean equals(Object that) {
        if( !(that instanceof Person) )
            return false;
        return this.lastName.equals(((Person)that).lastName) &&
                this.firstNames.equals(((Person)that).firstNames) &&
                this.dob.equals(((Person)that).dob);
    }

    public ArrayList<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public int compareTo(Person o){
        int result;
        result = lastName.compareTo(o.lastName);
        if (result == 0) {
            result = firstNames.compareTo(o.firstNames);
            if (result == 0)
                result = dob.compareTo(o.dob);
        }
        return result;
    }

    public String toString() {
        return lastName+"/"+firstNames+" DOB="+dob;
    }

    public String getLastName(){
        return lastName;
    }
  
    public String getFirstName(){
        return firstNames;
    }
  
    public LocalDate getDob() {
        return dob;
    }
  
    public String getName() {
      return firstNames + " " + lastName;
    }

    @Override
    public String lists() {
        String str = "Your Grocery Lists [";
        for (GroceryList l: groceryLists) {
            str += "\t" + l.getName();
        }
        str += "]";
        return str;
    }

    @Override
    public String displayList(GroceryList g) {
        return g.toString();
    }

    @Override
    public boolean createGroceryList(String name) {
        for (GroceryList g: groceryLists) {
            if (g.getName() == name) {
                return false;
            }
        }
        GroceryList g = new GroceryList(name);
        groceryLists.add(g);
        return true;
    }

    @Override
    public GroceryList getGroceryList(String name) {
        for (GroceryList l : groceryLists) {
            if (l.getName().toUpperCase().equals(name.toUpperCase())) return l;
        }
        return null;
    }

    @Override
    public String getTotal(GroceryList g) {
        DecimalFormat df = new DecimalFormat("#.##");
        double total = 0;
        String s = "This is the contents and total of [" + g.getName() + "] Grocery List: \n";
        for (GroceryItem i : g.getCart().keySet()) {
            int quantity = g.getQuantity(i);
            double price = i.getPrice();
            s += "\t►" + i.getName() + ": \n   ➢Quantity: " + quantity + "\n   ➢Price per item: $" + price + "\n";
            total += price * quantity;
        }
        s += "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\nTotal: $" + df.format(total) + "\nPercentage Off: " + (int)(discount*100) + "%\nDiscount: $" + df.format(total*discount) + "\nYou pay: $" + df.format(total-(total*discount));
        return s;
    }
}
