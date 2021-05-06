
public class Potion extends Box {
	
	int life;
	
	/**
	 * Constructeur
	 * @param name
	 * @param life
	 */
	public Potion(String name, int life) {
		this.name = name;
		this.life = life;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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
		viewer.showDetail("Vous buvez " + this.getName() + ", vous avez maintenant " + player.getLife() + " points de vie.");
		
		// afficher le résultat = showPlayer
		viewer.showPlayer(player);
	}
	
	
	
}
