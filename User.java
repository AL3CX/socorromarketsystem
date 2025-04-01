package socorromarketsystem;

public class User {
	// User attributes
	public int id;
	private String name;
	
	// User constructor
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	// Setters + Getters
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
}
