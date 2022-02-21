package main.models;

public class AutoFilmItems {
    public int id;
    public String type;
    public int price;
    public String brand;
    public int auto_film_id;

    public AutoFilmItems(int id, String type, int price, int auto_film_id){
        this.id = id;
        this.type = type;
        this.price = price;
        this.auto_film_id = auto_film_id;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }
}
