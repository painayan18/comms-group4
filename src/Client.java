import java.io.*; // streams
import java.net.*; // sockets
import java.util.Scanner;

import clientGUI.GUI;

public class Client {
	
	public static void main(String[] args) {
		Socket server = null;
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		
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
}
