package player;
import box.Weapon;

/**
 * Classe pour gérer un Warrior (hérite de Player).
 * 
 * @see Player
 * @author yannick
 *
 */
public class Warrior extends Player {

	/**
	 * Constructeur, utilise aussi celui de la classe parente Player. Crée un Warrior avec les paramètres standard et son nom.
	 * 
	 * @param name : le nom à donner au warrior.
	 */
	public Warrior(String name) {
		super(name, "warrior.png", 5, 10, 5, new Weapon("Rien", "fist.png", 0), new Weapon("Rien", "fist.png", 0), "Shield");
	}
}
