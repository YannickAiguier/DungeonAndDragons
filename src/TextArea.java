import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class TextArea extends JLabel {
	
	public TextArea(String string, int fontSize, int width, int height) {
		super(string);
		this.setForeground(Color.white);
		this.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
		this.setHorizontalAlignment(CENTER);
		this.setMaximumSize(new Dimension(width, height));
	}

}
