/**
 * Az alapanyagot reprezentáló osztály.
 */
public class MaterialBottle {

    /**
     * A megszerzett anyagot hozzáadja a virológus inventory-jához.
     * @param s the virológus, akihez hozzáadjuk az anyagot.
     */
    public void AddMaterial(Scientist s) {
        s.GetMaterialPocket().AddMaterial(this);
    }

    /**
     * Másolatot készít az alapanyagról.
     * @return az elkészült másolat
     */
    public MaterialBottle Copy() {
        return new MaterialBottle();
    }

    /**
     * Visszaadja az alapanyag nevét
     * @return az alapanyag neve
     */
    public String ToString() {
        return "material";
    }
}