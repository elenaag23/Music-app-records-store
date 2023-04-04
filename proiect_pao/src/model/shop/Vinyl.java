package model.shop;

import model.typeofmusic.Album;

import java.util.*;

public class Vinyl extends Product {

    private List<Album> albums = new ArrayList<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Vinyl() {
    }

    public Vinyl(int id, float price, int noInStock) {
        super(id, price,noInStock);
    }

//    @Override
//    public float compareTo(CD c) {
//        return 0;
//    }

    public Vinyl(String name, List<Album> albums) {
        this.albums = albums;
        this.name = name;
    }

    public Vinyl(int id, float price, int noInStock, String name, List<Album> albums) {
        super(id, price, noInStock);
        this.name = name;
        this.albums = albums;
    }

    public void read()
    {
        Scanner var = new Scanner(System.in);

        System.out.println("What is the name of the vinyl? Please add the artist name or the genre of the vinyl.");
        this.name = var.nextLine();

        System.out.println("How many albums are on this vinyl?");

        int noAlbums = Integer.parseInt(var.nextLine());

        for(int i=0;i < noAlbums; i++)
        {
            Album album = new Album();
            album.read();
            albums.add(album);
        }

        System.out.println("Write the price of this vinyl.");
        this.price = Float.parseFloat(var.nextLine());
        System.out.println("Write how many pieces of this vinyl are added in the stock: ");
        this.noInStock = Integer.parseInt(var.nextLine());
    }

    @Override
    public String toString() {

        String output = "Vinyl named '" + name + " costs " + price + "' euro and contains the following albums: \n";
        for(Album album: albums)
        {
            output += "'" + album.getName() + "' by artist " + album.getArtist().getName() + "\n";
            output += "Currently, there are " + noInStock + " items in stock." +"\n";

        }
        return output;
    }
}
