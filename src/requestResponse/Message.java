package requestResponse;

import java.io.Serializable;
import java.util.List;

import sharedData.*;

public class Message implements Serializable {
	public static enum Type {CR_DESC, CR_DATA, U_DATA, CM, EMPTY};
	private Type type;
	
	private List<String> objectsAsString;
	
	Message() {
		type = Type.EMPTY;
	}
	
	private void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setCRDS(List<ChatRoomDescription> crds) {
		objectsAsString.clear();
		for(ChatRoomDescription crd : crds) {
			objectsAsString.add(crd.toString());
		}
		setType(Type.CR_DESC);
	}
	
	public List<ChatRoomDescription> getCRDS() throws Exception {
		if(this.type != Type.CR_DESC) throw new Exception("Wrong type of get called for current message type!");
		List<ChatRoomDescription> crds;
		for(String str : objectsAsString ) {
			crds.add(new ChatRoomDescription(str));
		}
		return crds;
	}
	
	public void setCRD(ChatRoomData crd) {
		this.crd = crd;
	}
	
	public ChatRoomData getCRD() {
		return crd;
	}
	
	public void setUserList(List<UserData> userList) {
		this.userList = userList;
	}
	
	public List<UserData> getUserList() {
		return userList;
	}
	
	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}
	
	public ChatMessage getChatMessage() {
		return chatMessage;
	}
}
