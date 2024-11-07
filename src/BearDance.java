//TODO DONE

import java.util.Random;

/**
 * Class of BearDance
 * It has a Prior level of 3. It affects last the lifetime of the BearDance.
 * Under its influence the Scientist moves randomly, if the bear steps on a warehouse tile he destroys the materials there.
 * If the bear meets another Scientist he applies the BearDance effect to them.
 */
public class BearDance extends Agent {

    /**
     * Constructor
     * Set RTL
     */
    public BearDance() {
        super(1);
    }

    /**
     * Under its influence the Scientist moves randomly, if the bear steps on a warehouse tile he destroys the materials there.
     * If the bear meets another Scientist he applies the BearDance effect to them.
     * @param s The Scientist who is influenced by the agent
     */
    public boolean Influence(Scientist s) {
        s.GetTile().DestroyMaterial();
        Random randNum = new Random();
        try {
            s.Move(randNum.nextInt(s.GetTile().GetNeighbors().size() - 1));
        }
        catch(Exception e) {}

        for (int i = 0; i < s.GetTile().GetScientists().size(); i++) {
            if (s != s.GetTile().GetScientists().get(i)) {
                s.UseAgent(s, s.GetTile().GetScientists().get(i), new BearDance());
            }
        }
        return true;
    }

    @Override
    /**
     * This agent last the lifetime of the agent, and cannot be removed.
     * @param s Unused variable, but is needed for other agent children.
     * @return true if the agent needs to be removed.
     */
    public boolean DecreaseEffectLength(Scientist s) {
        return GetRTL() == 0;
    }

    /**
     * Returns the priority level of given agent
     * @return prior level
     */
    public int Prior() {
        return 3;
    }

    /**
     * Makes a copy
     * @return copy
     */
    public BearDance Copy() {
        return new BearDance();
    }

    /**
     * ToString of BearDance Class
     * @return name of Class
     */
    public String ToString() {
        return "bear";
    }
}