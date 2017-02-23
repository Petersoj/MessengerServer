package messenger.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
	
	private PacketType packetType;
	
	public Packet(PacketType packetType){
		this.packetType = packetType;
	}
	
	public void writeContent(DataOutputStream dataOutputStream) throws IOException{
		dataOutputStream.writeUTF(packetType.name());
	}
	
	public abstract void readContent(DataInputStream dataInputStream) throws IOException ;
	
	public PacketType getPacketType() {
		return packetType;
	}
	
	public enum PacketType {
		MESSAGE, FILE, USER;
	}
}
