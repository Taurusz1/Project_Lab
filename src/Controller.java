import javax.swing.*;
import java.io.*;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * A megjelenítést és a működést egyesítő osztály.
 */
public class Controller {
    private static Controller instance = null; /** a controller példánya **/
    private static Scientist currentScientist; /** az aktuális játékos **/
    private static Scientist target = null; /** az aktuális játékos által megtámadni kívánt játékos **/

    /**
     * Controller példány elkészítése
     * @return Controller példánya
     */
    public static Controller GetInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * A nyertes kiiratása.
     * @param s a játékot nyerő
     */
    public void Win(Scientist s){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, s.GetScientistID()+" YOUR WINNER");
        Init();
    }
    /**
     * A képernyő frissítése
     */
    public void Update() {
        POV.getInstance().DrawCurrent(currentScientist, target);
    }

    /**
     * Játékosszám bekérése
     */
    public void Init() {
        boolean wrongInput = true;
        while (wrongInput){
            try {
                JFrame jFrame = new JFrame();
                Game.GetInstance().Reset();
                LoadMap();
                String getMessage = JOptionPane.showInputDialog(jFrame, "How Many Players Are There?");
                int sNum = parseInt(getMessage);
                for(int i = 0; i < sNum; i++) {
                    Random rand = new Random();
                    int tileNum = rand.nextInt(Game.GetInstance().GetMap().GetTiles().size());
                    String command = "AddScientist Player" + (i+1) + " " + Game.GetInstance().GetMap().GetTiles().get(tileNum).GetTileID();
                    Interpreter(command.split(" "));
                }
                currentScientist = Game.GetInstance().StartGame();
                target = currentScientist;
                Update();
                wrongInput = false;
            }
            catch(Exception e) {}
        }
    }

    /***
     * A játéktér beolvasása
     */
    public static void LoadMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader("map.txt"))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] commands = line.split(" ");
                Interpreter(commands);
            }
        }
        catch (IOException e) {
            System.out.println("File not found...");
        }
    }

    /**
     * kitalált parancsnyelv parancsainak megvalósítása
     * @param commandArgs kiadott parancs
     */
    public static void Interpreter(String[] commandArgs) {
        Map map = Game.GetInstance().GetMap();

        Tile tile1 = null;
        Tile tile2 = null;
        Scientist scientist1 = currentScientist;
        Scientist scientist2 = target;
        boolean tileExists = false;
        boolean tileIsBunker = false;
        boolean tileIsLab = false;
        Agent agent1 = null;
        if (!(commandArgs[0].startsWith("List") || commandArgs[0].equals("ScientistInfo") || commandArgs[0].equals("LoadGame") || commandArgs[0].equals("SaveGame"))) {
            Game.GetInstance().GetCommandList().add(commandArgs);
        }

        for(int i=0;i<commandArgs.length;i++) {
            System.out.print(commandArgs[i] + " ");
        }
        System.out.println();
        switch (commandArgs[0]) {
            //Inicializáló parancsok:
            case "AddTile":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                switch (commandArgs[2]) {
                    case "lab":
                        map.GetTiles().add(new LabTile(commandArgs[1]));
                        break;
                    case "infectedlab":
                        map.GetTiles().add(new InfectedLabTile(commandArgs[1]));
                        break;
                    case "warehouse":
                        map.GetTiles().add(new WareHouseTile(commandArgs[1]));
                        break;
                    case "bunker":
                        map.GetTiles().add(new BunkerTile(commandArgs[1]));
                        break;
                    case "normal":
                        map.GetTiles().add(new Tile(commandArgs[1]));
                        break;
                    default:
                        System.out.println(commandArgs[2] + " type field doesn't exist");
                }
                break;

            case "SetNeighbours":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                for (int i = 0; i < map.GetTiles().size(); i++) {
                    if (map.GetTiles().get(i).GetTileID().equals(commandArgs[1]))
                        tile1 = map.GetTiles().get(i);
                    else if (map.GetTiles().get(i).GetTileID().equals(commandArgs[2]))
                        tile2 = map.GetTiles().get(i);
                }
                if (tile1 == null)
                    System.out.println(commandArgs[1] + " Tile ID doesn't exists");
                else if (tile2 == null)
                    System.out.println(commandArgs[2] + " Tile ID doesn't exists");
                else {
                    tile1.SetNeighbor(tile2);
                    tile2.SetNeighbor(tile1);
                }
                break;

            case "AddScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                for (int i = 0; i < map.GetTiles().size(); i++) {
                    if (map.GetTiles().get(i).GetTileID().equals(commandArgs[2]))
                        tile1 = map.GetTiles().get(i);
                }
                if (tile1 == null) {
                    System.out.println(commandArgs[2] + " Tile ID doesn't exists");
                    break;
                }
                Scientist newScientist = new Scientist(commandArgs[1]);
                map.GetScientists().add(newScientist);
                newScientist.SetTile(tile1);
                tile1.GetScientists().add(newScientist);
                if(newScientist.GetTile().ToString().equals("infectedlab")) {
                    newScientist.AddToAgentOnScientist(new BearDance());
                }
                break;

            case "AddGearToScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                switch (commandArgs[1]) {
                    case "bag":
                        scientist1.AddGear(new Bag());
                        break;
                    case "cloak":
                        scientist1.AddGear(new Cloak());
                        break;
                    case "gloves":
                        scientist1.AddGear(new Gloves());
                        break;
                    case "axe":
                        scientist1.AddGear(new Axe());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type gear doesn't exist");
                }
                break;

            case "AddGeneticCodeToScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                switch (commandArgs[1]) {
                    case "forget":
                        scientist1.GetGeneticCodes().add(new ForgetCode());
                        break;
                    case "immune":
                        scientist1.GetGeneticCodes().add(new ImmuneCode());
                        break;
                    case "stun":
                        scientist1.GetGeneticCodes().add(new StunCode());
                        break;
                    case "vitus":
                        scientist1.GetGeneticCodes().add(new VitusCode());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type genetic code doesn't exist");
                }
                break;

            case "AddAgentToScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                switch (commandArgs[1]) {
                    case "forget":
                        scientist1.AddAgentToPocket(new Forget());
                        break;
                    case "immune":
                        scientist1.AddAgentToPocket(new Immune());
                        break;
                    case "stun":
                        scientist1.AddAgentToPocket(new Stun());
                        break;
                    case "vitus":
                        scientist1.AddAgentToPocket(new VitusDance());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type agent doesn't exist");
                }
                break;

            case "AddActiveAgentToScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                switch (commandArgs[1]) {
                    case "bear":
                        scientist1.AddToAgentOnScientist(new BearDance());
                        break;
                    case "forget":
                        scientist1.AddToAgentOnScientist(new Forget());
                        if (scientist1.GetGeneticCodes().size() > 0) //TODO EZ ÍGY BIZTOS?
                            scientist1.GetGeneticCodes().remove(0);
                        break;
                    case "immune":
                        scientist1.AddToAgentOnScientist(new Immune());
                        break;
                    case "stun":
                        scientist1.AddToAgentOnScientist(new Stun());
                        break;
                    case "vitus":
                        scientist1.AddToAgentOnScientist(new VitusDance());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type agent doesn't exist");
                }
                break;

            case "AddMaterialToScientist":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                if (!IsNumeric(commandArgs[1])) {
                    System.out.println(commandArgs[1] + " is not a valid quantity");
                    break;
                }
                for (int i = 0; i < Integer.parseInt(commandArgs[1]); i++) {
                    scientist1.GetMaterialPocket().GetMaterials().add(new MaterialBottle());
                }
                break;

            case "AddGearToBunker":
                tileExists = false;
                tileIsBunker = false;
                for (int i = 0; i < Game.GetInstance().GetMap().GetTiles().size(); i++) {
                    if (commandArgs[2].equals(Game.GetInstance().GetMap().GetTiles().get(i).GetTileID())) {
                        tileExists = true;
                        if (Game.GetInstance().GetMap().GetTiles().get(i).ToString().equals("bunker")) {
                            tile1 = Game.GetInstance().GetMap().GetTiles().get(i);
                            tileIsBunker = true;
                        } else {
                            System.out.println(commandArgs[2] + " is not a bunker tile");
                            break;
                        }
                    }
                }
                if (!tileIsBunker) {
                    break;
                }
                if (!tileExists) {
                    System.out.println(commandArgs[2] + " is not a valid tile");
                    break;
                }

                switch (commandArgs[1]) {
                    case "axe":
                        tile1.AddGear(new Axe());
                        break;
                    case "bag":
                        tile1.AddGear(new Bag());
                        break;
                    case "cloak":
                        tile1.AddGear(new Cloak());
                        break;
                    case "gloves":
                        tile1.AddGear(new Gloves());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type gear doesn't exist");
                }
                break;

            case "AddGeneticCode":
                tileExists = false;
                tileIsLab = false;
                for (int i = 0; i < Game.GetInstance().GetMap().GetTiles().size(); i++) {
                    if (commandArgs[2].equals(Game.GetInstance().GetMap().GetTiles().get(i).GetTileID())) {
                        tileExists = true;

                        if (Game.GetInstance().GetMap().GetTiles().get(i).ToString().equals("lab") || Game.GetInstance().GetMap().GetTiles().get(i).ToString().equals("infectedlab")) {
                            tile1 = Game.GetInstance().GetMap().GetTiles().get(i);
                            tileIsLab = true;
                        }
                        else {
                            System.out.println(commandArgs[2] + " is not a bunker tile");
                            break;
                        }
                    }
                }
                if (!tileIsLab) {
                    break;
                }
                if (!tileExists) {
                    System.out.println(commandArgs[2] + " is not a valid tile");
                    break;
                }

                switch (commandArgs[1]) {
                    case "forget":
                        tile1.SetGeneticCode(new ForgetCode());
                        break;
                    case "immune":
                        tile1.SetGeneticCode(new ImmuneCode());
                        break;
                    case "stun":
                        tile1.SetGeneticCode(new StunCode());
                        break;
                    case "vitus":
                        tile1.SetGeneticCode(new VitusCode());
                        break;
                    default:
                        System.out.println(commandArgs[1] + " type gear doesn't exist");
                }
                break;

            case "AddMaterial":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                for (int i = 0; i < map.GetTiles().size(); i++) {
                    if (map.GetTiles().get(i).GetTileID().equals(commandArgs[1]))
                        tile1 = map.GetTiles().get(i);
                }
                if (tile1 == null) {
                    System.out.println(commandArgs[1] + " Tile ID doesn't exists");
                    break;
                }
                tile1.SetMaterialOnTile();
                break;

            //Cselekvést kiváltó parancsok:
            case "CraftAgent":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                for (int i = 0; i < scientist1.GetGeneticCodes().size(); i++) {
                    if (scientist1.GetGeneticCodes().get(i).ToString().equals(commandArgs[2]))
                        scientist1.Craft(scientist1.GetGeneticCodes().get(i));
                }
                break;

            case "ScientistMoves":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                int tileNum = 0;
                for (int i = 0; i < currentScientist.GetTile().GetNeighbors().size(); i++) {
                    if (currentScientist.GetTile().GetNeighbors().get(i).GetTileID().equals(commandArgs[2]))
                        tileNum = i;
                }
                scientist1.Move(tileNum);
                break;

            case "ScientistInteracts":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                scientist1.Interact();
                break;

            case "UseAgent":
                if (commandArgs.length < 4) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                for (int i = 0; i < scientist1.GetAgentPocket().size(); i++) {
                    if (scientist1.GetAgentPocket().get(i).ToString().equals(commandArgs[3]))
                        agent1 = scientist1.GetAgentPocket().get(i);
                }
                scientist1.UseAgent(scientist1, scientist2, agent1);
                break;

            case "StealGear":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                if(!scientist1.GetScientistID().equals(scientist2.GetScientistID())) {
                    scientist1.StealItem(scientist2);
                }
                break;

            case "UseAxe":
                if (commandArgs.length < 3) {
                    System.out.println("Not enough arguments.");
                    break;
                }

                if (scientist1.GetScientistID().equals(scientist2.GetScientistID())) {
                    break;
                }

                scientist1.UseAxe(scientist2);
                break;

            //Lekérdező parancsok:
            case "ReachableTiles":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                tile1 = scientist1.GetTile();
                for (int i = 0; i < tile1.GetNeighbors().size(); i++) {
                    System.out.println(i + "\t" + tile1.GetNeighbors().get(i).ToString() + "\t" + tile1.GetNeighbors().get(i).GetStuff() + "\t" + tile1.GetNeighbors().get(i).ScientistsToString());
                }
                System.out.println("");
                break;

            case "ScientistInfo":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                System.out.println("Scientist ID: " + scientist1.GetScientistID());
                System.out.println("Gear:");
                for (int i = 0; i < scientist1.GetGear().size(); i++) {
                    System.out.println(scientist1.GetGear().get(i).ToString() + " " + scientist1.GetGear().get(i).AttributeToString());
                }

                System.out.println("Genetic codes:");
                for (int i = 0; i < scientist1.GetGeneticCodes().size(); i++) {
                    System.out.println(scientist1.GetGeneticCodes().get(i).ToString() + " " + scientist1.GetGeneticCodes().get(i).GetMaterialCost());
                }

                System.out.println("Agents:");
                for (int i = 0; i < scientist1.GetAgentPocket().size(); i++) {
                    System.out.println(scientist1.GetAgentPocket().get(i).ToString());
                }

                System.out.println("Active agents:");
                for (int i = 0; i < scientist1.GetAgentOnScientist().size(); i++) {
                    System.out.println(scientist1.GetAgentOnScientist().get(i).ToString() + " " + scientist1.GetAgentOnScientist().get(i).GetRTL());
                }

                if (scientist1.GetMaterialPocket().GetMaterials() == null) {
                    System.out.println("Material in pocket: 0");
                } else
                    System.out.println("Material in pocket: " + scientist1.GetMaterialPocket().GetMaterials().size());
                System.out.println("Material in bag: " + scientist1.GetMaterialAmountInBags());
                System.out.println("");
                break;

            case "ListAllTiles":
                for (int i = 0; i < map.GetTiles().size(); i++) {
                    System.out.print(map.GetTiles().get(i).GetTileID() + "\t" + map.GetTiles().get(i).ToString() + "\t");

                    if (map.GetTiles().get(i).GetNeighbors().size() == 0) System.out.print("none");
                    if (map.GetTiles().get(i).GetNeighbors().size() > 0)
                        System.out.print(map.GetTiles().get(i).GetNeighbors().get(0).GetTileID());
                    for (int j = 1; j < map.GetTiles().get(i).GetNeighbors().size(); j++) {
                        System.out.print("," + map.GetTiles().get(i).GetNeighbors().get(j).GetTileID());
                    }
                    System.out.print("\t");

                    System.out.print((map.GetTiles().get(i)).GetStuff());
                    System.out.print("\t");

                    if (map.GetTiles().get(i).GetScientists().size() == 0) System.out.print("none");
                    if (map.GetTiles().get(i).GetScientists().size() > 0)
                        System.out.print(map.GetTiles().get(i).GetScientists().get(0).GetScientistID());
                    for (int j = 1; j < map.GetTiles().get(i).GetScientists().size(); j++) {
                        System.out.print("," + map.GetTiles().get(i).GetScientists().get(j).GetScientistID());
                    }
                    System.out.print("\n");
                }
                System.out.println("");
                break;

            case "ListAllScientists":
                for (int j = 0; j < map.GetScientists().size(); j++) {
                    System.out.print(map.GetScientists().get(j).GetScientistID() + "\t");

                    int bagCount = 0;
                    int glovesCount = 0;
                    int axeCount = 0;
                    int cloakCount = 0;
                    for (int k = 0; k < map.GetScientists().get(j).GetGear().size(); k++) {
                        if (map.GetScientists().get(j).GetGear().get(k).ToString().equals("bag"))
                            bagCount++;
                        if (map.GetScientists().get(j).GetGear().get(k).ToString().equals("gloves"))
                            glovesCount++;
                        if (map.GetScientists().get(j).GetGear().get(k).ToString().equals("axe"))
                            axeCount++;
                        if (map.GetScientists().get(j).GetGear().get(k).ToString().equals("cloak"))
                            cloakCount++;
                    }
                    if (bagCount == 1) System.out.print("bag");
                    if (bagCount > 1) System.out.print("bag:" + bagCount);

                    if (glovesCount == 1 && bagCount > 0) System.out.print(", gloves");
                    if (glovesCount == 1) System.out.print("gloves");
                    if (glovesCount > 1 && bagCount > 0) System.out.print(", gloves:" + glovesCount);
                    if (glovesCount > 1) System.out.print("gloves:" + glovesCount);

                    if (axeCount == 1 && glovesCount > 0) System.out.print(", axe");
                    if (axeCount == 1) System.out.print("axe");
                    if (axeCount > 1 && glovesCount > 0) System.out.print(", axe:" + axeCount);
                    if (axeCount > 1) System.out.print("axe:" + axeCount);

                    if (cloakCount == 1 && axeCount > 0) System.out.print(", cloak");
                    if (cloakCount == 1) System.out.print("cloak");
                    if (cloakCount > 1 && axeCount > 0) System.out.print(", cloak:" + cloakCount);
                    if (cloakCount > 1) System.out.print("cloak:" + cloakCount);
                    if (bagCount == 0 && glovesCount == 0 && axeCount == 0 && cloakCount == 0) System.out.print("none");
                    System.out.print("\t");

                    boolean vitus = false;
                    boolean forget = false;
                    boolean immune = false;
                    boolean stun = false;

                    for (int k = 0; k < map.GetScientists().get(j).GetGeneticCodes().size(); k++) {
                        if (map.GetScientists().get(j).GetGeneticCodes().get(k).ToString().equals("vitusCode"))
                            vitus = true;
                        if (map.GetScientists().get(j).GetGeneticCodes().get(k).ToString().equals("stunCode"))
                            stun = true;
                        if (map.GetScientists().get(j).GetGeneticCodes().get(k).ToString().equals("forgetCode"))
                            forget = true;
                        if (map.GetScientists().get(j).GetGeneticCodes().get(k).ToString().equals("immuneCode"))
                            immune = true;
                    }

                    if (vitus) System.out.print("vitus");
                    if (vitus && forget) System.out.print(", forget");
                    if (forget) System.out.print("forget");
                    if (forget && immune) System.out.print(", immune");
                    if (immune) System.out.print("immune");
                    if (immune && stun) System.out.print(", stun");
                    if (stun) System.out.print("stun");
                    if (!vitus && !forget && !stun && !immune) System.out.print("none");
                    System.out.print("\t");

                    vitus = false;
                    forget = false;
                    immune = false;
                    stun = false;

                    for (int k = 0; k < map.GetScientists().get(j).GetAgentPocket().size(); k++) {
                        if (map.GetScientists().get(j).GetAgentPocket().get(k).ToString().equals("vitus"))
                            vitus = true;
                        if (map.GetScientists().get(j).GetAgentPocket().get(k).ToString().equals("stun"))
                            stun = true;
                        if (map.GetScientists().get(j).GetAgentPocket().get(k).ToString().equals("forget"))
                            forget = true;
                        if (map.GetScientists().get(j).GetAgentPocket().get(k).ToString().equals("immune"))
                            immune = true;
                    }

                    if (vitus) System.out.print("vitus");
                    if (vitus && forget) System.out.print(", forget");
                    if (forget) System.out.print("forget");
                    if (forget && immune) System.out.print(", immune");
                    if (immune) System.out.print("immune");
                    if (immune && stun) System.out.print(", stun");
                    if (stun) System.out.print("stun");
                    if (!vitus && !forget && !stun && !immune) System.out.print("none");
                    System.out.print("\t");

                    boolean first = false;
                    for (int k = 0; k < map.GetScientists().get(j).GetAgentOnScientist().size(); k++) {
                        if (first) System.out.print(",");
                        System.out.print(map.GetScientists().get(j).GetAgentOnScientist().get(k).ToString() + ":" + map.GetScientists().get(j).GetAgentOnScientist().get(k).GetRTL());
                        first = true;
                    }
                    if (map.GetScientists().get(j).GetAgentOnScientist().size() == 0) System.out.print("none");
                    System.out.println("\t" + map.GetScientists().get(j).GetMaterialPocket().getSize() + "\t" + map.GetScientists().get(j).GetMaterialAmountInBags());
                }
                System.out.println("");
                break;

            case "SaveGame":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                IO io = new IO();
                io.SaveGame(commandArgs[1], Game.GetInstance().GetCommandList());
                break;

            case "LoadGame":
                if (commandArgs.length < 2) {
                    System.out.println("Not enough arguments.");
                    break;
                }
                io = new IO();
                io.LoadGame(commandArgs[1]);
                break;

            case "EndTurn":
                currentScientist.EndTurn();
                for(int i = 0; i<Game.GetInstance().GetMap().GetScientists().size(); i++) {
                    if(currentScientist.GetScientistID().equals(Game.GetInstance().GetMap().GetScientists().get(i).GetScientistID())) {
                        if(i == Game.GetInstance().GetMap().GetScientists().size() - 1) {
                            currentScientist = Game.GetInstance().GetMap().GetScientists().get(0);
                            break;
                        }
                        else {
                            currentScientist = Game.GetInstance().GetMap().GetScientists().get(i + 1);
                            break;
                        }
                    }
                }
                System.out.println(currentScientist.GetScientistID());
                target = currentScientist;
                currentScientist.Step();
                break;

            case "Exit":
                return;

            default:
                System.out.println("Command doesn't exist.");
        }
    }

    /**
     * annak vizsgálata, számot írtunk-e be
     * @param strNum megadott szöveg
     * @return true, ha szám
     */
    public static boolean IsNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * gombokról döntjük el hogy mihez tartoznak
     * @param drawable amiből megszerezzük a kirajzolandó object gombját
     */
    public void ButtonConverter(String drawable){
        String[] commandArgs;
        String[] drawableSplit = drawable.split(" ");
        switch(drawableSplit[0]){
            case "vitus":
                commandArgs = new String[]{"UseAgent", currentScientist.GetScientistID(), target.GetScientistID(), "vitus"};
                Interpreter(commandArgs);
                break;
            case "stun":
                commandArgs = new String[]{"UseAgent", currentScientist.GetScientistID(), target.GetScientistID(), "stun"};
                Interpreter(commandArgs);
                break;
            case "immune":
                commandArgs = new String[]{"UseAgent", currentScientist.GetScientistID(), target.GetScientistID(), "immune"};
                Interpreter(commandArgs);
                break;
            case "forget":
                commandArgs = new String[]{"UseAgent", currentScientist.GetScientistID(), target.GetScientistID(), "forget"};
                Interpreter(commandArgs);
                break;
            case "craftVitus":
                commandArgs = new String[]{"CraftAgent", currentScientist.GetScientistID(), "vitusCode"};
                Interpreter(commandArgs);
                break;
            case "craftStun":
                commandArgs = new String[]{"CraftAgent", currentScientist.GetScientistID(), "stunCode"};
                Interpreter(commandArgs);
                break;
            case "craftImmune":
                commandArgs = new String[]{"CraftAgent", currentScientist.GetScientistID(), "immuneCode"};
                Interpreter(commandArgs);
                break;
            case "craftForget":
                commandArgs = new String[]{"CraftAgent", currentScientist.GetScientistID(), "forgetCode"};
                Interpreter(commandArgs);
                break;
            case "steal":
                commandArgs = new String[]{"StealGear", currentScientist.GetScientistID(), target.GetScientistID()};
                Interpreter(commandArgs);
                break;
            case "axe":
                commandArgs = new String[]{"UseAxe", currentScientist.GetScientistID(), target.GetScientistID()};
                Interpreter(commandArgs);
                break;
            case "target":
                for (int i = 0; i < Game.GetInstance().GetMap().GetScientists().size(); i++) {
                    if (Game.GetInstance().GetMap().GetScientists().get(i).GetScientistID().equals(drawableSplit[1])) {
                        target = Game.GetInstance().GetMap().GetScientists().get(i);
                    }
                }
                break;
            case "tile":
                if(drawableSplit[1].equals(currentScientist.GetTile().GetTileID())) {
                    commandArgs = new String[]{"ScientistInteracts", currentScientist.GetScientistID()};
                    Interpreter(commandArgs);
                }
                else {
                    for (int i = 0; i < currentScientist.GetTile().GetNeighbors().size(); i++) {
                        if (currentScientist.GetTile().GetNeighbor(i).GetTileID().equals(drawableSplit[1])) {
                            commandArgs = new String[]{"ScientistMoves", currentScientist.GetScientistID(), drawableSplit[1]};
                            Interpreter(commandArgs);
                        }
                    }
                }
                break;
            case "endTurn":
                commandArgs = new String[]{"EndTurn"};
                Interpreter(commandArgs);
                break;
            default:
                System.out.println("no command");
                break;
        }
        Update();
    }
}