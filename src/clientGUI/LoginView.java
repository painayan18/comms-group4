package clientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.*;

import requestResponse.LoginMessage;

public class LoginView {
	private GUI.State state;
	private ObjectOutputStream objectOutputStream;
	
	private JFrame frame;
	private JSplitPane splitPaneUD; // up-down split
	private JPanel loginPane; // the up
	
	private JTextField usernameField;
	private JTextField passwordField;
	
	private JPanel buttonPane; // the down
	
	private JButton loginButton; 
	private JButton loginAsITButton;
	private JButton closeButton;
	
	/* splitPaneUD in frame
	 *  __________________
	 * |loginPane         |
	 * |    |username|    |
	 * |    |password|    |
	 * |__________________|
	 * |                  |
	 * |buttonPane        |
	 * ||close||IT||login||
	 * |__________________|
	 */
	
	public LoginView() {
		state = GUI.State.LV;
		usernameField = new JTextField();
		passwordField = new JTextField();
		
		loginPane = new JPanel(new BoxLayout(loginPane, BoxLayout.Y_AXIS));
		loginPane.add(usernameField);
		loginPane.add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendLoginRequest();
			}
			
		});
		loginAsITButton = new JButton("IT Login");
		loginAsITButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendLoginRequestAsIT();
			}
			
		});
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
			
		});
		
		buttonPane = new JPanel();
		buttonPane.add(loginButton);
		buttonPane.add(loginAsITButton);
		buttonPane.add(closeButton);
		
		splitPaneUD = new JSplitPane(JSplitPane.VERTICAL_SPLIT, loginPane, buttonPane);
		
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(splitPaneUD);
		
	}
	
	public void show() {
		frame.setEnabled(true);
		frame.setVisible(true);
	}
	
	private void sendLoginRequest() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		LoginMessage message = new LoginMessage(username, password, false);
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendLoginRequestAsIT() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		LoginMessage message = new LoginMessage(username, password, true);
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		state = GUI.State.CLOSED;
		frame.setVisible(false);
		frame.dispose();
		notify(); // GUI should have been waiting, so this should wake it. 
	}
}
