package messenger.view.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import messenger.controller.DataController;
import messenger.util.Utils;
import messenger.view.ServerFrame;

public class ServerMenuBar extends JMenuBar {
	
	private ServerFrame serverFrame;
	
	private JMenu serverMenu;
	private JMenuItem serverPortItem;
	private JMenuItem serverStartItem;
	
	public ServerMenuBar(ServerFrame serverFrame){
		super();
		
		this.serverFrame = serverFrame;
		
		this.serverMenu = new JMenu("Server");
		this.serverPortItem = new JMenuItem("Server Port");
		this.serverStartItem = new JMenuItem("Start Server");

		this.addMenus();
		this.setupListeners();
	}
	
	private void addMenus(){
		this.serverMenu.add(serverPortItem);
		this.serverMenu.addSeparator();
		this.serverMenu.add(serverStartItem);
		
		this.add(serverMenu);
	}
	
	private void setupListeners(){
		this.serverPortItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataController dataController = serverFrame.getServerController().getDataController();
				String portEntered = (String) JOptionPane.showInputDialog(serverFrame, "Enter a port number", "Port Number", 
						JOptionPane.PLAIN_MESSAGE, null, null, dataController.getServerPort());
				int portNumber = Utils.getNumberFromString(portEntered);
				if(portNumber != -1){ // Number conversion didn't work
					dataController.setServerPort(portNumber);
				}
			}
		});
		
		this.serverStartItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverStartItem.setText("Stop Server");
			}
		});
	}

}
