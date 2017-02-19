package messenger.server.client;

import java.net.Socket;

public class ClientConnection extends Thread {
	
	private MessengerClient messengerClient;
	
	private Socket socket;
	
	public ClientConnection(MessengerClient messengerClient, Socket socket){
		this.messengerClient = messengerClient;
		this.socket = socket;
	}
	
	@Override
	public void run(){
		
	}

}
