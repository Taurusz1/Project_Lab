//TODO DONE

/**
 * This agent makes the influenced forget all genetic codes, he has learned.
 * It has a priority of 2. This means, that if the Scientist has multiple affects on him, agents with priorities lower than 2,
 * influence the Scientist sooner.
 */
public class Forget extends Agent {

    /**
     * Constructor
     * Set RTL
     */
    public Forget() {
        super(2);
    }

    /**
     * Under its influence the Scientist forgets every genetic code, that he learned.
     * @param s The Scientist who is influenced by the agent
     */
    public boolean Influence(Scientist s) {
        if(GetRTL() > 0){
            if(s.GetGeneticCodes().size()>0){
                s.GetGeneticCodes().removeAll(s.GetGeneticCodes());
            }
            RemoveEffect(s);
        }
        return false;
    }

    /**
     * Returns the priority level of given agent
     * @return prior level
     */
    public int Prior() {
        return 2;
    }

    /**
     * ToString of Forget Class
     * @return name of Class
     */
    public String ToString() {
        return "forget";
    }
}