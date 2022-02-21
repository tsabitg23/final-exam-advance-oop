package main.layout;

import main.ShopInventory;
import main.models.CarTypes;
import main.models.CustomerCars;
import main.models.Customers;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainMenu extends JPanel {
    private ShopInventory shop = ShopInventory.getInstance();

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage bgImage = null;
        try {
            bgImage = ImageIO.read(new File(getClass().getResource("./bg.png").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }

    MainMenu() throws SQLException, IOException, URISyntaxException {
        setBounds(300, 90, 550, 450);
        setVisible(true);
        setLayout(null);

        JLabel picLabel = new JLabel("HEII");
        picLabel.setLocation(0,0);
        add(picLabel);

    }
}
