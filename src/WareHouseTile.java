//TODO DONE

/**
 * The class of warehouse tile
 */
public class WareHouseTile extends Tile {
    private MaterialBottle materialBottle;/**The material of the tile*/

    /**
     * Constructor
     * @param ID the tile's ID
     */
    public WareHouseTile(String ID) {
        super(ID);
    }

    @Override
    /**
     * The s scientist gets 10 materials from the tile's materials
     * @param s scientist who gets a material
     */
    public void Interact(Scientist s) {
        if (materialBottle != null) {
            for (int i = 0; i < 10; ++i) {
                MaterialBottle copy = materialBottle.Copy();
                copy.AddMaterial(s);
            }
        }
    }

    @Override
    /**
     * Destroy all the materials in the warehouse
     */
    public void DestroyMaterial() {
        this.materialBottle = null;
    }

    @Override
    /**
     * Return with the type of the tile
     * @return type of tile
     */
    public String ToString() {
        return "warehouse";
    }

    @Override
    /**
     * Return the stuff of the tile.
     * @return the name of the stuff
     */
    public String GetStuff() {
        if (materialBottle == null) {
            return "none";
        } else {
            return materialBottle.ToString();
        }
    }

    @Override
    /**
     * Set infinite material source on the tile
     */
    public void SetMaterialOnTile() {
        this.materialBottle = new MaterialBottle();
    }
}