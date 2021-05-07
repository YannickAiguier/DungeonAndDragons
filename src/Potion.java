
public class Potion extends Box {	

	/**
	 * @param name
	 * @param img
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Potion(String name, String img, int life) {
		super(name, img, life, 0, "");
	}

	@Override
	public String toString() {
		return "une " + name;
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
