package messenger.server.client;

import java.net.Socket;

import messenger.server.MessengerServer;

public class MessengerClient {
	
	private static int currentClientID = 0;
	
	
	private MessengerServer messengerServer;
	
	private int clientID;
	private ClientConnection clientConnection;
	
	private String userName;
	
	public MessengerClient(MessengerServer messengerServer){
		this.messengerServer = messengerServer;
		this.clientID = currentClientID++;
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

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
