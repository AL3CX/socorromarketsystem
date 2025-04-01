package socorromarketsystem;

// Electronic Product Subclass of Product
public class ElectronicProduct extends Product {
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
	
	// Setters + Getters
	public String getBrand() { return this.brand; }
	public String getModel() { return this.model; }
	public String getWarrantyPeriod() { return this.warrantyPeriod; }

	// Override default print format
	@Override
	public String toString() {
		return "\n\n\tProduct ID: " + getProductID() +
		       "\n\tName: " + getName() +
		       "\n\tPrice: " + getPrice() +
		       "\n\tDescription: " + getDescription() +
		       "\n\tStock Quantity: " + getStockQuantity() +
		       "\n\tBrand: " + getBrand() +
		       "\n\tModel: " + getModel() +
		       "\n\tWarranty Period: " + getWarrantyPeriod();
	}
}
