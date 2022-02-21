package main.layout;

import main.models.AutoFilmItems;
import main.ShopInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class ViewItems extends JScrollPane{
    private JTable table = new JTable();
    private DefaultTableModel dtm = new DefaultTableModel();
    ShopInventory shop = ShopInventory.getInstance();

    ViewItems() throws SQLException {
        setViewportView(table);
        table.setModel(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("Brand");
        dtm.addColumn("Tipe");
        dtm.addColumn("Harga (USD)");
        setVisible(true);

        for(int i=0;i<shop.shopItems.size();i++){
            AutoFilmItems item = shop.shopItems.get(i);
            String type = (item.type.equals("full")) ? "Paket (1 mobil)" : "Depan belakang saja";
            dtm.addRow(new Object[]{item.id,item.brand,type,item.price});
        }
    }

}
