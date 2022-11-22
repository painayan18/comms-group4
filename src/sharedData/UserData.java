package sharedData;

import java.io.Serializable;

public class UserData implements Serializable {
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
	
}
