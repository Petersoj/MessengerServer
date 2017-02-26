package messenger.packet;

import java.awt.image.BufferedImage;

import messenger.controller.Debug;
import messenger.packet.packet.PacketMessage;
import messenger.packet.packet.PacketMessage.PacketMessageType;
import messenger.packet.packet.PacketUser;
import messenger.packet.packet.PacketUser.PacketUserType;
import messenger.server.MessengerServer;
import messenger.server.client.MessengerClient;

public class PacketHandler {
	
	private MessengerClient messengerClient;
	private MessengerServer messengerServer;
	
	public PacketHandler(MessengerClient messengerClient){
		this.messengerClient = messengerClient;
		this.messengerServer = messengerClient.getMessengerServer();
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		PacketMessageType packetMessageType = packetMessage.getPacketMessageType();
		
		if(packetMessageType == PacketMessageType.MESSAGE){
			String message = packetMessage.getMessage();
			if(message.length() > 20000){ // That's way to big, I mean, come on. Just no.
				message = message.substring(0, 20000);
			}
			
			PacketMessage sendingPacketMessage = new PacketMessage(PacketMessageType.MESSAGE);
			sendingPacketMessage.setUserID(messengerClient.getClientID());
			sendingPacketMessage.setMessage(message);
			
			messengerServer.getServerConnection().sendPacketToClients(sendingPacketMessage, messengerClient);
			Debug.consoleLog("Send packet to clients");
		}else if(packetMessageType == PacketMessageType.FILE_NOTIFIER){
			// The client should never send this PacketMessageType
		}
	}
	
	public void handlePacketFile(){
		
	}
	
	public void handlePacketUser(PacketUser packetUser){
		PacketUserType packetUserType = packetUser.getPacketUserType();
		
		if(packetUserType == PacketUserType.USERNAME){
			String userName = packetUser.getUserName();
			if(userName.length() > 20){ // The client should limit this, but just incase.
				userName = userName.substring(0, 20);
			}
			
			if(messengerClient.getUserImage() == null){ // This means the client sent this packet for the first time aka bonjour
				for(MessengerClient otherServerUser : messengerClient.getMessengerServer().getServerConnection().getMessengerClients()){
					if(otherServerUser.getClientID() != messengerClient.getClientID()){
						
						PacketUser userNamePacket = new PacketUser(PacketUserType.USERNAME);
						userNamePacket.setUserID(otherServerUser.getClientID());
						userNamePacket.setUserName(otherServerUser.getUserName());
						
						PacketUser userColorPacket = new PacketUser(PacketUserType.COLOR);
						userColorPacket.setUserID(otherServerUser.getClientID());
						userColorPacket.setUserColor(otherServerUser.getUserColor());
						
						PacketUser userImagePacket = new PacketUser(PacketUserType.IMAGE_ICON);
						userImagePacket.setUserID(otherServerUser.getClientID());
						userImagePacket.setUserImage(otherServerUser.getUserImage());
						
						// !!! Order is Important !!!
						messengerClient.getClientConnection().sendPacket(userNamePacket);
						messengerClient.getClientConnection().sendPacket(userColorPacket);
						messengerClient.getClientConnection().sendPacket(userImagePacket);
					}
				}
			}
			
			messengerClient.setUserName(userName);
			
			PacketUser sendingPacketUser = new PacketUser(PacketUserType.USERNAME);
			sendingPacketUser.setUserID(messengerClient.getClientID());
			sendingPacketUser.setUserName(userName);
			
			messengerServer.getServerConnection().sendPacketToClients(sendingPacketUser, messengerClient);
		}else if(packetUserType == PacketUserType.COLOR){
			String userColor = packetUser.getUserColor();
			
			if(userColor == null || userColor.equals("")){ // Just incase a client sent a non-valid color.
				userColor = "RED";
			}
			
			messengerClient.setUserColor(userColor);
			
			PacketUser sendingPacketUser = new PacketUser(PacketUserType.COLOR);
			sendingPacketUser.setUserID(messengerClient.getClientID());
			sendingPacketUser.setUserColor(userColor);
			
			messengerServer.getServerConnection().sendPacketToClients(sendingPacketUser, messengerClient);
		}else if(packetUserType == PacketUserType.IMAGE_ICON){
			BufferedImage userImage = packetUser.getUserImage();
			
			if(userImage == null){ // We could just send some random image, but nah.
				Debug.consoleLog("User image was null");
				return;
			}
			
			messengerClient.setUserImage(userImage);
			
			PacketUser sendingPacketUser = new PacketUser(PacketUserType.IMAGE_ICON);
			sendingPacketUser.setUserID(messengerClient.getClientID());
			sendingPacketUser.setUserImage(userImage);
			
			messengerServer.getServerConnection().sendPacketToClients(sendingPacketUser, messengerClient);
		}else if(packetUserType == PacketUserType.LEAVE){
			// The client should never send this.
		}
	}
}
