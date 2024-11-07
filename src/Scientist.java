import java.util.ArrayList;

/**
 * Ezen osztály reprezentálja a játékosok által irányított entitást, a virológust.
 */
public class Scientist implements Steppable {
    private String scientistID; /**virológus azonosítója*/
    private Tile tile; /**mező, ahol a virológus áll*/
    private ArrayList<Gear> gear; /**Size 3*/
    private ArrayList<GeneticCode> geneticCodes; /**genetikai kódok, amik a virológusnál vannak*/
    private ArrayList<Agent> agentOnScientist; /**a virológusra ható ágensek “zsebe”*/
    private ArrayList<Agent> agentPocket; /** a virológus rendelkezésére álló ágensek “zsebe”*/
    private MaterialPocket materialPocket; /** a virológus “zsebe”, itt kerül tárolásra minden alapanyag, ami a virológusnál van, de nem zsákban*/
    private ChemistryStation chemistryStation; /**a chemistry station, melyben felhasználáskor összegyűjthetők az alapanyagok*/
    private boolean steppedThisRound; /**lépett-e az adott körben a virológus*/

    /**
     * Virológus  konstruktora.
     */
    public Scientist(String ID){
        scientistID = ID;
        gear = new ArrayList<>();
        geneticCodes = new ArrayList<>();
        agentOnScientist = new ArrayList<>();
        agentPocket = new ArrayList<>();
        materialPocket = new MaterialPocket();
        materialPocket.SetOwner(this);
        chemistryStation = new ChemistryStation(this);
    }

    /**
     * A virológus köre, minden interakciója, ebben a függvényben fut le.
     */
    public void Step() {
        steppedThisRound = false;
        boolean agentBlocked = false;
        for (int i = 1; i < 5 && !agentBlocked; i++){
            for (int j = 0; j < agentOnScientist.size(); j++) {
                if (!agentBlocked && i == agentOnScientist.get(j).Prior()){
                    agentBlocked = agentOnScientist.get(j).Influence(this);
                }
            }
        }
    }

    /**
     * A virológus a d paraméter által meghatározott irányba lép.
     * @param d the irány, azaz a mező adott szomszédja
     */
    public void Move(int d) {
        if(!steppedThisRound) {
            Tile tile2 = tile.GetNeighbor(d);
            tile.Remove(this);
            tile2.Accept(this);
            steppedThisRound = true;
        }
    }

    /**
     * A virológus előállítja a paraméterként kapott kódhoz megfelelő ágenst, amennyiben rendelkezik a megfelelő anyagokkal.
     * @param g the genetikai kód, amiből elő akarjuk állítani az ágenst.
     */
    public void Craft(GeneticCode g){
        if(GetIsStunned() && !GetHasImmune())
            return;

        chemistryStation.LoadStation();
        g.Craft(chemistryStation);
        chemistryStation.UnloadStation();
    }

    /**
     * A virológus az a paraméterrel jelzett ágenst alkalmazni próbálja az s2-vel jelzett virológusra. Az s1 paraméterben önmagát is átadja.
     * @param s1 the virológus, aki keni az ágenst
     * @param s2 the virológus, akire kenik az ágenst
     * @param a  the ágens, ami kenődik
     */
    public void UseAgent(Scientist s1, Scientist s2, Agent a){
        if(GetIsStunned() && !GetHasImmune()){
            return;
        }
        else if(s1 == s2){
            s1.GetAgentPocket().remove(a);
            a.AddEffect(s2);
        }
        else if(tile.CheckScientist(s2)) {
            s1.GetAgentPocket().remove(a);
            s2.UseItem(s1, a);
        }
    }

    /**
     * Ellopja az s paraméterrel jelölt, azonos mezőn található virológus felszerelését.
     * @param s the virológus, akitől lopni akarunk
     */
    public void StealItem(Scientist s) {
        if(this.GetTile().CheckStunned(s)){
            for(int i = 0; i < s.GetGear().size(); i++)
            {
                if(s.gear.get(i) != null && this.gear.size() < 3) {
                    Gear stolen = s.gear.get(i);
                    stolen.Remove(s);
                    stolen.Add(this);
                }
            }
        }
    }

    /**
     * Felhasználja a rendelkezésére álló felszereléseket az s Scientist ellen, aki az a ágenst próbálta rákenni.
     * @param s the virológus, aki ellen fel akarjuk használni a védőfelszerelést.
     * @param a the ágens, ami kenődött
     */
    public void UseItem(Scientist s, Agent a){
        if(GetIsStunned() && !GetHasImmune()){
            return;
        }
        if(gear.size()==0)
            a.AddEffect(this);
        else {
            boolean gearBlocked = false;
            for (int i = 1; i < 3 && !gearBlocked; i++){
                for (int j = 0; j < gear.size(); j++){
                    Gear g  = gear.get(j);
                    if (!gearBlocked && i == g.Prior()){
                        gearBlocked = g.Use(s,a);
                    }
                }
            }
            if(!gearBlocked)
                a.AddEffect(this);
        }
    }

    /**
     * Virológus megöli a paraméterben megadott virológust.
     */
    public void UseAxe(Scientist s){
        if(GetIsStunned() && !GetHasImmune()){
            return;
        }
        else if(tile == s.tile){
            for (int i = 0; i < this.GetGear().size(); i++) {
                if(this.GetGear().get(i).ToString().equals("axe") && this.GetGear().get(i).AttributeToString().equals("1")){
                    this.GetGear().get(i).Use(s, null);
                    return;
                }
            }
        }
    }

    /**
     * A virológus körének vége.
     */
    public void EndTurn(){
        for (Agent a : GetAgentOnScientist()) {
            a.DecreaseEffectLength(this);
        }
    }

    /**
     * A virológus akciót kezdeményez a mezővel amin áll, ami a típusától függően reagál rá.
     */
    public void Interact(){
        if(GetIsStunned() && !GetHasImmune()){
            return;
        }
        tile.Interact(this);
    }

    /**
     * vége a játéknak, ha a virológus már az összes genetikai kódot megtanulta.
     */
    public void CheckWinCondition() {
        if(geneticCodes.size() == 4){
            Controller.GetInstance().Win(this);
        }
    }

    /**
     * Annak beállítása, hogy a virológus éppen hol áll.
     * @param t the mező, ahol van a virológus.
     */
    public void SetTile(Tile t) {
        tile = t;
    }

    /**
     * A virológus zsebének lekérdezése.
     * @return the virológus zsebe
     */
    public MaterialPocket GetMaterialPocket(){
        return materialPocket;
    }

    /**
     * A virológus táskájának/táskájának lekérdezése.
     * @return the virológus zsebe
     */
    public int GetMaterialAmountInBags(){
        int amount = 0;
        for (int i = 0; i < gear.size(); i++) {
            if (gear.get(i).ToString() == "bag")
                amount += ((Bag)gear.get(i)).GetMaterialBottles().size();
        }
        return amount;
    }

    /**
     * felszerelés hozzáadása a virológushoz.
     * @param b the felszerelés, melyet hozzáadunk a virológushoz
     */
    public void AddGear(Gear b) {
        gear.add(b);
        b.SetOwner(this);
    }

    /**
     * Köpeny használata egy ágensen
     * @param a ágens, amire használjuk a köpenyt
     */
    public void UseCloak(Agent a){
        boolean cloakFound = false;
        for (Gear g : gear) {
            if (2 == g.Prior() && !cloakFound){
                cloakFound = true;
                g.Use(g.GetOwner(), a);
            }
        }
        if(!cloakFound) {
            this.AddToAgentOnScientist(a);
        }
    }

    /**
     * Bénítás beállítása
     */
    public boolean GetHasBear() {
        for(Agent a : GetAgentOnScientist())
            if(a.ToString().equals("bear"))
                return true;
        return false;
    }

    /**
     * Bénítás beállítása
     */
    public boolean GetIsStunned() {
        for(Agent a : GetAgentOnScientist())
            if(a.ToString().equals("stun"))
                return true;
        return false;
    }

    /**
     * Beállítja hasImmune értékét
     */
    public boolean GetHasImmune() {
        for(Agent a : GetAgentOnScientist())
            if(a.ToString().equals("immune"))
                return true;
        return false;
    }

    /**
     * Visszaadja a virológuson lévő ágenseket
     * @return a virológuson lévő ágensek
     */
    public ArrayList<Agent> GetAgentOnScientist() {
        return agentOnScientist;
    }

    /**
     * Annak a mezőnek a hozzáadása, ahol a virológus áll
     * @return a mező, ahol a virológus áll
     */
    public Tile GetTile() {
        return tile;
    }

    /**
     * Visszaadja a virológus azonosítóját
     * @return virológus azonosítója
     */
    public String GetScientistID() {
        return scientistID;
    }

    /**
     * visszaadja a virológus által tárolt ágensek listáját.
     * @return ágens lista
     */
    public ArrayList<Agent> GetAgentPocket() {
        return agentPocket;
    }

    /**
     * A virológus által birtokolt védőfelszerelések lekérdezése.
     * @return the array list
     */
    public ArrayList<Gear> GetGear() {
        return gear;
    }

    /**
     * A virológus által birtokolt genetikai kódokat adja vissza.
     * @return the genetikai kódok listája.
     */
    public ArrayList<GeneticCode> GetGeneticCodes(){
        return geneticCodes;
    }

    /**
     * Agent hozzáadása a virológushoz.
     * @param a the a
     */
    public void AddAgentToPocket(Agent a) {
        agentPocket.add(a);
    }

    /**
     * Ható ágens hozzáadása a virológushoz.
     * @param a the ágens, melynek hatását érvényesíteni szeretnénk a virológuson.
     */
    public void AddToAgentOnScientist(Agent a) {
        agentOnScientist.add(a);
    }
}