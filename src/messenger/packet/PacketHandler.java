package messenger.packet;

import messenger.server.MessengerServer;
import messenger.server.client.MessengerClient;

public class PacketHandler {
	
	private MessengerClient messengerClient;
	private MessengerServer messengerServer;
	
	public PacketHandler(MessengerClient messengerClient){
		this.messengerClient = messengerClient;
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		if(messengerClient.getUserColor() == null && packetMessage.getMessage().equals("bonjour!")){ // Client send Packet message for first time.
			messengerClient.setUserName(packetMessage.getUserName());
			messengerClient.setUserColor(packetMessage.getUserColor());
		}else{
			
		}
	}
}
