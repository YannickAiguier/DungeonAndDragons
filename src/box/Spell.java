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
	 * <p>Pas de life pour un sort, le forClass Magician est automatique.</p>
	 * 
	* @param name : nom de l'objet qi'il y a sur la case.
	 * @param img : le nom du fichier image la représentant.
	 * @param attack : une force d'attaque, représentant celle de l'arme trouvée ou celle du monstre présent sur la case.
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
