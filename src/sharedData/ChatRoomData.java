package sharedData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public ChatRoomData(String objectAsString) throws Exception {
		String[] fields = objectAsString.split("\n", 3);
		if (fields.length == 3 && fields[0] == "ChatRoomData") {
			this.id = Integer.parseInt(fields[1]);
			
			Pattern userDataPattern = Pattern.compile("UserData\\n[^\\n]*\\n[^\\n]*\\n[^\\n]*\\n");
			Matcher userDataMatcher = userDataPattern.matcher(fields[3]);
			while(userDataMatcher.find()) {
				String userDataMatch = userDataMatcher.group();
				users.add(new UserData(userDataMatch));
			}
			
			Pattern chatMessagePattern = Pattern.compile("ChatMessage\\n[^\\n]*\\n[^\\n]*\\n[^\\n]*\\n[\\n]*\\n");
			Matcher chatMessageMatcher = chatMessagePattern.matcher(fields[3]);
			while(chatMessageMatcher.find()) {
				String chatMessageMatch = chatMessageMatcher.group();
				messages.add(new ChatMessage(chatMessageMatch));	
			}
		}
		else {
			throw new Exception("Invalid string for ChatRoomData!");
		}
	}
	
	@Override
	public String toString() {
		String str = "ChatRoomData" + "\n" + id + "\n";
		for (UserData user : users) {
			str += user;
		}
		for (ChatMessage message : messages) {
			str += message;
		}
		return str;
	}
}
