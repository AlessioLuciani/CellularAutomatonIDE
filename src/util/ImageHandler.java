package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashSet;

/**
 * Utilizzata per fare operazioni sulle immagini.
 */
public class ImageHandler {
	
	/**
	 * Colori rimanenti dopo un reduceColors.
	 */
	private HashSet<Color> fewColors;
	
	/**
	 * Riduce i colori di una BufferedImage, lasciandone un massimo di 27.
	 * Salva i colori rimanenti in fewColors.
	 * @param image
	 * @return
	 */
	public BufferedImage reduceColors(BufferedImage image) {
		fewColors = new HashSet<>();
		for (int y = 0; y <  image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int divFactor = 100;
				int addition = 50;
				Color color = new Color(image.getRGB(x, y));
				color = new Color(color.getRed()/divFactor*divFactor+addition, color.getGreen()/divFactor*divFactor+addition, color.getBlue()/divFactor*divFactor+addition);
				image.setRGB(x, y, color.getRGB());		
				fewColors.add(color);
			}
		}
		return image;
	}
	
	/**
	 * Ridimensiona una BufferedImage.
	 * @param image
	 * @param newW
	 * @param newH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage image, int newW, int newH) { 
	    Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage newImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = newImage.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	   
	    return newImage;
	}  

	/**
	 * Getter di fewColors.
	 * @return
	 */
	public HashSet<Color> getFewColors() {
		return fewColors;
	}
}
