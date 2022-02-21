package main.layout;

import main.models.CarTypes;
import main.ShopInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class InstallPriceList extends JScrollPane{
    private JTable table = new JTable();
    private DefaultTableModel dtm = new DefaultTableModel();
    ShopInventory shop = ShopInventory.getInstance();

    InstallPriceList() throws SQLException {
        setViewportView(table);
        table.setModel(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("Jenis mobil");
        dtm.addColumn("Harga instalasi (USD)");
        setVisible(true);

        for(int i=0;i<shop.installPrices.size();i++){
            CarTypes item = shop.installPrices.get(i);
            dtm.addRow(new Object[]{item.id,item.name, item.price});
        }
    }

}
