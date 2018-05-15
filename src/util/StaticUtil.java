package util;

import java.awt.Color;

/**mettiamo qui metodi statici che possono essere utili un po' ovunque*/
public class StaticUtil {
	
	/**restituisce una stringa ordinata per il colore rgb*/
	public static String colorToRgbString(Color c) {
		String colorStr = "(r:"+c.getRed()+", g:"+c.getGreen()+"; b:"+c.getBlue()+")";
		return colorStr;
	}
}
