
public class Spell extends MeanOfAttack {
	
	/**
	 * @param name
	 * @param img
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Spell(String name, String img, int attack) {
		super(name, img, attack, "Magician");
	}

	@Override
	protected String equip(Player player) {
		
		if (player instanceof Magician) {
			return player.changeItem(this);
		} else {
			return "Seul un Magicien peut utiliser cette arme.";
		}
	}
	
}
