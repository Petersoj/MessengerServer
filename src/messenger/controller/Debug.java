package messenger.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import messenger.view.ServerPanel;

public class Debug {
	
	private static ServerPanel serverPanelInstance;

	public static void presentError(String title, String message){
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
		});
	}
	
	public static void presentError(String title, Exception e){
		presentError(title, getCustomStackTrace(e));
	}
	
	public static void consoleLog(Exception e){
		consoleLog(getCustomStackTrace(e));
	}
	
	public static void consoleLog(String message){
		SwingUtilities.invokeLater(() -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
			Date currentDate = new Date(System.currentTimeMillis());
			serverPanelInstance.addMessageToConsole("[" + dateFormat.format(currentDate) + "] " + message + "\n");
		});
	}
	
	private static String getCustomStackTrace(Exception e){ 
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		String message = e.getClass().getName() + " - " + e.getMessage() + "\n\n";
		for(int index = 0; index < stackTraceElements.length; index++){
			if(index > 10){ // Don't wanna print to many lines :) && don't want an indexOutOfBoundsException
				break;
			}
			StackTraceElement stackTraceElement = stackTraceElements[index];
			message += stackTraceElement.getClassName() + " " + stackTraceElement.getMethodName() + " " + stackTraceElement.getLineNumber() + "\n";
		}
		return message;
	}
	
	public static void setServerPanelInstance(ServerPanel serverPanel){
		serverPanelInstance = serverPanel;
	}
}
