
public class Weapon extends MeanOfAttack {

	// constructeur, utilise celui de la classe parente MeanOfAttack
	public Weapon(String name, int attack) {
		super(name, attack);
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
