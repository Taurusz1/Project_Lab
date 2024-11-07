/**
 * A különböző ágenseket leíró genetikai kódot leíró absztrakt osztály.
 * Megtalálhatóak laboratóriumok falára felkarcolva, amiket a virológusok megtanulhatnak, majd ezek ismeretében előállíthatnak ágenseket.
 */
public abstract class GeneticCode {
    protected int materialCost; /**Az adott genetikai kódból megtanulható ágens elkészítésének költsége*/

    /**
     * A paraméterként megkapott virológusnak odaad egy kódot(receptet).
     * @param s a virológus, akinek odaadjuk a kódot
     */
    public abstract void Learn(Scientist s);

    /**
     * Amennyiben elegendő anyaggal rendelkezik a virológus, elkészíti a megfelelő ágenst a kód segítségével.
     * @param c a ChemistryStation, melyben a Scientist rendelkezésére álló alapanyagok összegyűjtésre kerültek
     */
    public abstract void Craft(ChemistryStation c);

    /**
     * Hozzáadja az elkészült ágenst a virológushuz.
     * @param s a virológus, akihez hozzáadjuk az elkészült ágenst
     */
    public abstract void AddCraftedAgent(Scientist s);

    /**
     * Másolatot készít a genetikai kódról.
     * @return az elkészült másolat
     */
    public abstract GeneticCode Copy();

    /**
     * Visszaadja a genetikai kód nevét
     * @return genetikai kód
     */
    public abstract String ToString();

    /**
     * visszaadja a materialCost értékét.
     * @return materialCost
     */
    public int GetMaterialCost() {
        return materialCost;
    }
}