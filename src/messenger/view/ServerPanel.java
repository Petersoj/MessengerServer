package messenger.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import messenger.controller.DataController;

public class ServerPanel extends JPanel {
	
	private ServerFrame serverFrame;
	
	private SpringLayout springLayout;
	
	private JLabel userListLabel;
	private JLabel resourceListLabel;
	private DefaultListModel<String> userListModel;
	private JList<String> userList;
	private JScrollPane userListScrollPane;
	private DefaultListModel<String> resourceListModel;
	private JList<String> resourceList;
	private JScrollPane resourceListScrollPane;
	private JButton kickButton;
	private JButton disposeButton;
	
	public ServerPanel(ServerFrame serverFrame){
		super();
		
		this.serverFrame = serverFrame;
		this.springLayout = new SpringLayout();
		
		this.userListLabel = new JLabel("Users");
		this.resourceListLabel = new JLabel("Resources");
		
		this.userListModel = new DefaultListModel<String>();
		this.userList = new JList<String>(userListModel);
		this.userListScrollPane = new JScrollPane(userList);
		
		this.resourceListModel = new DefaultListModel<String>();
		this.resourceList = new JList<String>(resourceListModel);
		this.resourceListScrollPane = new JScrollPane(resourceList);

		this.kickButton = new JButton("Kick User");
		this.disposeButton = new JButton("Dispose of Resource");
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}

	private void setupComponents(){
		DataController dataController = this.serverFrame.getServerController().getDataController();
		
		this.userListLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		this.resourceListLabel.setFont(dataController.getVerdanaFont().deriveFont(20f));
		
		this.userList.setFont(dataController.getVerdanaFont());
		this.userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.userList.setLayoutOrientation(JList.VERTICAL);
		this.userListScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.resourceList.setFont(dataController.getVerdanaFont());
		this.resourceList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.resourceList.setLayoutOrientation(JList.VERTICAL);
		this.resourceListScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.kickButton.setFont(dataController.getVerdanaFont());
		this.disposeButton.setFont(dataController.getVerdanaFont());
	}

	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(userListLabel);
		this.add(userListScrollPane);
		this.add(resourceListLabel);
		this.add(resourceListScrollPane);
		this.add(kickButton);
		this.add(disposeButton);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, userListScrollPane, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, userListScrollPane, -80, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, userListScrollPane, -55, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.WEST, userListScrollPane, 40, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, userListLabel, -10, SpringLayout.NORTH, userListScrollPane);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userListLabel, 0, SpringLayout.HORIZONTAL_CENTER, userListScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, resourceListScrollPane, 20, SpringLayout.NORTH, userListScrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, resourceListScrollPane, -20, SpringLayout.SOUTH, userListScrollPane);
		springLayout.putConstraint(SpringLayout.EAST, resourceListScrollPane, -40, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, resourceListScrollPane, 55, SpringLayout.HORIZONTAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, resourceListLabel, 0, SpringLayout.NORTH, userListLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resourceListLabel, 0, SpringLayout.HORIZONTAL_CENTER, resourceListScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, kickButton, 30, SpringLayout.SOUTH, userListScrollPane);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, kickButton, 0, SpringLayout.HORIZONTAL_CENTER, userListScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, disposeButton, 0, SpringLayout.NORTH, kickButton);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, disposeButton, 0, SpringLayout.HORIZONTAL_CENTER, resourceListScrollPane);
	}
	
	private void setupListeners(){
		
	}
}
