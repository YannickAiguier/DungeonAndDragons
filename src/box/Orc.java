package box;

import player.Player;
import player.Warrior;
import viewers.Viewer;

public class Orc extends Monster {

	public Orc() {
		super("Orc", "orc.png", 10, 6);
	}
	
	@Override
	public void fight(Player player, Viewer viewer) {
		if (player instanceof Warrior) {
			while (player.isAlive() && this.isAlive()) {
				viewer.addDetail(player.attackMonster(this));
				if (this.isAlive()) {
					viewer.addDetail(this.attackPlayer(player));
				} else {
					viewer.addDetail(player.getName() + " tue le " + name);
				}
			}
		} else {
			viewer.addDetail(player.getName() + " n'est pas un guerrier, l'Orc se désintéresse de lui.");
		}
		
	}

}
