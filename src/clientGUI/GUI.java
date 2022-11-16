package clientGUI;

import java.io.ObjectOutputStream;

import sharedData.*;
import requestResponse.*;

public class GUI implements Runnable {
	ObjectOutputStream objectOutputStream;
	UserData currentUser;
	enum State {LV, CRLV, CRV, ULV, SCRLV, ITCRLV, ITCRV};
	/*
	 * LV		login view 
	 * CRLV		chat room list view
	 * CRV		chat room view
	 * ULV		user list view
	 * SCRLV	special chat room view
	 * 
	 * ITCRLV	IT chat room list view
	 * ITCRV	IT chat room view
	 */
	State state;
	
	ChatRoomListView crlv;
	ChatRoomView crv;
	UserListView ulv;
	LoginView lv;
	
	GUI(UserData currentUser, ObjectOutputStream objectOutputStream) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(Message message) {
		
	}
	
	public void loginUpdate(LoginMessage loginMessage) {
		
	}
	
	private void doChatRoomListView() {
		
	}
	
	private void doChatRoomView() {
		
	}
	
	private void doUserListView() {
		
	}
	
	private void doLoginView() {
		
	}
}
