//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw material to the screen
 */
public class DrawMaterialBottle extends JButton {
    private BufferedImage img; /**Image of the material*/

    /**
     * Constructor
     */
    public DrawMaterialBottle(){
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "materialBottle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}

