package viewers;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * 
 * Classe qui gère les Jlabel qui recevront des images, avec 2 fonctions pour afficher une image ou ne rien afficher.
 * <p>Classe qui hérite de JLabel.</p>
 * 
 *  @see #setImg(String, int, int)
 *  @see #removeImg()
 * 
 * @author yannick
 *
 */
public class GraphArea extends JLabel {
	
	public GraphArea() {
		super();
	}
	
	/**
	 * Affiche l'image dans ce GraphArea, aux dimensions voulues.
	 * 
	 * @param img : nom du fichier contenant l'image.
	 * @param width : largeur à afficher.
	 * @param height : hauteur à afficher.
	 */
	public void setImg(String img, int width, int height) {
		try {
			URL myImage = ClassLoader.getSystemResource("images/" + img);
			Image resizedImage = ImageIO.read(myImage).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(resizedImage));
		} catch (Exception e1) {
			System.out.println("Impossible de trouver l'image");
			
		}
	}
	
	/**
	 * Supprime l'image affichée dans ce GraphArea.
	 */
	public void removeImg() {
		this.setIcon(null);
	}
}
