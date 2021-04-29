
public class MeanOfAttack {

	String name;
	int attack;
	/**
	 * @param name
	 * @param attack
	 */
	public MeanOfAttack(String name, int attack) {
		this.name = name;
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
	@Override
	public String toString() {
		return "MeanOfAttack [name=" + name + ", attack=" + attack + "]";
	}
	
	

}
