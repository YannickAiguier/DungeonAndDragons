package box;
import dad.GameEngine;
import player.Player;
import viewers.Viewer;

/**
 * Classe pour gérer les potions (hérite de Box).
 * 
 * @author yannick
 *
 */
public class Potion extends Box {

	/**
	 * Constructeur, utilise celui de la classe parente Box.
	 * <p>
	 * Pas d'attack et forClass pour une potion.
	 * </p>
	 *
	 * 
	* @param name : nom de l'objet qi'il y a sur la case.
	 * @param img : le nom du fichier image la représentant.
	 * @param life : de la vie, soit à ajouter à celle du héros (potion), soit indiquant la vie du monstre présent sur la case.
	 */
	public Potion(String name, String img, int life) {
		super(name, img, life, 0, "");
	}

	@Override
	public String toString() {
		return "une " + name;
	}

	/**
	 * Gère le traitement d'une case de plateau de jeu lorsque le joueur s'y arrête.
	 * <p>
	 * Ici c'est le joueur qui boit une potion de vie.
	 * </p>
	 * 
	 * @param player : le joueur sur la case.
	 * @param viewer : l'objet Viewer auquel passer les résultats pour affichage.
	 * 
	 */
	@Override
	public void process(Player player, Viewer viewer) {
		// afficher le contenu de la case
		viewer.showBox(this);
		// afficher ce qu'il se passe
		viewer.showEvent("Vous trouvez " + toString());
		// augmenter le niveau de vie du joueur, mais pas au-delà de sa vie maximum
		player.setLife(Math.min(player.getMaxLife(), player.getLife() + this.getLife()));
		viewer.showDetail("Vous buvez " + this.name + ", vous avez maintenant " + player.getLife() + " points de vie.");
		// afficher le résultat = showPlayer
		viewer.showPlayer(player);
	}

}
