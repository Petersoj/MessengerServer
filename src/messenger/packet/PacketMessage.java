package messenger.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import messenger.util.MessengerColor;

public class PacketMessage {

	private String userName;
	private MessengerColor userColor;
	private String message;
	
	public PacketMessage(){
		this("", MessengerColor.BLUE, ""); // default random values
	}

	public PacketMessage(String userName, MessengerColor userColor, String message) {
		this.userName = userName;
		this.userColor = userColor;
		this.message = message;
	}

	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeUTF(userColor.name());
		dataOutputStream.writeUTF(message);
	}

	public void readContent(DataInputStream dataInputStream) throws IOException{
		this.userName = dataInputStream.readUTF();
		this.userColor = MessengerColor.valueOf(dataInputStream.readUTF());
		this.message = dataInputStream.readUTF();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MessengerColor getUserColor() {
		return userColor;
	}

	public void setUserColor(MessengerColor userColor) {
		this.userColor = userColor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}