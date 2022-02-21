package main.layout;

import main.ShopInventory;
import main.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InsertTransaction extends JPanel {
    private ShopInventory shop = ShopInventory.getInstance();
    private SpinnerNumberModel mprice = new SpinnerNumberModel(1, 1, 200000, 1);
    private SpinnerNumberModel mstock = new SpinnerNumberModel(1, 1, 100, 1);
    private ArrayList<AutoFilmItems> items;
    private AutoFilm selectedBrand;

    private JLabel customerLabel = new JLabel("Customer");
    private JComboBox<String> customerComboBox;

    private JLabel customerCarLabel = new JLabel("Car");
    private JComboBox<String> customerCarComboBox;

    private JLabel brandLabel = new JLabel("Brand");
    private JComboBox<String> brandComboBox;

    private JLabel itemLabel = new JLabel("Item");
    private JComboBox<String> itemComboBox;

    private JLabel carInfoLabel = new JLabel("Car");
    private JLabel carInfoValLabel = new JLabel("");
    private JLabel installPriceLabel = new JLabel("Installation Price");
    private JLabel installPriceValLabel = new JLabel("");
    private JLabel itemPriceLabel = new JLabel("Item price");
    private JLabel itemPriceValLabel = new JLabel("");
    private JLabel totalPriceLabel = new JLabel("Total");
    private JLabel totalPriceValLabel = new JLabel("");

    private CustomerCars firstCar = null;
    private AutoFilmItems selectedItem = null;
    private Customers selectedCustomer = null;
    ArrayList<CustomerCars> customerCars = null;

    private int totalPriceTrasaction = 0;


    private void countTotal(){
        int total = 0;
        int installPrices = -1;
        int itemIndex= itemComboBox.getSelectedIndex();

        if(firstCar != null){
            installPrices = firstCar.install_price;
        }

        if(installPrices >=0 && itemIndex >= 0){
            total = selectedBrand.countTotalPrice(carInfoValLabel.getText(), installPrices, itemIndex);
            totalPriceValLabel.setText(String.valueOf(total));
            this.totalPriceTrasaction = total;
        } else {
            totalPriceValLabel.setText("-");
            this.totalPriceTrasaction = 0;
        }

    }

    InsertTransaction() throws SQLException {
        setBounds(300, 90, 550, 450);
        setVisible(true);

        setLayout(null);

        ArrayList<Customers> customerOption = shop.customers;

        String emptyOptions[] = {""};
        customerComboBox = new JComboBox(emptyOptions);

        for(int i=0;i< customerOption.size();i++){
            customerComboBox.addItem(customerOption.get(i).name);
        }

        customerLabel.setSize(200, 20);
        customerLabel.setLocation(100, 10);
        add(customerLabel);

        customerComboBox.setSize(150, 20);
        customerComboBox.setLocation(300, 10);
        customerComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int customerSelected = customerComboBox.getSelectedIndex();
                customerCarComboBox.removeAllItems();
                if(customerSelected < 1){
                    customerCarComboBox.removeAllItems();
                    carInfoValLabel.setText("");
                    installPriceValLabel.setText("");
                } else {
                    int id = shop.customers.get(customerComboBox.getSelectedIndex()-1).id;
                    selectedCustomer = new Customers(id, "","",0);
                    try {
                        customerCars = selectedCustomer.getCars(true);
                        for(int i=0;i<customerCars.size();i++){
                            customerCarComboBox.addItem(customerCars.get(i).plate_number);
                        }
                        if(customerCars.size() > 0){
                            firstCar = customerCars.get(0);
                            carInfoValLabel.setText(firstCar.plate_number + " ("+ firstCar.type + ")");
                            installPriceValLabel.setText(firstCar.install_price + "USD");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

                countTotal();
            }
        });
        add(customerComboBox);


        customerCarLabel.setSize(200, 20);
        customerCarLabel.setLocation(100, 60);
        add(customerCarLabel);

        customerCarComboBox = new JComboBox();
        customerCarComboBox.setSize(150, 20);
        customerCarComboBox.setLocation(300, 60);
        customerCarComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int index = customerCarComboBox.getSelectedIndex();
                if(index < 0) {
                    index = 0;
                }
                if(selectedCustomer != null){
                    firstCar = customerCars.get(index);
                    carInfoValLabel.setText(firstCar.plate_number + " ("+ firstCar.type + ")");
                    installPriceValLabel.setText(firstCar.install_price + "USD");
                }
                countTotal();
            }
        });
        add(customerCarComboBox);


        ArrayList<AutoFilm> brandOptions = new ArrayList<>();
        brandOptions.add(shop.vkoolData);
        brandOptions.add(shop.lumarData);
        brandOptions.add(shop.mmmData);

        brandComboBox = new JComboBox(emptyOptions);

        for(int i=0;i< brandOptions.size();i++){
            brandComboBox.addItem(brandOptions.get(i).getName());
        }


        brandLabel.setSize(200, 20);
        brandLabel.setLocation(100, 110);
        add(brandLabel);

        brandComboBox.setSize(150, 20);
        brandComboBox.setLocation(300, 110);
        brandComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int brandSelected = brandComboBox.getSelectedIndex();
                itemComboBox.removeAllItems();
                if(brandSelected < 1){
                    itemComboBox.removeAllItems();
                    itemPriceValLabel.setText("");
                    selectedBrand = null;
                } else {
                    AutoFilm selectedOption = brandOptions.get(brandComboBox.getSelectedIndex()-1);
                    items = selectedOption.getItem();
                    selectedBrand = selectedOption;
                    for(int i=0;i<items.size();i++){
                        String type = (items.get(i).type.equals("full")) ? "Paket (1 mobil)" : "Depan belakang saja";
                        itemComboBox.addItem(type);
                    }
                    if(items.size() > 0){
                        selectedItem = items.get(0);
                        itemPriceValLabel.setText(selectedItem.price + "USD");
                    }
                }

                countTotal();
            }
        });
        add(brandComboBox);

        itemLabel.setSize(200, 20);
        itemLabel.setLocation(100, 160);
        add(itemLabel);

        itemComboBox = new JComboBox();
        itemComboBox.setSize(150, 20);
        itemComboBox.setLocation(300, 160);
        itemComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
               int index = itemComboBox.getSelectedIndex();
               if(index < 0) {
                   index = 0;
               }
               selectedItem = items.get(index);
               itemPriceValLabel.setText(selectedItem.price + "USD");
                countTotal();
            }
        });
        add(itemComboBox);

        carInfoLabel.setSize(200, 20);
        carInfoLabel.setLocation(100, 260);
        add(carInfoLabel);
        carInfoValLabel.setSize(200, 20);
        carInfoValLabel.setLocation(300, 260);
        add(carInfoValLabel);
        installPriceLabel.setSize(200, 20);
        installPriceLabel.setLocation(100, 280);
        add(installPriceLabel);
        installPriceValLabel.setSize(200, 20);
        installPriceValLabel.setLocation(300, 280);
        add(installPriceValLabel);

        itemPriceLabel.setSize(200, 20);
        itemPriceLabel.setLocation(100, 300);
        add(itemPriceLabel);
        itemPriceValLabel.setSize(200, 20);
        itemPriceValLabel.setLocation(300, 300);
        add(itemPriceValLabel);
        totalPriceLabel.setSize(200, 20);
        totalPriceLabel.setLocation(100, 320);
        add(totalPriceLabel);
        totalPriceValLabel.setSize(200, 20);
        totalPriceValLabel.setLocation(300, 320);
        add(totalPriceValLabel);


        JButton insert = new JButton("Submit");
        insert.setSize(100, 20);
        insert.setLocation(225, 380);
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(customerComboBox.getSelectedIndex() < 1){
                    JOptionPane.showMessageDialog(null, "Please select Customer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(customerCarComboBox.getSelectedIndex() < 0){
                    JOptionPane.showMessageDialog(null, "Please select car", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(brandComboBox.getSelectedIndex() < 1){
                    JOptionPane.showMessageDialog(null, "Please select Auto film brand", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(itemComboBox.getSelectedIndex() < 0){
                    JOptionPane.showMessageDialog(null, "Please select package", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                insert.setEnabled(false);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddhhss");
                String bookingCode = "B"+ LocalDateTime.now().format(formatter);
                Booking newBooking = new Booking(0, firstCar.id, bookingCode, totalPriceTrasaction, "", "", firstCar.plate_number);
                newBooking.setItemId(selectedItem.id);
                newBooking.saveBooking();
                customerComboBox.setSelectedIndex(0);
                customerCarComboBox.removeAllItems();
                brandComboBox.setSelectedIndex(0);
                itemComboBox.removeAllItems();
                carInfoValLabel.setText("");
                installPriceValLabel.setText("");
                itemPriceValLabel.setText("");
                totalPriceValLabel.setText("");
                selectedBrand = null;
                firstCar = null;
                selectedItem = null;
                selectedCustomer = null;
                customerCars = null;
                totalPriceTrasaction = 0;
                try {
                    shop.loadTransaction();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                insert.setEnabled(true);
            }
        });
        add(insert);

    }
}
