//TODO DONE

import java.util.ArrayList;

/**
 * The class of bag. If a scientist has a bag he can take more materials
 */
public class Bag extends Gear {
    private ArrayList<MaterialBottle> materialBottles; /**The materials of the bag*/

    /**
     * Constructor
     */
    public Bag() {
        super(0);
        materialBottles = new ArrayList<MaterialBottle>();
    }

    /**
     * It is not protect from any of agents - it's not doing anything
     * @param s the agent user
     * @param a the agent
     * @return false - it can not use successfully
     */
    public boolean Use(Scientist s, Agent a) {
        return false;
    }

    /**
     * Add materials to the bag
     * @param mb materials
     */
    public void AddMaterial(MaterialBottle mb) {
        materialBottles.add(mb);
        if (materialBottles.size() > 30)
            materialBottles.remove(materialBottles.size() - 1);
    }

    /**
     * Make a copy
     * @return the copy
     */
    public Gear Copy() {
        return new Bag();
    }

    /**
     * Visszaadja a táskában tárolt anyagokat.
     * @return anyagok listája, ami a táskában van
     */
    public ArrayList<MaterialBottle> GetMaterials() {
        return materialBottles;
    }

    /**
     * Return the name of the gear
     * @return name of the gear
     */
    public String ToString() {
        return "bag";
    }

    @Override
    /**
     * Return usable rounds
     * @return usage
     */
    public String AttributeToString() {
        return String.valueOf(materialBottles.size());
    }

    /**
     * Return materials of the bag
     * @return materials of the bag
     */
    public ArrayList<MaterialBottle> GetMaterialBottles() {
        return materialBottles;
    }
}