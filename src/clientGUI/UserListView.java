package clientGUI;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.util.List;

import sharedData.UserData;

public class UserListView implements Runnable {
	GUI.State state;
	
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	} 
	
	private void requestUserData() {
		
	}
	
	private void formNewChatRoom() {
		
	}
	
	private void addUsersToChatRoom() {
		
	}
	
}
