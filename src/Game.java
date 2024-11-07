//TODO DONE

import java.util.ArrayList;

/**
 * A singleton class that handles starting and stopping the game.
 */
public class Game {
    private static Game instance = null; /**Instance of the current game*/
    private Map map; /**The field of the game*/
    private ArrayList<String[]> commandList; /**List of the commands*/
    private Scientist current = null;/**The current player of the game*/

    /**
     * Contructor which creates the map of the game
     */
    public Game() {
        map = new Map();
        commandList = new ArrayList<String[]>();
    }

    /**
     * Return the instance of the game
     */
    public static Game GetInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }

    /**
     * Reset game parameters
     */
    public void Reset() {
        map = new Map();
        commandList.clear();
    }

    /**
     * Start of the game
     */
    public Scientist StartGame() {
        current = map.GetScientists().get(0);
        current.Step();
        return current;
    }

    /**
     * Return the map of the game
     * @return map of the game
     */
    public Map GetMap() {
        return map;
    }

    /**
     * Return the list of commands
     * @return list of commands
     */
    public ArrayList<String[]> GetCommandList() {
        return commandList;
    }
}