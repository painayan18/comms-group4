package clientGUI;

import javax.swing.*;

import requestResponse.Message;

import java.awt.*;
import java.io.ObjectOutputStream;
import java.util.List;

import sharedData.UserData;

public class UserListView {
	GUI.State state;
	UserData currentUser;
	
	private ObjectOutputStream objectOutputStream;
	private List<UserData> userData;

	private JFrame frame;
	private JSplitPane splitPaneUD; // up-down split
	private JList<String> userList; // the up
	private JPanel buttonPane; // the down
	
	private JButton backButton; 
	private JButton addToChatRoomButton;
	private JButton formNewChatRoomButton;
	
	/* splitPaneUD in frame
	 *  __________________
	 * |                  |
	 * |userList          |
	 * |                  |
	 * |__________________|
	 * |                  |
	 * |buttonPane        |
	 * ||back||add||form| |
	 * |__________________|
	 */

	UserListView(UserData currentUser, ObjectOutputStream objectOutputStream, GUI.State state) {
		this.currentUser = currentUser;
		this.objectOutputStream = objectOutputStream;
		this.state = state;
		
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		//frame.setContentPane(splitPaneUD);
	}
	
	private void requestUserData() {
		Message message;
		objectOutputStream.writeObject(message);
	}
	
	private void formNewChatRoom() {
		
	}
	
	private void addUsersToChatRoom() {
		
	}
	
}
