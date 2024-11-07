//TODO DONE

import java.util.ArrayList;

/**
 * The class of gear axe
 * The Axe is used for killing the attacked Scientist.
 */
public class Axe extends Gear {
    /**
     * Represents the number of times the equipment can be used, if the number is 0, the axe is "blunt"
     * and cannot be used, but it remains with the Scientist.
     */
    private int Uses = 1;

    /**
     * Constructor
     */
    public Axe() {
        super(-1);
        Uses = 1;
    }

    /**
     * Tries to kill the Scientist given as a Parameter, by removing it from the main list and the list on the Tile that it stands on.
     * @param s the target scientist
     */
    public void Kill(Scientist s) {
        ArrayList<Scientist> tmp = Game.GetInstance().GetMap().GetScientists();
        if (Uses > 0) {
            for (int i = 0; i < tmp.size(); i++) {
                if (tmp.get(i).GetScientistID().equals(s.GetScientistID())){
                    for (int j = 0; j < tmp.get(i).GetTile().GetScientists().size(); j++) {
                        tmp.get(i).GetTile().Remove(tmp.get(i));
                        tmp.remove(i);
                        break;
                    }
                }
            }
            Uses--;
        }
    }

    /**
     * This Method is used as a Delegate for every action that can be done with the gear class.
     * @param s The Scientist whom we want to kill
     * @param a This parameter is unused but is necessary for other gear class children
     * @return false, this parameter is always false but is necessary for other gear class children to be able to block the usage of other gears
     */
    public boolean Use(Scientist s, Agent a) {
        Kill(s);
        return false;
    }

    /**
     * Makes a copy
     * @return copy
     */
    public Gear Copy() {
        return new Axe();
    }

    /**
     * ToString of Axe Class
     * @return name of Class
     */
    public String ToString() {
        return "axe";
    }

    /**
     * Makes the number of uses into a String
     * @return String of uses
     */
    public String AttributeToString() {
        return String.valueOf(Uses);
    }
}