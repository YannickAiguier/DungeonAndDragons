package box;
/**
 * Classe pour gérer un Sorcier (hérite de Monster).
 * 
 * @see Monster
 * @author yannick
 *
 */
public class Sorcerer extends Monster {
	
	/**
	 * Constructeur, sans paramètres. Crée un sorcier avec les paramètres standards (image, vie, force).
	 */
	public Sorcerer() {
		super("Sorcier", "sorcerer.png", 9, 2);
	}

}