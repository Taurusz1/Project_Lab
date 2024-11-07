//TODO DONE

/**
 * This agent makes the influenced immune to all other agent effects.
 * It has a priority of 1. This means, that if the Scientist has multiple affects on him, agents with priorities lower than 1,
 * influence the Scientist sooner.
 */
public class Immune extends Agent {

    /**
     * Constructor
     * Set RTL
     */
    public Immune() {
        super(4);
    }

    /**
     * Under its influence the Scientist is immune to all other agent effects, by returning true, it blocks every other agent that
     * would come after it in the priority list.
     * @param s The Scientist who is influenced by the agent
     */
    public boolean Influence(Scientist s) {
        return true;
    }

    /**
     * Returns the priority level of given agent
     * @return prior level
     */
    public int Prior() {
        return 1;
    }

    /**
     * ToString of Forget Class
     * @return name of Class
     */
    public String ToString() {
        return "immune";
    }
}