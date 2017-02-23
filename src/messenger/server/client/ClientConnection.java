package messenger.server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import messenger.controller.Debug;
import messenger.packet.Packet;
import messenger.packet.Packet.PacketType;
import messenger.packet.packet.PacketMessage;

public class ClientConnection extends Thread {
	
	private MessengerClient messengerClient;
	
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientConnection(MessengerClient messengerClient, Socket socket){
		this.messengerClient = messengerClient;
		this.socket = socket;
	}
	
	@Override
	public void run(){
		try{
			this.dataInputStream = new DataInputStream(socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e){
			Debug.consoleLog(e);
		}
		
		while(socket != null && !socket.isClosed()){
			try{
				PacketType packetType = PacketType.valueOf(dataInputStream.readUTF()); // The PacketType is always sent first
				switch(packetType){
					case MESSAGE:
						PacketMessage packetMessage = new PacketMessage();
						packetMessage.readContent(dataInputStream);
						break;
					case FILE:
						
						break;
					case USER:
						
						break;
				}
			}catch(Exception e){
				Debug.consoleLog(e);
				this.closeConnection(); // Error happened and that should never happen ;)
			}
		}
		this.closeConnection();
	}
	
	public void sendPacket(Packet packet){
		try{
			packet.writeContent(dataOutputStream);
		}catch(Exception e){
			
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
