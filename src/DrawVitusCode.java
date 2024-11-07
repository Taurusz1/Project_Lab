//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw vitusdance genetic code to the screen
 */
public class DrawVitusCode extends JButton {
    private BufferedImage img; /**Image of the vitusdance genetic code*/

    /**
     * Constructor
     * @param clickable true if the player click the image something will happen
     */
    public DrawVitusCode(boolean clickable){
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "vitusCode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        if(clickable){
            addMouseListener(new ButtonMouseListener());
        }
    }

    /**
     * Events of the mouse
     */
    public class ButtonMouseListener implements MouseListener {

        /**
         * Event of the pressed button
         * @param e event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                Controller.GetInstance().ButtonConverter("craftVitus");
            }
        }

        @Override
        public void mouseClicked(MouseEvent e){}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}

