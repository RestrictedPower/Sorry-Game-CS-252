package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mcv.controller.Controller;
/**
 * This class is used to save and load gamestates.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class GameSaver {
	/**
	 * Saves the current gamestate as a save.sorry file
	 * 
	 * @return boolean True on success false on fail.
	 */
	public static boolean saveGame() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("save.sorry");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(Controller.state);
			objectOutputStream.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads a gamestate from a save.sorry file
	 * 
	 * @return GameState The loaded gamestate, null if it doesn't exist.
	 */
	public static GameState recoverGame() {
		try {
			FileInputStream fileInputStream = new FileInputStream("save.sorry");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			GameState savedGame = (GameState) objectInputStream.readObject();
			objectInputStream.close();
			return savedGame;
		} catch (Exception e) {
			return null;
		}
	}
}
