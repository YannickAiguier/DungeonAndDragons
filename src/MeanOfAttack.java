
public abstract class MeanOfAttack extends Box {
	
	/**
	 * @param name
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public MeanOfAttack(String name, int attack, String forClass) {
		super(name, 0, attack, forClass);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " [name=" + name + ", attack=" + attack + "]";
	}
	
	@Override
	public void process(Player player, Viewer viewer) {

		// afficher le contenu de la case = showBox
		viewer.showBox(this);

		// afficher ce qu'il se passe = showEvent, fight monstre
		viewer.showEvent("Vous trouvez un " + this.name + ".");
		// fonction pour s'équiper de l'objet (ou non)
		viewer.showDetail(this.equip(player));
		// afficher le résultat = showPlayer
		viewer.showPlayer(player);

	}
	
	protected abstract String equip(Player player);

}
