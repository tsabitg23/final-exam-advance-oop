package main.layout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MainGui extends JFrame implements ActionListener {
    static JMenuBar mb;

    // JMenu
    static JMenu menu1, menu2, menu3;

    // Menu items
    static JMenuItem m0, m1, m2, m3,m4, s1, s2;

    // create a frame
    static JFrame f;

    // a label
    static JLabel l, title;

    static JScrollPane viewItems;

    static {
        try {
            viewItems = new ViewItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static JScrollPane viewInstallPrices;

    static {
        try {
            viewInstallPrices = new InstallPriceList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static JScrollPane viewCustomerList;

    static {
        try {
            viewCustomerList = new CustomerList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static JScrollPane viewTransactionList;

    static JPanel insertMember;

    static {
        try {
            insertMember = new InsertMember();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static JPanel insertTransaction;

    static JPanel mainMenu;

    static {
        try {
            mainMenu = new MainMenu();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public MainGui() throws SQLException {
    }

    // main class
    public static void main() throws SQLException {
        // create an object of the class
        MainGui m = new MainGui();

        // create a frame
        f = new JFrame("Auto Film Shop");

        // create a label
        l = new JLabel("no task ");
        title = new JLabel("Main Menu");
        title.setFont(new Font("Sans Serif", Font.BOLD, 16));

        // create a menubar
        mb = new JMenuBar();

        // create a menu
        menu1 = new JMenu("Shop");
        menu2 = new JMenu("Price List");
        menu3 = new JMenu("Customers");

        // create menuitems
        m0 = new JMenuItem("Main Menu");
        m1 = new JMenuItem("Transaction List");
        m2 = new JMenuItem("Add Transaction");
        m3 = new JMenuItem("Item List");
        m4 = new JMenuItem("Install Price List");
        s1 = new JMenuItem("Customer List");
        s2 = new JMenuItem("Add Customer");

        // add ActionListener to menuItems
        m0.addActionListener(m);
        m1.addActionListener(m);
        m2.addActionListener(m);
        m3.addActionListener(m);
        m4.addActionListener(m);
        s1.addActionListener(m);
        s2.addActionListener(m);

        // add menu items to menu
        menu1.add(m0);
        menu1.add(m1);
        menu1.add(m2);
        menu2.add(m3);
        menu2.add(m4);
        menu3.add(s1);
        menu3.add(s2);

        // add menu to menu bar
        mb.add(menu1);
        mb.add(menu2);
        mb.add(menu3);

        // add menubar to frame
        f.setJMenuBar(mb);
        f.add(title, BorderLayout.NORTH);
        f.add(mainMenu, BorderLayout.CENTER);

        // set the size of the frame
        f.setSize(600, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        title.setText(s);
        f.getContentPane().removeAll();
        switch (s){
            case "Transaction List" :
                try {
                    viewTransactionList = new ViewBooking();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                f.add(viewTransactionList, BorderLayout.CENTER);
                break;
            case "Add Transaction" :
                try {
                    insertTransaction = new InsertTransaction();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                f.add(insertTransaction, BorderLayout.CENTER);
                break;
            case "Item List" :
                f.add(viewItems, BorderLayout.CENTER);

                break;

            case "Install Price List" :
                f.add(viewInstallPrices, BorderLayout.CENTER);

                break;
            case "Customer List":
                try {
                    viewCustomerList = new CustomerList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                f.add(viewCustomerList, BorderLayout.CENTER);

                break;
            case "Add Customer" :
                f.add(insertMember, BorderLayout.CENTER);
                break;
            default:
                break;
        }
        f.add(title, BorderLayout.NORTH);
        f.validate();
        f.revalidate();
        f.repaint();
        f.setVisible(true);
    }
}
