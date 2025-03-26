package socorromarketsystem;

public class Product {
	// Product Attributes
	private int productID;
	private String name;
	private double price;
	private String description;
	private int stockQuantity;
		
	// Product Constructor
	public Product(int productID, String name, double price,
		       String description, int stockQuantity) {
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}

	// Electronic Product Subclass
	public class ElectronicProduct extends Product {
		// Electronic Product Attributes
		private String brand;
		private String model;
		private String warrantyPeriod;

		// Electronic Product Constructor
		public ElectronicProduct(String brand, String model,
					  String warrantyPeriod) {
			super(
		}

