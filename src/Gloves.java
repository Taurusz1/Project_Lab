//TODO DONE

/**
 * The class of gloves
 * If a scientist have gloves he can attack the one attacking him back without any injury
 */
public class Gloves extends Gear {
    private int Uses = 3; /**Represents the number of times the equipment can be used*/

    /**
     * Constructor
     */
    public Gloves() {
        super(1);
        Uses = 3;
    }

    /**
     * It calls for ThrowEffect() if a scientist wants to attack him with an agent
     * @param s the agent user
     * @param a the agent
     * @return successfull usage
     */
    public boolean Use(Scientist s, Agent a) {
        this.ThrowEffect(s, a);
        Uses--;
        if (Uses == 0) {
            this.Remove(this.GetOwner());
        }
        return true;
    }

    /**
     * Throw the agnet's effect to the first attacker
     * @param s the agent user who attacks first
     * @param a the agent
     */
    public void ThrowEffect(Scientist s, Agent a) {
        a.AddEffect(s);
    }

    /**
     * Make a copy
     * @return the copy
     */
    @Override
    public Gear Copy() {
        return new Gloves();
    }

    /**
     * Return the name of the gear
     * @return name of the gear
     */
    public String ToString() {
        return "gloves";
    }

    @Override
    /**
     * Return usable rounds
     * @return usage
     */
    public String AttributeToString() {
        return String.valueOf(Uses);
    }
}