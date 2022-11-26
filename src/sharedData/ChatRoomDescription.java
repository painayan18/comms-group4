package sharedData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatRoomDescription {
	List<UserData> users;
	ChatMessage mostRecentMessage;
	int id;
	
	public ChatRoomDescription() {
		this.users = null;
		this.mostRecentMessage = null;
		this.id = -1;
	}
	
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
	
	public ChatRoomDescription(String objectAsString) throws Exception {
		String[] fields = objectAsString.split("\n", 3);
		if (fields.length == 3 && fields[0] == "ChatRoomDescription") {
			this.id = Integer.parseInt(fields[1]);
			
			Pattern userDataPattern = Pattern.compile("UserData\\n[^\\n]*\\n[^\\n]*\\n[^\\n]*\\n");
			Matcher userDataMatcher = userDataPattern.matcher(fields[3]);
			while(userDataMatcher.find()) {
				String userDataMatch = userDataMatcher.group();
				users.add(new UserData(userDataMatch));
			}
			
			Pattern chatMessagePattern = Pattern.compile("ChatMessage\\n[^\\n]*\\n[^\\n]*\\n[^\\n]*\\n[\\n]*\\n");
			Matcher chatMessageMatcher = chatMessagePattern.matcher(fields[3]);
			String chatMessageMatch = chatMessageMatcher.group();
			this.mostRecentMessage = new ChatMessage(chatMessageMatch);	
		}
		else {
			throw new Exception("Invalid string for ChatRoomDescription!");
		}
	}
	
	@Override
	public String toString() {
		String str = "ChatRoomDescription" + "\n" + id + "\n";
		for (UserData user : users) {
			str += user;
		}
		str += mostRecentMessage;
		return str;
	}

}
