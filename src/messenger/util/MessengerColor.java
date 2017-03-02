package messenger.util;

import java.awt.Color;

public enum MessengerColor {
	
	RED(231, 76, 60), ORANGE(243, 156, 18), GREEN(46, 204, 113), BLUE(52, 152, 219), PURPLE(155, 89, 182), GRAY(189, 195, 199);
	
	private Color color;
	
	private MessengerColor(int r, int g, int b){
		color = new Color(r, g, b);
	}
	
	public Color getColor(){
		return color;
	}
	
	public Color applyAlpha(int alpha){
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}

}
