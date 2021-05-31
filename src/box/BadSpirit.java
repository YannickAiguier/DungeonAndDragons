package box;

import player.Magician;
import player.Player;
import viewers.Viewer;

public class BadSpirit extends Monster {

	public BadSpirit() {
		super("Mauvais esprit", "bad_spirit.png", 15, 5);
	}

	@Override
	public void fight(Player player, Viewer viewer) {
		if (player instanceof Magician) {
			while (player.isAlive() && this.isAlive()) {
				viewer.addDetail(player.attackMonster(this, player.getMoa(player.getChosenSlot())));
				if (this.isAlive()) {
					viewer.addDetail(this.attackPlayer(player));
				} else {
					viewer.addDetail(player.getName() + " tue le " + name);
				}
			}
		} else {
			viewer.addDetail(
					player.getName() + " n'est pas un magicien, il ne sent pas le présence du mauvais esprit...");
		}

	}
}
