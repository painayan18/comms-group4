package sharedData;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	String senderName;
	int senderID;
	int groupID;
	String contents;

	ChatMessage(UserData sender, ChatRoomData recipient, String contents) {
		senderName = sender.getName();
		senderID = sender.getID();
		groupID = recipient.getID();
		this.contents = contents;
	}

	public String getSenderName() {
		return senderName;
	}

	public int getSenderID() {
		return senderID;
	}

	public int getGroupID() {
		return groupID;
	}

	public String getContents() {
		return contents;
	}
	
}
