import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class MainGraphics implements Viewer {

	Player Player;
	MyUtils u;
	MyGame game;
	static boolean waitBtnClic;

	public MainGraphics() {
		u = new MyUtils();
		game = new MyGame();
		waitBtnClic = true;
	}

	/**
	 * @return the waitBtnClic
	 */
	public boolean isWaitBtnClic() {
		return waitBtnClic;
	}

	/**
	 * @param waitBtnClic the waitBtnClic to set
	 */
	public void setWaitBtnClic(boolean waitBtnClic) {
		this.waitBtnClic = waitBtnClic;
	}

	public static void main(String[] args) throws IOException {

		// déclarations et instanciations
		// la fenêtre principale
		JFrame myWindow = new JFrame("DungeonsAndDragons");

		// l'objet MyGame qui crée tout l'interface graphique du jeu
		MyGame game = new MyGame();

		// le menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Action");
		JMenu menuItem1 = new JMenu("Nouveau Personnage");
		JMenuItem menuItem2 = new JMenuItem("Lancer le jeu");
		JMenuItem menuItem3 = new JMenuItem("Quitter");
		JMenuItem warriorItem = new JMenuItem("Guerrier");
		JMenuItem MagicianItem = new JMenuItem("Magicien");

		// paramétrage
		myWindow.setSize(1200, 900);
		myWindow.setResizable(false);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setLocationRelativeTo(null);

		// TODO décommenter ligne ci-dessous à la fin des tests
		// menuItem2.setEnabled(false);

		// listeners
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.showGamePanel(true);
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(myWindow, "Quitter le jeu ?", "Quitter",
						JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					System.exit(0);
				}
			}
		});

		warriorItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Guerrier ?",
						"Nouveau Guerrier", JOptionPane.QUESTION_MESSAGE);
				System.out.println(name);
			}
		});

		MagicianItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Magicien ?",
						"Nouveau Magicien", JOptionPane.QUESTION_MESSAGE);
				System.out.println(name);
			}
		});

		game.rollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clic();
			}
		});

		// construction du menu
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuItem1.add(warriorItem);
		menuItem1.add(MagicianItem);
		menuBar.add(menu);

		// construction de la fenêtre principale
		myWindow.setJMenuBar(menuBar);
		myWindow.setContentPane(game.getAll());

		// affichage de la fenêtre
		myWindow.setVisible(true);

	}

	@Override
	public void showMove(int dice, int playerPosition) {
		game.storyMove.setText("Vous avancez de " + dice + " case(s) et arrivez en case " + playerPosition + ".");
	}

	@Override
	public void showEvent(String s) {
		game.storyEvent.setText(s);

	}

	@Override
	public void showDetail(String s) {
		game.storyDetail.setText(s);

	}

	@Override
	public void showPlayer(Player player) {
		// TODO : afficher l'image
		game.playerName.setText(player.getName());
		game.playerLife.setText(String.valueOf(player.getLife()));
		game.playerAttack.setText(String.valueOf(player.getAttack()));
		game.playerTotalAttack.setText(String.valueOf(player.getAttack() + player.getFirstAttack().getAttack()));
	}

	@Override
	public void showBox(Box box) {
		// TODO afficher l'image
		game.boxName.setText(box.name);
		game.boxLife.setText(String.valueOf(box.life));
		game.boxAttack.setText(String.valueOf(box.attack));
		game.boxClass.setText(String.valueOf(box.life));

	}

	@Override
	public boolean waitDice() {
		// attendre le clic sur bouton...
		while (isWaitBtnClic()) {

		}
		return false;
	}

	public static void clic() {
		waitBtnClic = false;
	}

}
