//TODO DONE

/**
 * The class of agents
 */
public abstract class Agent {
    private int RTL; /**The remaining time of the agent's effect*/

    /**
     * Constructor
     * Set RTL
     */
    public Agent(int i) {
        RTL = i;
    }

    /**
     * Return RTL
     * @return the RTL
     */
    public int GetRTL() {
        return RTL;
    }

    /**
     * Add effect to s Scientist
     * @param s scientist whom we add effect
     */
    public void AddEffect(Scientist s) {
        s.AddToAgentOnScientist(this);
    }

    /**
     * Exerts its effect to the scientist on the current round, who has the agent
     * @param s scientist whom exerts the agent's effect
     * @return true if the effect happened
     */
    public abstract boolean Influence(Scientist s);

    /**
     * Remove effect from the scientist
     * @param s scientist who had the effect
     */
    public void RemoveEffect(Scientist s) {
        s.GetAgentOnScientist().remove(this);
    }

    /**
     * The agent affects then decreases rtl
     * @param s scientist
     * @return true if rtl == 0
     */
    public boolean DecreaseEffectLength(Scientist s) {
        RTL--;
        return GetRTL() == 0;
    }

    /**
     * It defines the prior level of the agent
     * @return prior level
     */
    public abstract int Prior();

    /**
     * Return with the name of agent
     * @return name of agent
     */
    public abstract String ToString();
}