package box;
import player.Magician;
import player.Player;

/**
 * Classe pour gérer un sort (hérite de MeanOfAttack).
 * 
 * @see MeanOfAttack
 * @author yannick
 *
 */
public class Spell extends MeanOfAttack {
	
	/**
	 * Construteur, utilise celui de la classe parente MeanOfAttack.
	 * <p>Pas de life pour un sort, le forClass Magician est automatique.
	 * @param name
	 * @param img
	 * @param attack
	 */
	public Spell(String name, String img, int attack) {
		super(name, img, attack, "Magician");
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
		
		if (player instanceof Magician) {
			return player.changeItem(this);
		} else {
			return "Seul un Magicien peut utiliser cette arme.";
		}
	}
	
}
