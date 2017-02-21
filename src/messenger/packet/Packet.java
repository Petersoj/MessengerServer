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
		dataOutputStream.writeUTF(packetType.toString());
	}
	
	public void readContent(DataInputStream dataInputStream) throws IOException{
		this.packetType = PacketType.valueOf(dataInputStream.readUTF());
	}
	
	public PacketType getPacketType() {
		return packetType;
	}
	
	public enum PacketType {
		MESSAGE, FILE, USER;
	}
}
