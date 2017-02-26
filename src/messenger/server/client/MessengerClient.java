package messenger.server.client;

import java.awt.image.BufferedImage;
import java.net.Socket;

import messenger.server.MessengerServer;

public class MessengerClient {
	
	private static int currentClientID = 0;
	
	private MessengerServer messengerServer;
	
	private int clientID;
	private ClientConnection clientConnection;
	
	private String userName;
	private String userColor;
	private BufferedImage userImage;
	
	public MessengerClient(MessengerServer messengerServer){
		this.messengerServer = messengerServer;
		this.clientID = ++currentClientID;
	}
	
	public void acceptConnection(Socket socket){
		this.clientConnection = new ClientConnection(this, socket);
		this.clientConnection.start();
	}
	
	public ClientConnection getClientConnection(){
		return clientConnection;
	}
	
	public int getClientID() {
		return clientID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserColor() {
		return userColor;
	}

	public void setUserColor(String userColor) {
		this.userColor = userColor;
	}

	public BufferedImage getUserImage() {
		return userImage;
	}

	public void setUserImage(BufferedImage userImage) {
		this.userImage = userImage;
	}

	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
