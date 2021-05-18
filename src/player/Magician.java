package player;
import box.Spell;

/**
 * Classe pour gérer un Magicien (hérite de Player).
 * 
 * @see Player
 * @author yannick
 *
 */
public class Magician extends Player {

	/**
	 * Constructeur, utilise aussi celui de la classe parente Player. Crée un Magicien avec les paramètres standard et son nom.
	 * 
	 * @param name : le nom à donner au magicien.
	 */
	public Magician(String name) {
		super(name, "magician.png", 3, 6, 8, new Spell("Rien", "primary_spell.png", 0), "Philter");
	}

}
