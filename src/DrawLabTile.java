//TODO DONE

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw laboratory to the screen
 */
public class DrawLabTile extends JButton {
    private BufferedImage img; /**Image of the laboratory*/
    private String tileID; /**The ID of the tile*/

    /**
     * Constructor
     * @param tileID the ID of the tile
     * @param drawScientist true if the tile has scientist
     */
    public DrawLabTile(String tileID, boolean drawScientist){
        this.tileID = tileID;
        try {
            if(drawScientist)
                img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "labTileScientist.png"));
            else
                img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + "labTile.png"));
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
                Controller.GetInstance().ButtonConverter("tile "+ tileID);
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

