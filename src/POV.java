import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;

/**
 * A nézetet megvalósító osztály. Ezt látja a felhasználó, és ezzel tud interaktálni.
 */
public class POV extends JFrame {
    private static POV instance = null; /**A Controller példánya.*/
    public JFrame frame = new JFrame("");

    /**
     * Visszaadja a pov példányát, illetve elkészíti azt, ha mér nem létezett
     * @return pov példánya
     */
    public static POV getInstance() {
        if (instance == null)
            instance = new POV();
        return instance;
    }

    /**
     * A pálya mezőinek kirajzolása
     * @param s az a virológus, aki által látott mezőket ki akarjuk rajzolni
     * @param panel A Panel, amire rajzolunk
     */
    public void DrawTiles(Scientist s, JPanel panel)
    {
        int xCenter = 700;
        int yCenter = 500;
        if(s.GetTile().GetScientists().size()>0) {
            String[] IDs = new String[s.GetTile().GetScientists().size()];

            for (int j = 0; j <s.GetTile().GetScientists().size(); j++){
                IDs[j]= s.GetTile().GetScientists().get(j).GetScientistID();
            }

            DrawScientist scientists = new DrawScientist(IDs);
            scientists.setBounds(xCenter-40, yCenter-160, scientists.getPreferredSize().width, scientists.getPreferredSize().height);
            panel.add(scientists);
        }

        switch(s.GetTile().ToString())
        {
            case "normal":
                DrawNormalTile myNormalTile = new DrawNormalTile(s.GetTile().GetTileID(), true);
                myNormalTile.setBounds(xCenter - myNormalTile.getPreferredSize().width/2, yCenter - myNormalTile.getPreferredSize().width/2, myNormalTile.getPreferredSize().width, myNormalTile.getPreferredSize().height);
                panel.add(myNormalTile);
                break;
            case "lab":
                DrawLabTile myLabTile = new DrawLabTile(s.GetTile().GetTileID(), true);
                myLabTile.setBounds(xCenter - myLabTile.getPreferredSize().width/2, yCenter - myLabTile.getPreferredSize().width/2, myLabTile.getPreferredSize().width, myLabTile.getPreferredSize().height);
                panel.add(myLabTile);
                break;
            case "warehouse":
                DrawWarehouseTile myWareHouseTile = new DrawWarehouseTile(s.GetTile().GetTileID(), true);
                myWareHouseTile.setBounds(xCenter - myWareHouseTile.getPreferredSize().width/2, yCenter - myWareHouseTile.getPreferredSize().width/2, myWareHouseTile.getPreferredSize().width, myWareHouseTile.getPreferredSize().height);
                panel.add(myWareHouseTile);
                break;
            case "bunker":
                DrawBunkerTile myBunkerTile = new DrawBunkerTile(s.GetTile().GetTileID(), true);
                myBunkerTile.setBounds(xCenter - myBunkerTile.getPreferredSize().width/2, yCenter - myBunkerTile.getPreferredSize().width/2, myBunkerTile.getPreferredSize().width, myBunkerTile.getPreferredSize().height);
                panel.add(myBunkerTile);
                break;
            case "infectedlab":
                DrawLabTile myInfectedTile = new DrawLabTile(s.GetTile().GetTileID(), true);
                myInfectedTile.setBounds(xCenter - myInfectedTile.getPreferredSize().width/2, yCenter - myInfectedTile.getPreferredSize().width/2, myInfectedTile.getPreferredSize().width, myInfectedTile.getPreferredSize().height);
                panel.add(myInfectedTile);
                break;
        }

        ArrayList<Tile> neighbors = s.GetTile().GetNeighbors();
        int nNeighbors = neighbors.size();

        for (int i = 0; i< nNeighbors; i++){
            Tile currentNeighbour = neighbors.get(i);
            double angle = ((2 * Math.PI) / nNeighbors) * i;
            int xCoord = xCenter + (int)(300 * Math.cos(angle));
            int yCoord = yCenter + (int)(300 * Math.sin(angle));

            if(currentNeighbour.GetScientists().size()>0) {
                String[] IDs = new String[currentNeighbour.GetScientists().size()];

                for (int j = 0; j <currentNeighbour.GetScientists().size(); j++){
                    IDs[j]= currentNeighbour.GetScientists().get(j).GetScientistID();
                }

                DrawScientist scientists = new DrawScientist(IDs);
                scientists.setBounds(xCoord-40, yCoord-160, scientists.getPreferredSize().width, scientists.getPreferredSize().height);
                panel.add(scientists);
            }

            switch(currentNeighbour.ToString())
            {

                case "normal":
                    DrawNormalTile myNormalTile = new DrawNormalTile(currentNeighbour.GetTileID(), currentNeighbour.GetScientists().size()>0);
                    myNormalTile.setBounds(xCoord - myNormalTile.getPreferredSize().width/2, yCoord - myNormalTile.getPreferredSize().width/2, myNormalTile.getPreferredSize().width, myNormalTile.getPreferredSize().height);
                    panel.add(myNormalTile);
                    break;
                case "lab":
                    DrawLabTile myLabTile = new DrawLabTile(currentNeighbour.GetTileID(), currentNeighbour.GetScientists().size()>0);
                    myLabTile.setBounds(xCoord - myLabTile.getPreferredSize().width/2, yCoord - myLabTile.getPreferredSize().width/2, myLabTile.getPreferredSize().width, myLabTile.getPreferredSize().height);
                    panel.add(myLabTile);
                    break;
                case "warehouse":
                    DrawWarehouseTile myWareHouseTile = new DrawWarehouseTile(currentNeighbour.GetTileID(), currentNeighbour.GetScientists().size()>0);
                    myWareHouseTile.setBounds(xCoord - myWareHouseTile.getPreferredSize().width/2, yCoord - myWareHouseTile.getPreferredSize().width/2, myWareHouseTile.getPreferredSize().width, myWareHouseTile.getPreferredSize().height);
                    panel.add(myWareHouseTile);
                    break;
                case "bunker":
                    DrawBunkerTile myBunkerTile = new DrawBunkerTile(currentNeighbour.GetTileID(), currentNeighbour.GetScientists().size()>0);
                    myBunkerTile.setBounds(xCoord - myBunkerTile.getPreferredSize().width/2, yCoord - myBunkerTile.getPreferredSize().width/2, myBunkerTile.getPreferredSize().width, myBunkerTile.getPreferredSize().height);
                    panel.add(myBunkerTile);
                    break;
                case "infectedlab":
                    DrawLabTile myInfectedTile = new DrawLabTile(currentNeighbour.GetTileID(), currentNeighbour.GetScientists().size()>0);
                    myInfectedTile.setBounds(xCoord - myInfectedTile.getPreferredSize().width/2, yCoord - myInfectedTile.getPreferredSize().width/2, myInfectedTile.getPreferredSize().width, myInfectedTile.getPreferredSize().height);
                    panel.add(myInfectedTile);
                    break;
            }
        }
    }

    /**
     * Kirajzolja az aktuális állását a pályának
     * @param s virológus, aki megváltoztatta a pályát
     * @param target virológus, akit támadunk, ha támadunk valakit
     */
    public void DrawCurrent(Scientist s, Scientist target)  {
        frame.getContentPane().removeAll();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //inventory panel felépítése
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setBackground(Color.CYAN);

        //baloldali panel felépítése
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel, BorderLayout.WEST);

        //loot megjelenítése
        JLabel lootLabel = new JLabel("Loot:");
        lootLabel.setFont(new Font("Myriad Pro",Font.PLAIN, 44));
        leftPanel.add(lootLabel);

        switch (s.GetTile().GetStuff()){
            case "axe":
                leftPanel.add(new DrawAxe(false));
                break;
            case "bag":
                leftPanel.add(new DrawBag());
                break;
            case "cloak":
                leftPanel.add(new DrawCloak());
                break;
            case "gloves":
                leftPanel.add(new DrawGloves());
                break;
            case "immuneCode":
                leftPanel.add(new DrawImmuneCode(false));
                break;
            case "stunCode":
                leftPanel.add(new DrawStunCode(false));
                break;
            case "vitusCode":
                leftPanel.add(new DrawVitusCode(false));
                break;
            case "forgetCode":
                leftPanel.add(new DrawForgetCode(false));
                break;
            case "materialBottle":
                leftPanel.add(new DrawMaterialBottle());
                break;
            default:
                JLabel none = new JLabel("NONE");
                none.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
                leftPanel.add(none);
        }

        JLabel separatorLabel = new JLabel(" ");
        separatorLabel.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        leftPanel.add(separatorLabel);

        AgentDictionaryLabel myDictionary = new AgentDictionaryLabel();
        leftPanel.add(myDictionary);


        //jobboldali panel felépítése
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        frame.add(rightPanel, BorderLayout.EAST);

        EndTurnButton end = new EndTurnButton();
        rightPanel.add(end);

        JLabel separatorLabel2 = new JLabel(" ");
        separatorLabel2.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        rightPanel.add(separatorLabel2);

        StealButton steal = new StealButton();
        rightPanel.add(steal);

        JLabel separatorLabel3 = new JLabel(" ");
        separatorLabel3.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        rightPanel.add(separatorLabel3);

        JLabel currantPlayerLabel = new JLabel("Current player:" );
        currantPlayerLabel.setFont(new Font("Myriad Pro",Font.PLAIN, 44));
        rightPanel.add(currantPlayerLabel);

        JLabel currentPlayer = new JLabel(s.GetScientistID());
        currentPlayer.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        rightPanel.add(currentPlayer);

        JLabel separatorLabel4 = new JLabel(" ");
        separatorLabel4.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        rightPanel.add(separatorLabel4);

        JLabel targetLabel = new JLabel("Selected target:" );
        targetLabel.setFont(new Font("Myriad Pro",Font.PLAIN, 44));
        rightPanel.add(targetLabel);

        JLabel targetPlayer = new JLabel(target.GetScientistID());
        targetPlayer.setFont(new Font("Myriad Pro",Font.PLAIN, 64));
        rightPanel.add(targetPlayer);


        //tényleges játéktér
        JPanel mainPanel = new JPanel(null);
        frame.add(mainPanel, BorderLayout.CENTER);

        DrawTiles(s, mainPanel);

        int gearCount = s.GetGear().size();
        for(int i = 0; i<gearCount; i++)
        {
            switch (s.GetGear().get(i).ToString()){
                case "axe":
                    DrawAxe myAxe = new DrawAxe(true);
                    bottomPanel.add(myAxe);
                    break;
                case  "bag":
                    DrawBag myBag = new DrawBag();
                    bottomPanel.add(myBag);
                    break;
                case "gloves":
                    DrawGloves myGloves = new DrawGloves();
                    bottomPanel.add(myGloves);
                    break;
                case "cloak":
                    DrawCloak myCloak = new DrawCloak();
                    bottomPanel.add(myCloak);

            }
            Label gearLabel = new Label(s.GetGear().get(i).AttributeToString());
            bottomPanel.add(gearLabel);
        }

        //genetikus kódok kirajzolása
        int geneticCodeCount = s.GetGeneticCodes().size();
        for(int i =0; i< geneticCodeCount;i++)
        {
            switch (s.GetGeneticCodes().get(i).ToString()){

                case "forgetCode":
                    DrawForgetCode myForgetCode = new DrawForgetCode(true);
                    bottomPanel.add(myForgetCode);
                    break;
                case  "immuneCode":
                    DrawImmuneCode myImmuneCode = new DrawImmuneCode(true);
                    bottomPanel.add(myImmuneCode);
                    break;
                case "vitusCode":
                    DrawVitusCode myVitusCode = new DrawVitusCode(true);
                    bottomPanel.add(myVitusCode);
                    break;
                case "stunCode":
                    DrawStunCode myStunCode = new DrawStunCode(true);
                    bottomPanel.add(myStunCode);
            }
        }

        //használható ágensek kirajzolása
        int agentPocketCount = s.GetAgentPocket().size();
        int forgetCnt = 0;
        int stunCnt = 0;
        int immuneCnt = 0;
        int vitusCnt = 0;
        for(int i =0; i< agentPocketCount;i++)
        {
            switch (s.GetAgentPocket().get(i).ToString()){
                case "forget":
                    forgetCnt++;
                    break;
                case "stun":
                    stunCnt++;
                    break;
                case  "immune":
                    immuneCnt++;
                    break;
                case "vitus":
                    vitusCnt++;
                    break;
            }
        }
        if(forgetCnt>0){
            DrawForgetAgent myForgetAgent = new DrawForgetAgent(true);
            bottomPanel.add(myForgetAgent);
            bottomPanel.add(new Label(String.valueOf(forgetCnt)));
        }
        if(stunCnt>0){
            DrawStun myStunAgent = new DrawStun(true);
            bottomPanel.add(myStunAgent);
            bottomPanel.add(new Label(String.valueOf(stunCnt)));
        }
        if(immuneCnt>0){
            DrawImmune myImmuneAgent = new DrawImmune(true);
            bottomPanel.add(myImmuneAgent);
            bottomPanel.add(new Label(String.valueOf(immuneCnt)));
        }
        if(vitusCnt>0){
            DrawVitusDance myVitusDance = new DrawVitusDance(true);
            bottomPanel.add(myVitusDance);
            bottomPanel.add(new Label(String.valueOf(vitusCnt)));
        }

        //zsebben lévő alapanyag kirajzolása
        DrawMaterialBottle myMaterialBottle = new DrawMaterialBottle();
        bottomPanel.add(myMaterialBottle);
        bottomPanel.add(new Label(String.valueOf(s.GetMaterialPocket().GetMaterials().size())));

        //ható ágensek kirajzolása
        int agentOnScientistCount = s.GetAgentOnScientist().size();
        int forgetMax = 0;
        int stunMax = 0;
        int immuneMax = 0;
        int vitusMax = 0;
        int bearMax = 0;

        for(int i = 0; i < agentOnScientistCount; i++)
        {
            switch (s.GetAgentOnScientist().get(i).ToString()){
                case "forget":
                    if (forgetMax < s.GetAgentOnScientist().get(i).GetRTL()){
                        forgetMax = s.GetAgentOnScientist().get(i).GetRTL();
                    }
                    break;

                case "stun":
                    if (stunMax < s.GetAgentOnScientist().get(i).GetRTL()){
                        stunMax = s.GetAgentOnScientist().get(i).GetRTL();
                    }
                    break;

                case  "immune":
                    if (immuneMax < s.GetAgentOnScientist().get(i).GetRTL()) {
                        immuneMax = s.GetAgentOnScientist().get(i).GetRTL();
                    }
                    break;

                case "vitus":
                    if (vitusMax < s.GetAgentOnScientist().get(i).GetRTL()){
                        vitusMax = s.GetAgentOnScientist().get(i).GetRTL();
                    }
                    break;

                case "bear":
                    if (bearMax < s.GetAgentOnScientist().get(i).GetRTL()){
                        bearMax = s.GetAgentOnScientist().get(i).GetRTL();
                    }
                    break;
            }
        }
        if(forgetMax>0){
            DrawForgetAgent myForgetAgent = new DrawForgetAgent(false);
            bottomPanel.add(myForgetAgent);
            bottomPanel.add(new Label(String.valueOf(forgetMax)));
        }
        if(stunMax>0){
            DrawStun myStunAgent = new DrawStun(false);
            bottomPanel.add(myStunAgent);
            bottomPanel.add(new Label(String.valueOf(stunMax)));
        }
        if(immuneMax>0){
            DrawImmune myImmuneAgent = new DrawImmune(false);
            bottomPanel.add(myImmuneAgent);
            bottomPanel.add(new Label(String.valueOf(immuneMax)));
        }
        if(vitusMax>0){
            DrawVitusDance myVitusAgent = new DrawVitusDance(false);
            bottomPanel.add(myVitusAgent);
            bottomPanel.add(new Label(String.valueOf(vitusMax)));
        }
        if(bearMax>0){
            DrawBearDance myBearAgent = new DrawBearDance(false);
            bottomPanel.add(myBearAgent);
            bottomPanel.add(new Label(String.valueOf(bearMax)));
        }


        bottomPanel.setVisible(true);
        mainPanel.setVisible(true);
        frame.setVisible(true);
    }
}