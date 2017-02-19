package messenger.server;

import java.net.ServerSocket;
import java.util.ArrayList;

import messenger.controller.Debug;
import messenger.server.client.MessengerClient;

public class ServerConnection extends Thread {
	
	private MessengerServer messengerServer;
	
	private ServerSocket serverSocket;
	private ArrayList<MessengerClient> messengerClients;
	
	public ServerConnection(MessengerServer messengerServer){
		this.messengerServer = messengerServer;
		this.messengerClients = new ArrayList<MessengerClient>();
	}
	
	@Override
	public void run(){
		try {
			this.serverSocket = new ServerSocket(messengerServer.getServerController().getDataController().getServerPort());
		}catch(Exception e){
			Debug.presentError("ServerConnection thread", e);
		}
		
		while(this.serverSocket != null && !this.serverSocket.isClosed() && this.serverSocket.isBound()){
			try {
				MessengerClient messengerClient = new MessengerClient(messengerServer);
				this.messengerClients.add(messengerClient);
				messengerClient.acceptConnection(serverSocket.accept());
			}catch(Exception e){
				Debug.presentError("Server .accept", e);
			}
		}
		this.closeServer();
	}
	
	public MessengerClient getClientByID(int clientID){
		for(MessengerClient messengerClient : messengerClients){
			if(messengerClient.getClientID() == clientID){
				return messengerClient;
			}
		}
		return null;
	}
	
	public void closeServer(){
		try{
			if(serverSocket != null){
				serverSocket.close();
			}
		}catch(Exception e){
			Debug.presentError("Closing server", e);
		}
	}
	
	public ArrayList<MessengerClient> getClientConnections(){
		return messengerClients;
	}
	
	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
