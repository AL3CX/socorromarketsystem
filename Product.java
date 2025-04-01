package socorromarketsystem;

public class Product {
	// Product Attributes
	private int productID;
	private String name;
	private double price;
	private String description;
	private int stockQuantity;
	public double discount = 0.0;

	// applicable to all products
	public static double entireDiscount = 0.0;
	public static double taxes = 0.0;
		
	// Product Constructor
	public Product(int productID, String name, double price,
		       String description, int stockQuantity) {
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}

	// Setters + Getters
	public int getProductID() { return this.productID; }
	public void setProductID(int productID) { this.productID = productID; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	public double getPrice() { return this.price; }
	public void setPrice(double price) { this.price = price; }
	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }
	public int getStockQuantity() { return this.stockQuantity; }
	public void setStockQuantity(int stockQuantity){ this.stockQuantity = stockQuantity; }
}

