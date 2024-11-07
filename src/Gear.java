//TODO DONE

/**
 * The class of gears
 * With gears the scientist gets different powers
 */
public abstract class Gear {
    private Scientist scientist; /**The owner of the gear*/
    private int prior;

    public Gear(int i) {
        prior = i;
    }

    /**
     * Add gear to s scientist
     * @param s the owner of the gear
     */
    public void Add(Scientist s) {
        s.GetGear().add(this);
        this.SetOwner(s);
        if (s.GetGear().size() > 3)
            this.Remove(s);
    }

    /**
     * Remove gear from s scientist
     * @param s scientist who lose gear
     */
    public void Remove(Scientist s) {
        s.GetGear().remove(this);
        this.SetOwner(null);
    }

    /**
     * Using gear's powers
     * @param s the agent user
     * @param a the agent
     * @return successfull usage
     */
    public abstract boolean Use(Scientist s, Agent a);

    /**
     * Return prior level of the gear
     * @return prior level
     */
    public int Prior() {
        return prior;
    }

    /**
     * Return owner of the gear
     * @return owner
     */
    public Scientist GetOwner() {
        return scientist;
    }

    /**
     * Set the owner of the gear
     * @param s owner
     */
    public void SetOwner(Scientist s) {
        scientist = s;
    }

    /**
     * Make a copy
     * @return the copy
     */
    public abstract Gear Copy();

    /**
     * Return the name of the gear
     * @return name of the gear
     */
    public abstract String ToString();

    /**
     * Return usable rounds
     * @return usage
     */
    public String AttributeToString() {
        return "infinite";
    }
}