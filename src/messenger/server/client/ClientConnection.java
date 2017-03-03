package messenger.server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.SwingUtilities;

import messenger.controller.Debug;
import messenger.packet.PacketHandler;
import messenger.packet.PacketMessage;
import messenger.util.MessengerColor;

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
				
			}catch(SocketException e){
				this.closeConnection();
			}catch(EOFException e){
				this.closeConnection();
			}catch(Exception e){
				Debug.consoleLog(e);
				this.closeConnection(); // Error happened and that should never happen ;)
			}
		}
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
		Debug.consoleLog("User \'" + messengerClient.getUserName() + "\' has left.");
		
		SwingUtilities.invokeLater(() -> {
			messengerClient.getMessengerServer().getServerController().getServerFrame().getServerPanel().removeUser(messengerClient);
		});
		
		this.messengerClient.getMessengerServer().getServerConnection().getMessengerClients().remove(messengerClient);
		PacketMessage leaveMessage = new PacketMessage(messengerClient.getUserName(), MessengerColor.BLUE, "server-userleave");
		this.messengerClient.getMessengerServer().getServerConnection().sendPacketToClients(leaveMessage, messengerClient);
	}
}
