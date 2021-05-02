
public class GameEngine {

	Player player1;

	public GameEngine(Player player) {
		player1 = player;
	}

	public void start() {
		System.out.println("Jeu lancé");
		GameBoard myGameBoard = new GameBoard();
		while (myGameBoard.playerNotOnLastBox()) {
			myGameBoard.advancePlayer();
			System.out.println(player1.getName() + myGameBoard);
		}
		System.out.println(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
	}

	// fonction qui gère la récupération d'un coffre
	private String getChest(Object content) {
		switch (content.getClass().getName()) {
		case "Potion":
			player1.setLife(player1.getLife() + ((Potion) content).getLife());
			return player1.getName() + " boit " + ((Potion) content).getName() + " et gagne "
					+ ((Potion) content).getLife() + " points de vie.";
		case "Weapon":
			if (content.getClass().getName() == "Warrior") {
				return player1.changeItem((Weapon) content);
			} else {
				return "Pas de chance le coffre ne contient que des armes pour guerrier.";
			}
		case "Spell":
			if (content.getClass().getName() == "Magician") {
				return player1.changeItem((Weapon) content);
			} else {
				return "Pas de chance le coffre ne contient que des sorts pour magicien.";
			}
		default:
			return "Oups...";
		}
	}
}
