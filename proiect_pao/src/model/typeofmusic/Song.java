package model.typeofmusic;

import java.util.*;

public class Song extends TypeOfMusic {

    private String name;
    private Artist artist;
    private double duration;
    private static int noStreams;

    public int getNoStreams() {
        return noStreams;
    }

    public void setNoStreams(int noStreams) {
        this.noStreams = noStreams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Song(){this.artist = new Artist();}

    public Song(int id, String category) {super(id, category);
    }

    public Song(String name, Artist artist, double duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
    }

    public Song(int id, String category, String name, Artist artist, double duration) {
        super(id, category);
        this.setCategory(category);
        this.name = name;
        this.artist = artist;
        this.duration = duration;
    }

    public void read()
    {
        Scanner var=new Scanner (System.in);

        System.out.println("Write the name of the song you want to add:");
        this.name=var.nextLine();

        this.artist.read();

        System.out.println("Write the duration of the song:");
        this.duration = Double.parseDouble(var.nextLine());

        System.out.println("Write the song genre (Uppercase first: ex. Pop) :");
        this.category = var.nextLine();

    }

    public void readAlbum()
    {
        Scanner var=new Scanner (System.in);

        System.out.println("Write the name of the song you want to add:");
        this.name=var.nextLine();
        System.out.println("Write the duration of the song:");
        this.duration = Double.parseDouble(var.nextLine());


    }

    @Override
    public String toString() {

        return "Song name is '" + name + "' played by " + artist.getName() + " with a duration of " + duration + " minutes."
                + " Song is found in the '" + category + "' genre and it has " + noStreams +
                " number of streams and " + noLikes + " number of likes.";
    }


}