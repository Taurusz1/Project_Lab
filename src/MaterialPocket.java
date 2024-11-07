import java.util.ArrayList;

/**
 * A virológus egy “zsebe”, ebben tárolódnak az anyagok, amelyek a virológusnál vannak, de nem zsákban.
 */
public class MaterialPocket {
    private ArrayList<MaterialBottle> materialBottles; /**A zsebben lévő anyagok listája*/
    private Scientist owner;/**A zseb tulajdonosa*/
    private int pocketSize = 50; /**A zseb maximális mérete*/

    /**
     * Constructor
     */
    public MaterialPocket() {
        materialBottles = new ArrayList<MaterialBottle>();
    }

    /**
     * A megszerzett anyagot hozzáadja a virológus inventoryjához.
     * @param mb the anyag, melyet hozzáadunk a virológushoz
     */
    public void AddMaterial(MaterialBottle mb) {
        if (!CheckMaterialPocket()) {
            for (int k = 0; k < owner.GetGear().size(); k++) {
                if (owner.GetGear().get(k).ToString().equals("bag")) {
                    Bag tmp = (Bag) owner.GetGear().get(k);
                    if (tmp.GetMaterials().size() < 30) {
                        tmp.AddMaterial(mb);
                    }
                }
            }
        }
        else
            this.materialBottles.add(mb);
    }

    /**
     * Megnézi, fér-e még anyag a virológus zsebébe.
     * @return the igaz, ha fér
     */
    public boolean CheckMaterialPocket() {
        if (materialBottles.size() < 50)
            return true;
        return false;
    }

    /**
     * Zsebet birtokló virológus beállítása.
     * @param s the s
     */
    public void SetOwner(Scientist s) {
        owner = s;
    }

    /**
     * Visszaadja a zsebben lévő anyagok listáját
     * @return anygaok listája
     */
    public ArrayList<MaterialBottle> GetMaterials() {
        return materialBottles;
    }

    /**
     * visszaadja a zseb méretét.
     * @return zseb mérete
     */
    public int getSize() {
        return pocketSize;
    }
}