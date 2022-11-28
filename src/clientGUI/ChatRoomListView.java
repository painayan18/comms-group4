package clientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import requestResponse.Message;
import sharedData.ChatRoomDescription;
import sharedData.UserData;

public class ChatRoomListView {
	GUI.State state;
	UserData currentUser;
	
	private ObjectOutputStream objectOutputStream;
	private ArrayList<ChatRoomDescription> chatData;
	
	private JFrame frame;
	private JSplitPane splitPaneUD; // up-down split
	private JList<String> chatList; // the up
	private JPanel buttonPane; // the down
	
	private JButton backButton; 
	private JButton goToUserListButton;
	private JButton openChatRoomButton;
	
	/* splitPaneUD in frame
	 *  ___________________
	 * |                   |
	 * |chatList           |
	 * |                   |
	 * |___________________|
	 * |                   |
	 * |buttonPane         |
	 * ||back||users||open||
	 * |___________________|
	 */
	
	/* SPECIAL
	 * splitPaneUD in frame
	 *  ___________________
	 * |                   |
	 * |chatList           |
	 * |                   |
	 * |___________________|
	 * |                   |
	 * |buttonPane         |
	 * ||back||select|     |
	 * |___________________|
	 */
	
	public ChatRoomListView(UserData currentUser, ObjectOutputStream objectOutputStream, GUI.State state) {
		this.state = state;
		this.currentUser = currentUser;
		this.objectOutputStream = objectOutputStream;
		
		// I'm putting this in a weird order because I worry about 
		// referencing it during asynchronous behavior later. 
		chatList = new JList<String>(); 
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
			
		});
		
		if(state == GUI.State.SCRLV) {
			goToUserListButton = new JButton("Add Users To Chat");
			goToUserListButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					goToUserList();
				}
				
			});
			openChatRoomButton = null;
			
			buttonPane = new JPanel();
			buttonPane.add(backButton);
			buttonPane.add(goToUserListButton);
		} 
		else {
			goToUserListButton = new JButton("User List");
			goToUserListButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					goToUserList();
				}
				
			});
			openChatRoomButton = new JButton("Form new Chat Room");
			openChatRoomButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					openChatRoom();
				}
				
			});

			buttonPane = new JPanel();
			buttonPane.add(backButton);
			buttonPane.add(goToUserListButton);
			buttonPane.add(openChatRoomButton);
		}
				
		
		
		splitPaneUD = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userList, buttonPane);
		
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(splitPaneUD);
		
		requestChatRoomDescriptions();
		/* the following should happen asynchronously in updateChatRoomDescriptions()
		Vector<String> chatStrings = new Vector<String>(); 
		for (ChatRoomDescription chat : chatData) {
			String str = chat.getID(); //TODO change to something other than ID
			chatStrings.add();
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
	
	private void requestChatRoomDescriptions() {
		Message message = new Message();
		message.setType(Message.Type.CR_DESC);
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateChatRoomDescriptions(Message message) {
		try {
			chatData = message.getCRDS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vector<String> chatStrings = new Vector<String>(); 
		for (ChatRoomDescription chat : chatData) {
			String str = "Chat Room ID: " + chat.getId(); //TODO change to something other than ID
			chatStrings.add(str);
		}
		chatList.setListData(chatStrings);
		if (chatStrings.size() != 0) {
			chatList.setSelectedIndex(0); 
		}
		else {
			chatList.setSelectedIndex(-1);
		}
	}
	
	private void goToUserList() {
		state = GUI.State.ULV;
		frame.setVisible(false);
		if(state != GUI.State.SCRLV) {
			frame.dispose(); // we should not dispose when 
		}
		notify(); // GUI should have been waiting, so this should wake it.
	}
	
	private void openChatRoom() {
		state = GUI.State.CRV;
		frame.setVisible(false);
		//frame.dispose(); 
		notify(); // GUI should have been waiting, so this should wake it.
	}
	
	public ChatRoomDescription getSelectedChatRoom() {
		int chatIndex = chatList.getSelectedIndex();
		return chatData.get(chatIndex);
	}
	
	public void close() {
		state = GUI.State.LV;
		frame.setVisible(false);
		frame.dispose(); 
		notify(); // GUI should have been waiting, so this should wake it.
	}

}
