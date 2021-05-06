
public abstract class Box {
	
	String name;
	int life;
	int attack;
	String forClass;	

	/**
	 * @param name
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Box(String name, int life, int attack, String forClass) {
		this.name = name;
		this.life = life;
		this.attack = attack;
		this.forClass = forClass;
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
