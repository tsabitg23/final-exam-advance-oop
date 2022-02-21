package main.models;

import main.Connect;

public class Booking {
    public int id;
    public int customer_car_id;
    public String booking_code;
    public int total;
    public String created_at;
    public String name;
    public String plate_number;
    public int item_id;
    private Connect connect = Connect.getInstance();
    public String brand;
    public String itemType;

    public Booking(int id, int customer_car_id, String booking_code, int total, String created_at, String name, String plate_number){
        this.id=id;
        this.customer_car_id=customer_car_id;
        this.booking_code = booking_code;
        this.total = total;
        this.created_at = created_at;
        this.name = name;
        this.plate_number = plate_number;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public void setItemInfo(String brand, String itemType){
        this.brand = brand;
        this.itemType = (itemType.equals("full")) ? "Paket (1 mobil)" : "Depan belakang saja";
    }

    public void saveBooking(){
        String query = "INSERT INTO booking(customer_car_id, booking_code, total)"
                + " values (?, ?, ?)";
        String parameter[] = {String.valueOf(this.customer_car_id), this.booking_code, String.valueOf(this.total)};
        int bookingId = connect.preparedStatementQuery(query, parameter);
        String insertCarQuery = "INSERT INTO booking_detail(booking_id, item_id)"
                + " values (?, ?)";
        String insertParam[] = {String.valueOf(bookingId), String.valueOf(this.item_id)};
        int carId = connect.preparedStatementQuery(insertCarQuery, insertParam);
    }
}
