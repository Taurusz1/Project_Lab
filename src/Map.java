import java.util.ArrayList;

/**
 * A teljes pályát reprezentáló singleton osztály, mely Tile-okból tevődik össze.
 */
public class Map implements Steppable {
    private ArrayList<Scientist> scientists; /**virológusok listája, akik játszanak*/
    private ArrayList<Tile> tiles; /**mezők, amikből felépül a játékpálya*/

    /**
     * Map inicializálása.
     */
    public Map() {
        scientists = new ArrayList<>();
        tiles = new ArrayList<>();
    }

    /**
     * A pályán található dolgok léptethető dolgokat lépteti.
     */
    public void Step() {
        for (int i = 0; i < scientists.size(); i++)
            scientists.get(i).Step();
    }

    /**
     * Scientistek listájának lekérdezése
     * @return scientists lista
     */
    public ArrayList<Scientist> GetScientists() {
        return scientists;
    }

    /**
     * Mezők listájának lekérdezése
     * @return mezők lista
     */
    public ArrayList<Tile> GetTiles() {
        return tiles;
    }
}