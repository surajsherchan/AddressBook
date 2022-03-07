package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import java.awt.*;

public class AddressEntryCellRenderer implements ListCellRenderer<AddressEntry> {

    private AddressEntryGUI addressEntryGUI;

    private final Color defaultColor;
    private final Color selectedColor;

    public AddressEntryCellRenderer() {
        addressEntryGUI = new AddressEntryGUI("");

        defaultColor = new Color(238, 238, 238);
        selectedColor = new Color(184, 207, 229);
    }

    /**
     * Return a component that has been configured to display the specified
     * value. That component's <code>paint</code> method is then called to
     * "render" the cell.  If it is necessary to compute the dimensions
     * of a list because the list cells do not have a fixed size, this method
     * is called to generate a component on which <code>getPreferredSize</code>
     * can be invoked.
     *
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     * @see JList
     * @see ListSelectionModel
     * @see ListModel
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends AddressEntry> list, AddressEntry value, int index, boolean isSelected, boolean cellHasFocus) {
        addressEntryGUI.setText(value.getLastName() + ", " + value.getFirstName());
        if (isSelected) {
            addressEntryGUI.getRoot().setBackground(selectedColor);
        } else {
            addressEntryGUI.getRoot().setBackground(defaultColor);
        }
        return addressEntryGUI.getRoot();
    }
}
