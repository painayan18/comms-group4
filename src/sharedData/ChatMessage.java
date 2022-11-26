package sharedData;

public class ChatMessage {
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
	
	public ChatMessage(String objectAsString) throws Exception {
		String[] fields = objectAsString.split("\n");
		if (fields.length == 5 && fields[0] == "ChatMessage") {
			this.senderName = fields[1];
			this.senderID = Integer.parseInt(fields[2]);
			this.groupID = Integer.parseInt(fields[3]);
			this.contents = fields[4];
		}
		else {
			throw new Exception("Invalid string for ChatMessage!");
		}
	}
	
	@Override
	public String toString() {
		return "ChatMessage" + "\n" + senderName + "\n" + senderID + "\n" + groupID + "\n" + contents + "\n";
	}
	
}
