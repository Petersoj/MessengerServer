package messenger.server;

import messenger.controller.ServerController;

public class MessengerServer {
	
	private ServerController serverController;
	
	private ServerConnection serverConnection;
	
	public MessengerServer(ServerController serverController){
		this.serverController = serverController;
	}
	
	public void startServer(){
		this.serverConnection = new ServerConnection(this);
		this.serverConnection.start();
	}
	

	public ServerController getServerController() {
		return serverController;
	}

	public ServerConnection getServerConnection() {
		return serverConnection;
	}
}
