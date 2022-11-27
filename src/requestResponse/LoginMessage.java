package requestResponse;

import java.io.Serializable;

import sharedData.UserData;

public class LoginMessage implements Serializable {
	private String username;
	private String password;
	private String userDataAsString;
	private boolean isIT;
	
	public LoginMessage(String username, String password, boolean isIT) {
		this.username = username;
		this.password = password;
		this.userDataAsString = null;
		this.isIT = isIT;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserData getUserData() throws Exception {
		if (userDataAsString == null) return null;
		return new UserData(userDataAsString);
	}

	public void setUserData(UserData userData) {
		this.userDataAsString = userData.toString();
	}

	public boolean isIT() {
		return isIT;
	}
	
}
