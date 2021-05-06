
public class Spell extends MeanOfAttack {

	// constructeur, utilise celui de la classe parente MeanOfAttack
	public Spell(String name, int attack) {
		super(name, attack);
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
