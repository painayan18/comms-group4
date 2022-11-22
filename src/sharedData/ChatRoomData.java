package sharedData;

import java.util.List;

public class ChatRoomData {
	List<UserData> users;
	List<ChatMessage> messages;
	int id;
	
	public ChatRoomData(List<UserData> users, List<ChatMessage> messages, int id) {
		this.users = users;
		this.messages = messages;
		this.id = id;
	}

	public List<UserData> getUsers() {
		return users;
	}

	public List<ChatMessage> getMessages() {
		return messages;
	}

	public int getID() {
		return id;
	}
	
	
}
