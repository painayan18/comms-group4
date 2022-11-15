import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.*;

public class Server {
	public static void main(String[] args) {
		int numClients = 0;
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(4242);
			server.setReuseAddress(true);
			while(true) {
				Socket client = server.accept();
				ClientHandler clientHandler = new ClientHandler(client);
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
	
	private static class ClientHandler implements Runnable {
		private final Socket client;
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		
		public ClientHandler(Socket client) {
			this.client = client;
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
				
			} catch (IOException | ClassNotFoundException e) {
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
