
public class Player {

	String name;
	int life;
	int attack;
	int baseAttack;
	MeanOfAttack attackType;
	String protectionType;
	
	/**
	 * 
	 */
	public Player() {
	}

	/**
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	

	/**
	 * @param name
	 * @param life
	 * @param baseAttack
	 */
	public Player(String name, int life, int baseAttack) {
		this.name = name;
		this.life = life;
		this.attack = baseAttack;
		this.baseAttack = baseAttack;
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
	 * @return the baseAttack
	 */
	public int getBaseAttack() {
		return baseAttack;
	}

	/**
	 * @param baseAttack the baseAttack to set
	 */
	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}

	/**
	 * @return the attackType
	 */
	public MeanOfAttack getAttackType() {
		return attackType;
	}

	/**
	 * @param attackType the attackType to set
	 */
	public void setAttackType(MeanOfAttack attackType) {
		this.attackType = attackType;
	}

	/**
	 * @return the protectionType
	 */
	public String getProtectionType() {
		return protectionType;
	}

	/**
	 * @param protectionType the protectionType to set
	 */
	public void setProtectionType(String protectionType) {
		this.protectionType = protectionType;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", life=" + life + ", attack=" + attack + ", baseAttack=" + baseAttack
				+ ", protectionType=" + protectionType + "]";
	}
	
	public static void main(String[] args) {
		Player myPlayer = new Player("Yannick", 100, 150);
		System.out.println(myPlayer);
	}
	
	
}