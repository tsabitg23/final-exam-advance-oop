package main.models;

import main.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customers {
    public int id;
    public String name;
    public String phone_number;
    public int car_count;
    private ArrayList<CustomerCars> cars;
    private Connect connect = Connect.getInstance();

    public Customers(int id, String name, String phone_number, int car_count){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.car_count = car_count;
    }

    public void addCars(ArrayList<CustomerCars> cars){
        this.cars = cars;
    }

    private void setId(int id){
        this.id = id;
    }

    public void saveCustomer(){
        String query = "INSERT INTO customers (name, phone_number)"
                + " values (?, ?)";
        String parameter[] = {this.name, this.phone_number};
        int customerId = connect.preparedStatementQuery(query, parameter);
        String insertCarQuery = "INSERT INTO customer_cars (customer_id, car_type_id, plate_number)"
                + " values (?, ?, ?)";
        for(int i=0;i<this.cars.size();i++){
            CustomerCars newCar = this.cars.get(i);
            String insertParam[] = {String.valueOf(customerId), String.valueOf(newCar.car_type_id), newCar.plate_number};
            int carId = connect.preparedStatementQuery(insertCarQuery, insertParam);
        }
    }

    public ArrayList<CustomerCars> getCars(boolean runQuery) throws SQLException {
        if(runQuery){
            String query = "select a.*, b.name as type, b.installation_price from customer_cars a inner join car_types b on b.id = a.car_type_id where a.customer_id = '"+this.id+"'";
            ResultSet data = connect.executeQuery(query);
            ArrayList<CustomerCars> resultCars = new ArrayList<>();
            while(data.next()){
                int id = data.getInt("id");
                String plateNumber = data.getString("plate_number");
                int carTypeId = data.getInt("car_type_id");
                String carType = data.getString("type");
                int installPrice = data.getInt("installation_price");

                CustomerCars car = new CustomerCars(id, plateNumber, carTypeId);
                car.setType(carType);
                car.setInstallPrice(installPrice);
                resultCars.add(car);
            }
            this.cars = resultCars;
        }
        return this.cars;
    }
}
