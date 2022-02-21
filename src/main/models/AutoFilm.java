package main.models;

import java.util.ArrayList;

public class AutoFilm {
    int id;
    String name;
    ArrayList<AutoFilmItems> items = new ArrayList<AutoFilmItems>();

    AutoFilm(int id, String name){
        this.id = id;
        this.name = name;
    }

    // OVERLOADING
    public int countTotalPrice(String type){
        int prices = 500; // default price
        int installPrices = 0;
        if(type.equals("full")){
            installPrices = 200; //default install price
        } else if(type.equals("front_back_only")){
            installPrices = 100;
        }
        return prices+installPrices;
    }

    // OVERLOADING
    public int countTotalPrice(String type, int installPrice, int itemIndex){
        AutoFilmItems selectedItem = this.items.get(itemIndex);
        int itemPrice = selectedItem.price;
        return itemPrice + installPrice;
    }

    public void addItems(int id, String type, int price, int autoFilmId){
        AutoFilmItems newItem = new AutoFilmItems(id, type, price, autoFilmId);
        items.add(newItem);
    }

    public ArrayList<AutoFilmItems> getItems(){
        return this.items;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public ArrayList<AutoFilmItems> getItem(){
        return this.items;
    }
}
