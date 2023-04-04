package model.shop;

import model.typeofmusic.Album;
import java.util.*;

public class CD extends Product {

    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public CD() {
        this.album = new Album();
    }

    public CD(int id, float price, int noInStock) {
        super(id, price, noInStock);
    }

    public CD(Album album) {
        this.album = album;
    }

    public CD(int id, float price, int noInStock, Album album) {
        super(id, price, noInStock);
        this.album = album;
    }

    public void read()
    {
        this.album.read();

        Scanner var = new Scanner(System.in);
        System.out.println("Write the price of this CD: ");
        this.price = Float.parseFloat(var.nextLine());
        System.out.println("Write the number of pieces that are added into the stock:");
        this.noInStock = Integer.parseInt(var.nextLine());
    }

    @Override
    public String toString() {
        return "CD of album " + album.getName() + " costs " + price + " euro. Currently, there are " + noInStock + " items in stock.";
    }

}
