package clientGUI;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
	
	public GUI(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
		currentUser = null;
		
		pendingNewChats = new ArrayList<ChatRoomDescription>();
		
		crlv = null;
		crv = null;
		ulv = null;
		lv = null;
		
		state = State.CLOSED;
	}

	@Override
	public void run() {
		state = State.LV;
		while(state != State.CLOSED) {
			switch (state) {
			case LV: 
				doLoginView();
				break;
				
			case ULV: 
				doUserListView();
				break;
				
			case ITCRLV:
			case SCRLV:
			case CRLV:
				doChatRoomListView();
				break; 
				
			case ITCRV:
			case CRV:	
				doChatRoomView();
				break; 
				
			case CLOSED:
				break;
			}
		}
	}
	
	public void update(Message message) {
		switch (state) {
		case LV:
			break; // should be dealing with LoginMessage in LV
			
		case ULV:
			ulv.updateUserData(message); // should deal with CR_DATA type
			break;
		
		case ITCRLV:
		case SCRLV:
		case CRLV:
			crlv.updateChatRoomDescriptions(message); // should deal with CR_DESC type
			break; 
			
		case ITCRV:
		case CRV:	
			crv.refreshForNewMessage(message); // should deal with both U_DATA and CM types
			break; 
			
		case CLOSED:
			break; // I don't think any messages should appear in this state.
		}
	}
	
	public void loginUpdate(LoginMessage loginMessage) {
		try {
			currentUser = loginMessage.getUserData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(currentUser != null) lv.close();
	}
	
	private void doChatRoomListView() {
		if (state == State.ULV) {
			state = State.SCRLV; // for non-special cases, the state should be right already
			crlv = new ChatRoomListView(currentUser, objectOutputStream, State.SCRLV);
			crlv.show();
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/* this will happen in doUserListView
			if (crlv.getState() == State.CLOSED) {
				crlv = null;
				state = State.ULV;
			}
			else {
				crlv = null;
				state = State.CRV; // note: our original state diagram doesn't show this move, but it's what I want
			}
			*/
		}
		else {
			crlv = new ChatRoomListView(currentUser, objectOutputStream, State.CRLV);
			crlv.show();
		}
	}
	
	private void doChatRoomView() {
		
	}
	
	private void doUserListView() {
		ulv = new UserListView(currentUser, objectOutputStream);
		ulv.show();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (ulv.getState() == State.SCRLV) {
			ArrayList<UserData> selectedUsers = ulv.getSelectedUsers();
			ulv.close();
			doChatRoomListView(); // note: normally we'd change state and exit back to run, but this is the special one. 
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ChatRoomDescription selectedChatRoom = crlv.getSelectedChatRoom();
			for(UserData user : selectedUsers) {
				selectedChatRoom.addUser(user);
			}
			
			objectOutputStream.writeObject(); //TODO left off here.
			
			if (crlv.getState() == State.CLOSED) {
				crlv = null;
				state = State.ULV;
			}
			else {
				crlv.close();
				crlv = null;
				state = State.CRV; // note: our original state diagram doesn't show this move, but it's what I want
			}
			
		}
		else if (ulv.getState() == State.CRLV) {
			state = State.CRLV;
		}
		else {
			
		}
	}
	
	// done
	private void doLoginView() {
		lv = new LoginView();
		lv.show();
		
		try {
			wait();
			// wait for loginUpdate() to be called by client, which calls lv.close(), which calls notify()
			// or wait for close button to be pressed, which calls notify()
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (lv.getState() == State.CLOSED) {
			lv = null;
			state = State.CLOSED;
		}
		else {
			lv.close();
			lv = null;
			state = State.CRLV;
		}
	}
}
