package messenger.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import messenger.controller.DataController;
import messenger.server.client.MessengerClient;

public class ServerPanel extends JPanel {
	
	private ServerFrame serverFrame;
	
	private SpringLayout springLayout;

	private JLabel consoleLabel;
	private JLabel userListLabel;
	private JTextArea consoleTextArea;
	private JScrollPane consoleScrollPane;
	private DefaultListModel<MessengerClient> userListModel;
	private JList<MessengerClient> userList;
	private JScrollPane userListScrollPane;
	
	public ServerPanel(ServerFrame serverFrame){
		super();
		
		this.serverFrame = serverFrame;
		this.springLayout = new SpringLayout();

		this.consoleLabel = new JLabel("Console");
		this.userListLabel = new JLabel("Users");
		
		this.consoleTextArea = new JTextArea();
		this.consoleScrollPane = new JScrollPane(consoleTextArea);
		
		this.userListModel = new DefaultListModel<MessengerClient>();
		this.userList = new JList<MessengerClient>(userListModel);
		this.userListScrollPane = new JScrollPane(userList);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}

	private void setupComponents(){
		DataController dataController = this.serverFrame.getServerController().getDataController();
		
		this.userListLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		this.consoleLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		
		this.consoleTextArea.setFont(dataController.getVerdanaFont().deriveFont(13f));
		this.consoleTextArea.setLineWrap(true);
		this.consoleTextArea.setWrapStyleWord(true);
		this.consoleTextArea.setEditable(false);
		
		this.consoleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.consoleScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.userList.setFont(dataController.getVerdanaFont());
		this.userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.userList.setLayoutOrientation(JList.VERTICAL);
		this.userListScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
	}

	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(consoleLabel);
		this.add(consoleScrollPane);
		this.add(userListLabel);
		this.add(userListScrollPane);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, consoleScrollPane, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, consoleScrollPane, -20, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, consoleScrollPane, 40, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.WEST, consoleScrollPane, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, consoleLabel, -10, SpringLayout.NORTH, consoleScrollPane);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, consoleLabel, 0, SpringLayout.HORIZONTAL_CENTER, consoleScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, userListScrollPane, 0, SpringLayout.NORTH, consoleScrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, userListScrollPane, 0, SpringLayout.SOUTH, consoleScrollPane);
		springLayout.putConstraint(SpringLayout.EAST, userListScrollPane, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, userListScrollPane, 40, SpringLayout.EAST, consoleScrollPane);

		springLayout.putConstraint(SpringLayout.SOUTH, userListLabel, 0, SpringLayout.SOUTH, consoleLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userListLabel, 0, SpringLayout.HORIZONTAL_CENTER, userListScrollPane);
	}
	
	private void setupListeners(){
		this.userList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				MessengerClient selectedUser = userList.getSelectedValue();
				if(selectedUser != null){
					userList.setSelectionBackground(selectedUser.getUserColor().getColor());
				}
			}
		});
	}
	
	public void addMessageToConsole(String message){
		this.consoleTextArea.append(message);
	}
	
	public void addUser(MessengerClient messengerClient){
		this.userListModel.addElement(messengerClient);
	}
	
	public void updateUsername(MessengerClient messengerClient){
		this.removeUser(messengerClient);
		this.addUser(messengerClient);
	}
	
	public void removeUser(MessengerClient messengerClient){
		this.userListModel.removeElement(messengerClient);
	}
}
