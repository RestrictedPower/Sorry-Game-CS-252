package helpers;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mcv.controller.Controller;
import mcv.model.Color;
import mcv.model.Pawn;
import mcv.model.Player;
import mcv.view.JLayeredPaneExtension;
/**
 * This class contains helper functions
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Util {
	/**
	 * Get the possition a pawn should have after making a Forward move.
	 * 
	 * @param p The pawn that we check the move on.
	 * @param move The amount of steps the pawn should move.
	 * @param canStart Can the pawn start by using this move?
	 * 
	 * @return -1 if the move is invalid,0 if the move sends the pawn to home, else the position it could move on.
	 */
	public static int getForwardPossition(Pawn p, int move, boolean canStart) {
		int c = p.getPosition();
		if (c == 71 || c == 72) return canStart ? 5 : -1;
		if (c == 73 || c == 74) return canStart ? 35 : -1;
		if (p.getColor() == Color.RED) {
			if (c >= 66) {
				int l = c + move;
				if (l == 71)
					return 0;
				if (l > 71)
					return -1;
				return l;
			}
			if (c >= 4 && c + move <= 60)
				return move + c;
			if (c + move > 60) {
				int l = c + move - 60;
				if (l <= 3)
					return l;
				if (l == 9)
					return 0;
				if (l < 9)
					return 66 + l - 4;
				return -1;
			}
			int l = c + move;
			if (l <= 3)
				return 3;
			if (l == 9)
				return 0;
			if (l < 9)
				return 66 + l - 4;
			return -1;
		} else {
			if (c >= 61) {
				int l = c + move;
				if (l == 66)
					return 0;
				if (l > 66)
					return -1;
				return l;
			}
			if (c >= 34)
				return c + move == 60 ? 60 : (c + move) % 60;
			if (c + move <= 33)
				return c + move;
			int l = c + move - 33;
			if (l <= 5)
				return 61 + l - 1;
			if (l == 6)
				return 0;
			return -1;
		}
	}

	/**
	 * Get the possition a pawn should have after making a Backward move.
	 * 
	 * @param p The pawn that we check the move on.
	 * @param move The amount of steps the pawn should move.
	 * 
	 * @return -1 if the move is invalid, else the position it could move on.
	 */
	public static int getBackwardPossition(Pawn p, int move) {
		int c = p.getPosition();
		for(int i = 71; i<=78; i++) if(c==i) return -1;
		if (c > 60) {
			if (p.getColor() == Color.RED) {
				if (c - move >= 66) return c - move;
				move -= c-65;
				c = 3;
			} else {
				if (c - move >= 61) return c-move;
				move -= c-60;
				c = 33;
			}
		}
		if (c - move == 0)return 60;
		return (c - move + 60) % 60;
	}

	/**
	 * Used to check if a position on the board has a players pawn.
	 * 
	 * @param p The player we want to check
	 * @param position The position we want to check
	 * 
	 * @return null if there is no pawn of that player on this position, the pawn otherwise.
	 */
	public static Pawn getSameColorPawn(Player p, int position) {
		Player pl = Controller.state.getPlayer1().getColor()==p.getColor()?Controller.state.getPlayer1():Controller.state.getPlayer2();
		if(pl.getPawn1().getPosition()==position) return pl.getPawn1();
		if(pl.getPawn2().getPosition()==position) return pl.getPawn2();
		return null;
	}
	
	/**
	 * Cleans a position by sending the pawn on it back to start.
	 * 
	 * @param position The position we want to free.
	 */
	public static void cleanSpot(int position) {
		Player p1 = Controller.state.getPlayer1();
		Player p2 = Controller.state.getPlayer2();
		if (p1.getPawn1().getPosition() == position) MoveManager.moveToStart(p1, p1.getPawn1());
		if (p1.getPawn2().getPosition() == position) MoveManager.moveToStart(p1, p1.getPawn2());
		if (p2.getPawn1().getPosition() == position) MoveManager.moveToStart(p2, p2.getPawn1());
		if (p2.getPawn2().getPosition() == position) MoveManager.moveToStart(p2, p2.getPawn2());
	}
	
	/**
	 * Check if a player has won and if yes return them.
	 * 
	 * @return null if no one has won yet or the Player who has won otherwise.
	 */
	public static Player won() {
		{
			Player pl = Controller.state.getPlayer1();
			Pawn p1 = pl.getPawn1();
			Pawn p2 = pl.getPawn2();
			if (pl.getColor() == Color.RED) {
				if (p1.getPosition() == 75 && p2.getPosition() == 76)
					return pl;
				if (p1.getPosition() == 76 && p2.getPosition() == 75)
					return pl;
			} else {
				if (p1.getPosition() == 77 && p2.getPosition() == 78)
					return pl;
				if (p1.getPosition() == 78 && p2.getPosition() == 77)
					return pl;
			}
		}
		{
			Player pl = Controller.state.getPlayer2();
			Pawn p1 = pl.getPawn1();
			Pawn p2 = pl.getPawn2();
			if (pl.getColor() == Color.RED) {
				if (p1.getPosition() == 75 && p2.getPosition() == 76)
					return pl;
				if (p1.getPosition() == 76 && p2.getPosition() == 75)
					return pl;
			} else {
				if (p1.getPosition() == 77 && p2.getPosition() == 78)
					return pl;
				if (p1.getPosition() == 78 && p2.getPosition() == 77)
					return pl;
			}
		}
		return null;
	}
	
	/**
	 * Finds the enemy of the player
	 * 
	 * @param p The player to get the enemy of
	 * 
	 * @return The enemy player
	 */
	public static Player getEnemy(Player p) {
		if(Controller.state.getPlayer1().getName().equalsIgnoreCase(p.getName())) return Controller.state.getPlayer2();
		return Controller.state.getPlayer1();
	}
	
	/**
	 * Gets a text from a user by using inputDialog.
	 * 
	 * @param msg The question the user will be asked.
	 * 
	 * @return The text user has given.
	 */
	public static String getText(String msg) {
		String s = JOptionPane.showInputDialog(null,msg);      
		if(s == null||s.equals("")) return getText(msg);
		return s;
	}
	
	/**
	 * Cleans a position by sending the pawn on it back to start
	 * 
	 * @param msg The question the user will be asked.
	 * @param possibilities All the options the user can choose.
	 * 
	 * @return The one of the possibilities the user chose.
	 */
	public static String getChoice(String msg, String[] possibilities) {
		String v = (String) JOptionPane.showInputDialog(null, msg, "Choose an option:", JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
		while ((v == null) || (v.length() == 0))
			v = (String) JOptionPane.showInputDialog(null, msg, "An option is required!:", JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
		return v;
	}
	
	/**
	 * Returns the size of the ladder if there is a ladder at the position we check
	 * 
	 * @param position The position we want to check
	 * 
	 * @return The Size of the ladder
	 */
	public static int getLadderSize(int position) {
		if(position == 2) return 3;
		if(position == 17) return 3;
		if(position == 32) return 3;
		if(position == 47) return 3;
		if(position == 10) return 4;
		if(position == 25) return 4;
		if(position == 40) return 4;
		if(position == 55) return 4;
		return -1;
	}
	
	/**
	 * Returns the Color of the ladder if there is a ladder at the position we check
	 * 
	 * @param position The position we want to check
	 * 
	 * @return The Color of the ladder
	 */
	public static Color getLadderColor(int position) {
		if(position == 2) return Color.RED;
		if(position == 17) return Color.BLUE;
		if(position == 32) return Color.YELLOW;
		if(position == 47) return Color.GREEN;
		if(position == 10) return Color.RED;
		if(position == 25) return Color.BLUE;
		if(position == 40) return Color.YELLOW;
		if(position == 55) return Color.GREEN;
		return null;
	}
	
	/**
	 * Returns a random integer between min and max values.
	 * 
	 * @param min The min value.
	 * @param max The max value.
	 * 
	 * @return The randomly generated value.
	 */
	public static int getRandom(int min, int max) {
		return new Random().nextInt(max + 1 - min) + min;
	}
	
	/**
	 * Returns JComponent rectangle.
	 * 
	 * @param x The x value that the rectangle should have.
	 * @param y The y value that the rectangle should have.
	 * @param w The width that the rectangle should have.
	 * @param h The height that the rectangle should have.
	 * @param c The color that the rectangle should have.
	 * 
	 * @return The JComponent rectangle.
	 */
	public static JComponent getRect(int x, int y, int w, int h, java.awt.Color c) {
        JPanel s = new JPanel(); 
        s.setBounds(x,y,w,h);
        s.setBackground(c);
        s.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        return s;
	}
	
	/**
	 * Returns JComponent rectangle with a custom border Size and border Color.
	 * 
	 * @param x The x value that the rectangle should have.
	 * @param y The y value that the rectangle should have.
	 * @param w The width that the rectangle should have.
	 * @param h The height that the rectangle should have.
	 * @param c The color that the rectangle should have.
	 * @param borderColor The border color that the rectangle should have.
	 * @param bordersize The border size that the rectange should have.
	 * 
	 * @return The JComponent rectangle.
	 */
	public static JComponent getRect(int x, int y, int w, int h, java.awt.Color c, java.awt.Color borderColor, int bordersize) {
        JPanel s = new JPanel(); 
        s.setBounds(x,y,w,h);
        s.setBackground(c);
        s.setBorder(BorderFactory.createLineBorder(borderColor, bordersize));
        return s;
	}
	
	/**
	 * Returns a an image JComponent
	 * 
	 * @param url The URL of the image.
	 * @param scaleX The X value that the rectangle should be scaled to.
	  * @param scaleY The Y value that the rectangle should be scaled to.
	 * @param border Add border to the image
	 * 
	 * @return The JComponent image.
	 */
	public static JComponent getImg(String url,int scaleX,int scaleY, boolean border) {
        URL URL = Controller.cldr.getResource(url);
        Image img = new ImageIcon(URL).getImage().getScaledInstance(scaleX , scaleY, 0);
        JLayeredPaneExtension e = new JLayeredPaneExtension(img);
        if(border)e.setBorder(BorderFactory.createLineBorder(java.awt.Color.black, 2));
        e.setSize(new Dimension(scaleX,scaleY));
        return e;
	}
}
