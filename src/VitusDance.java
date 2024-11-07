//TODO DONE

import java.util.Random;

/**
 * This agent makes the influenced move randomly.
 * It has a priority of 5. This means, that if the Scientist has multiple affects on him, agents with priorities lower than 5,
 * influence the Scientist sooner.
 */
public class VitusDance extends Agent {

    /**
     * Constructor
     * Set RTL
     */
    public VitusDance() {
        super(3);
    }

    /**
     * Under its influence the Scientist forgets every genetic code, that he learned.
     * @param s The Scientist who is influenced by the agent
     */
    public boolean Influence(Scientist s) {
        if(GetRTL() > 0){
            Random randNum = new Random();
            s.Move(randNum.nextInt(s.GetTile().GetNeighbors().size() - 1));
        }
        return false;
    }

    /**
     * Returns the priority level of given agent
     * @return prior level
     */
    public int Prior() {
        return 5;
    }

    /**
     * ToString of Forget Class
     * @return name of Class
     */
    public String ToString() {
        return "vitus";
    }
}