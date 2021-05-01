
public abstract class Player {

	private String name;
	private int life;
	private int attack;
	private MeanOfAttack firstAttack;
	private String protectionType;

	/**
	 * Constructeur sans paramètre
	 */
	public Player() {
	}

	/**
	 * Constructeur
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Constructeur
	 * 
	 * @param name
	 * @param life
	 * @param attack
	 * @param firstAttack
	 * @param protectionType
	 */
	public Player(String name, int life, int attack, MeanOfAttack firstAttack, String protectionType) {
		this.name = name;
		this.life = life;
		this.attack = attack;
		this.firstAttack = firstAttack;
		this.protectionType = protectionType;
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
		return this.getClass().getName() + " [name= " + name + ", life= " + life + ", attack= " + attack
				+ ", firstAttack= " + firstAttack + " , protectionType= " + protectionType + "]";
	}

	// méthode d'attaque d'un monstre
	public void attackMonster(Monster monster) {
		int dmg = this.attack + firstAttack.getAttack();
		monster.setLife(monster.getLife() - dmg);
	}

	// méthode qui change l'équipement seulement s'il est meilleur que l'actuel
	public void changeItem(MeanOfAttack item) {
		if (item.getAttack() > firstAttack.getAttack()) {
			firstAttack = item;
		} else {
			System.out.println("Votre équipement est meilleur, vous le gardez.");
		}
	}

}