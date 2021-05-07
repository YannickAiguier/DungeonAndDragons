
public class Weapon extends MeanOfAttack {

	/**
	 * @param name
	 * @param img
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Weapon(String name, String img, int attack) {
		super(name, img, attack, "Warrior");
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
