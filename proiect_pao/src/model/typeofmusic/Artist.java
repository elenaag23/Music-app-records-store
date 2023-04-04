package model.typeofmusic;

import java.util.Scanner;

public class Artist {

    private int id;
    private String name;

    private int noStreams;

    public Artist(){}

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void read()
    {
        Scanner var=new Scanner (System.in);

        System.out.println("Write the name of the artist: ");
        this.name=var.nextLine();
    }

    @Override
    public String toString() {
        return "Artist " + name + " has " + noStreams + " number of streams.";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoStreams() {
        return noStreams;
    }

    public void setNoStreams(int noStreams) {
        this.noStreams = noStreams;
    }
}
