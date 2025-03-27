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
	public static class ElectronicProduct extends Product {
		// Electronic Product Attributes
		private String brand;
		private String model;
		private String warrantyPeriod;

		// Electronic Product Constructor
		public ElectronicProduct(int productID, String name, double price,
								 String description, int stockQuantity,
								 String brand, String model,
					  			 String warrantyPeriod) {
			super(productID, name, price, description, stockQuantity);
			this.brand = brand;
			this.model = model;
			this.warrantyPeriod = warrantyPeriod;
		}

		// Override default print format
		@Override
		public String toString() {
			return "\n\n\tProduct ID: " + this.productID +
			       "\n\tName: " + this.name +
			       "\n\tPrice: " + this.price +
			       "\n\tDescription: " + this.description +
			       "\n\tStock Quantity: " + this.stockQuantity +
			       "\n\tBrand: " + this.brand +
			       "\n\tModel: " + this.model +
			       "\n\tWarranty Period: " + this.warrantyPeriod;
		}
	}

	// Book Subclass
	public static class Book extends Product {
		// Book Attributes
		private String author;
		private long ISBN;
		private String publisher;
		private String genre;

		// Book Constructor
		public Book(int productID, String name, double price,
					String description, int stockQuantity,
					String author, long ISBN, String publisher,
					String genre) {
			super(productID, name, price, description, stockQuantity);
			this.author = author;
			this.ISBN = ISBN;
			this.publisher = publisher;
			this.genre = genre;
		}
	}
}

