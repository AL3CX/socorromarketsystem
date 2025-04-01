package socorromarketsystem;

import java.util.ArrayList;

// Customer is a subclass of User
public class Customer extends User {
	// Customer attributes
	// Contact info
	private String phoneNumber;
	private String email;
	// Purchase history string array
	private String[] purchaseHistory;
	// Current shopping cart
	public ArrayList<int[]> cart;

	// Customer constructor
	public Customer(int id, String name, String phoneNumber,
			String email, String[] purchaseHistory) {
		super(id, name);
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.purchaseHistory = purchaseHistory;
	}

	// Setters + Getters
	public String getPhoneNumber() { return this.phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	public String[] getPurchaseHistory() { return this.purchaseHistory; }
	public void setPurchaseHistory(String[] purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	// Override default print format
	@Override
	public String toString() {

		// Make purchaseHistory String
		String purchaseHistoryStr = String.join(";", getPurchaseHistory());

		return "\n\n\tCustomer ID: " + id +
		       "\n\tName: " + getName() +
		       "\n\tPhone #: " + getPhoneNumber() +
		       "\n\tEmail: " + getEmail() +
		       "\n\tPurchase History: " + purchaseHistoryStr;
	}

	// Function to simulate a checkout, calculating total cost
	public double generateCheckout() {
		double totalCost = 0.0;
		double discountTotal = 0.0;
		System.out.println();
		System.out.println("\t\t=================================================\n");
		System.out.println("\t\t\t\t\t CHECKOUT");

		// Display products purchasing
		for(int[] entry:cart) {
			// Search for id mathces in electronic products
			for(ElectronicProduct eproduct:SystemMain.eproductsList) {
				if (entry[0] == eproduct.getProductID()) {
					// Add to cost
					totalCost += eproduct.getPrice()*entry[1]; //quantity*price
					// Add any product-specific discounts
					discountTotal += eproduct.discount;
					System.out.println(eproduct);
					System.out.println();
					System.out.println("\t\t\tquantity purchasing: "+entry[1]);

					// Update stock quantity
					int newQuantity = eproduct.getStockQuantity() - entry[1];
					eproduct.setStockQuantity(newQuantity);
				}
			}

			for(Book book:SystemMain.booksList) {
				if (entry[0] == book.getProductID()) {
					totalCost += book.getPrice()*entry[1]; // quantity*price
					// Add any product-specific discounts
					discountTotal += book.discount;
					System.out.println(book);
					System.out.println();
					System.out.println("\t\tquantity purchasing: "+entry[1]);

					// Update stock quantity
					int newQuantity = book.getStockQuantity() - entry[1];
					book.setStockQuantity(newQuantity);
				}
			}
		}

		System.out.println("\t\t\t---------------------------------------\n");
		

		// Apply any entire taxes & discounts
		totalCost += Product.taxes;
		discountTotal += Product.entireDiscount;
		totalCost -= discountTotal;

		System.out.println("\t\t\tTotal Cost: "+totalCost);
		System.out.println("\t\t\tTaxes: "+Product.taxes);
		System.out.println("\t\t\tDiscounts: "+discountTotal);
		System.out.println("\t\t=================================================\n");

		return totalCost;
	}
}
