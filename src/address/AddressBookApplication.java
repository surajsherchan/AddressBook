package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import address.gui.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * AddressBookApplication is the entry point of the application and
 * calls some of the {@link Menu} class methods.
 */
public class AddressBookApplication {

    /**
     * The entry point of the {@link AddressBookApplication} and calls
     * some of the {@link Menu} class methods.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) {

        AddressBook ab = new AddressBook();
        AddressBookConnection connection;
        if (args.length == 5) {
            try {
                String login = args[0];
                String password = args[1];
                String host = args[2];
                String port = args[3];
                String sid = args[4];

                connection = new AddressBookConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + sid, login, password);
                initAddressBook(ab, connection);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid login. Could not connect to database.");
        }

        ab.listEntries();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                var frame = new JFrame();
                MenuGUI menu = new MenuGUI(ab);

                frame.setTitle("Address Book Application");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                frame.setMinimumSize(new Dimension(360, 360));
                frame.setSize(640, 480);
                frame.setLocationRelativeTo(null);

                frame.add(menu.getRoot());
                frame.setVisible(true);
            }
        });
    }

    /**
     * Initializes the {@link AddressBook} ab from a database.
     *
     * @param ab   The {@link AddressBook} that will have two
     *             {@link AddressEntry address entries} added to it.
     * @param conn A database connection.
     */
    private static void initAddressBook(AddressBook ab, AddressBookConnection conn) throws SQLException {
        for (AddressEntry entry : conn.getEntries()) {
            ab.addEntry(entry);
        }
    }
}
