
public abstract class Player {

	String name;
	int life;
	int attack;
	MeanOfAttack firstAttack;
	String protectionType;
	
	/**
	 * Constructeur sans paramètre
	 */
	public Player() {
	}

	/**
	 * Constructeur
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	

	/**
	 * Constructeur
	 * @param name
	 * @param life
	 * @param baseAttack
	 */
	public Player(String name, int life, int attack) {
		this.name = name;
		this.life = life;
		this.attack = attack;
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
	 * @return the attackType
	 */
	public MeanOfAttack getFirstAttack() {
		return firstAttack;
	}

	/**
	 * @param attackType the attackType to set
	 */
	public void setFirstAttack(MeanOfAttack attackType) {
		this.firstAttack = attackType;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [name= " + name + ", life= " + life + ", attack= " + attack + ", baseAttack= " + attack
				+ ", protectionType= " + protectionType + "]";
	}	
	
}