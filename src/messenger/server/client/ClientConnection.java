package messenger.server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import messenger.controller.Debug;
import messenger.packet.PacketHandler;
import messenger.packet.PacketMessage;

public class ClientConnection extends Thread {
	
	private MessengerClient messengerClient;
	
	private PacketHandler packetHandler;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientConnection(MessengerClient messengerClient, Socket socket){
		this.messengerClient = messengerClient;
		this.socket = socket;
		this.packetHandler = new PacketHandler(messengerClient);
	}
	
	@Override
	public void run(){
		try{
			this.dataInputStream = new DataInputStream(socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e){
			Debug.consoleLog(e);
		}
		
		while(socket != null && !socket.isClosed() && socket.isConnected()){
			try{
				
				PacketMessage packetMessage = new PacketMessage();
				packetMessage.readContent(dataInputStream);
				packetHandler.handlePacketMessage(packetMessage);
				
			}catch(Exception e){
				e.printStackTrace();
				this.closeConnection(); // Error happened and that should never happen ;)
			}
		}
		this.closeConnection();
	}

	public void sendPacketMessage(PacketMessage packetMessage){
		try {
			packetMessage.writeContent(dataOutputStream);
		}catch(Exception e) {
			Debug.presentError("Sending packet", e);
		}
	}

	public void closeConnection(){
		try{
			if(socket != null && socket.isConnected()){
				dataInputStream.close();
				dataOutputStream.close();
				socket.close();
			}
		}catch(Exception e){
			Debug.consoleLog(e);
		}
	}
}
