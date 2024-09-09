import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.*;


// This tester is for grocery processing
public class Tester {
	public static void main(String[] args) {

		Registry registry = new Registry();
		AvailableStores availableStores = new AvailableStores();

		// Create some people and students in the container
		createPeopleAndStudents(registry);

		// Create some courses in the container
		createCourses(registry);

		// Enroll students by name in courses by name
		registerStudents(registry);

		//Show current state (requirement 2a and 2b as List<String> suitable for printing)
		System.out.println("Registered after initial registrations:\n" + registry.reportRegistrations());
		System.out.println("Wait-listed after initial registrations:\n" + registry.reportWaitListed());

		// Remove some courses for some students
		dropStudents(registry);

		//Show current state again
		System.out.println("Registered after some drop:\n" + registry.reportRegistrations());
		System.out.println("Wait-listed after some drop:\n" + registry.reportWaitListed());

		System.out.println("\nInfo on all courses:\n" + registry.getALlCourseInfo());

		createStores(availableStores);
		createGroceryItems(availableStores);

		makeRandomLists(registry);

		Shop(registry, availableStores);
		groceryStoreSaleInformation(availableStores);
	}

	public static void createPeopleAndStudents(Registry r) {

		// Assumed parameters familyName, givenNameS, studentID, day, month, year (of birth)
		r.recordPerson("Smith", "Adam", 1, 12, 1984);
		r.recordStudent("Quincy", "Elizabeth", 1000001, 12, 1, 1990);
		r.recordUnderGradStudent("Smith", "Betsey", 1000002, 10, 3, 2000);
		r.recordUnderGradStudent("Smith", "Mary", 1000003, 1, 10, 2003);
		r.recordUnderGradStudent("Smith", "Abigail", 1000004, 21, 8, 2005);
		r.recordGradStudent("Franklin", "Benjamin", 1000005, 19, 3, 2020);
		r.recordGradStudent("Washington", "George", 1000006, 2, 11, 1984);
		r.recordGradStudent("Custis", "Daniel Parke", 1000007, 12, 12, 1980);

		r.recordStudent("Custis", "Martha Dandridge", 1000008, 29, 11, 1984);
		r.recordStudent("Custis", "Daniel Parke Jr", 1000009, 2, 12, 2003);
		r.recordStudent("Custis", "Adam Parke", 1000010, 10, 1, 2004);
		r.recordStudent("Custis", "Martha Parke \"Patsy\"", 1000011, 4, 8, 2006);
		r.recordPerson("Henry", "Patrick", 9, 5, 190);
	}

	public static void createCourses(Registry c) {

		// Assumed parameters departmentName, courseNumber, courseTitle, maxCapacity
		c.recordCourse("COMP", 170, "Intro to OOP", 3);
		c.recordCourse("COMP", 271, "Data Structures I", 5);
		c.recordCourse("COMP", 272, "Data Structures II", 3);
	}

	public static void registerStudents(Registry c) {
		c.enrollStudent("Quincy", "Elizabeth", "COMP", 170);
		c.enrollStudent("Quincy", "Elizabeth", "COMP", 271);
		c.enrollStudent("Quincy", "Elizabeth", "COMP", 272);
		c.enrollStudent("Smith", "Betsey", "COMP", 170);
		c.enrollStudent("Smith", "Betsey", "COMP", 170);            //duplicate, should be ignored
		c.enrollStudent("Smith", "Mary", "COMP", 170);
		c.enrollStudent("Franklin", "Benjamin", "COMP", 170);      //will waitlist
		c.enrollStudent("Franklin", "Benjamin", "COMP", 272);
		c.enrollStudent("Washington", "George", "COMP", 271);
		c.enrollStudent("Custis", "Martha Dandridge", "COMP", 271);
		c.enrollStudent("Custis", "Daniel Parke Jr", "COMP", 272);
		c.enrollStudent("Custis", "Adam Parke", "COMP", 170);    //will waitlist
		c.enrollStudent("Custis", "Adam Parke", "COMP", 272);    //will waitlist
        /*expected
		170 [Elizabeth, Betsey, Mary] wait [Benjamin, Adam]
		271 [Elizabeth,George, Martha] wait [ ]
		272 [Elizabeth, Benjamin, Daniel Jr] wait [Adam]
		 */
	}

	public static void dropStudents(Registry c) {
		c.removeStudent("Smith", "Abigail", "COMP", 170);    //in no courses, should do nothing
		c.removeStudent("Washington", "George", "COMP", 170);    //registered but not this course, should do nothing
		c.removeStudent("Custis", "Martha Dandridge", "COMP", 271);    //remove, no wait list present
		c.removeStudent("Custis", "Daniel Parke Jr", "COMP", 272);    //remove, process wait list
        /*expected
		170 [Elizabeth, Betsey, Mary] wait [Benjamin, Adam]
		271 [Elizabeth,George] wait [ ]
		272 [Elizabeth, Benjamin, Adam] wait [ ]
		 */
	}

	public static void createStores(AvailableStores s) {
		s.recordStore("Jewel Roger's Park");
		s.recordStore("Mariano's Evanston");
	}

	public static void createGroceryItems(AvailableStores s) {

		int store = 0;
		for (GroceryStore g : s.getStores()) {
			//These will be same in all stores
			g.addItem("Peanut butter", 3.99, "Aisle 2");
			g.addItem("Apples, delicious", 0.99, "Produce");
			g.addItem("Florida oranges", 1.29, "Produce");
			g.addItem("Oat Milk, 1 quart", 1.99, "Aisle 1");
			g.addItem("Blueberry muffins", 4.59, "Bakery");
			g.addItem("Coffee beans", 6.00, "Aisle 2");
			if (store == 0) {
				g.addItem("Whole wheat bread", 4.99, "Aisle 2");
				g.addItem("Orange juice", 1.99, "Aisle 1");
			}
			if (store == 1) {
				g.addItem("Whole wheat bread", 1.99, "Aisle 7");
				g.addItem("Orange juice", 3.99, "Aisle 6");
			}
			store++;
		}
	}

	public static void makeRandomLists(Registry r) {
		Student s1 = r.findStudent("George Washington");
		s1.createGroceryList("Food");
		s1.createGroceryList("Gaming");
		s1.createGroceryList("Clothing");
	}


	public static void Shop(Registry p, AvailableStores s) {

		UIManager.put("OptionPane.messageFont", new Font("System", Font.BOLD, 32));
		UIManager.put("TextField.font", new FontUIResource(new Font("SansSerif",Font.BOLD,16)));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
		UIManager.put("OptionPane.comboBoxFont", new Font("SansSerif",Font.BOLD,16));

		ImageIcon icon = new ImageIcon("src/shoppingIcon.png");
		Image scaledIcon = icon.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
		ImageIcon GroceryIcon = new ImageIcon(scaledIcon);

		GroceryStore groceryStore = null;
		Scanner scan = new Scanner(System.in);
		GroceryList groceryList = null;
		GroceryItem groceryItem = null;

		boolean validInput = false;
		Person person = null;
		while (!validInput) {
			String name = (String) JOptionPane.showInputDialog(null, "Welcome to Shopping App\n▚▚▚▚▚▚▚▚▚▚▚▚▚\n      What is your Name?","Shopping Application",JOptionPane.INFORMATION_MESSAGE,GroceryIcon,null,"");
			if (name == null) System.exit(0);
			person = p.find(name);
			if (person != null) {
				validInput = true;
			} else {
				JOptionPane.showMessageDialog(null, "⚠Person does not exist in the registry⚠\nTry Again", "INVALID INPUT", JOptionPane.WARNING_MESSAGE,GroceryIcon);
			}
		}
		validInput = false;
		while (!validInput) {
			int reply = JOptionPane.showConfirmDialog(null, "Hello " + person.getName() + ".\n▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚\n" + "Would you like to make a new list\n","Shopping Application", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,GroceryIcon);
			if (reply == -1 || reply == 2) System.exit(0);

			String gList = " ";

			if(reply == JOptionPane.YES_OPTION) {
				gList = (String) JOptionPane.showInputDialog(null, "What will the name of your list be? :","Shopping Application",JOptionPane.INFORMATION_MESSAGE,GroceryIcon,null,"");

				if (gList == null) System.exit(0);

				person.createGroceryList(gList);
				groceryList = person.groceryLists.get(0);
				validInput = true;

			}else if(person.groceryLists.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You do not have any Grocery Lists\nTry Again", "Shopping Application", JOptionPane.WARNING_MESSAGE,GroceryIcon);
			}else{
				ArrayList<GroceryList> gll = person.groceryLists;
				ArrayList<String> slists = new ArrayList<>();
				gll.forEach(n -> slists.add(n.getName()));
				String[] options = new String[slists.size()];
				options = slists.toArray(options);
				String gl = (String)JOptionPane.showInputDialog(null,"Choose one of your existing lists", "Shopping Application", JOptionPane.QUESTION_MESSAGE,GroceryIcon,options,options[0]);
				groceryList = person.getGroceryList(gl);
				validInput = true;
			}
		}

		Set<GroceryStore> as = new HashSet<GroceryStore>(s.getStores());
		Set<String> asn = new HashSet<String>();
		as.forEach(n -> asn.add(n.getName()));
		String[] stores = new String[asn.size()];
		stores = asn.toArray(stores);
		String ags = (String)JOptionPane.showInputDialog(null,"Choose one of the available grocery stores", "Shopping Application", JOptionPane.QUESTION_MESSAGE,GroceryIcon,stores,stores[0]);
		if (ags == null) System.exit(0);
		groceryStore = s.find(ags);

		boolean stillBuying = true;
		while(stillBuying) {

			Set<String> items = new HashSet<>();
			groceryStore.getItems().forEach(n -> items.add(n.getName()));
			String[] itemNames = new String[items.size()];
			itemNames = items.toArray(itemNames);

			String itemName = (String) JOptionPane.showInputDialog(null,"What item would you like to purchase?\n" + groceryStore, "Shopping Application", JOptionPane.QUESTION_MESSAGE, GroceryIcon, itemNames,itemNames[0]);
			groceryItem = groceryStore.getItem(itemName);

			int confirm = JOptionPane.showConfirmDialog(null, groceryItem.getName() + "\n$" + groceryItem.getPrice() + " each. \nWould you like to buy?","Shopping Application",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,GroceryIcon);
			if (confirm == -1 || confirm == 2) System.exit(0);

			if(confirm == JOptionPane.YES_OPTION) {
				validInput = false;
				int amount = 0;
				while (!validInput){
					try {
						String quantity = (String) JOptionPane.showInputDialog(null, groceryItem.getName() + "\n$" + groceryItem.getPrice() + " each. \nHow many would you like to buy?", "Shopping Application", JOptionPane.INFORMATION_MESSAGE, GroceryIcon, null, "");
						if (quantity == null) System.exit(0);
						amount = Integer.parseInt(quantity);
						if(amount <= 0) throw new Exception();
						validInput = true;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Invalid Input\nTry Again", "Shopping Application", JOptionPane.WARNING_MESSAGE,GroceryIcon);
					}
				}

				groceryList.addItem(groceryItem,amount);
			}

			JOptionPane.showMessageDialog(null,"Current list: \n" + person.displayList(groceryList),"Shopping Application", JOptionPane.PLAIN_MESSAGE,GroceryIcon);


			int r = JOptionPane.showConfirmDialog(null, "Would you like to remove any items?","Shopping Application",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,GroceryIcon);
			if (r == -1 || r == 2) System.exit(0);
			if(r == JOptionPane.YES_OPTION) {
				int amount = 0;
				Set<String> lnTemp = new HashSet<>();
				groceryList.getCart().keySet().forEach(n -> lnTemp.add(n.getName()));
				String[] listNames = new String[lnTemp.size()];
				listNames = lnTemp.toArray(listNames);
				itemName = (String) JOptionPane.showInputDialog(null, "What item would you like remove?\n" + groceryList, "Shopping Application", JOptionPane.QUESTION_MESSAGE, GroceryIcon, listNames, listNames[0]);
				groceryItem = groceryStore.getItem(itemName);
				if (itemName != null) {
					validInput = false;
					while (!validInput) {
						try {
							String quantity = (String) JOptionPane.showInputDialog(null, groceryItem.getName() + "\n ►Quantity: " + groceryList.getQuantity(groceryItem) + "\n ►Price: $" + groceryItem.getPrice() + "\nHow many would you like to remove from your list?", "Shopping Application", JOptionPane.INFORMATION_MESSAGE, GroceryIcon, null, "");
							if (quantity == null) System.exit(0);
							amount = Integer.parseInt(quantity);
							if (amount < 0) throw new Exception();
							validInput = true;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Invalid Input\nTry Again", "Shopping Application", JOptionPane.WARNING_MESSAGE, GroceryIcon);
						}
					}
					groceryList.removeItem(groceryItem, amount);

					JOptionPane.showMessageDialog(null, "Current list: \n" + person.displayList(groceryList), "Shopping Application", JOptionPane.PLAIN_MESSAGE, GroceryIcon);
				}
			}


			int keepShopping = JOptionPane.showConfirmDialog(null, "Would you like to continue shopping?","Shopping Application",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,GroceryIcon);
			if (keepShopping == -1 || keepShopping == 2) System.exit(0);
			if(keepShopping == JOptionPane.NO_OPTION) {
				stillBuying = false;
			}
		}
		groceryStore.updatePurchasedItems(groceryList.getCart());
		JOptionPane.showMessageDialog(null,"Here is your list and total cost: \n" + person.getTotal(groceryList),"Shopper Application", JOptionPane.PLAIN_MESSAGE,GroceryIcon);
		System.out.println(groceryStore.getPurchasedItemInformation());

		int keepUsingApp = JOptionPane.showConfirmDialog(null, "Would you like to keep using the Shopping Application","Shopping Application", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,GroceryIcon);
		if(keepUsingApp == -1) System.exit(0);
		else if(keepUsingApp == JOptionPane.YES_OPTION) Shop(p,s);
	}

	public static void groceryStoreSaleInformation(AvailableStores s) {
		UIManager.put("OptionPane.messageFont", new Font("System", Font.BOLD, 32));
		UIManager.put("TextField.font", new FontUIResource(new Font("SansSerif",Font.BOLD,16)));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
		UIManager.put("OptionPane.comboBoxFont", new Font("SansSerif",Font.BOLD,16));

		ImageIcon icon = new ImageIcon("src/shoppingIcon.png");
		Image scaledIcon = icon.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
		ImageIcon GroceryIcon = new ImageIcon(scaledIcon);

		GroceryStore groceryStore = null;

		JOptionPane.showMessageDialog(null,"▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚\nWelcome to the Grocery Store Purchase Summary Portal\n▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚","Grocery Store Purchase Summary Portal", JOptionPane.PLAIN_MESSAGE,GroceryIcon);

		Set<GroceryStore> as = new HashSet<GroceryStore>(s.getStores());
		Set<String> asn = new HashSet<String>();
		as.forEach(n -> asn.add(n.getName()));
		String[] stores = new String[asn.size()];
		stores = asn.toArray(stores);
		String store = (String)JOptionPane.showInputDialog(null,"What grocery store would you like to see the purchase summary", "Grocery Store Purchase Summary Portal", JOptionPane.QUESTION_MESSAGE,GroceryIcon,stores,stores[0]);
		if (store == null) System.exit(0);
		groceryStore = s.find(store);

		String storeSummary = groceryStore.getPurchasedItemInformation();
		JOptionPane.showMessageDialog(null,"▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚\n" + storeSummary + "\n▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚","Grocery Store Purchase Summary Portal", JOptionPane.PLAIN_MESSAGE,GroceryIcon);
	}
}

