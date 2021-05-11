package viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import box.Box;
import box.MeanOfAttack;
import box.Monster;
import box.Potion;
import player.Player;

/**
 * Classe qui gère la représentation graphique du jeu, implémente l'interface
 * Viewer.
 * <p>
 * Utilisée par MainGraphics pour l'affichage, elle utilise les classes TextArea
 * et GraphArea pour afficher les textes et images.<br>
 * Elle est découpée en plusieurs JPanel. Le premier d'entre eux est fullPanel
 * il est découpé en 5 zones :
 * <ul>
 * <li>titlePanel : zone d'affichage du titre</li>
 * <li>leftDecoPanel : zone à gauche de l'écraan, n'affiche rien mais sert pour
 * gérer les marges</li>
 * <li>rightDecoPanel : zone à gauche de l'écraan, n'affiche rien mais sert pour
 * gérer les marges</li>
 * <li>gamePanel : zone principale au centre, affiche tout le déroulement du
 * jeu, état du joueur et des cases du plateau de jeu</li>
 * <li>bottonPanel : zone d'affichage du décor en bas de la fenêtre</li>
 * </ul>
 * </p>
 * 
 * @see JPanel
 * @see Viewer
 * @see textArea
 * @see GraphArea
 * @author yannick
 *
 */
public class MyGame implements Viewer {

	JPanel fullPanel, titlePanel, leftDecoPanel, rightDecoPanel, bottomPanel, gamePanel, boardPanel, playerFullPanel,
			playerPanel, playerWeaponPanel, storyPanel, boxPanel, buttonsPanel;
	GraphArea titlePicture, bottomPicture, playerWeaponPicture, boxPicture, playerPicture;
	TextArea playerName, playerLife, playerAttack, playerTotalAttack, playerWeaponAttack, storyEvent, storyDetail,
			storyMove, boxName, boxLife, boxAttack, boxClass;
	JButton rollDice;

	/**
	 * Constructeur, initialise tous les composant à null. Ils seront remplis plus
	 * tard.
	 */
	public MyGame() {
		this.fullPanel = null;
		this.titlePanel = null;
		this.leftDecoPanel = null;
		this.rightDecoPanel = null;
		this.bottomPanel = null;
		this.gamePanel = null;
		this.boardPanel = null;
		this.playerFullPanel = null;
		this.playerPanel = null;
		this.playerWeaponPanel = null;
		this.storyPanel = null;
		this.boxPanel = null;
		this.buttonsPanel = null;
		this.titlePicture = null;
		this.bottomPicture = null;
		this.playerWeaponPicture = null;
		this.boxPicture = null;
		this.playerPicture = null;
		this.playerName = null;
		this.playerLife = null;
		this.playerAttack = null;
		this.playerTotalAttack = null;
		this.playerWeaponAttack = null;
		this.storyEvent = null;
		this.storyDetail = null;
		this.storyMove = null;
		this.boxName = null;
		this.boxLife = null;
		this.boxAttack = null;
		this.boxClass = null;
		this.rollDice = null;
	}
	
	// le seul getter/setter intéressant est celui du dé, donc les autres ne sont pas implémentés.
	
	/**
	 * @return the rollDice
	 */
	public JButton getRollDice() {
		return rollDice;
	}

	/**
	 * @param rollDice the rollDice to set
	 */
	public void setRollDice(JButton rollDice) {
		this.rollDice = rollDice;
	}

	/**
	 * Crée toute la structure d'affichage, leur affecte le contenu de base pour le
	 * début de partie et renvoie le résultat (fullPanel contenant tout le reste).
	 * 
	 * @see #createFullPanel()
	 * @see #createTitleAndBottom()
	 * @see #createGamePanel()
	 * @see #createPlayerFullPanel()
	 * @see #createPlayerPanel()
	 * @see #createStoryPanel()
	 * @see #createBoxPanel()
	 * @see #createDiceButton()
	 * @see #assembleAllPanels()
	 * 
	 * @return JPanel : le conteneur parent (fullPanel)
	 */
	public JPanel init() {
		// crée fullPanel et sa structure, conteneur parent
		createFullPanel();
		// crée le titre en haut et le décor en bas
		createTitleAndBottom();
		// crée la zone du déroulement du jeu et sa tructure
		createGamePanel();
		// crée la zone d'affichage de l'état du joueur
		createPlayerFullPanel();
		createPlayerPanel();
		// crée la zone d'affichage du déroulement de l'histoire
		createStoryPanel();
		// crée la zone d'affichage du contenu de la case
		createBoxPanel();
		// crée le bouton du dé
		createDiceButton();
		// assemble le tout
		assembleAllPanels();

		return fullPanel;
	}

	/**
	 * Crée fullPanel et sa structure, conteneur parent.
	 */
	private void createFullPanel() {
		// création du JPanel parent
		fullPanel = new JPanel(new BorderLayout());
		fullPanel.setBackground(Color.black);

		// création des 5 JPanels enfants
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(1200, 100));
		titlePanel.setBackground(Color.black);

		leftDecoPanel = new JPanel();
		leftDecoPanel.setPreferredSize(new Dimension(50, 600));
		leftDecoPanel.setBackground(Color.black);

		rightDecoPanel = new JPanel();
		rightDecoPanel.setPreferredSize(new Dimension(50, 600));
		rightDecoPanel.setBackground(Color.black);

		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1200, 100));
		bottomPanel.setBackground(Color.black);

		gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBackground(Color.black);
		gamePanel.setVisible(false);
	}

	/**
	 * Crée le titre en haut et le décor en bas. 
	 */
	private void createTitleAndBottom() {
		// création des décors
		titlePicture = new GraphArea();
		titlePicture.setMinimumSize(new Dimension(1000, 100));
		titlePicture.setImg("title.png", 1000, 100);
		bottomPicture = new GraphArea();
		bottomPicture.setMinimumSize(new Dimension(1200, 100));
		bottomPicture.setImg("bottom_border.png", 1200, 100);
	}

	/**
	 * Crée la zone du déroulement du jeu et sa tructure.
	 */
	private void createGamePanel() {
		// création des JPanels enfants de gamePanel
		boardPanel = new JPanel();
		boardPanel.setBackground(Color.black);
		boardPanel.setPreferredSize(new Dimension(1000, 150));
		playerFullPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		playerFullPanel.setBackground(Color.black);
		playerFullPanel.setPreferredSize(new Dimension(400, 500));
		storyPanel = new JPanel();
		storyPanel.setBackground(Color.black);
		storyPanel.setPreferredSize(new Dimension(500, 500));
		boxPanel = new JPanel();
		boxPanel.setBackground(Color.black);
		boxPanel.setPreferredSize(new Dimension(200, 500));
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.black);
		buttonsPanel.setPreferredSize(new Dimension(1000, 50));
	}

	/**
	 * Crée la zone d'affichage de l'état du joueur (séparé en 2 zones : le joueur et son arme).
	 */
	private void createPlayerFullPanel() {
		// création des JPanel enfants de playerFullPanel
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.setBackground(Color.black);
		playerPanel.setPreferredSize(new Dimension(300, 500));
		playerWeaponPanel = new JPanel();
		playerWeaponPanel.setLayout(new BoxLayout(playerWeaponPanel, BoxLayout.Y_AXIS));
		playerWeaponPanel.setBackground(Color.black);
		playerWeaponPanel.setPreferredSize(new Dimension(100, 500));
	}

	/**
	 * Crée les éléments d'affichage des caractéristiques du joueur.
	 */
	private void createPlayerPanel() {
		// création des éléments de playerPanel
		playerPicture = new GraphArea();
		playerPicture.setMinimumSize(new Dimension(200, 300));
		playerName = new TextArea("", 20, 300, 25);
		playerLife = new TextArea("", 20, 300, 25);
		playerAttack = new TextArea("", 20, 300, 25);
		playerWeaponAttack = new TextArea("", 20, 300, 25);
		playerTotalAttack = new TextArea("", 20, 300, 25);

		// création des éléments de playerWeaponPicture
		playerWeaponPicture = new GraphArea();
	}

	/**
	 * Crée la zone d'affichage du déroulement de l'histoire.
	 */
	private void createStoryPanel() {
		// création des éléments de storyPanel
		storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
		storyMove = new TextArea("", 20, 300, 25);
		storyMove.setMaximumSize(new Dimension(500, 100));
		storyEvent = new TextArea("", 20, 300, 25);
		storyEvent.setMaximumSize(new Dimension(500, 100));
		storyDetail = new TextArea("", 20, 300, 25);
		storyDetail.setMaximumSize(new Dimension(500, 300));
	}

	/**
	 * Crée la zone d'affichage du contenu de la case.
	 */
	private void createBoxPanel() {
		// création des éléments de boxPanel
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		boxPicture = new GraphArea();
		boxName = new TextArea("", 20, 300, 50);
		boxLife = new TextArea("", 20, 300, 25);
		boxAttack = new TextArea("", 20, 300, 25);
		boxClass = new TextArea("", 20, 300, 25);
	}

	/**
	 * Crée le bouton du dé.
	 */
	private void createDiceButton() {
		// création des éléments de buttonsPanel
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		try {
			URL myImage = ClassLoader.getSystemResource("images/dice.png");
			Image resizedImage = ImageIO.read(myImage).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			rollDice = new JButton(new ImageIcon(resizedImage));
			rollDice.setMargin(new Insets(0, 0, 0, 0));
			rollDice.setContentAreaFilled(false);
			rollDice.setBorderPainted(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Assemble le tout.
	 */
	private void assembleAllPanels() {
		// assemblage du playerPanel
		playerPanel.add(playerPicture);
		playerPanel.add(playerName);
		playerPanel.add(playerLife);
		playerPanel.add(playerAttack);
		playerPanel.add(playerWeaponAttack);
		playerPanel.add(playerTotalAttack);
		playerWeaponPanel.add(playerWeaponPicture);
		playerFullPanel.add(playerPanel);
		playerFullPanel.add(playerWeaponPanel);
		storyPanel.add(storyMove);
		storyPanel.add(storyEvent);
		storyPanel.add(storyDetail);
		boxPanel.add(boxPicture);
		boxPanel.add(boxName);
		boxPanel.add(boxLife);
		boxPanel.add(boxAttack);
		boxPanel.add(boxClass);
		buttonsPanel.add(rollDice);
		gamePanel.add(boardPanel, BorderLayout.PAGE_START);
		gamePanel.add(playerFullPanel, BorderLayout.LINE_START);
		gamePanel.add(storyPanel, BorderLayout.CENTER);
		gamePanel.add(boxPanel, BorderLayout.LINE_END);
		gamePanel.add(buttonsPanel, BorderLayout.PAGE_END);
		titlePanel.add(titlePicture);
		bottomPanel.add(bottomPicture);
		fullPanel.add(titlePanel, BorderLayout.PAGE_START);
		fullPanel.add(leftDecoPanel, BorderLayout.LINE_START);
		fullPanel.add(rightDecoPanel, BorderLayout.LINE_END);
		fullPanel.add(bottomPanel, BorderLayout.PAGE_END);
		fullPanel.add(gamePanel, BorderLayout.CENTER);
	}

	

	// afficher les infos du jeu au lancement de la partie
	/**
	 * Affiche ou non le gamePanel.
	 * 
	 * @param b : booléen
	 */
	public void showGamePanel(boolean b) {
		gamePanel.setVisible(b);
	}

	/**
	 * Gère l'affichage récapitulatif de l'état du joueur et de son équipement.
	 * 
	 * @param player : le joueur à afficher.
	 */
	@Override
	public void showPlayer(Player player) {
		playerPicture.setImg(player.getImg(), 250, 200);
		playerName.setText(player.getName());
		playerLife.setText("Vie : " + String.valueOf(player.getLife()));
		playerAttack.setText("Attaque de base : " + String.valueOf(player.getAttack()));
		playerWeaponAttack.setText("Attaque de l'arme : " + String.valueOf(player.getFirstAttack().getAttack()));
		playerTotalAttack.setText(
				"Attaque totale : " + String.valueOf(player.getAttack() + player.getFirstAttack().getAttack()));
		playerWeaponPicture.setImg(player.getFirstAttack().getImg(), 100, 100);
	}

	/**
	 * Gère l'affichage de la progression du joueur.
	 * 
	 * @param dice : le résultat du lancer de dé.
	 * @param playerPosition : la position du joueur sur le plateau de jeu.
	 */
	@Override
	public void showMove(int dice, int playerPosition) {
		storyMove.setText("<html><p style=\"text-align: center\">Vous avancez de " + dice
				+ " case(s) et arrivez en case " + playerPosition + ".</p></html>");
	}

	/**
	 * Gère l'affichage de l'évènement sur la case.
	 * 
	 * @param s : le message à afficher.
	 */
	@Override
	public void showEvent(String s) {
		if (s == "Case vide, on continue...") {
			resetShowBox();
		}
		storyEvent.setText("<html><p style=\"text-align: center\">" + s + "</p></html>");

	}

	/**
	 * Gère l'affichage des détails de l'évènement (combat, s'équiper, boire une potion,...).
	 * 
	 * @param s : le message à afficher.
	 */
	@Override
	public void showDetail(String s) {
		storyDetail.setText("<html><p style=\"text-align: center\">" + s + "</p></html>");
	}

	/**
	 * Gère l'affichage des détails de l'évènement sur plusieurs lignes (combat, s'équiper, boire une potion,...).
	 * 
	 * 
	 * @param s : le message à afficher.
	 * @see MyGame#addDetail(String)
	 */
	@Override
	public void addDetail(String s) {
		storyDetail.setText(addText(s));
	}

	/**
	 * Gère l'affichage graphique du contenu de la case.
	 * 
	 * @param box : la case à afficher.
	 * @see MyGame#showBox(Box)
	 */
	@Override
	public void showBox(Box box) {
		boxName.setText("<html><p style=\"text-align: center\">" + box.getName() + "</p></html>");
		if (box instanceof Potion) {
			boxPicture.setImg(box.getImg(), 200, 200);
			boxLife.setText("Vie : " + String.valueOf(box.getLife()));
			boxAttack.setText("");
			boxClass.setText("");
		} else if (box instanceof MeanOfAttack) {
			boxPicture.setImg(box.getImg(), 200, 200);
			boxLife.setText("");
			boxAttack.setText("Attaque : " + String.valueOf(box.getAttack()));
			boxClass.setText("");
		} else if (box instanceof Monster) {
			boxPicture.setImg(box.getImg(), 200, 200);
			boxLife.setText("Vie : " + String.valueOf(box.getLife()));
			boxAttack.setText("Attaque : " + String.valueOf(box.getAttack()));
			boxClass.setText("");
		}
	}

	/**
	 * Gère l'attente d'appui sur la touche entrée pour lancer le dé.
	 * <p>Inutile en mode graphique, implémentée pour compatibilité avec l'interface Viewer.</p>
	 * 
	 * @return booléen : false lorsque l'utilisateur a appuyé sur la touche entrée seule.
	 */
	@Override
	public boolean waitDice() {
		// rien pour le mode graphique
		return false;
	}

	
	/**
	 * Ajoute une nouvelle ligne au texte qui sera affiché dans showDetail.
	 * 
	 * @param s : le texte à ajouter.
	 * @return la nouvelle chaine de caractères (ancien texte + nouveau texte).
	 */
	private String addText(String s) {
		String detail = storyDetail.getText();
		if (detail == "") {
			detail = "</html>";
		}
		detail = detail.substring(0, detail.length() - 11);
		detail = detail + "<br>" + s + "</p></html>";
		return detail;
	}

	
	/**
	 * Efface le contenu de showBox, pour le case d'une case vide.
	 */
	public void resetShowBox() {
		boxPicture.removeImg();
		boxName.setText("");
		boxLife.setText(String.valueOf(""));
		boxAttack.setText(String.valueOf(""));
		boxClass.setText(String.valueOf(""));
	}

}
