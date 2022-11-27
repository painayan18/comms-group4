import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import server.*;

public class Server {
	// I reckon TreeMaps would be more efficient if we made the right comparators. Lists will do for now.
	private static List<User> users; 
	private static List<ChatRoom> chatRooms;
	
	public static void main(String[] args) {
		ServerSocket server = null;
		int numClients = 0;
		try {
			server = new ServerSocket(4242);
			server.setReuseAddress(true);
			while(true) {
				Socket client = server.accept();
				numClients++;
				ClientHandler clientHandler = new ClientHandler(client, numClients);
				new Thread(clientHandler).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void load() {
		
	}
	
	private static void save() {
		
	}
	
	private static class ClientHandler implements Runnable {
		private final Socket client;
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		int clientID; // clientID is mostly here for if it ever makes debugging easier
		
		public ClientHandler(Socket client, int clientID) {
			this.client = client;
			this.clientID = clientID;
		}
		
		@Override
		public void run() {
			try {
				// for sending to client
				OutputStream outputStream = client.getOutputStream(); 
				objectOutputStream = new ObjectOutputStream(outputStream);
				//example: objectOutputStream.writeObject(message);
				
				// for reading from client
				InputStream inputStream = client.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);
				//example: Message message = (Message) objectInputStream.readObject(); // note: blocking
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				// close socket and terminate thread
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
