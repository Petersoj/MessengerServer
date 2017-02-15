package messenger.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import messenger.controller.ServerController;

public class ServerFrame extends JFrame {
	
	private ServerController serverController;
	
	private ServerPanel serverPanel;
	
	public ServerFrame(ServerController serverController){
		this.serverController = serverController;
		
		this.serverPanel = new ServerPanel(this);
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Messenger");
		this.setSize(500, 300);
		this.setMinimumSize(new Dimension(400, 250));
		this.setLocationRelativeTo(null); // Centers the frame
		
		//this.setJMenuBar(messengerMenuBar);

		this.setContentPane(serverPanel);
		
		this.setVisible(true);
		
	}
	
	public ServerController getServerController(){
		return serverController;
	}

}
