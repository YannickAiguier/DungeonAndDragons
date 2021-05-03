
public class Potion {
	
	String name;
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
	
	
	
}
