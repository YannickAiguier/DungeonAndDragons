package viewers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

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

public class MyGame implements Viewer {

	JPanel all, gamePanel, boardPanel, playerFullPanel, playerPanel, playerWeaponPanel, storyPanel, boxPanel,
			buttonsPanel;
	GraphArea titlePicture, bottomPicture, playerWeaponPicture, boxPicture, playerPicture;
	TextArea playerName, playerLife, playerAttack, playerTotalAttack, playerWeaponAttack;
	TextArea storyEvent, storyDetail, storyMove;
	TextArea boxName, boxLife, boxAttack, boxClass;
	JButton rollDice;
	String detail;

	public MyGame() {
		detail = "";

		// création du JPanel parent
		all = new JPanel(new BorderLayout());
		all.setBackground(Color.black);

		// création des 5 JPanels enfants
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(1200, 100));
		titlePanel.setBackground(Color.black);

		JPanel leftDecoPanel = new JPanel();
		leftDecoPanel.setPreferredSize(new Dimension(50, 600));
		leftDecoPanel.setBackground(Color.black);

		JPanel rightDecoPanel = new JPanel();
		rightDecoPanel.setPreferredSize(new Dimension(50, 600));
		rightDecoPanel.setBackground(Color.black);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1200, 100));
		bottomPanel.setBackground(Color.black);

		gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBackground(Color.black);
		gamePanel.setVisible(false);
		
		// création des décors
		titlePicture = new GraphArea();
		titlePicture.setMinimumSize(new Dimension(1000, 100));
		titlePicture.setImg("title.png", 1000, 100);
		bottomPicture = new GraphArea();
		bottomPicture.setMinimumSize(new Dimension(1200, 100));
		bottomPicture.setImg("bottom_border.png", 1200, 100);

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

		// création des JPanel enfants de playerFullPanel
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.setBackground(Color.black);
		playerPanel.setPreferredSize(new Dimension(300, 500));
		playerWeaponPanel = new JPanel();
		playerWeaponPanel.setLayout(new BoxLayout(playerWeaponPanel, BoxLayout.Y_AXIS));
		playerWeaponPanel.setBackground(Color.black);
		playerWeaponPanel.setPreferredSize(new Dimension(100, 500));

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

		// création des éléments de storyPanel
		storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
		storyMove = new TextArea("", 20, 300, 25);
		storyMove.setMaximumSize(new Dimension(500, 100));
		storyEvent = new TextArea("", 20, 300, 25);
		storyEvent.setMaximumSize(new Dimension(500, 100));
		storyDetail = new TextArea("", 20, 300, 25);
		storyDetail.setMaximumSize(new Dimension(500, 300));

		// création des éléments de boxPanel
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		boxPicture = new GraphArea();
		boxName = new TextArea("", 20, 300, 50);
		boxLife = new TextArea("", 20, 300, 25);
		boxAttack = new TextArea("", 20, 300, 25);
		boxClass = new TextArea("", 20, 300, 25);

		// création des éléments de buttonsPanel
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		try {
			File file = new File("./resources/images/dice.png");
			Image resizedImage = ImageIO.read(file).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			rollDice = new JButton(new ImageIcon(resizedImage));
			rollDice.setMargin(new Insets(0, 0, 0, 0));
			rollDice.setContentAreaFilled(false);
			rollDice.setBorderPainted(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// assemblage !
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
		all.add(titlePanel, BorderLayout.PAGE_START);
		all.add(leftDecoPanel, BorderLayout.LINE_START);
		all.add(rightDecoPanel, BorderLayout.LINE_END);
		all.add(bottomPanel, BorderLayout.PAGE_END);
		all.add(gamePanel, BorderLayout.CENTER);
	}
	
	

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



	// récupérer le JPanel parent
	public JPanel getAll() {
		return all;
	}

	// afficher les infos du jeu au lancement de la partie
	public void showGamePanel(boolean b) {
		gamePanel.setVisible(b);
	}

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

	@Override
	public void showMove(int dice, int playerPosition) {
		storyMove.setText("<html><p style=\"text-align: center\">Vous avancez de " + dice
				+ " case(s) et arrivez en case " + playerPosition + ".</p></html>");
	}

	@Override
	public void showEvent(String s) {
		if (s == "Case vide, on continue...") {
			resetShowBox();
		}
		storyEvent.setText("<html><p style=\"text-align: center\">" + s + "</p></html>");

	}

	@Override
	public void showDetail(String s) {
		storyDetail.setText("<html><p style=\"text-align: center\">" + s + "</p></html>");
	}

	@Override
	public void addDetail(String s) {
		storyDetail.setText(addText(s));
	}

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

	@Override
	public boolean waitDice() {
		// rien pour le mode graphique
		return false;
	}

	private String addText(String s) {
		detail = storyDetail.getText();
		if (detail == "") {
			detail = "</html>";
		}
		detail = detail.substring(0, detail.length() - 11);
		detail = detail + "<br>" + s + "</p></html>";
		return detail;
	}

	public void resetShowBox() {
		boxPicture.removeImg();
		boxName.setText("");
		boxLife.setText(String.valueOf(""));
		boxAttack.setText(String.valueOf(""));
		boxClass.setText(String.valueOf(""));
	}

}
