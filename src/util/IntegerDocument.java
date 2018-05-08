package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**documento da assegnare alle text field per contenere solo numeri*/
public class IntegerDocument extends PlainDocument {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		String cleaned = "";
		for(int i=0; i<str.length(); i++)
			if(str.charAt(i) <= '9' && str.charAt(i) >= '0') //mantiene solo numeri
				cleaned += str.charAt(i);
		super.insertString(offs, cleaned, a);
	}
}
