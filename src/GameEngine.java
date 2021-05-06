
public class GameEngine {

	Player player1;
	Viewer viewer;

	public GameEngine(Player player, Viewer viewer) {
		player1 = player;
		this.viewer = viewer;
	}

	// fonction qui gère le jeu une fois la partie démarrée
	public void start() {
		MyUtils u = new MyUtils();
		u.print("Jeu lancé");
		
		// création et initialisation du plateau de jeu
		GameBoard myGameBoard = new GameBoard();
		myGameBoard.initBoard();
		u.print("1");
		
		// tant que le joueur n'est pas arrivé au bout et qu'il est en vie
		while (myGameBoard.playerNotOnLastBox() && player1.isAlive()) {
			u.print("2");
			// jouer un tour
			// int dice = myGameBoard.advancePlayer();
			//viewer.playRound(dice, myGameBoard.getPlayerPos());
			while(viewer.waitDice()) {
				
			}
			// lancer le dé et avancer le joueur
			int dice = myGameBoard.advancePlayer();
			u.print(String.valueOf(u));
			// afficher la position
			viewer.showMove(dice, myGameBoard.getPlayerPos());

			// traitement de la case
			if (myGameBoard.getBox() == null) {
				viewer.showEvent("Case vide, on continue...");
			} else {
				myGameBoard.getBox().process(player1, viewer);
			}
		}
		if (player1.isAlive()) {
			viewer.showDetail(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
		} else {
			viewer.showDetail(player1.getName() + " a trouvé la mort en combattant un " + myGameBoard.getBox().getName());
		}
	}
}