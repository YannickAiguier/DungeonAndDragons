import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class MainGraphics {

	public static void main(String[] args) throws IOException {

		// déclarations et instanciations
		JFrame myWindow = new JFrame("DaD");
		JPanel myPanel = new JPanel();
		JLabel myLabel = new JLabel("Texte à afficher");
		JTextField myTextField = new JTextField("");
		JButton myButton = new JButton("Cliquez");
		
		JPanel newPanel = createMyGame();
		
		// test image
		Image myResizedImage;
		File myFile;
		JLabel showImg;
		try {
			myFile = new File("/home/yannick/campus/DungeonsAndDragons/resources/images/titi.gif");
			//myResizedImage = ImageIO.read(myFile).getScaledInstance(200, 320, Image.SCALE_SMOOTH);
			myResizedImage = ImageIO.read(myFile);
			showImg = new JLabel(new ImageIcon(myResizedImage));
			myPanel.add(showImg);
			// mettre à jour l'image dans le JLabel
//			myFile = new File("/home/yannick/campus/DungeonsAndDragons/resources/images/elf.png");
//			myResizedImage = ImageIO.read(myFile);
//			showImg.setIcon(new ImageIcon(myResizedImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		/////

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
		
		myPanel.setBackground(Color.black);
		myLabel.setForeground(Color.white);
		myLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		myTextField.setColumns(20);

		// TODO décommenter ligne ci-dessous à la fin des tests
		//menuItem2.setEnabled(false);

		// listeners
		myButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clic clic !!");
			}
		});

		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok Lancer le jeu");
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok quitter le jeu");
				int choose = JOptionPane.showConfirmDialog(myWindow, "Quitter le jeu ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					System.exit(0);
				}
			}
		});
		
		warriorItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok créer guerrier");
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Guerrier ?", "Nouveau Guerrier", JOptionPane.QUESTION_MESSAGE);
				System.out.println(name);
			}
		});
		
		MagicianItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok créer magicien");
				String name = (String) JOptionPane.showInputDialog(myWindow, "Quel nom pour votre Magicien ?", "Nouveau Magicien", JOptionPane.QUESTION_MESSAGE);
				System.out.println(name);
			}
		});

		// construction
		myPanel.add(myLabel);
		myPanel.add(myTextField);
		myPanel.add(myButton);

		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuItem1.add(warriorItem);
		menuItem1.add(MagicianItem);
		menuBar.add(menu);

		myWindow.setJMenuBar(menuBar);
		myWindow.setContentPane(newPanel);

		myWindow.setVisible(true);
		
	}
	
	// méthode de construction du panel général
	private static JPanel createMyGame() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.black);
		
		// 5 panels dedans
		JPanel titlePanel = new JPanel();
		JPanel leftDecoPanel = new JPanel();
		JPanel rightDecoPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel gamePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(1200, 150));
		titlePanel.setBackground(Color.cyan);
		panel.add(titlePanel, BorderLayout.PAGE_START);
		
		
		return panel;
	}

}
