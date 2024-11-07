//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw beardance to the screen
 */
public class DrawBearDance extends JButton {
    private BufferedImage img; /**Image of the beardance*/

    /**
     * Constructor
     * @param clickable true if the player click the image something will happen
     */
    public DrawBearDance(boolean clickable){
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "bear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}

