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
		currentUser = null;
		
		crlv = null;
		crv = null;
		ulv = null;
		lv = null;
		
		state = State.CLOSED;
	}

	@Override
	public void run() {
		doLoginView();
	}
	
	public void update(Message message) {
		
	}
	
	public void loginUpdate(LoginMessage loginMessage) {
		currentUser = loginMessage.getUserData();
		if(currentUser != null) lv.close();
	}
	
	private void doChatRoomListView() {
		
	}
	
	private void doChatRoomView() {
		
	}
	
	private void doUserListView() {
		state = State.ULV;
	}
	
	private void doLoginView() {
		state = State.LV;
		lv = new LoginView();
		lv.show();
		
		try {
			wait();
			// wait for loginUpdate to be called
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (lv.getState() == State.CLOSED) {
			lv = null;
			state = State.CLOSED;
		}
		else {
			lv = null;
			doChatRoomListView();
		}
	}
}
