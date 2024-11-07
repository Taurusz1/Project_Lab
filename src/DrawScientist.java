//TODO DONE

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Draw scientist to the screen
 */
public class DrawScientist extends JComboBox {

    /**
     * Constructor
     * @param scientistsOnTile the players
     */
    public DrawScientist(String[] scientistsOnTile){
        addItem("");
        for (String scientist: scientistsOnTile) {
            addItem(scientist);
        }
        addItemListener(new ItemSelectHandler());
    }

    /**
     * Events
     */
    private class ItemSelectHandler implements ItemListener {

        /**
         * The event of the changing of the scientist's state
         * @param e event
         */
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                Object source = e.getSource();
                if (source instanceof JComboBox) {
                    JComboBox cb = (JComboBox)source;
                    Object selectedItem = cb.getSelectedItem();
                    Controller.GetInstance().ButtonConverter("target " + selectedItem.toString());
                }
            }
        }
    }
}