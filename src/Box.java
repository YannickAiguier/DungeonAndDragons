
public abstract class Box {
	
	String name;
		
	/**
	 * @param name
	 */
	public Box(String name) {
		this.name = name;
	}
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	// fonction qui gère le traitement d'une case de plateau de jeu
	public abstract void process(Player player, Viewer viewer);
	
}
