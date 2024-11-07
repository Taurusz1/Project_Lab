//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw gloves to the screen
 */
public class DrawGloves extends JButton {
    private BufferedImage img; /**Image of the gloves*/

    /**
     * Constructor
     */
    public DrawGloves() {
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "gloves.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}