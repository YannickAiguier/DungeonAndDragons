package box;
/**
 * Classe pour gérer un Dragon (hérite de Monster).
 * 
 * @see Monster
 * @author yannick
 *
 */
public class Dragon extends Monster {
	
	/**
	 * Constructeur, sans paramètres. Crée un dragon avec les paramètres standards (image, vie, force).
	 */
	public Dragon() {
		super("Dragon", "dragon.png", 15, 4);
	}

}