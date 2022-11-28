package clientGUI;

import javax.swing.*;

import clientGUI.GUI.State;
import requestResponse.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import sharedData.UserData;

public class UserListView {
	GUI.State state;
	UserData currentUser;
	
	private ObjectOutputStream objectOutputStream;
	private ArrayList<UserData> userData;

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

	UserListView(UserData currentUser, ObjectOutputStream objectOutputStream) {
		this.state = State.ULV;
		this.currentUser = currentUser;
		this.objectOutputStream = objectOutputStream;
		
		
		// I'm putting this in a weird order because I worry about 
		// referencing it during asynchronous behavior later. 
		userList = new JList<String>(); 
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
			
		});
		addToChatRoomButton = new JButton("Add to Chat Room");
		addToChatRoomButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addUsersToChatRoom();
			}
			
		});
		formNewChatRoomButton = new JButton("Form new Chat Room");
		formNewChatRoomButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				formNewChatRoom();
			}
			
		});

		
		buttonPane = new JPanel();
		buttonPane.add(backButton);
		buttonPane.add(addToChatRoomButton);
		buttonPane.add(formNewChatRoomButton);
		
		splitPaneUD = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userList, buttonPane);
		
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(splitPaneUD);
		
		requestUserData();
		/* the following should happen asynchronously in updateUserData()
		Vector<String> userNames = new Vector<String>(); 
		for (UserData user : userData) {
			userNames.add(user.getName());
		}
		userList.setListData(userNames);
		if (userNames.size() != 0) {
			userList.setSelectedIndex(0); 
		}
		else {
			userList.setSelectedIndex(-1);
		}
		*/		
		
		
	}
	
	public GUI.State getState() {
		return this.state;
	}
	
	public void show() {
		frame.setEnabled(true);
		frame.setVisible(true);
	}
	
	private void requestUserData() {
		Message message = new Message();
		message.setType(Message.Type.U_DATA);
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserData(Message message) {
		try {
			userData = message.getUserData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vector<String> userNames = new Vector<String>(); 
		for (UserData user : userData) {
			userNames.add(user.getName());
		}
		userList.setListData(userNames);
		if (userNames.size() != 0) {
			userList.setSelectedIndex(0); 
		}
		else {
			userList.setSelectedIndex(-1);
		}
	}
	
	private void formNewChatRoom() {
		state = GUI.State.CRLV;
		frame.setVisible(false);
		//frame.dispose(); 
		notify(); // GUI should have been waiting, so this should wake it. 
	}
	
	public ArrayList<UserData> getSelectedUsers() {
		ArrayList<UserData> selectedUsers = new ArrayList<UserData>();
		int[] userIndices = userList.getSelectedIndices();
		for (int index : userIndices) {
			selectedUsers.add(userData.get(index));
		}
		return selectedUsers;
	}
	
	private void addUsersToChatRoom() {
		state = GUI.State.SCRLV;
		frame.setVisible(false);
		notify(); // GUI should have been waiting, so this should wake it. 
	}
	
	public void close() {
		state = GUI.State.CLOSED;
		frame.setVisible(false);
		frame.dispose();
		notify(); // GUI should have been waiting, so this should wake it. 
	}
	
}
