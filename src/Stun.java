//TODO DONE
/**
 * This agent makes the influenced unable to act while the effect is on him, effectively skipping his turn.
 * It has a priority of 1. This means, that if the Scientist has multiple affects on him, agents with priorities lower than 1,
 * influence the Scientist sooner.
 */
public class Stun extends Agent {

    /**
     * Constructor
     * Set RTL
     */
    public Stun() {
        super(3);
    }

    /**
     * Under its influence the Scientist forgets every genetic code, that he learned.
     * @param s The Scientist who is influenced by the agent
     */
    public boolean Influence(Scientist s) {
        if(GetRTL() > 0) {
            Controller.GetInstance().Interpreter(new String[]{"EndTurn"});
        }
        return false;
    }

    /**
     * Returns the priority level of given agent
     * @return prior level
     */
    public int Prior() {
        return 4;
    }

    /**
     * ToString of Forget Class
     * @return name of Class
     */
    public String ToString() {
        return "stun";
    }
}