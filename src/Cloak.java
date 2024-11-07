//TODO DONE

import java.util.Random;

/**
 * The class os cloak
 * It protects the scientist 82,3% from agent's attacks
 */
public class Cloak extends Gear {

    public Cloak() {
        super(2);
    }

    /**
     * Calls the RollChance() method, which returns if the agent that it tries to negate hits, or deflects.
     * @param s Scientist that is trying to apply the agent
     * @param a Agent used in the attack
     * @return true if the agent shouldn't be applied, false if it should be applied
     */
    public boolean Use(Scientist s, Agent a) {
        return this.RollChance();
    }

    /**
     * Decides if the agent that it's trying to defend hits, or not.
     * @return true if the agent shouldn't be applied, false if it should be applied
     */
    public boolean RollChance() {
        Random rand = new Random();
        int num = rand.nextInt(1000) + 1;
        if (num < 823) {
            return true;
        }
        return false;
    }

    /**
     * Makes a copy
     * @return copy
     */
    public Gear Copy() {
        return new Cloak();
    }

    /**
     * ToString of Cloak Class
     * @return name of Class
     */
    public String ToString() {
        return "cloak";
    }

    /**
     * Return with empty text
     * @return empty text
     */
    public String AttributeToString() {
        return "";
    }
}