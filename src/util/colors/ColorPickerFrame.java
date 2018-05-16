package util.colors;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

public class ColorPickerFrame extends JFrame{
	
	/**
	 * raccorda le due Palette Standard e Custom in un'unica classe #non utilizzare#
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame inizialize(List<Color> ColorList) {
		return new CustomColorPicker(ColorList);
	}
}
