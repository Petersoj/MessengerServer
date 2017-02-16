package messenger.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import messenger.controller.Debug;
import messenger.controller.ServerController;
import messenger.view.menubar.ServerMenuBar;

public class ServerFrame extends JFrame {
	
	private ServerController serverController;
	
	private ServerMenuBar serverMenuBar;
	private ServerPanel serverPanel;
	
	public ServerFrame(ServerController serverController){
		this.serverController = serverController;
		
		this.setupProperties();
		this.serverMenuBar = new ServerMenuBar(this);
		this.serverPanel = new ServerPanel(this);
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Messenger");
		this.setSize(550, 300);
		this.setMinimumSize(new Dimension(500, 250));
		this.setLocationRelativeTo(null); // Centers the frame
		
		this.setJMenuBar(serverMenuBar);

		this.setContentPane(serverPanel);
		
		this.setVisible(true);
	}
	
	private void setupProperties(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Messenger Server");
		} catch (Exception e) {
			Debug.presentError("Setup Properties", e);
		}
	}
	
	public ServerController getServerController(){
		return serverController;
	}
	
	public ServerPanel getServerPanel(){
		return serverPanel;
	}
}
