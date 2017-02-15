package messenger.controller;

import messenger.view.ServerFrame;

public class ServerController {
	
	private ServerFrame serverFrame;
	
	public ServerController(){
		this.serverFrame = new ServerFrame(this);
	}
	
	public void start(){
		
	}
	
	public ServerFrame getServerFrame(){
		return serverFrame;
	}

}
