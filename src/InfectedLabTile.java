//TODO DONE

/**
 * The class of laboratory tile which infected with beardance virus
 */
public class InfectedLabTile extends LabTile {
    private BearDance bearDance; /**The beardance of the tile*/

    /**
     * Constructor
     * @param ID the tile's ID
     */
    public InfectedLabTile(String ID) {
        super(ID);
        bearDance = new BearDance();
    }

    @Override
    /**
     * Add the s scientist to the scientists list
     * @param s scientist who steps on the tile
     */
    public void Accept(Scientist s) {
        scientists.add(s);
        s.SetTile(this);
        BearDance copyBearDance = bearDance.Copy();
        s.UseCloak(copyBearDance);
    }

    @Override
    /**
     * Return the tile's type
     * @return tile's type
     */
    public String ToString() {
        return "infectedlab";
    }
}