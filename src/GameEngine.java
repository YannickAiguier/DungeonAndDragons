
public class GameEngine {

	Player player1;
	Viewer viewer;
	GameBoard myGameBoard;
	int dice;

	public GameEngine() {
		this.player1 = null;
		this.viewer = null;
		this.dice = 0;
		this.myGameBoard = null;
	}
	
	public GameEngine(Player player, Viewer viewer) {
		this();
		this.player1 = player;
		this.viewer = viewer;
	}

	// fonction qui gère le jeu une fois la partie démarrée
	public void start() {
		MyUtils u = new MyUtils();
		u.print("Jeu lancé");
		
		// création et initialisation du plateau de jeu
		initBoard();
		
		// tant que le joueur n'est pas arrivé au bout et qu'il est en vie
		while (isNotGameOver()) {
			// jouer un tour
			while(viewer.waitDice()) {
				
			}
			// lancer le dé, avancer le joueur et afficher sa position
			letsGo();

			// traitement de la case
			boxProcess();
		}
		gameEnd();
	}	

	public void initBoard() {
		myGameBoard = new GameBoard();
		myGameBoard.initBoard();
	}
	
	public boolean isNotGameOver() {
		return this.myGameBoard.playerNotOnLastBox() && player1.isAlive();
	}
	
	public boolean isGameOver() {
		return !(this.myGameBoard.playerNotOnLastBox() && player1.isAlive());
	}
	
	public void letsGo() {
		dice = myGameBoard.advancePlayer();
		viewer.showMove(dice, myGameBoard.getPlayerPos());
	}
	
	public void boxProcess() {
		if (myGameBoard.getBox() == null) {
			viewer.showEvent("Case vide, on continue...");
		} else {
			myGameBoard.getBox().process(player1, viewer);
		}
	}
	
	public void gameEnd() {
		if (player1.isAlive()) {
			viewer.showDetail(player1.getName() + " est arrivé à la fin du plateau. Jeu terminé !");
		} else {
			viewer.showDetail(player1.getName() + " a trouvé la mort en combattant un " + myGameBoard.getBox().getName());
		}
	}
	
}