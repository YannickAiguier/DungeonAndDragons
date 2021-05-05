import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyGame {
	
	JPanel all, gamePanel, boardPanel, playerFullPanel, playerPanel, playerWeaponPanel, storyPanel, boxPanel, buttonsPanel;
	// TODO ligne suivante à changer en GraphArea lors de la mise en place des images
	TextArea playerWeaponPicture, boxPicture;
	GraphArea playerPicture;
	TextArea playerName, playerLife, playerAttack, playerTotalAttack, playerWeaponAttack;
	TextArea storyEvent, storyDetail, storyMove;
	TextArea boxName, boxLife, boxAttack, boxClass;
	JButton rollDice;
	
	public MyGame() {
		// création du JPanel parent
		all = new JPanel(new BorderLayout());
		all.setBackground(Color.black);
		
		// création des 5 JPanels enfants
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(1200, 100));
		titlePanel.setBackground(Color.cyan);
		
		JPanel leftDecoPanel = new JPanel();
		leftDecoPanel.setPreferredSize(new Dimension(100, 600));
		leftDecoPanel.setBackground(Color.pink);
		
		JPanel rightDecoPanel = new JPanel();
		rightDecoPanel.setPreferredSize(new Dimension(100, 600));
		rightDecoPanel.setBackground(Color.pink);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1200, 100));
		bottomPanel.setBackground(Color.cyan);
		
		gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBackground(Color.green);
		gamePanel.setVisible(false);
		
		// création des JPanels enfants de gamePanel
		boardPanel = new JPanel();
		boardPanel.setBackground(Color.blue);
		boardPanel.setPreferredSize(new Dimension(1000, 150));
		playerFullPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		playerFullPanel.setBackground(Color.orange);
		playerFullPanel.setPreferredSize(new Dimension(400, 500));
		storyPanel = new JPanel();
		storyPanel.setBackground(Color.gray);
		storyPanel.setPreferredSize(new Dimension(400, 500));
		boxPanel = new JPanel();
		boxPanel.setBackground(Color.orange);
		boxPanel.setPreferredSize(new Dimension(200, 500));
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.black);
		buttonsPanel.setPreferredSize(new Dimension(1000, 50));
		
		// création des JPanel enfants de playerFullPanel
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.setBackground(Color.red);
		playerPanel.setPreferredSize(new Dimension(300, 500));
		playerWeaponPanel = new JPanel();
		playerWeaponPanel.setLayout(new BoxLayout(playerWeaponPanel, BoxLayout.Y_AXIS));
		playerWeaponPanel.setBackground(Color.green);
		playerWeaponPanel.setPreferredSize(new Dimension(100, 500));
		
		// création des éléments de playerPanel
		playerPicture = new GraphArea();
		playerPicture.setImg("orc.png", 200, 300);
		playerPicture.setMinimumSize(new Dimension(200, 300));
		playerName = new TextArea("Player Name", 20, 300, 25);
		playerLife = new TextArea("Player Life", 20, 300, 25);
		playerAttack = new TextArea("Player Attack", 20, 300, 25);
		playerTotalAttack = new TextArea("Player Total Attack", 20, 300, 25);
		
		// création des éléments de playerWeaponPicture
		playerWeaponPicture = new TextArea("Player Weapon Picture", 20, 300, 25);
		playerWeaponAttack = new TextArea("Player Weapon Attack", 20, 300, 25);
		
		// création des éléments de storyPanel
		//Border border = BorderFactory.createLineBorder(Color.blue, 1);
		storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
		storyEvent = new TextArea("Story Event", 20, 300, 25);
		//storyEvent.setHorizontalAlignment(SwingConstants.CENTER);
		storyEvent.setMaximumSize(new Dimension(400, 100));
		//storyEvent.setBorder(border);
		storyDetail = new TextArea("Story Detail", 20, 300, 25);
		//storyDetail.setHorizontalAlignment(SwingConstants.CENTER);
		storyDetail.setMaximumSize(new Dimension(400, 300));
		storyMove = new TextArea("Story Move", 20, 300, 25);
		//storyMove.setHorizontalAlignment(SwingConstants.CENTER);
		storyMove.setMaximumSize(new Dimension(400, 100));
		
		// création des éléments de boxPanel
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		boxPicture = new TextArea("Box Picture", 20, 300, 25);
		boxName = new TextArea("Box Name", 20, 300, 25);
		boxLife = new TextArea("Box Life", 20, 300, 25);
		boxAttack = new TextArea("Box Attack", 20, 300, 25);
		boxClass = new TextArea("Box Class", 20, 300, 25);
		
		// création des éléments de buttonsPanel
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		try {
			File file = new File("/home/yannick/campus/DungeonsAndDragons/resources/images/dice.png");
			Image resizedImage = ImageIO.read(file).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			rollDice = new JButton(new ImageIcon(resizedImage));
			rollDice.setMargin(new Insets(0, 0, 0, 0));
			rollDice.setContentAreaFilled(false);
			rollDice.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		
		// assemblage !
		playerPanel.add(playerPicture);
		playerPanel.add(playerName);
		playerPanel.add(playerLife);
		playerPanel.add(playerAttack);
		playerPanel.add(playerTotalAttack);
		playerWeaponPanel.add(playerWeaponPicture);
		playerWeaponPanel.add(playerWeaponAttack);
		playerFullPanel.add(playerPanel);
		playerFullPanel.add(playerWeaponPanel);
		storyPanel.add(storyEvent);
		storyPanel.add(storyDetail);
		storyPanel.add(storyMove);
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
		all.add(titlePanel, BorderLayout.PAGE_START);
		all.add(leftDecoPanel, BorderLayout.LINE_START);
		all.add(rightDecoPanel, BorderLayout.LINE_END);
		all.add(bottomPanel, BorderLayout.PAGE_END);
		all.add(gamePanel, BorderLayout.CENTER);
	}
	
	// récupérer le JPanel parent
	public JPanel getAll() {
		return all;
	}
	
	// afficher les infos du jeu au lancement de la partie
	public void showGamePanel(boolean b) {
		gamePanel.setVisible(b);
	}
	
	// 

}
