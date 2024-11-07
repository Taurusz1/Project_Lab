//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Responsible for the showing of the agents
 */
public class AgentDictionaryLabel extends JLabel {
    private BufferedImage img; /**The agent's image*/

    /**
     * Constructor
     */
    public AgentDictionaryLabel() {
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "agentDictionary.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setIcon(new ImageIcon(img));
    }
}