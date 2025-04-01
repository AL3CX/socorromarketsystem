package socorromarketsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SystemData {
	
	public static void readElectronicProductsFromFile(String filePath, ArrayList<ElectronicProduct> eproductsList) {
		File fp = new File(filePath);

		int productID;
		String name;
		double price;
		String description;
		int stockQuantity;
		String brand;
		String model;
		String warrantyPeriod;

		try {
			Scanner scannie = new Scanner(fp);

			if (scannie.hasNextLine()) {
				scannie.nextLine(); // skip header line
			}

			// Read and process each line of the CSV file
			while (scannie.hasNextLine()) {
				String line = scannie.nextLine();
				String[] tokens = line.split(",");
				productID = Integer.parseInt(tokens[0]);
				name = tokens[1];
				price = Double.parseDouble(tokens[2]);
				description = tokens[3];
				stockQuantity = Integer.parseInt(tokens[4]);
				brand = tokens[5];
				model = tokens[6];
				warrantyPeriod = tokens[7];

				ElectronicProduct eproduct = new ElectronicProduct(productID, name, price, description, stockQuantity, brand, model, warrantyPeriod);

				eproductsList.add(eproduct);
			}
			scannie.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static void readBooksFromFile(String filePath, ArrayList<Book> booksList) {
		File fp = new File(filePath);

		int productID;
		String name;
		double price;
		String description;
		int stockQuantity;
		String author;
		long ISBN;
		String publisher;
		String genre;

		try {
			Scanner scannie = new Scanner(fp);

			if (scannie.hasNextLine()) {
				scannie.nextLine(); // skip header line
			}

			// Read and process each line of the CSV file
			while(scannie.hasNextLine()) {
				String line = scannie.nextLine();
				String[] tokens = line.split(",");
				productID = Integer.parseInt(tokens[0]);
				name = tokens[1];
				price = Double.parseDouble(tokens[2]);
				description = tokens[3];
				stockQuantity = Integer.parseInt(tokens[4]);
				author = tokens[5];
				ISBN = Long.parseLong(tokens[6]);
				publisher = tokens[7];
				genre = tokens[8];

				Book book = new Book(productID, name, price, description, stockQuantity, author, ISBN, publisher, genre);

				booksList.add(book);
			}
			scannie.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static void readCustomersFromFile(String filePath, ArrayList<Customer> membersList) {
		File fp = new File(filePath);

		int customerID;
		String name;
		String phoneNumber;
		String email;
		String[] purchaseHistory;

		try {
			Scanner scannie = new Scanner(fp);

			if (scannie.hasNextLine()) {
				scannie.nextLine(); // skip header line
			}

			// Read and process each line of the CSV file
			while (scannie.hasNextLine()) {
				String line = scannie.nextLine();
				String[] tokens = line.split(",");
				customerID = Integer.parseInt(tokens[0]);
				name = tokens[1];
				phoneNumber = tokens[2];
				email = tokens[3];
				purchaseHistory = tokens[4].split(";");

				Customer member = new Customer(customerID, name, phoneNumber, email, purchaseHistory);

				membersList.add(member);
			}
			scannie.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static void readTransactionHistoryFromFile(String filePath, ArrayList<Transaction> transactionHistory) {
		File fp = new File(filePath);

		int id;
		int customerID;
		String[] productsPurchased;
		String[] quantity;
		double totalCost;
		String date;

		try {
			Scanner scannie = new Scanner(fp);

			if (scannie.hasNextLine()) {
				scannie.nextLine(); // skip header line
			}

			// Read and process each line of the CSV file
			while (scannie.hasNextLine()) {
				String line = scannie.nextLine();
				String[] tokens = line.split(",");
				id = Integer.parseInt(tokens[0]);
				customerID = Integer.parseInt(tokens[1]);
				productsPurchased = tokens[2].split(";");
				quantity = tokens[3].split(";");
				totalCost = Double.parseDouble(tokens[4]);
				date = tokens[5];

				Transaction transaction = new Transaction(id, customerID, productsPurchased, quantity, totalCost, date);

				transactionHistory.add(transaction);
			}
			scannie.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static void saveElectronicProductsListToFile(String filePath, ArrayList<ElectronicProduct> eproductsList) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

			// Write header line
			writer.println("Product ID,Name,Price,Description,Stock Quantity,Brand,Model,Warranty Period");

			// Print attributes to csv file
			for (ElectronicProduct ep:eproductsList) {
				// Convert entries
				writer.println(
					ep.getProductID() + "," +
					ep.getName() + "," +
					ep.getPrice() + "," +
					ep.getDescription() + "," +
					ep.getStockQuantity() + "," +
					ep.getBrand() + "," +
					ep.getModel() + "," +
					ep.getWarrantyPeriod());
			}
		} catch (IOException e) {
			System.out.println("Error writing to file: " + filePath);
			e.printStackTrace();
		}
	}

	public static void saveBooksListToFile(String filePath, ArrayList<Book> booksList) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

			// Write header line
			writer.println("Product ID,Name,Price,Description,Stock Quantity,Author,ISBN,Publisher,Genre");

			// Print attributes to csv file
			for(Book bk:booksList) {
				// Convert entries
				writer.println(
					bk.getProductID() + "," +
					bk.getName() + "," +
					bk.getPrice() + "," +
					bk.getDescription() + "," +
					bk.getStockQuantity() + "," +
					bk.getAuthor() + "," +
					bk.getISBN() + "," +
					bk.getPublisher() + "," +
					bk.getGenre());
			}
		} catch (IOException e) {
			System.out.println("Error writing to file: " + filePath);
			e.printStackTrace();
		}
	}

	public static void saveCustomersListToFile(String filePath, ArrayList<Customer> membersList) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

			// Write header line
			writer.println("Customer ID,Name,Phone #,Email,Purchase History");

			// Print attributes to csv file
			for(Customer c:membersList) {
				// purchaseHistory conversion
				String[] purchaseHistory = c.getPurchaseHistory();
				String purchaseHistoryStr = String.join(";", purchaseHistory);
				
				// Convert entries
				writer.println(
					c.id + "," +
					c.getName() + "," +
					c.getPhoneNumber() + "," +
					c.getEmail() + "," +
					purchaseHistoryStr);
			}
		} catch (IOException e) {
			System.out.println("Error writing to file: " + filePath);
			e.printStackTrace();
		}
	}
	
	public static void saveTransactionsHistoryToFile(String filePath, ArrayList<Transaction> transactionsHistory) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

			// Write header line
			writer.println("Transaction ID,Customer ID,Products Purchased,Quantity,Total Cost,Date");

			// Print attributes to csv file
			for (Transaction t:transactionsHistory) {
				// Products Purchased + Quantity conversion
				String[] productsPurchased = t.getProductsPurchased();
				String productsPurchasedStr = String.join(";", productsPurchased);

				String[] quantity = t.getQuantity();
				String quantityStr = String.join(";", quantity);

				// Convert entries
				writer.println(
					t.id + "," +
					t.getCustomerID() + "," +
					productsPurchasedStr + "," +
					quantityStr + "," +
					t.getTotalCost() + "," +
					t.getDate());
			}
		} catch (IOException e) {
			System.out.println("Error writing to file: " + filePath);
			e.printStackTrace();
		}
	}

}
