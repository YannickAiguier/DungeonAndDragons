
public class Weapon extends MeanOfAttack {

	/**
	 * @param name
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Weapon(String name, int attack) {
		super(name, attack, "Warrior");
	}

	@Override
	protected String equip(Player player) {
		
		if (player instanceof Warrior) {
			return player.changeItem(this);
		} else {
			return "Seul un guerrier peut utiliser cette arme.";
		}
	}
	
	
	
}
