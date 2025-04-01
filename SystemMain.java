package socorromarketsystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class SystemMain {
	// SystemMain Attributes
	// Initialized data lists
	public static ArrayList<ElectronicProduct> eproductsList = new ArrayList<>();
	public static ArrayList<Book> booksList = new ArrayList<>();
	private static ArrayList<Customer> membersList = new ArrayList<>();
	private static ArrayList<Transaction> transactionsHistory = new ArrayList<>();
	// File paths to data files
	public static String eproductsListPath = "socorromarketsystem/systemfiles/Electronic_Products_Inventory.csv";
	public static String booksListPath = "socorromarketsystem/systemfiles/Books_Inventory.csv";
	public static String customersListPath = "socorromarketsystem/systemfiles/Customers_Information.csv";
	public static String transactionHistoryPath = "socorromarketsystem/systemfiles/Transactions_History.csv";

	public static void main(String[] args) {
		// Declaring data lists from files
		SystemData.readElectronicProductsFromFile(eproductsListPath,
							  eproductsList);
		SystemData.readBooksFromFile(booksListPath,
					     booksList);
		SystemData.readCustomersFromFile(customersListPath,
					     membersList);
		SystemData.readTransactionHistoryFromFile(transactionHistoryPath,
							   transactionsHistory);
		
		// Loop login until user exits
		while (true) {
			User loggedInUser = login();

			if(loggedInUser == null) {
				System.out.println("You are not a member!");
				System.exit(0);
			}
		}
	}

	public static User login() {
		Scanner scannie = new Scanner(System.in);
		int id;
		loginDisplay();
		while(!(scannie.hasNextInt())) {
			System.out.print("Invalid input.\nEnter ID: ");
			scannie.nextLine();
		}
		id = scannie.nextInt();

		// Check if the user has a membership
		Customer member = customerHasMembership(id);

		// If they are a member, display a welcome and return the customer object
		if (member != null) {
			memberDisplay(member);
			return member;
		}
		// Check if admin
		else if (userIsAdmin(id)) {
			User admin = new User(id, "Admin");
			adminDisplay(admin);
			return admin;
		}

		// Return null if no matching user is found
		return null;
	}

	public static void loginDisplay() {
		System.out.println();
		System.out.println("\t---------------------------------------------------------------");
		System.out.println("\t\tWelcome to the Socorro Market System!\n");
		System.out.println("\t\tPlease enter your membership ID to continue...");
		System.out.println("\t---------------------------------------------------------------");
		System.out.println();

		// Prompt user to enter ID
		System.out.print("Enter ID: ");
	}
	
	// Checks if the customer is part of the members list, hence having a membership.
	public static Customer customerHasMembership(int id) {
		for (Customer mem:membersList) {
			if (id == mem.id) { return mem; }
		}

		// Return null if no member matching the id was found
		return null;
	}

	public static boolean userIsAdmin(int id) { 
		int firstDigit = (id/100)%10; // this extracts the first digit e.g. if id was 500 -> 5
		if(firstDigit == 1) {
			return true;
		} else { 
			return false;
		}
	}

	// User display
	
	// Member display
	public static void memberDisplay(Customer member) {
		// Status + Welcome message
		System.out.println("Status: Member\nWelcome "+member.getName());

		// Display products
		displayProducts();
		
		// Prompt user to add to cart
		// Initialize the shopping cart array list
		member.cart = new ArrayList<>();
		Scanner scannie = new Scanner(System.in);
		int id = 1;
		int quantity = 1;

		System.out.println("\nAdd item to cart by 'Product ID'.\nFinish shopping with input '0'.");
			System.out.println();
		while(id != 0) {
			System.out.print("Add: ");
			
			// Make sure input is an int
			while(!(scannie.hasNextInt())) {
				System.out.print("Invalid input.\nAdd: ");
				scannie.nextLine();
			}
			id = scannie.nextInt();
			scannie.nextLine();

			if (isProductID(id)) { 
				// Prompt quantity
				System.out.print("Quantity: ");
				while(!(scannie.hasNextInt())) {
					System.out.print("Invalid input.\nQuantity: ");
					scannie.nextLine();
				}
				quantity = scannie.nextInt();
				scannie.nextLine();
				member.cart.add(new int[] {id, quantity});
				System.out.println("Added to cart");
			}
			else if (id !=0) { 
				System.out.println("Product not found.");
			}
		}

		// Prompt checkout
		double totalCost = member.generateCheckout();
		
		// Save transaction
		ArrayList<String> productsPurchased = new ArrayList<>();
		ArrayList<String> quantities = new ArrayList<>();

		for (int[] entry:member.cart) {
			productsPurchased.add(Integer.toString(entry[0]));
			quantities.add(Integer.toString(entry[1]));
		}
		
		// Convert lists to arrays
		String[] productsPurchasedArr = productsPurchased.toArray(new String[0]);
		String[] quantitiesArr = quantities.toArray(new String[0]);

		// Generate today's date for receipt
		String todayDate = java.time.LocalDate.now().toString();

		// Generate new transaction entry
		Transaction transaction = new Transaction(Transaction.nextTransactionID, member.id, productsPurchasedArr, quantitiesArr, totalCost, todayDate);

		// Add to Customer Purchase History
		String[] oldPurchases = member.getPurchaseHistory();
		String[] purchaseHistory = Arrays.copyOf(oldPurchases, oldPurchases.length + 1);
		purchaseHistory[purchaseHistory.length - 1] = Integer.toString(Transaction.nextTransactionID);
		member.setPurchaseHistory(purchaseHistory);

		// Add to Transaction History
		transactionsHistory.add(transaction);

		// Update data
		updateDataFiles();

	}

	// Admin display
	public static void adminDisplay(User admin) {
		System.out.println("Status: Admin\n");

		// Prompt admin for option selection
		Scanner scannie = new Scanner(System.in);
		int choice = 0;

		// Display and loop options for admin
		while(choice != 5) {
			adminMenu();
			System.out.print("Enter choice: ");
			while(!(scannie.hasNextInt())) {
				System.out.print("Invalid input.\nEnter choice: ");
				scannie.nextLine();
			}
			choice = scannie.nextInt();
			scannie.nextLine();

			// Depending on option, perform action
			switch(choice) {
				case 1:
					displayProducts();
					break;
				case 2:
					System.out.println("\t\tTRANSACTIONS HISTORY");
					System.out.println(transactionsHistory);
					break;
				case 3:
					System.out.println("\t\tCUSTOMER INFORMATION"); 
					System.out.println(membersList);
					break;
				case 4:
					addPromotionDiscounts();
					break;
				case 5:
					System.out.println("Logging out...");
					break;
				case 6:
					System.out.println("Turning system off...");
					System.exit(0);
					
			}
		}
	}

	public static void addPromotionDiscounts() {
		Scanner scannie = new Scanner(System.in);
		int choice = 0;

		System.out.println("\t1. Add discount for electronics");
		System.out.println("\t2. Add discount for books");
		System.out.println("\t3. Add discount for entire orders");
		System.out.print("Enter choice: ");
		while(!(scannie.hasNextInt())) {
			System.out.print("Invalid input.\nEnter choice: ");
			scannie.nextLine();
		}
		choice = scannie.nextInt();
		scannie.nextLine();
		int productID = 0;
		double discount = 0.0;

		// Options
		switch(choice) {
			case 1:
				System.out.print("Enter Product ID: ");
				while(!(scannie.hasNextInt())) {
					System.out.print("Invalid input.\nEnter Product ID: ");
					scannie.nextLine();
				}
				productID = scannie.nextInt();
				scannie.nextLine();

				System.out.print("Enter discount: ");
				while(!(scannie.hasNextDouble())) {
					System.out.print("Invalid input.\nEnter discount: ");
					scannie.nextLine();
				}
				discount = scannie.nextDouble();
				scannie.nextLine();

				for (ElectronicProduct eproduct:eproductsList) {
					if (productID == eproduct.getProductID()) {
						eproduct.discount = discount;
						System.out.println("Discount applied!");
					}
				}
				break;
			case 2:
				System.out.print("Enter Product ID: ");
				while(!(scannie.hasNextInt())) {
					System.out.print("Invalid input.\nEnter Product ID: ");
					scannie.nextLine();
				}
				productID = scannie.nextInt();
				scannie.nextLine();

				System.out.print("Enter discount: ");
				while(!(scannie.hasNextDouble())) {
					System.out.print("Invalid input.\nEnter discount: ");
					scannie.nextLine();
				}
				discount = scannie.nextDouble();
				scannie.nextLine();

				for (Book bk:booksList) {
					if (productID == bk.getProductID()) {
						bk.discount = discount;
						System.out.println("Discount applied!");
					}
				}
				break;
			case 3:
				System.out.print("Enter discount: ");
				while(!(scannie.hasNextDouble())) {
					System.out.print("Invalid input.\nEnter discount: ");
					scannie.nextLine();
				}
				discount = scannie.nextDouble();
				scannie.nextLine();

				// Apply to all products
				Product.entireDiscount = discount;
				System.out.println("Discount applied!");
				break;
		}
				
	}

	public static void adminMenu() {
		System.out.println("\t1. Display all inventory");
		System.out.println("\t2. Display Transactions History");
		System.out.println("\t3. Display Customers Information");
		System.out.println("\t4. Add promotional discounts");
		System.out.println("\t5. Logout");
		System.out.println("\t6. Turn off system");
	}

	public static void displayProducts() {
		System.out.println();
		System.out.println("\t\t----------------------------------------------------------");
		System.out.println("\t\t\t\t\t\tELECTRONICS\n");

		System.out.println(eproductsList);	

		System.out.println("\t\t----------------------------------------------------------");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\tBOOKS\n");
		
		System.out.println(booksList);

		System.out.println();
		System.out.println("\t\t----------------------------------------------------------");
	}
	
	public static boolean isProductID(int id) {
		// Check if it's an electronic product
		for (ElectronicProduct eproduct:eproductsList) {
			if (id == eproduct.getProductID()) { return true; }
		}

		for (Book book:booksList) {
			if (id == book.getProductID()) { return true; }
		}

		// If no matching id is found return false
		return false;
	}

	public static void updateDataFiles() {
		// Save any changes to electronic products
		SystemData.saveElectronicProductsListToFile(eproductsListPath, eproductsList);
		
		// Save any changes to books
		SystemData.saveBooksListToFile(booksListPath, booksList);

		// Save any changes to members
		SystemData.saveCustomersListToFile(customersListPath, membersList);

		// Save any new transactions
		SystemData.saveTransactionsHistoryToFile(transactionHistoryPath, transactionsHistory);
	}

}
