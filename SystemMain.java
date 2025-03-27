package socorromarketsystem;

import java.util.Scanner;
import java.util.ArrayList;

public class SystemMain {
	public static void main(String[] args) {
		// Initializing data lists
		ArrayList<Product.ElectronicProduct> eproductsList = new ArrayList<>();

		// File paths to data files
		String eproductsListPath = "socorromarketsystem/systemfiles/Electronic_Products_Inventory.csv";

		// Declaring data lists from files
		SystemData.readElectronicProductsFromFile(eproductsListPath,
							  eproductsList);
	}
}
