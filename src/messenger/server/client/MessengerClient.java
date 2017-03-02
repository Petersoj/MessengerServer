package messenger.server.client;

import java.net.Socket;

import messenger.server.MessengerServer;
import messenger.util.MessengerColor;

public class MessengerClient {
	
	private static int currentClientID = 0;
	
	private MessengerServer messengerServer;
	
	private int clientID;
	private ClientConnection clientConnection;
	
	private String userName;
	private MessengerColor userColor;
	
	public MessengerClient(MessengerServer messengerServer){
		this.messengerServer = messengerServer;
		this.clientID = currentClientID++;
		
		this.userName = "";
		this.userColor = null; // This is used for clients who have joined for the first time
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

	public MessengerColor getUserColor() {
		return userColor;
	}

	public void setUserColor(MessengerColor userColor) {
		this.userColor = userColor;
	}

	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
