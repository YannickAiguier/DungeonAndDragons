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
	 * 
	 * @param name : nom de l'objet qi'il y a sur la case.
	 * @param img : le nom du fichier image la représentant.
	 * @param attack : une force d'attaque, représentant celle de l'arme trouvée ou celle du monstre présent sur la case.
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
	public String equip(Player player) {
		
		if (player instanceof Warrior) {
			return player.changeItem(this, player.getChosenSlot());
		} else {
			return "Seul un guerrier peut utiliser cette arme.";
		}
	}	
	
}
