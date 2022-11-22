package sharedData;

import java.util.List;

public class ChatRoomDescription {
	List<UserData> users;
	ChatMessage mostRecentMessage;
	int id;
	
	public ChatRoomDescription(List<UserData> users, ChatMessage mostRecentMessage, int id) {
		this.users = users;
		this.mostRecentMessage = mostRecentMessage;
		this.id = id;
	}

	public List<UserData> getUsers() {
		return users;
	}

	public ChatMessage getMostRecentMessage() {
		return mostRecentMessage;
	}

	public int getId() {
		return id;
	}

}
