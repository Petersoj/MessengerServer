package messenger.controller;

import messenger.server.MessengerServer;
import messenger.view.ServerFrame;

public class ServerController {
	
	private DataController dataController;
	private ServerFrame serverFrame;
	private MessengerServer messengerServer;
	
	public ServerController(){
		this.dataController = new DataController(this);
		if(!dataController.errorOccured()){
			this.serverFrame = new ServerFrame(this);
			this.messengerServer = new MessengerServer(this);
			Debug.setServerPanelInstance(this.serverFrame.getServerPanel());
		}
	}
	
	public void start(){
		
	}
	
	public DataController getDataController(){
		return dataController;
	}
	
	public ServerFrame getServerFrame(){
		return serverFrame;
	}
	
	public MessengerServer getMessengerServer(){
		return messengerServer;
	}

}
