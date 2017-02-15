package messenger.view;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

public class ServerPanel extends JPanel {
	
	private ServerFrame serverFrame;
	
	private SpringLayout springLayout;
	
	private DefaultListModel<String> userListModel;
	private JList<String> userList;
	
	public ServerPanel(ServerFrame serverFrame){
		super();
		
		this.serverFrame = serverFrame;
		this.springLayout = new SpringLayout();
		this.userListModel = new DefaultListModel<String>();
		this.userList = new JList<String>(userListModel);
		
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}

	private void setupComponents(){
		userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		userList.setLayoutOrientation(JList.VERTICAL);
		
		
	}

	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(userList);
		
		this.setBackground(Color.WHITE);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, userList, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, userList, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, userList, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, userList, 0, SpringLayout.WEST, this);
	}
	
	private void setupListeners(){
		
	}
}
