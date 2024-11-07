//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw the button which changes the scientists
 */
public class EndTurnButton extends JButton {
    private BufferedImage img; /**Image of the button*/

    /**
     * Constructor
     */
    public EndTurnButton() {
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "endTurn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(new ImageIcon(img));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(new ButtonMouseListener());
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
                Controller.GetInstance().ButtonConverter("endTurn");
            }
        }

        @Override
        public void mouseClicked(MouseEvent e){}

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e){}

        @Override
        public void mouseExited(MouseEvent e){}
    }
}

