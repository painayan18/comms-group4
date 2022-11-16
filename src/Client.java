import java.io.*; // streams
import java.net.*; // sockets

import clientGUI.GUI;

public class Client {
	private static Socket server = null;
	private static ObjectInputStream objectInputStream;
	private static ObjectOutputStream objectOutputStream;
	private static GUI gui;
	
	public static void main(String[] args) {
		try {
			server = new Socket("127.0.0.1", 4242);
			
			// for sending to server
			OutputStream outputStream = server.getOutputStream(); 
			objectOutputStream = new ObjectOutputStream(outputStream);
			//example: objectOutputStream.writeObject(message);
			
			// for reading from server
			InputStream inputStream = server.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			//example: Message message = (Message) objectInputStream.readObject(); // note: blocking
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void chatMessageNotification() {
		
	}
}
