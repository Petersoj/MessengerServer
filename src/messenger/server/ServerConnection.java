package messenger.server;

import java.net.ServerSocket;
import java.util.ArrayList;

import messenger.controller.Debug;
import messenger.packet.Packet;
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
				messengerClient.acceptConnection(serverSocket.accept());
				this.messengerClients.add(messengerClient);
			}catch(Exception e){
				if(!e.getMessage().equals("Socket closed")){
					Debug.consoleLog(e);
					this.closeServer();
				}
			}
		}
		this.closeServer();
	}
	
	public void sendPacketToClients(Packet packet, MessengerClient... excludedClients){
		for(MessengerClient serverUser : this.messengerClients){
			for(MessengerClient excluded : excludedClients){
				if(serverUser.getClientID() != excluded.getClientID()){
					System.out.println("Sent packet " + packet.getPacketType().name());
					serverUser.getClientConnection().sendPacket(packet);
				}
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
