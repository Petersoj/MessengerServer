package messenger.controller;

import messenger.view.ServerFrame;

public class ServerController {
	
	private DataController dataController;
	private ServerFrame serverFrame;
	
	public ServerController(){
		this.dataController = new DataController(this);
		if(!dataController.errorOccured()){
			this.serverFrame = new ServerFrame(this);
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

}
