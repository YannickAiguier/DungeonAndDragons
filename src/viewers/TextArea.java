package viewers;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Classe qui gère les Jlabel qui reçoivent du texte.
 * <p>
 * Classe qui hérite de JLabel.
 * </p>
 * 
 * @author yannick
 *
 */
public class TextArea extends JLabel {

	/**
	 * Affiche le texte dans ce GraphArea, aux dimensions voulues pour la police et le JLabel.
	 * 
	 * @param string : le texte à afficher.
	 * @param fontSize : la taille de police à utiliser.
	 * @param width : largeur du JLabel à afficher.
	 * @param height : hauteur du JLabel à afficher.
	 */
	public TextArea(String string, int fontSize, int width, int height) {
		super(string);
		this.setForeground(Color.white);
		this.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
		this.setHorizontalAlignment(CENTER);
		this.setMaximumSize(new Dimension(width, height));
	}

}
