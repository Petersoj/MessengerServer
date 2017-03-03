package messenger.packet;

import javax.swing.SwingUtilities;

import messenger.controller.Debug;
import messenger.server.MessengerServer;
import messenger.server.client.MessengerClient;
import messenger.util.MessengerColor;

public class PacketHandler {
	
	private MessengerClient messengerClient;
	private MessengerServer messengerServer;
	
	public PacketHandler(MessengerClient messengerClient){
		this.messengerClient = messengerClient;
		this.messengerServer = messengerClient.getMessengerServer();
	}
	
	public void handlePacketMessage(PacketMessage packetMessage){
		if(messengerClient.getUserColor() == null && packetMessage.getMessage().equals("bonjour!")){ // Client send Packet message for first time.
			messengerClient.setUserName(packetMessage.getUserName());
			messengerClient.setUserColor(packetMessage.getUserColor());
			
			PacketMessage joinPacketMessage = new PacketMessage(packetMessage.getUserName(), MessengerColor.BLUE, "server-userjoin");
			messengerServer.getServerConnection().sendPacketToClients(joinPacketMessage, messengerClient);
			
			for(MessengerClient serverUser : messengerServer.getServerConnection().getMessengerClients()){
				if(serverUser != messengerClient){
					PacketMessage serverUserJoinPacket = new PacketMessage(serverUser.getUserName(), MessengerColor.BLUE, "server-userjoin");
					messengerClient.getClientConnection().sendPacketMessage(serverUserJoinPacket);
				}
			}
			
			SwingUtilities.invokeLater(() -> {
				messengerServer.getServerController().getServerFrame().getServerPanel().addUser(messengerClient);
			});
			
			Debug.consoleLog("User \'" + messengerClient.getUserName() + "\' has joined.");
		}else{
			messengerClient.setUserName(packetMessage.getUserName());
			messengerClient.setUserColor(packetMessage.getUserColor());
			
			PacketMessage sendingPacketMessage = new PacketMessage(packetMessage.getUserName(), packetMessage.getUserColor(), packetMessage.getMessage());
			messengerServer.getServerConnection().sendPacketToClients(sendingPacketMessage, messengerClient);
		}
		
		if(packetMessage.getUserName() != null){
			SwingUtilities.invokeLater(() -> {
				messengerServer.getServerController().getServerFrame().getServerPanel().updateUsername(messengerClient);
			});
		}
	}
	
}
