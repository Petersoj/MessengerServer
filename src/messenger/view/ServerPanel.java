package messenger.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import messenger.controller.DataController;

public class ServerPanel extends JPanel {
	
	private ServerFrame serverFrame;
	
	private SpringLayout springLayout;

	private JLabel consoleLabel;
	private JLabel userListLabel;
	private JTextArea consoleTextArea;
	private JScrollPane consoleScrollPane;
	private DefaultListModel<String> userListModel;
	private JList<String> userList;
	private JScrollPane userListScrollPane;
	private JButton kickButton;
	
	public ServerPanel(ServerFrame serverFrame){
		super();
		
		this.serverFrame = serverFrame;
		this.springLayout = new SpringLayout();

		this.consoleLabel = new JLabel("Console");
		this.userListLabel = new JLabel("Users");
		
		this.consoleTextArea = new JTextArea();
		this.consoleScrollPane = new JScrollPane(consoleTextArea);
		
		this.userListModel = new DefaultListModel<String>();
		this.userList = new JList<String>(userListModel);
		this.userListScrollPane = new JScrollPane(userList);
		
		this.kickButton = new JButton("Kick User");
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}

	private void setupComponents(){
		DataController dataController = this.serverFrame.getServerController().getDataController();
		
		this.userListLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		this.consoleLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		
		this.consoleTextArea.setFont(dataController.getVerdanaFont());
		this.consoleTextArea.setLineWrap(true);
		this.consoleTextArea.setWrapStyleWord(true);
		this.consoleTextArea.setEditable(false);
		
		this.consoleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.consoleScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.userList.setFont(dataController.getVerdanaFont());
		this.userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.userList.setLayoutOrientation(JList.VERTICAL);
		this.userListScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.kickButton.setFont(dataController.getVerdanaFont());
	}

	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(consoleLabel);
		this.add(consoleScrollPane);
		this.add(userListLabel);
		this.add(userListScrollPane);
		this.add(kickButton);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, consoleScrollPane, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, consoleScrollPane, -20, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, consoleScrollPane, 60, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.WEST, consoleScrollPane, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, consoleLabel, -10, SpringLayout.NORTH, consoleScrollPane);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, consoleLabel, 0, SpringLayout.HORIZONTAL_CENTER, consoleScrollPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, kickButton, 0, SpringLayout.SOUTH, consoleScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, userListScrollPane, 0, SpringLayout.NORTH, consoleScrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, userListScrollPane, -10, SpringLayout.NORTH, kickButton);
		springLayout.putConstraint(SpringLayout.EAST, userListScrollPane, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, userListScrollPane, 40, SpringLayout.EAST, consoleScrollPane);

		springLayout.putConstraint(SpringLayout.SOUTH, userListLabel, 0, SpringLayout.SOUTH, consoleLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userListLabel, 0, SpringLayout.HORIZONTAL_CENTER, userListScrollPane);

		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, kickButton, 0, SpringLayout.HORIZONTAL_CENTER, userListScrollPane);
	}
	
	private void setupListeners(){
		
	}
	
	public void addMessageToConsole(String message){
		
	}
	
	public void addUser(){
		
	}
}
