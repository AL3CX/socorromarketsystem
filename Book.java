package socorromarketsystem;

// Book Subclass
public class Book extends Product {
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

	// Setters + Getters
	public String getAuthor() { return this.author; }
	public long getISBN() { return this.ISBN; }
	public String getPublisher() { return this.publisher; }
	public String getGenre() { return this.genre; }

	// Override default print format 
	@Override
	public String toString() {
		return "\n\n\tProduct ID: " + getProductID() +
		       "\n\tName: " + getName() +
		       "\n\tPrice: " + getPrice() +
		       "\n\tDescription: " + getDescription() +
		       "\n\tStock Quantity: " + getStockQuantity() +
		       "\n\tAuthor: " + getAuthor() +
		       "\n\tISBN: " + getISBN() +
		       "\n\tPublisher: " + getPublisher() +
		       "\n\tGenre: " + getGenre();
	}
}
