package socorromarketsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SystemData {
	
	public static void readElectronicProductsFromFile(String filePath, ArrayList<Product.ElectronicProduct> eproductsList) {
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

				Product.ElectronicProduct eproduct = new Product.ElectronicProduct(productID, name, price, description, stockQuantity, brand, model, warrantyPeriod);

				eproductsList.add(eproduct);
			}
			
			scannie.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
}
