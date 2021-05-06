
public class Potion extends Box {	

	/**
	 * @param name
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Potion(String name, int life) {
		super(name, life, 0, "");
	}

	@Override
	public String toString() {
		return name + " qui restaure " + life + " points de vie";
	}

	@Override
	public void process(Player player, Viewer viewer) {
		
		// afficher le contenu de la case = showBox
		viewer.showBox(this);
		
		// afficher ce qu'il se passe = showEvent, showDetail
		viewer.showEvent("Vous trouvez " + toString());
		player.setLife(Math.min(player.getMaxLife(), player.getLife() + this.getLife()));
		viewer.showDetail("Vous buvez " + this.name + ", vous avez maintenant " + player.getLife() + " points de vie.");
		
		// afficher le résultat = showPlayer
		viewer.showPlayer(player);
	}
	
	
	
}
