
public class Spell extends MeanOfAttack {
	
	/**
	 * @param name
	 * @param life
	 * @param attack
	 * @param forClass
	 */
	public Spell(String name, int attack) {
		super(name, attack, "Magician");
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
