//TODO Done

import java.util.ArrayList;

/**
 * The Crafting Station enables the Scientist to make agents.
 * If the Scientist wants to craft an agents, he loads his materials into the CraftingStation, the Station consumes the materials needed,
 * gives back the extra materials and the crafted agent to the Scientist.
 */
public class ChemistryStation {
    private ArrayList<MaterialBottle> currentMaterials; /**Currently available materials.*/
    private Scientist owner; /**The Scientist, who owns the CraftingStation*/

    /**
     * Constructor
     * @param s The Scientist, who owns the CraftingStation.
     */
    public ChemistryStation(Scientist s) {
        currentMaterials = new ArrayList<>();
        owner = s;
    }

    /**
     * The Scientist loads his materials into the craftingStation.
     */
    public void LoadStation() {
        currentMaterials.addAll(owner.GetMaterialPocket().GetMaterials());
        owner.GetMaterialPocket().GetMaterials().clear();
        for (int i = 0; i < owner.GetGear().size(); i++) {
            if (owner.GetGear().get(i).ToString().equals("bag")) {
                Bag tmp = (Bag) owner.GetGear().get(i);
                currentMaterials.addAll(tmp.GetMaterials());
                tmp.GetMaterials().clear();
            }
        }
    }

    /**
     * The Scientist unloads his materials from the craftingStation.
     */
    public void UnloadStation() {
        if (GetCurrentMaterials() == 0) return;
        Bag tmp = null;
        for (int k = 0; k < owner.GetGear().size(); k++) {
            if (owner.GetGear().get(k).ToString().equals("bag")) {
                tmp = (Bag) owner.GetGear().get(k);
            }
        }
        for(int i = 0; i < currentMaterials.size(); i++){
            if (owner.GetMaterialPocket().CheckMaterialPocket()) {
                owner.GetMaterialPocket().GetMaterials().add(new MaterialBottle());
            }else{
                tmp.AddMaterial(new MaterialBottle());
            }
        }
        currentMaterials.clear();
    }

    /**
     * The station consumes the materials needed to craft the agent.
     * @param materialCost the amount of materials needed to be consumed
     */
    public void ConsumeMaterials(int materialCost) {
        for (int i = 0; i < materialCost; i++) {
            currentMaterials.remove(0);
        }
    }

    /**
     * Returns the owner of the station
     * @return owner of the Crafting Station
     */
    public Scientist GetOwner() {
        return owner;
    }

    /**
     * Currently available materials in the craftingStation.
     * @return amount of materials in the craftingStation.
     */
    public int GetCurrentMaterials() {
        return currentMaterials.size();
    }
}