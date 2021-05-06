
public class Menu extends Viewer {

	Player player;
	MyUtils u;

	public Menu() {
		u = new MyUtils();
	}

	// fonction qui démarre le menu
	public void start() {
		int choix = 0;
		while (choix < 1 || choix > 2) {
			u.print(("1) Nouveau personnage"));
			u.print(("2) Quitter le jeu"));
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				createMenu();
				choix = 0;
				break;
			case 2:
				u.print("Fin du programme.");
				System.exit(0);
				break;
			default:
				u.print("Choisissez 1 ou 2...");
				break;
			}
		}
	}

	// fonction qui gère le sous-menu de création de joueur
	private void createMenu() {
		int choix = 0;
		while (choix < 1 || choix > 3) {
			u.print("1) Guerrier");
			u.print("2) Magicien");
			u.print("3) Retour");
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				u.print("Création d'un guerrier");
				createPlayer("Warrior");
				playerMenu();
				choix = 0;
				break;
			case 2:
				u.print("Création d'un magicien");
				createPlayer("Magician");
				playerMenu();
				choix = 0;
				break;
			case 3:
				choix = 0;
				return;
			default:
				u.print("Choisissez 1, 2 ou 3...");
				break;
			}
		}
	}

	// fonction qui crée un guerrier ou un magicien
	private void createPlayer(String myClass) {
		String name = u.getText("Nom de votre personnage : ");
		if (myClass == "Warrior") {
			player = new Warrior(name);
		}
		if (myClass == "Magician") {
			player = new Magician(name);
		}
		u.print("Personnage créé.");
	}

	// fonction qui gère le sous-menu d'affichage ou de modification du joueur
	private void playerMenu() {
		int choix = 0;
		while (choix != 1 && choix != 2) {
			u.print("1) Afficher les caractéristiques du personnage");
			u.print("2) Modifier les caractéristiques du personnage");
			u.print("3) Retour");
			if (player != null) {
				u.print("4) Démarrer le jeu");
			}
			choix = u.getInt("Votre choix : ");
			switch (choix) {
			case 1:
				u.print("Caractéristiques de votre personnage : ");
				u.print(player.toString());
				choix = 0;
				break;
			case 2:
				createPlayer(player.getClass().getSimpleName());
				choix = 0;
				break;
			case 3:
				choix = 0;
				return;
			case 4:
				GameEngine myEngine = new GameEngine(player, this);
				myEngine.start();
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void showMove(int dice, int playerPosition) {
		u.print("vous avancez de " + dice + " case(s) et arrivez en case " + playerPosition + ".");		
	}

	@Override
	public void showEvent(String s) {
		u.print(s);
		
	}

	@Override
	public void showDetail(String s) {
		u.print(s);
		
	}

	@Override
	public void showPlayer(Player player) {
		u.print(player.getName() + " a " + player.getLife() + " points de vie, se bat avec " + player.getFirstAttack().getName() + " pour une force d'attaque de " + (player.getAttack() + player.getFirstAttack().getAttack()) + ".");
		
	}

	@Override
	public void showBox(Box box) {
		// rien à faire en mode texte
	}
	
	

}