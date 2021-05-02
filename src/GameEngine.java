
public class GameEngine {

	Player player1;
	Monster monster;

	public GameEngine(Player player) {
		player1 = player;
	}

	// fonction qui gère le jeu une fois la partie démarrée
	public void start() {
		System.out.println("Jeu lancé");
		// création et initialisation du plateau de jeu
		GameBoard myGameBoard = new GameBoard();
		myGameBoard.initBoard();
		// tant que le joueur n'est pas arrivé au bout et qu'il est en vie
		while (myGameBoard.playerNotOnLastBox() && player1.getLife() > 0) {
			// faire avancer le joueur et afficher sa position
			myGameBoard.advancePlayer();
			System.out.println(player1.getName() + myGameBoard);
			// traitement de la case : vide, coffre ou monstre
			if (myGameBoard.getBox() == null) {
				System.out.println("RAS, on continue.");
			} else if (myGameBoard.getBox().getClass().getName() == "Monster") {
				monster = (Monster) myGameBoard.getBox();
				fight(monster);
				if (player1.isAlive()) {
					System.out.println(player1.getName() + " a vaincu le " + monster.getClass().getName() + ", il lui reste " + player1.getLife() + " points de vie.");
				}
			} else {
				System.out.println(getChest(myGameBoard.getBox()));
			}
		}
		if (player1.isAlive()) {
			System.out.println(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
		} else {
			System.out.println(player1.getName() + " a trouvé la mort en combattant un " + monster.getClass().getName());
		}
		
	}

	// fonction qui gère la récupération d'un coffre
	private String getChest(Object content) {
		System.out.println("Contenu du coffre : " + content);
		switch (content.getClass().getName()) {
		case "Potion":
			player1.setLife(player1.getLife() + ((Potion) content).getLife());
			return player1.getName() + " boit " + ((Potion) content).getName() + " et gagne "
					+ ((Potion) content).getLife() + " points de vie.";
		case "Weapon":
			if (player1.getClass().getName() == "Warrior") {
				return player1.changeItem((Weapon) content);
			} else {
				return "Pas de chance le coffre ne contient que des armes pour guerrier.";
			}
		case "Spell":
			if (player1.getClass().getName() == "Magician") {
				return player1.changeItem((Spell) content);
			} else {
				return "Pas de chance le coffre ne contient que des sorts pour magicien.";
			}
		default:
			return "Oups...";
		}
	}

	// fonction qui gère un combat entre le joueur et un monstre jusqu'à la mort de
	// l'un d'entre eux
	private void fight(Monster monster) {
		while (player1.getLife() > 0 && monster.getLife() > 0) {
			System.out.println(player1.attackMonster(monster));
			if (monster.getLife() > 0) {
				System.out.println(monster.attackPlayer(player1));
			}
		}
	}
}
