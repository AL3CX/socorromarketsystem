package socorromarketsystem;

public class Transaction {

	// Transaction attributes
	public int id;
	private int customerID;
	private String[] productsPurchased;
	private String[] quantity;
	private double totalCost;
	private String date;
	// Store amount of transactions
	public static int nextTransactionID = 501; // 501 is the starting of the ids

	// Transaction constructor
	public Transaction(int id, int customerID, String[] productsPurchased,
			   String[] quantity, double totalCost, String date) {
		this.id = id;
		this.customerID = customerID;
		this.productsPurchased = productsPurchased;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.date = date;

		nextTransactionID++;
	}

	// Setters + Getters
	public int getCustomerID() { return this.customerID; }
	public String[] getProductsPurchased() { 
		return (productsPurchased != null ? productsPurchased: new String[0]); 
	}
	public String[] getQuantity() {
		return (quantity != null ? quantity : new String[0]); 
	}
	public double getTotalCost() { return this.totalCost; }
	public String getDate() { return this.date; }
	
	// Override default print format
	@Override
	public String toString() {

		// Make productsPurchased String
		String productsPurchasedStr = String.join(";", getProductsPurchased());

		// Make quantity String
		String quantityStr = String.join(";", getQuantity());


		return "\n\n\tTransaction ID: " + id +
		       "\n\tCustomer ID: " + getCustomerID() +
		       "\n\tProducts Purchased: " + productsPurchasedStr +
		       "\n\tQuantity: " + quantityStr +
		       "\n\tTotal Cost: " + getTotalCost() +
		       "\n\tDate: " + getDate();
	}
}
