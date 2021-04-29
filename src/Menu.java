import java.util.Scanner;

public class Menu {

	// fonction qui démarre le menu (lance le jeu)
	public static void start() {
		System.out.println("1) Nouveau personnage");
		System.out.println("2) Quitter le jeu");
		System.out.print("Votre choix : ");
		Scanner saisie = new Scanner(System.in);
		int choix = 0;
		while (choix != 1 && choix != 2) {
			choix = saisie.nextInt();
			saisie.nextLine();
			switch (choix) {
			case 1:
				createMenu();
				break;
			case 2:
				System.out.println("Fin du programme...");
				System.exit(0);
			default:
				System.out.println("Choisissez 1 pour créer un nouveau personnage ou 2 pour quitter le jeu");
				break;
			}
		}
		saisie.close();
	}
	
	// fonction qui gère le sous-menu de création de joueur
	private static void createMenu() {
		System.out.println("1) Guerrier");
		System.out.println("2) Magicien");
		System.out.print("Votre choix : ");
		Scanner saisie = new Scanner(System.in);
		int choix = 0;
		while (choix != 1 && choix != 2) {
			choix = saisie.nextInt();
			saisie.nextLine();
			switch (choix) {
			case 1:
				System.out.println("Création d'un guerrier");
				break;
			case 2:
				System.out.println("Création d'un magicien");
				break;
			default:
				System.out.println("Choisissez 1 pour créer un guerrier ou 2 pour créer un magicien");
				break;
			}
		}
		saisie.close();
	}
	
	// fonction qui crée un guerrier(1) ou un magicien (2)
	private static void createPlayer(int num) {
		// faire saisir nom, vie, attaque
		Player joueur;
		if(num == 1) {
			joueur = new Warrior(nom, vie, attaque);
		}
		if(num == 2) {
			joueur = new Magician(nom, vie, attaque);
		}
	}
	
	public static void main(String[] args) {
		start();
	}
}