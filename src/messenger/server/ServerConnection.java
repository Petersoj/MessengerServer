package messenger.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import messenger.controller.Debug;
import messenger.packet.PacketMessage;
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
			Debug.consoleLog(e);
		}
		
		Debug.consoleLog("Started listening on port " + messengerServer.getServerController().getDataController().getServerPort());
		try {
			Debug.consoleLog("Your local IP Address is " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			Debug.consoleLog("Don't know your local IP Address!");
		}
		
		while(this.serverSocket != null && !this.serverSocket.isClosed() && this.serverSocket.isBound()){
			try {
				Socket clientSocket = serverSocket.accept();
				MessengerClient messengerClient = new MessengerClient(messengerServer);
				this.messengerClients.add(messengerClient);
				messengerClient.acceptConnection(clientSocket);
			}catch(SocketException e){
				this.closeServer();
			}catch(Exception e){
				Debug.consoleLog(e);
				this.closeServer();
			}
		}
	}
	
	public MessengerClient getClientByID(int clientID){
		for(MessengerClient messengerClient : messengerClients){
			if(messengerClient.getClientID() == clientID){
				return messengerClient;
			}
		}
		return null;
	}

	public void sendPacketToClients(PacketMessage packetMessage, MessengerClient excludedClient){
		for(MessengerClient serverUser : messengerClients){
			if(serverUser.getClientID() != excludedClient.getClientID()){
				serverUser.getClientConnection().sendPacketMessage(packetMessage);
			}
		}
	}
	
	public void closeServer(){
		try{
			if(serverSocket != null){
				serverSocket.close();
			}
		}catch(Exception e){
			Debug.consoleLog(e);
		}
	}
	
	public ArrayList<MessengerClient> getMessengerClients(){
		return messengerClients;
	}
	
	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
