
public abstract class Monster extends Box {

	/**
	 * @param name
	 * @param img
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Monster(String name, String img, int life, int attack) {
		super(name, img, life, attack, "");
	}

	@Override
	public String toString() {
		return this.name + " a " + life + " points de vie et " + attack + " points d'attaque.";
	}

	// méthode d'attaque d'un joueur
	public String attackPlayer(Player player) {
		player.setLife(Math.max(0, player.getLife() - attack));
		return this.name + " riposte et lui inflige " + attack + " points de dégâts.";
	}

	@Override
	public void process(Player player, Viewer viewer) {

		// afficher le contenu de la case = showBox
		viewer.showBox(this);

		// afficher ce qu'il se passe = showEvent, fight monstre
		viewer.showEvent("Vous rencontrez un " + this.name + ".");
		// fonction pour fight monstre
		this.fight(player, viewer);
		// afficher le résultat = showPlayer
		viewer.showPlayer(player);

	}

	public void fight(Player player, Viewer viewer) {
		while (player.isAlive() && this.isAlive()) {
			viewer.addDetail(player.attackMonster(this));
			if (this.isAlive()) {
				viewer.addDetail(this.attackPlayer(player));
			} else {
				viewer.addDetail(player.getName() + " tue le " + name);
			}
		}
	}

	// fonction qui vérifie si le monstre est en vie
	public boolean isAlive() {
		return life > 0;
	}

}
