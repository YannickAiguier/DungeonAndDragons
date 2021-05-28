package box;

import player.Player;
import viewers.Viewer;

/**
 * Classe abstraite représentant tous les monstres pouvant être générés sur une
 * case du plateau de jeu (hérite de Box).
 * 
 * @see Box
 * @see Dragon
 * @see Gobelin
 * @see Sorcerer
 * @author yannick
 *
 */
public abstract class Monster extends Box {

	/**
	 * Constructeur, utilise aussi celui de la classe parente Box.
	 * <p>
	 * Pas de forClass pour un monstre.
	 * </p>
	 * 
	 * @param name   : nom de l'objet qi'il y a sur la case.
	 * @param img    : le nom du fichier image la représentant.
	 * @param life   : de la vie, soit à ajouter à celle du héros (potion), soit
	 *               indiquant la vie du monstre présent sur la case.
	 * @param attack : une force d'attaque, représentant celle de l'arme trouvée ou
	 *               celle du monstre présent sur la case.
	 */
	public Monster(String name, String img, int life, int attack) {
		super(name, img, life, attack, "");
	}

	@Override
	public String toString() {
		return this.name + " a " + life + " points de vie et " + attack + " points d'attaque.";
	}

	/**
	 * Gère le traitement d'une case de plateau de jeu lorsque le joueur s'y arrête.
	 * <p>
	 * Ici c'est un combat entre le monstre et le joueur.
	 * </p>
	 * 
	 * @param player : le joueur sur la case.
	 * @param viewer : l'objet Viewer auquel passer les résultats pour affichage.
	 * 
	 * @see #fight(Player, Viewer)
	 */
	@Override
	public void process(Player player, Viewer viewer) {
		// afficher le contenu de la case
		viewer.showBox(this);
		// afficher ce qu'il se passe
		viewer.showEvent("Vous rencontrez un " + name + ".");
		// choisir MoA
		viewer.chooseAttack(player, this);
		// gérer le combat
		//this.fight(player, viewer, 0);
		// afficher le résultat
		//viewer.showPlayer(player);

	}

	/**
	 * Gère le combat entre un monstre et le joueur.
	 * 
	 * @param player : le joueur qui se bat.
	 * @param viewer : l'objet Viewer auquel passer les résultats pour affichage.
	 * 
	 * @see #attackPlayer(Player)
	 * @see Player#attackMonster(Monster)
	 */
	public void fight(Player player, Viewer viewer) {
		while (player.isAlive() && this.isAlive()) {
			viewer.addDetail(player.attackMonster(this, player.getMoa(player.getChosenSlot())));
			if (this.isAlive()) {
				viewer.addDetail(this.attackPlayer(player));
			} else {
				viewer.addDetail(player.getName() + " tue le " + name);
			}
		}
	}

	/**
	 * Gère une attaque du monstre sur un joueur.
	 * 
	 * @param player : le joueur attaqué.
	 * @return Une chaine de caractères résumant le résultat de l'attaque.
	 */
	public String attackPlayer(Player player) {
		player.setLife(Math.max(0, player.getLife() - attack));
		return this.name + " riposte et lui inflige " + attack + " points de dégâts.";
	}

	/**
	 * Vérifie si le monstre est en vie.
	 * 
	 * @return booléen : true si le monstre est en vie, false s'il n'a plus de
	 *         points de vie.
	 */
	public boolean isAlive() {
		return life > 0;
	}

}
