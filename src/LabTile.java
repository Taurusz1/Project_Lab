//TODO DONE

/**
 * The class of laboratory tile
 */
public class LabTile extends Tile {
    private GeneticCode geneticCode; /**The genetic code of the tile*/

    /**
     * Constructor
     * @param ID the tile's ID
     */
    public LabTile(String ID) {
        super(ID);
    }

    @Override
    /**
     * The scientist learns the tile's genetic code
     * @param s scientist who learn the genetic code
     */
    public void Interact(Scientist s) {
        if (geneticCode != null) {
            GeneticCode copyGeneticCode = geneticCode.Copy();
            copyGeneticCode.Learn(s);
            s.CheckWinCondition();
        }
    }

    /**
     * Set the genetic code of the tile
     * @param g genetic code of the tile
     */
    public void SetGeneticCode(GeneticCode g) {
        geneticCode = g;
    }

    @Override
    /**
     * Return the tile's type
     * @return tile's type
     */
    public String ToString() {
        return "lab";
    }

    @Override
    /**
     * Return the stuff of the tile.
     * @return the name of the stuff
     */
    public String GetStuff() {
        if (geneticCode == null) {
            return "none";
        } else {
            return geneticCode.ToString();
        }
    }
}