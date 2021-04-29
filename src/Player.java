
public class Player {

	String name;
	int life;
	int attack;
	int baseAttack;
	MeanOfAttack attackType;
	String protectionType;
	
	public Player() {
		name = "";
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, int life, int attack) {
		this.name = name;
		this.life = life;
		this.attack = attack;
	}
}