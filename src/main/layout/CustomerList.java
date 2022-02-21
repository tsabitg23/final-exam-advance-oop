package main.layout;

import main.ShopInventory;
import main.models.CarTypes;
import main.models.Customers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class CustomerList extends JScrollPane{
    private JTable table = new JTable();
    private DefaultTableModel dtm = new DefaultTableModel();
    ShopInventory shop = ShopInventory.getInstance();

    CustomerList() throws SQLException {
        setViewportView(table);
        table.setModel(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("Nama");
        dtm.addColumn("Nomor telepon");
        dtm.addColumn("Jumlah mobil");
        setVisible(true);

        for(int i=0;i<shop.customers.size();i++){
            Customers item = shop.customers.get(i);
            dtm.addRow(new Object[]{item.id,item.name, item.phone_number, item.car_count});
        }
    }

}
