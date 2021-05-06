
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

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * @return the forClass
	 */
	public String getForClass() {
		return forClass;
	}

	/**
	 * @param forClass the forClass to set
	 */
	public void setForClass(String forClass) {
		this.forClass = forClass;
	}

	@Override
	public String toString() {
		return "Box [name=" + name + ", life=" + life + ", attack=" + attack + ", forClass=" + forClass + "]";
	}

	// fonction qui gère le traitement d'une case de plateau de jeu
	public abstract void process(Player player, Viewer viewer);
	
}
