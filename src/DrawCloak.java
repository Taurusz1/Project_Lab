//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw cloak to the screen
 */
public class DrawCloak extends JButton {
    private BufferedImage img; /**Image of the cloak*/

    /**
     * Constructor
     */
    public DrawCloak() {
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "cloak.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}