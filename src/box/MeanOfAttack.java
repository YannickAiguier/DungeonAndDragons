package box;
import dad.GameEngine;
import player.Player;
import viewers.Viewer;

/**
 * Classe abstraite pour gérer les moyens d'attaque Weapon et Spell (hérite de Box).
 * 
 * @see Box
 * @see Weapon
 * @see Spell
 * @author yannick
 *
 */
public abstract class MeanOfAttack extends Box {

	/**
	 * Constructeur, s'appuie entièrement sur le constructeur de Box.
	 * <p>
	 * Pas de vie pour un moyen d'attaque.
	 * </p>
	 * 
	 * @param name : nom de l'objet qi'il y a sur la case.
	 * @param img : le nom du fichier image la représentant.
	 * @param attack : une force d'attaque, représentant celle de l'arme trouvée ou celle du monstre présent sur la case.
	 * @param forClass : une "classe d'utilisation", permettant de savoir quel type de héro peut utiliser l'arme ou le sort trouvé.
	 */
	public MeanOfAttack(String name, String img, int attack, String forClass) {
		super(name, img, 0, attack, forClass);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [name=" + name + ", attack=" + attack + "]";
	}

	/**
	 * Gère le traitement d'une case de plateau de jeu lorsque le joueur s'y arrête.
	 * <p>
	 * Ici c'est une arme ou un sort, donc le joueur en est équipé s'il est de la
	 * classe voulue et que son équipement actuel est moins bon.
	 * </p>
	 * 
	 * @param player : le joueur sur la case.
	 * @param viewer : l'objet Viewer auquel passer les résultats pour affichage.
	 */
	@Override
	public void process(Player player, Viewer viewer) {
		// afficher le contenu de la case
		viewer.showBox(this);
		// afficher ce qu'il se passe
		viewer.showEvent("Vous trouvez un(e) " + this.name + ".");
		// choisir ou mettre le moa dans l'inventaire
		viewer.chooseInventorySlot(player, this);
		// S'équiper de l'objet s'il est meilleur que l'actuel
		//viewer.showDetail(this.equip(player));
		// afficher le résultat
		//viewer.showPlayer(player);
	}

	/**
	 * @param player
	 * @return
	 */
	public abstract String equip(Player player);

}
