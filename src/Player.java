
public abstract class Player {

	private String name;
	String img;
	private int life;
	private int maxLife;
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
	 * @param maxLife
	 * @param attack
	 * @param firstAttack
	 * @param protectionType
	 */
	public Player(String name, String img, int life, int maxLife, int attack, MeanOfAttack firstAttack, String protectionType) {
		this.name = name;
		this.img = img;
		this.life = life;
		this.maxLife = maxLife;
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
	 * @return the maxLife
	 */
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * @param maxLife the maxLife to set
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
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
	public String attackMonster(Monster monster) {
		int dmg = this.attack + firstAttack.getAttack();
		monster.setLife(Math.max(0, monster.getLife() - dmg));
		return name + " attaque un " + monster.getClass().getName() + " et lui inflige " + (attack + firstAttack.getAttack()) + " points de dégâts.";
	}

	// méthode qui change l'équipement seulement s'il est meilleur que l'actuel
	public String changeItem(MeanOfAttack item) {
		if (item.getAttack() > firstAttack.getAttack()) {
			firstAttack = item;
			return name + " s'équipe de " + item.getName() + " qui inflige " + item.getAttack() + " points de dégâts.";
		} else {
			return name + " trouve  " + item.getName() + " qui inflige " + item.getAttack() + " points de dégâts. Pas intéressant...";
		}
	}
	
	// fonction qui vérifie si le joueur est en vie
	public boolean isAlive() {
		return life > 0;
	}

}