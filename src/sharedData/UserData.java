package sharedData;

public class UserData {
	String name;
	int id;
	boolean isIT;
	
	public UserData(String name, int id, boolean isIT) {
		this.name = name;
		this.id = id;
		this.isIT = isIT;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public boolean isIT() {
		return isIT;
	}
	
	public UserData(String objectAsString) throws Exception {
		String[] fields = objectAsString.split("\n");
		if (fields.length == 4 && fields[0] == "UserData") {
			this.name = fields[1];
			this.id = Integer.parseInt(fields[2]);
			this.isIT = (fields[3].equals("y"));
		}
		else {
			throw new Exception("Invalid string for UserData!");
		}
	}
	
	@Override
	public String toString() {
		return "UserData" + "\n" + name + "\n" + id + "\n" + (isIT ? "y" : "n") + "\n";
	}
}
