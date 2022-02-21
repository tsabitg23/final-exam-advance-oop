package main.layout;

import main.ShopInventory;
import main.models.Booking;
import main.models.Customers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class ViewBooking extends JScrollPane{
    private JTable table = new JTable();
    private DefaultTableModel dtm = new DefaultTableModel();
    ShopInventory shop = ShopInventory.getInstance();

    ViewBooking() throws SQLException {
        setViewportView(table);
        table.setModel(dtm);
        dtm.addColumn("Booking Code");
        dtm.addColumn("Customer");
        dtm.addColumn("Nomor plat");
        dtm.addColumn("Auto Film");
        dtm.addColumn("Package");
        dtm.addColumn("Total biaya (USD)");
        setVisible(true);

        for(int i=0;i<shop.bookings.size();i++){
            Booking item = shop.bookings.get(i);
            dtm.addRow(new Object[]{item.booking_code,item.name, item.plate_number, item.brand, item.itemType, item.total});
        }
    }

}
