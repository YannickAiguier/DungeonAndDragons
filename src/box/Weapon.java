package box;
import player.Player;
import player.Warrior;

/**
 * Classe pour gérer une arme (hérite de MeanOfAttack).
 * 
 * @see MeanOfAttack
 * @author yannick
 *
 */
public class Weapon extends MeanOfAttack {

	/**
	 * Construteur, utilise celui de la classe parente MeanOfAttack.
	 * <p>Pas de life pour une arme, le forClass Warrior est automatique.
	 * @param name
	 * @param img
	 * @param attack
	 */
	public Weapon(String name, String img, int attack) {
		super(name, img, attack, "Warrior");
	}

	/**
	 * Tente d'équiper un joueur avec l'objet trouvé si celui-ci correspond à sa classe.
	 * 
	 * @param player : le joueur à équiper.
	 * 
	 * @return Une chaine de caractères correspondant au résultat de la tentaivve d'équipement.
	 * 
	 * @see Player#changeItem(MeanOfAttack)
	 */
	@Override
	protected String equip(Player player) {
		
		if (player instanceof Warrior) {
			return player.changeItem(this);
		} else {
			return "Seul un guerrier peut utiliser cette arme.";
		}
	}	
	
}
