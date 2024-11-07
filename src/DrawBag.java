//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw bag to the screen
 */
public class DrawBag extends JButton {
    private BufferedImage img; /**Image of the bag*/

    /**
     * Constructor
     */
    public DrawBag() {
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "bag.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}

