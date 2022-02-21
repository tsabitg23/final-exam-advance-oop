package main;

import main.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopInventory {
    private static ShopInventory shopInventory = null;
    public VKool vkoolData = new VKool(1, "V-Kool");
    public Lumar lumarData = new Lumar(2, "Lumar");
    public MMM mmmData = new MMM(3, "3M");

    public ArrayList<AutoFilmItems> shopItems = new ArrayList<AutoFilmItems>();
    public ArrayList<CarTypes> installPrices = new ArrayList<CarTypes>();
    public ArrayList<Customers> customers = new ArrayList<Customers>();
    public ArrayList<Booking> bookings = new ArrayList<Booking>();

    ShopInventory() throws SQLException {
        this.loadItems();
        this.loadCarTypes();
        this.loadCustomerList();
        this.loadTransaction();
    }

    public static ShopInventory getInstance() throws SQLException {
        if (shopInventory == null) shopInventory = new ShopInventory();
        return shopInventory;
    }

    private void loadItems() throws SQLException {
        // get data items for each auto film brand
        ResultSet items = Connect.getInstance().executeQuery("SELECT a.*, b.name as brand_name " +
                "FROM auto_film_items a " +
                "inner join auto_films b on a.auto_film_id = b.id");
        while(items.next()){
            int autoFilmId = items.getInt("auto_film_id");
            int itemId = items.getInt("id");
            String itemType = items.getString("type");
            String itemBrand = items.getString("brand_name");
            int itemPrice = items.getInt("price");

            AutoFilmItems newItem = new AutoFilmItems(itemId, itemType, itemPrice, autoFilmId);
            newItem.setBrand(itemBrand);
            shopItems.add(newItem);
            if(autoFilmId == 1) {
                vkoolData.addItems(itemId, itemType, itemPrice, autoFilmId);
            } else if(autoFilmId == 2){
                lumarData.addItems(itemId, itemType, itemPrice, autoFilmId);
            } else {
                mmmData.addItems(itemId, itemType, itemPrice, autoFilmId);
            }
        }
    }

    private void loadCarTypes() throws SQLException {
        ResultSet items = Connect.getInstance().executeQuery("SELECT * FROM car_types");
        while(items.next()){
            int itemId = items.getInt("id");
            String type = items.getString("name");
            int itemPrice = items.getInt("installation_price");
            installPrices.add(new CarTypes(itemId, type, itemPrice));
        }
    }

    public void loadCustomerList() throws SQLException {
        customers = new ArrayList<>();
        ResultSet items = Connect.getInstance().executeQuery("SELECT a.*, count(b.id) as car_count FROM customers a inner join customer_cars b on a.id = b.customer_id group by a.id");
        while(items.next()){
            int id = items.getInt("id");
            String name = items.getString("name");
            String phoneNumber = items.getString("phone_number");
            int carCount = items.getInt("car_count");
            customers.add(new Customers(id, name, phoneNumber, carCount));
        }
    }

    public void loadTransaction() throws SQLException {
        bookings = new ArrayList<>();
        ResultSet items = Connect.getInstance().executeQuery("select b.*,\n" +
                "       c.name,\n" +
                "       cc.plate_number,\n" +
                "       af.name as brand_name,\n" +
                "       afi.type as item_type\n" +
                "from booking b\n" +
                "    inner join customer_cars cc on b.customer_car_id = cc.id\n" +
                "    inner join customers c on cc.customer_id = c.id\n" +
                "    inner join booking_detail bd on b.id = bd.booking_id\n" +
                "    inner join auto_film_items afi on bd.item_id = afi.id\n" +
                "    inner join auto_films af on afi.auto_film_id = af.id\n" +
                "group by b.id;");
        while(items.next()){
            int id = items.getInt("id");;
            int customer_car_id = items.getInt("customer_car_id");;
            String booking_code = items.getString("booking_code");;
            int total = items.getInt("total");;
            String created_at = items.getString("created_at");;
            String name = items.getString("name");;
            String plate_number = items.getString("plate_number");
            String brand = items.getString("brand_name");
            String itemType = items.getString("item_type");
            Booking newBooking = new Booking(id, customer_car_id, booking_code, total,created_at,name,plate_number);
            newBooking.setItemInfo(brand, itemType);
            bookings.add(newBooking);
        }
    }
}
