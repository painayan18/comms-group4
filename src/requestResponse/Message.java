package requestResponse;

import java.io.Serializable;
import java.util.ArrayList;

import sharedData.*;

public class Message implements Serializable {
	public static enum Type {CR_DESC, CR_DATA, U_DATA, CM, EMPTY};
	private Type type;
	
	private ArrayList<String> objectsAsString;
	
	Message() {
		type = Type.EMPTY;
	}
	
	private void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setCRDS(ArrayList<ChatRoomDescription> crds) {
		objectsAsString.clear();
		for(ChatRoomDescription crd : crds) {
			objectsAsString.add(crd.toString());
		}
		setType(Type.CR_DESC);
	}
	
	public ArrayList<ChatRoomDescription> getCRDS() throws Exception {
		if(this.type != Type.CR_DESC) throw new Exception("Wrong type of get called for current message type!");
		ArrayList<ChatRoomDescription> crds = new ArrayList<ChatRoomDescription>();
		for(String str : objectsAsString ) {
			crds.add(new ChatRoomDescription(str));
		}
		return crds;
	}
	
	public void setCRD(ChatRoomData crd) {
		objectsAsString.clear();
		objectsAsString.add(crd.toString());
		setType(Type.CR_DATA);
	}
	
	public ChatRoomData getCRD() throws Exception {
		if(this.type != Type.CR_DATA) throw new Exception("Wrong type of get called for current message type!");
		return new ChatRoomData(objectsAsString.get(0));
	}
	
	public void setUserList(ArrayList<UserData> userList) {
		objectsAsString.clear();
		for (UserData user : userList) {
			objectsAsString.add(user.toString());
		}
		setType(Type.U_DATA);
	}
	
	public ArrayList<UserData> getUserList() throws Exception {
		if(this.type != Type.U_DATA) throw new Exception("Wrong type of get called for current message type!");
		ArrayList<UserData> userList = new ArrayList<UserData>();
		for(String str : objectsAsString ) {
			userList.add(new UserData(str));
		}
		return userList;
	}
	
	public void setChatMessage(ChatMessage chatMessage) {
		objectsAsString.clear();
		objectsAsString.add(chatMessage.toString());
		setType(Type.CM);
	}
	
	public ChatMessage getChatMessage() throws Exception {
		if(this.type != Type.CM) throw new Exception("Wrong type of get called for current message type!");
		return new ChatMessage(objectsAsString.get(0));
	}
}
