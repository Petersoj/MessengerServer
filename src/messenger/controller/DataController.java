package messenger.controller;

import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import messenger.util.Utils;

public class DataController {
	
	private ServerController serverController;
	
	private String dataFilePath;
	private boolean errorOccured;
	private int serverPort;
	private Font verdanaFont;
	
	public DataController(ServerController serverController){
		this.serverController = serverController;
		this.serverPort = -2;
		this.loadAssets();
	}
	
	private void loadAssets(){
		dataFilePath = this.getDefaultDataDirectory() + "/messenger";

		try{
			String stringTextData = new String(Files.readAllBytes(Paths.get(dataFilePath + "/textData.txt"))); // Text Data
			String[] textData = stringTextData.split(",");
			if(textData.length < 3){
				throw new ParseException("Could not parse TextData", textData.length);
			}
			for(String data : textData){
				String[] splitData = data.split("=");
				if(splitData[0].equalsIgnoreCase("port")){
					this.serverPort = Utils.getNumberFromString(splitData[1]);
				}
			}
			if(serverPort < 0){
				throw new ParseException("Getting Port", 0);
			}
		}catch(Exception e){
			this.errorOccured = true;
			Debug.presentError("Error in starting!", "Make sure to run Messenger Client\nfirst before running the server!");
		}
		
		this.verdanaFont = new Font("Verdana", Font.PLAIN, 15);
	}
	
	
	// Totally didn't have to look this up again :P
	private String getDefaultDataDirectory() {
		String os = System.getProperty("os.name").toUpperCase();
		if (os.contains("WIN")){
			return System.getenv("APPDATA");
		}else if (os.contains("MAC")){
			return System.getProperty("user.home") + "/Library/Application Support";
		}else if(os.contains("NUX")){
			return System.getProperty("user.home");
		}
		return System.getProperty("user.dir");
	}
	
	public boolean errorOccured(){
		return errorOccured;
	}

	public ServerController getServerController() {
		return serverController;
	}
	
	public void setServerPort(int portNumber){
		this.serverPort = portNumber;
	}
	
	public int getServerPort(){
		return serverPort;
	}
	
	public Font getVerdanaFont() {
		return verdanaFont;
	}
	
}
