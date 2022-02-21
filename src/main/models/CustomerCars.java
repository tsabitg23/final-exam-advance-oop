package main.models;

public class CustomerCars {
    public int id;
    public String plate_number;
    public int car_type_id;
    public String type;
    public int install_price;

    public CustomerCars(int id, String plate_number, int car_type_id){
        this.id = id;
        this.plate_number = plate_number;
        this.car_type_id = car_type_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInstallPrice(int installPrice) {
        this.install_price = installPrice;
    }
}
