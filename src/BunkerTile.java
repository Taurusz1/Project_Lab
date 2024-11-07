//TODO DONE

/**
 * The class of bunker tile. This type of tile has a gear.
 */
public class BunkerTile extends Tile {
    private Gear gear; /**The gear of the tile*/

    /**
     * Constructor
     * @param ID the tile's ID
     */
    public BunkerTile(String ID) {
        super(ID);
    }

    @Override
    /**
     * The scientist gets a gear from the tile
     * @param s scientist who gets the gear
     */
    public void Interact(Scientist s) {
        Gear copyGear = gear.Copy();
        copyGear.Add(s);
    }

    @Override
    /**
     * Add gear to the tile
     * @param g gear which we add to the tile
     */
    public void AddGear(Gear g) {
        gear = g;
    }

    @Override
    /**
     * Return with the tile's type
     * @return tile's type
     */
    public String ToString() {
        return "bunker";
    }

    @Override
    /**
     * Return the stuff of the tile.
     * @return the name of the stuff
     */
    public String GetStuff() {
        if (gear == null)
            return "none";
        else
            return gear.ToString();
    }
}