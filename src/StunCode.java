//TODO done
/**
 * Class of Stun Agents Crafting Recipe. It can be learned in Laboratories by interacting with them. Gives the ability to craft the Stun agent.
 */
public class StunCode extends GeneticCode {
    /**
     * Material cost of crafting this agent.
     */
    protected int materialCost = 30;

    /**
     * Gives the ability to craft this agent to the Scientist.
     * @param s Scientist, who gets the ability to craft.
     */
    public void Learn(Scientist s) {
        boolean alreadyHas = false;
        for (int i = 0; i < s.GetGeneticCodes().size(); i++) {
            if (s.GetGeneticCodes().get(i).ToString().equals("stunCode")) {
                alreadyHas = true;
                break;
            }
        }
        if (!alreadyHas)
            s.GetGeneticCodes().add(Copy());
    }

    /**
     * If the Scientist has the code for the agent, and enough materials, this crafts the agent.
     * @param c The Scientist's own Crafting Station, where the crafting takes place.
     */
    public void Craft(ChemistryStation c) {
        if (c.GetCurrentMaterials() < materialCost) {
            return;
        }
        else {
            c.ConsumeMaterials(materialCost);
            AddCraftedAgent(c.GetOwner());
        }
    }

    /**
     * Gives the Scientist the crafted agent.
     * @param s Scientist, who receives the agent.
     */
    public void AddCraftedAgent(Scientist s) {
        s.AddAgentToPocket(new Stun());
    }

    /**
     * Makes a copy
     * @return copy
     */
    public GeneticCode Copy() {
        return new StunCode();
    }

    /**
     * ToString of StunCode Class
     * @return name of Class
     */
    @Override
    public String ToString() {
        return "stunCode";
    }
}