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
	 * @param name
	 * @param int
	 * @param attack
	 * @param forClass
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
		// S'équiper de l'objet s'il est meilleur que l'actuel
		viewer.showDetail(this.equip(player));
		// afficher le résultat
		viewer.showPlayer(player);
	}

	/**
	 * @param player
	 * @return
	 */
	protected abstract String equip(Player player);

}
