package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import requestResponse.Message;
import sharedData.ChatMessage;
import sharedData.UserData;

public class User {
	private static int idCount = 0; 
	
	private ObjectOutputStream objectOutputStream;
	private String name;
	private String username;
	private String password;
	private int id;
	private boolean isIT;
	// I'm not liking the way I store ChatMessages again here; they are duplicates. 
	// We already have them in the ChatRooms of the Server.
	private ArrayList<ChatMessage> pendingMessages; 
	
	public User(String name, String username, String password, boolean isIT) {
		this.objectOutputStream = null;
		this.name = name;
		this.username = username;
		this.password = password;
		this.isIT = isIT;
		
		idCount++;
		this.id = idCount; // id could change with each server restart
	}
	
	public boolean areCredentialsValid(String userName, String password) {
		return this.username.equals(userName) && this.password.equals(password);
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public boolean isIT() {
		return isIT;
	}

	public void addPendingMessage(ChatMessage chatMessage) {
		if (objectOutputStream != null) {
			Message message = new Message();
			message.setChatMessage(chatMessage);
			try {
				objectOutputStream.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			pendingMessages.add(chatMessage);
		}
	}

	public UserData getUserData() {
		return new UserData(name, id, isIT);
	}
}
