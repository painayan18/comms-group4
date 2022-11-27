package clientGUI;

import java.io.ObjectOutputStream;

import sharedData.*;
import requestResponse.*;

public class GUI implements Runnable {
	private ObjectOutputStream objectOutputStream;
	private UserData currentUser;
	public static enum State {LV, CRLV, CRV, ULV, SCRLV, ITCRLV, ITCRV, CLOSED};
	/*
	 * LV		login view 
	 * CRLV		chat room list view
	 * CRV		chat room view
	 * ULV		user list view
	 * SCRLV	special chat room view
	 * 
	 * ITCRLV	IT chat room list view
	 * ITCRV	IT chat room view
	 * 
	 * CLOSED --> should be used to stop Client from going on. Could also be default before running. 
	 */
	private State state;
	
	private ChatRoomListView crlv;
	private ChatRoomView crv;
	private UserListView ulv;
	private LoginView lv;
	
	GUI(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
		crlv = null;
		crv = null;
		ulv = null;
		lv = null;
		
		state = State.CLOSED;
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
