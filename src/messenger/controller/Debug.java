package messenger.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Debug {

	public static void presentError(String title, String message){
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
		});
	}
	
	public static void presentError(String title, Exception e){
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		String message = e.getMessage() + "\n";
		for(int index = 0; index < stackTraceElements.length; index++){
			if(index > 6){ // Don't wanna print to many lines :)
				break;
			}
			StackTraceElement stackTraceElement = stackTraceElements[index];
			message += stackTraceElement.getClassName() + " " + stackTraceElement.getMethodName() + " " + stackTraceElement.getLineNumber() + "\n";
		}
		presentError(title, message);
	}

}
