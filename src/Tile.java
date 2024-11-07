//TODO DONE

import java.util.ArrayList;

/**
 * The tiles' of the game. These contain gears, materials and genetic codes which the scientists can collect.
 */
public class Tile {
    private String tileID; /**The ID of the tile*/
    protected ArrayList<Scientist> scientists; /**The list of this scientist who stands on the tile*/
    private ArrayList<Tile> neighbors; /**The neighbor tiles of the current tile*/

    /**
     * Constuctor which creates empty lists for the scientists and neighbor tiles
     */
    public Tile(String ID){
        tileID = ID;
        scientists = new ArrayList<Scientist>();
        neighbors = new ArrayList<Tile>();
    }

    /**
     * Add the s scientist to the scientists list
     * @param s scientist who steps on the tile
     */
    public void Accept(Scientist s){
        scientists.add(s);
        s.SetTile(this);
        if(s.GetHasBear()) {
            this.DestroyMaterial();
            for(int j = 0; j<Game.GetInstance().GetMap().GetScientists().size(); j++) {
                if(s.GetScientistID() != Game.GetInstance().GetMap().GetScientists().get(j).GetScientistID() && Game.GetInstance().GetMap().GetScientists().get(j).GetTile().GetTileID().equals(this.tileID)) {
                    Game.GetInstance().GetMap().GetScientists().get(j).AddToAgentOnScientist(new BearDance());
                }
            }
        }
    }

    /**
     * Remove the s scientist to the scientists list
     * @param s scientist who leaves the tile
     */
    public void Remove(Scientist s){
        scientists.remove(s);
    }

    /**
     * Return with the serial number of the choisen neighbor tile
     * @param d the serial number of the neighbor tile
     * @return the neighbor tile
     */
    public Tile GetNeighbor(int d){
        return neighbors.get(d % neighbors.size());
    }

    /**
     * Set neighbor state for the tile
     * @param t the neighbor
     */
    public void SetNeighbor(Tile t){
        neighbors.add(t);
    }

    /**
     * Check possibility of applying the effect of an agent
     * @param s the checked scientist
     * @return possibility of applying the effect of an agent
     */
    public boolean CheckScientist(Scientist s) {
        return scientists.contains(s);
    }

    /**
     * Check possibility of stealing
     * @param s the checked scientist
     * @return possibility of stealing
     */
    public boolean CheckStunned(Scientist s) {
        return scientists.contains(s) && s.GetIsStunned();
    }

    /**
     * There is nothing happening on simple tiles, other tiles have different functions
     * @param s scientist who interacts
     */
    public void Interact(Scientist s) {}

    /**
     * Destroy all the materials in the warehouse, on other tiles are nothing happening
     */
    public void DestroyMaterial() {}

    /**
     * Return the list of neighbor tiles
     * @return neighbor tiles
     */
    public ArrayList<Tile> GetNeighbors() {
        return neighbors;
    }

    /**
     * Return the list of scientists
     * @return scientists on the tile
     */
    public ArrayList<Scientist> GetScientists() {
        return scientists;
    }

    /**
     * Return the tile's ID
     * @return tile ID
     */
    public String GetTileID() {
        return tileID;
    }

    /**
     * Return the tile's type
     * @return tile's type
     */
    public String ToString() {
        return "normal";
    }

    /**
     * Set an infinite material source on tile
     */
    public void SetMaterialOnTile() {}

    /**
     * Set the genetic code on the tile
     * @param gc genetic code of tile
     */
    public void SetGeneticCode(GeneticCode gc) {}

    /**
     * Return the stuff of the tile.
     * @return the name of the stuff
     */
    public String GetStuff(){
        return "none";
    }

    /**
     * Return all the scientists on the tile in a string separated by a comma
     * @return string of scientists
     */
    public String ScientistsToString(){
        if (scientists.size()<1)
            return "none";

        String scientistsString ="";
        for (int i = 0; i < scientists.size()-1; i++) {
            scientistsString += (scientists.get(i).GetScientistID()+",");
        }
        scientistsString += (scientists.get(scientists.size()-1).GetScientistID()+",");
        return scientistsString;
    }

    /**
     * Add gear to the tile
     * @param g gear which we add to the tile
     */
    public void AddGear(Gear g) {}
}