package mcv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import helpers.GameState;
import helpers.MouseClickListener;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Pawn;
import mcv.model.Player;
import mcv.model.Square;
import mcv.model.Cards.Card;

public class View extends JFrame {
	/**
	 * This class displays the Graphics of the game.
	 * Basically it is a JFrame that gets updated by
	 * the current gamestate.
	 * 
	 * @author Kostas Anemozalis (CSD4149)
	 */
	private JLayeredPaneExtension basic_panel;
	private Square[] squares;
	private JButton recieveCard = new JButton();
	private JButton currentCard = new JButton();
	private JButton foldButton = new JButton();
	private JTextArea infoBox = new JTextArea();
	private JComponent sorryImg;
	private JComponent midSquare;
	private JComponent redStart;
	private JComponent yellowStart;
	private JComponent redHome;
	private JComponent yellowHome;
	private JLabel redStartLabel;
	private JLabel yellowStartLabel;
	private JLabel redHomeLabel;
	private JLabel yellowHomeLabel;
	private JLabel currentCardLabel;
	private JLabel recieveCardLabel;
	private ArrayList<JComponent> pawns = new ArrayList<JComponent>();
	/**
	 * Initialization of View 
	 */
	public View() {
		this.setResizable(false);
		this.setTitle("Sorry Game - CSD4149");
		this.setBounds(0, 0, 1024, 768+25);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setupMainView();
	}

	/**
	 * Setting up the main view of the game: The basic panel.
	 */
	public void setupMainView() {
		MouseClickListener ms = new MouseClickListener();
		URL imageURL = Controller.cldr.getResource("resources/images/background.png"); // image
		Image image = new ImageIcon(imageURL).getImage();
		basic_panel = new JLayeredPaneExtension(image);
		getContentPane().add(basic_panel);
		//Setting up JmenuBar
        JMenuBar mb = new JMenuBar(); 
        JMenu ng = new JMenu("New Game"); 
        ng.addMouseListener(ms);
        JMenu sg = new JMenu("Save Game"); 
        sg.addMouseListener(ms);
        JMenu lg = new JMenu("Continue Saved Game"); 
        lg.addMouseListener(ms);
        JMenu eg = new JMenu("Exit Game"); 
        eg.addMouseListener(ms);
        mb.add(ng);
        mb.add(sg);
        mb.add(lg);
        mb.add(eg);
        setJMenuBar(mb);
				
		// Setting up squares
		squares = new Square[79];
		for (int i = 1; i <= 16; i++)
			squares[i] = new Square(Controller.sqrSz * (i - 1), 0, false);
		for (int i = 17; i <= 31; i++)
			squares[i] = new Square(Controller.sqrSz * 15, Controller.sqrSz * (i - 16), false);
		for (int i = 32; i <= 46; i++)
			squares[i] = new Square(Controller.sqrSz * (15 - (i - 31)), Controller.sqrSz * 15, false);
		for (int i = 47; i <= 60; i++)
			squares[i] = new Square(0, Controller.sqrSz * (15 - (i - 46)), false);
		for (int i = 61; i <= 65; i++)
			squares[i] = new Square(Controller.sqrSz * 13, Controller.sqrSz * (15 - (i - 60)), false);
		for (int i = 66; i <= 70; i++)
			squares[i] = new Square(Controller.sqrSz * 2, Controller.sqrSz * (i - 65), false);
		squares[71] = new Square((int) (Controller.sqrSz * 3.5), (int) (Controller.sqrSz * 1.25), false); 
		squares[72] = new Square((int) (Controller.sqrSz * 4.5), (int) (Controller.sqrSz * 1.25), false); 
		squares[73] = new Square((int) (Controller.sqrSz * 10.5), (int) (Controller.sqrSz * 13.75), false);																								// start 1
		squares[74] = new Square((int) (Controller.sqrSz * 11.5), (int) (Controller.sqrSz * 13.75), false);
		squares[75] = new Square((int) (Controller.sqrSz * 1.5), (int) (Controller.sqrSz * 6.25), false);
		squares[76] = new Square((int) (Controller.sqrSz * 2.5), (int) (Controller.sqrSz * 6.25), false);
		squares[77] = new Square((int) (Controller.sqrSz * 12.5), (int) (Controller.sqrSz * 8.75), false);																						
		squares[78] = new Square((int) (Controller.sqrSz * 13.5), (int) (Controller.sqrSz * 8.75), false); 

		// adding ladders
		squares[2].setDefaultImage("resources/images/slides/redSlideStart.png");
		squares[10].setDefaultImage("resources/images/slides/redSlideStart.png");
		squares[17].setDefaultImage("resources/images/slides/blueSlideStart.png");
		squares[25].setDefaultImage("resources/images/slides/blueSlideStart.png");
		squares[32].setDefaultImage("resources/images/slides/yellowSlideStart.png");
		squares[40].setDefaultImage("resources/images/slides/yellowSlideStart.png");
		squares[47].setDefaultImage("resources/images/slides/greenSlideStart.png");
		squares[55].setDefaultImage("resources/images/slides/greenSlideStart.png");
		squares[5].setDefaultImage("resources/images/slides/redSlideEnd.png");
		squares[14].setDefaultImage("resources/images/slides/redSlideEnd.png");
		squares[20].setDefaultImage("resources/images/slides/blueSlideEnd.png");
		squares[29].setDefaultImage("resources/images/slides/blueSlideEnd.png");
		squares[35].setDefaultImage("resources/images/slides/yellowSlideEnd.png");
		squares[44].setDefaultImage("resources/images/slides/yellowSlideEnd.png");
		squares[50].setDefaultImage("resources/images/slides/greenSlideEnd.png");
		squares[59].setDefaultImage("resources/images/slides/greenSlideEnd.png");

		for (int i = 0; i < 3; i++) {
			if (i < 2) {
				squares[3 + i].setDefaultImage("resources/images/slides/redSlideMedium.png");
				squares[18 + i].setDefaultImage("resources/images/slides/blueSlideMedium.png");
				squares[33 + i].setDefaultImage("resources/images/slides/yellowSlideMedium.png");
				squares[48 + i].setDefaultImage("resources/images/slides/greenSlideMedium.png");
			}
			squares[11 + i].setDefaultImage("resources/images/slides/redSlideMedium.png");
			squares[26 + i].setDefaultImage("resources/images/slides/blueSlideMedium.png");
			squares[41 + i].setDefaultImage("resources/images/slides/yellowSlideMedium.png");
			squares[56 + i].setDefaultImage("resources/images/slides/greenSlideMedium.png");
		}
		
		//Creating the blue rectangle and sorry image
		midSquare = Util.getRect(Controller.sqrSz, Controller.sqrSz, 14 * Controller.sqrSz,14 * Controller.sqrSz, Color.cyan);
		sorryImg = Util.getImg("resources/images/sorryImage.png", 417 / 2, 121 / 2, false);
		sorryImg.setBounds(Controller.sqrSz * 6, (int) (Controller.sqrSz * 7.5), 417 / 2, 121 / 2);
		
		//Creating home and start graphics and labels
		redStart = Util.getRect((int) (Controller.sqrSz * 3.5), Controller.sqrSz,(int) (Controller.sqrSz * 2), Controller.sqrSz * 2, Color.white, Color.red, 5);
		yellowStart = Util.getRect((int) (Controller.sqrSz * 10.5), Controller.sqrSz * (13),(int) (Controller.sqrSz * 2), Controller.sqrSz * 2, Color.white, Color.yellow, 5);
		redHome = Util.getRect((int) (Controller.sqrSz * 1.5), Controller.sqrSz * 6,(int) (Controller.sqrSz * 2), Controller.sqrSz * 2, Color.white, Color.red, 5);
		yellowHome = Util.getRect((int) (Controller.sqrSz * 12.5), Controller.sqrSz * (8),(int) (Controller.sqrSz * 2), Controller.sqrSz * 2, Color.white, Color.yellow, 5);
		redHomeLabel = new JLabel("HOME");
		redHomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
		redHomeLabel.setBounds((int) (Controller.sqrSz * 1.8), (int) (Controller.sqrSz * 6.5), 100, 100);
		yellowHomeLabel = new JLabel("HOME");
		yellowHomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
		yellowHomeLabel.setBounds((int) (Controller.sqrSz * 12.8), (int) (Controller.sqrSz * 7.25), 100, 100);
		redStartLabel = new JLabel("START");
		redStartLabel.setFont(new Font("Serif", Font.BOLD, 20));
		redStartLabel.setBounds((int) (Controller.sqrSz * 3.8), (int) (Controller.sqrSz * 1.5), 100, 100);
		yellowStartLabel = new JLabel("START");
		yellowStartLabel.setFont(new Font("Serif", Font.BOLD, 20));
		yellowStartLabel.setBounds((int) (Controller.sqrSz * 10.8), (int) (Controller.sqrSz * 12.25), 100, 100);
		
		//Creating card labels
		currentCardLabel = new JLabel("Current Card");
		currentCardLabel.setFont(new Font("Serif", Font.BOLD, 15));
		currentCardLabel.setBounds((int) (Controller.sqrSz * 20.15), (int) (Controller.sqrSz * 5), 100, 100);
		recieveCardLabel = new JLabel("Recieve Card");
		recieveCardLabel.setFont(new Font("Serif", Font.BOLD, 15));
		recieveCardLabel.setBounds((int) (Controller.sqrSz * 16.76), (int) (Controller.sqrSz * 5), 100, 100);
		
		//Setting up the recieve card
		Image backCard = new ImageIcon(Controller.cldr.getResource("resources/images/cards/backCard.png")).getImage();
		backCard = backCard.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
		recieveCard.setIcon(new ImageIcon(backCard));
		recieveCard.setBounds(750, 100, 100, 150);
		recieveCard.addMouseListener(ms);
		
		//Setting up the current card.
		setCurrentCard(null);
		currentCard.setBounds(900, 100, 100, 150);
		
		//Setting up the fold button
		foldButton.setBounds(750, 300, 250, 75);
		foldButton.setText("Fold");
		foldButton.setFont(new Font("Serif", Font.BOLD, 20));
		foldButton.setBackground(Color.RED);
		foldButton.addMouseListener(ms);
		
		//Setting up the info box
		infoBox.setBounds(750, 450, 250, 200);
		infoBox.setEditable(false);
		infoBox.setFont(new Font("Serif", Font.BOLD, 15));
		
		//Adding the components
		basic_panel.add(infoBox,0);
		basic_panel.add(recieveCard,0);
		basic_panel.add(currentCard,0);
		basic_panel.add(currentCardLabel,0);
		basic_panel.add(recieveCardLabel,0);
		basic_panel.add(foldButton,0);
	    basic_panel.add(midSquare,4);
		basic_panel.add(sorryImg,3);
		basic_panel.add(redStart,2);
		basic_panel.add(yellowStart,2);
		basic_panel.add(redHome,2);
		basic_panel.add(yellowHome,2);
		basic_panel.add(redStartLabel,1);
		basic_panel.add(yellowStartLabel,1);
		basic_panel.add(redHomeLabel,1);
		basic_panel.add(yellowHomeLabel,1); 
	}


	/**
	 * Moves a pawn at a position
	 * 
	 * @param p The pawn to move
	 * @param pos The position to move the pawn to
	 */
	public void setPawn(Pawn p, int pos) {
		squares[pos].setImage(p.getImage());
	}
	
	/**
	 * Paints all the squares.
	 */
	public void paintSquares() {
		for (int i = 0; i < 79; i++) {
			if (squares[i] == null) continue;
			JComponent ex = squares[i].toImage();
			basic_panel.add(ex,0,0);
			pawns.add(ex);
		}
	}
	
	/**
	 * Clears all the squares.
	 */
	public void clearSquares() {
		for(int i = 0; i<79; i++) {
			if(squares[i] == null) continue;
			squares[i].setImage(null);
		} 
		for(JComponent c : pawns) basic_panel.remove(c);
		pawns.clear();
	}

	/**
	 * Sets the the current card to the parameter.
	 * 
	 * @param c The card to set.
	 */
	public void setCurrentCard(Card c) {
		if (c == null) {
			currentCard.setIcon(null);
			currentCard.setOpaque(false);
			currentCard.setContentAreaFilled(false);
			currentCard.setBorderPainted(false);
			return;
		}
		currentCard.setOpaque(true);
		currentCard.setContentAreaFilled(true);
		currentCard.setBorderPainted(true);
		URL imageURL = Controller.cldr.getResource(c.getImage());
		Image image2 = new ImageIcon(imageURL).getImage();
		image2 = image2.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
		currentCard.setIcon(new ImageIcon(image2));
	}

	/**
	 * Changes the current info box to the given String array
	 * 
	 * @param infobox The String array that should go into the infoBox
	 */
	public void setInfoBox(String[] infobox) {
		infoBox.setText("");
		for(int i = 0; i<infobox.length; i++) infoBox.append(infobox[i]+"\n");
	}

	/**
	 * Repaints the whole board due to the given GameState
	 * 
	 * @param state The given GameState
	 */
	public void repaint(GameState state) {
		clearSquares();
		Player p1 = state.getPlayer1();
		Player p2 = state.getPlayer2();
		setPawn(p1.getPawn1(), p1.getPawn1().getPosition());
		setPawn(p1.getPawn2(), p1.getPawn2().getPosition());
		setPawn(p2.getPawn1(), p2.getPawn1().getPosition());
		setPawn(p2.getPawn2(), p2.getPawn2().getPosition());
		setCurrentCard(state.getCurrentCard());
		setInfoBox(state.getInfoBox());
		paintSquares();
	}
}
