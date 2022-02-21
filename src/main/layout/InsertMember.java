package main.layout;

import main.ShopInventory;
import main.models.CarTypes;
import main.models.CustomerCars;
import main.models.Customers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertMember extends JPanel {
    private ShopInventory shop = ShopInventory.getInstance();
    private SpinnerNumberModel mprice = new SpinnerNumberModel(1, 1, 200000, 1);
    private SpinnerNumberModel mstock = new SpinnerNumberModel(1, 1, 100, 1);

    private JLabel jname = new JLabel("Name");
    private JTextField tname = new JTextField();
    private JLabel phoneLabel = new JLabel("Phone Number");
    private JTextField phoneTextField = new JTextField();

    private JLabel plateNumberLabel = new JLabel("Car plate number");
    private JTextField plateNumberTextField = new JTextField();

    private JLabel carTypeLabel = new JLabel("Car Type");
    private JComboBox<String> carTypeComboBox;

    private JScrollPane tableScrollPane = new JScrollPane();
    private JTable tableCar = new JTable();
    private DefaultTableModel dtm = new DefaultTableModel();
    JButton insertCar = new JButton("Add Car");

    private ArrayList<CustomerCars> draftCar = new ArrayList<CustomerCars>();


    InsertMember() throws SQLException {
        setBounds(300, 90, 550, 450);
        setVisible(true);

        setLayout(null);

        jname.setSize(200, 20);
        jname.setLocation(100, 10);
        add(jname);

        tname.setSize(150, 20);
        tname.setLocation(300, 10);
        add(tname);

        phoneLabel.setSize(200, 20);
        phoneLabel.setLocation(100, 60);
        add(phoneLabel);

        phoneTextField.setSize(150, 20);
        phoneTextField.setLocation(300, 60);
        add(phoneTextField);

        plateNumberLabel.setSize(200, 20);
        plateNumberLabel.setLocation(100, 110);
        add(plateNumberLabel);

        plateNumberTextField.setSize(150, 20);
        plateNumberTextField.setLocation(300, 110);
        add(plateNumberTextField);

        carTypeLabel.setSize(200, 20);
        carTypeLabel.setLocation(100, 160);
        add(carTypeLabel);

        ArrayList<CarTypes> carTypeOption = shop.installPrices;

        carTypeComboBox = new JComboBox();

        for(int i=0;i< carTypeOption.size();i++){
            carTypeComboBox.addItem(carTypeOption.get(i).name);
        }

        carTypeComboBox.setSize(150, 20);
        carTypeComboBox.setLocation(300, 160);
        add(carTypeComboBox);

        insertCar.setSize(100, 20);
        insertCar.setLocation(300, 190);
        insertCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(plateNumberTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Plate number is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String type = carTypeComboBox.getSelectedItem().toString();
                int typeIndex = carTypeComboBox.getSelectedIndex();
                int typeId = shop.installPrices.get(typeIndex).id;
                String plateNumber = plateNumberTextField.getText();

                CustomerCars newCar = new CustomerCars(0, plateNumber, typeId);
                draftCar.add(newCar);

                dtm.addRow(new Object[]{plateNumber,type});
                carTypeComboBox.setSelectedIndex(0);
                plateNumberTextField.setText("");
            }
        });
        add(insertCar);

        JPanel panelTable = new JPanel();
        panelTable.setSize(400, 150);
        panelTable.setLocation(100, 210);
        tableScrollPane.setViewportView(tableCar);
        tableCar.setModel(dtm);
        dtm.addColumn("Plate Number");
        dtm.addColumn("Type");
        panelTable.add(tableScrollPane, BorderLayout.NORTH);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableCar.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        add(panelTable);

        JButton insert = new JButton("Submit");
        insert.setSize(100, 20);
        insert.setLocation(225, 380);
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tname.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(phoneTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Phone number is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(dtm.getRowCount() == 0){
                    JOptionPane.showMessageDialog(null, "Insert at least one car", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    String name = tname.getText();
                    String phoneNumber = phoneTextField.getText();
                    insert.setEnabled(false);

                    Customers newCustomer = new Customers(0, name, phoneNumber, 0);
                    newCustomer.addCars(draftCar);
                    newCustomer.saveCustomer();

                    tname.setText("");
                    phoneTextField.setText("");
                    dtm.setRowCount(0);
                    plateNumberTextField.setText("");
                    carTypeComboBox.setSelectedIndex(0);
                    try {
                        shop.loadCustomerList();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    insert.setEnabled(true);
                }
            }
        });
        add(insert);

    }
}
