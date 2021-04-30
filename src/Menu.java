import java.util.Scanner;

public class Menu {
	
	Scanner saisie;
	int choix;
	Player player;

	public Menu() {
		saisie = new Scanner(System.in);
		choix = 0;
	}

	// fonction qui démarre le menu
	public void start() {
		choix = 0;
		while (choix < 1 || choix > 2 ) {
			System.out.println("1) Nouveau personnage");
			System.out.println("2) Quitter le jeu");
			System.out.print("Votre choix : ");
			choix = saisie.nextInt();
			saisie.nextLine();
			switch (choix) {
			case 1:
				createMenu();
				choix = 0;
				break;
			case 2:
				System.out.println("Fin du programme.");
				System.exit(0);
				break;		
			default:
				System.out.println("Choisissez 1 ou 2...");
				break;
			}
		}
		saisie.close();
	}
	
	// fonction qui gère le sous-menu de création de joueur
	private void createMenu() {
		choix = 0;
		while (choix < 1 || choix > 3) {
			System.out.println("1) Guerrier");
			System.out.println("2) Magicien");
			System.out.println("3) Retour");
			System.out.print("Votre choix : ");
			choix = saisie.nextInt();
			saisie.nextLine();
			switch (choix) {
			case 1:
				System.out.println("Création d'un guerrier");
				createPlayer("Warrior");
				playerMenu();
				choix = 0;
				break;
			case 2:
				System.out.println("Création d'un magicien");
				createPlayer("Magician");
				playerMenu();
				choix = 0;
				break;
			case 3:
				choix = 0;
				return;
			default:
				System.out.println("Choisissez 1, 2 ou 3...");
				break;
			}
		}
	}
	
	// fonction qui crée un guerrier ou un magicien
	private void createPlayer(String myClass) {
		String name;
		int life, attack;
		System.out.println("Nom de votre personnage : ");
		name = saisie.nextLine();
		System.out.println("Vie de votre personnage : ");
		life = saisie.nextInt();
		saisie.nextLine();
		System.out.println("Attaque de votre personnage : ");
		attack = saisie.nextInt();
		saisie.nextLine();
		if(myClass == "Warrior") {
			player = new Warrior(name, life, attack);
		}
		if(myClass == "Magician") {
			player = new Magician(name, life, attack);
		}
		System.out.println("Personnage créé.");		
	}
	
	// fonction qui gère le sous-menu d'affichage ou de modification du joueur
	private void playerMenu() {
		choix = 0;
		while (choix != 1 && choix != 2) {
			System.out.println("1) Afficher les caractéristiques du personnage");
			System.out.println("2) Modifier les caractéristiques du personnage");
			System.out.println("3) Retour");
			if (player != null) {
				System.out.println("4) Démarrer le jeu");
			}
			System.out.print("Votre choix : ");
			choix = saisie.nextInt();
			saisie.nextLine();
			switch (choix) {
			case 1:
				System.out.println("Caractéristiques de votre personnage : ");
				System.out.println(player);
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
				GameEngine myEngine = new GameEngine();
				myEngine.start();
				saisie.close();
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Menu firstMenu = new Menu();
		
		firstMenu.start();
	}
}