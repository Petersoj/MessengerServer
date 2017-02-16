package messenger.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	
	public static byte[] bufferedImageToBytes(BufferedImage bufferedImage, String formatName) throws IOException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8000); // creates a buffer of 8 kilobytes.
		byte[] bytes = null;
		ImageIO.write(bufferedImage, formatName, outputStream);
		outputStream.flush(); // Gets rid of bytes that weren't filled in the buffer
		bytes = outputStream.toByteArray();
		outputStream.close();
		return bytes;
	}
	
	public static BufferedImage bytesToBufferedImage(byte[] imageBytes) throws IOException {
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(imageBytes);
		return ImageIO.read(byteInputStream);
	}
	
	public static int getNumberFromString(String numberToConvert){
		int returnValue;
		try{
			returnValue = Integer.parseInt(numberToConvert);
		}catch(NumberFormatException e){
			returnValue = -1;
		}
		return returnValue;
	}

}
