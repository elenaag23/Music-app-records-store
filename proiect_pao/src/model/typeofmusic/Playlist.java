package model.typeofmusic;

import java.util.*;

public class Playlist extends TypeOfMusic {

    private String name;
    private List<Song> songs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Playlist() {
    }

    public Playlist(int id, String genre) {
        super(id, genre);
    }

    public Playlist(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public Playlist(int id, String category, String name, List<Song> songs) {
        super(id, category);
        this.name = name;
        this.songs = songs;
    }

    public void read()
    {
        Scanner var = new Scanner(System.in);
        System.out.println("Write your playlist' name here: ");
        this.name = var.nextLine();
        System.out.println("Write the genre of the playlist here (Uppercase first: ex. Pop): ");
        this.category = var.nextLine();

    }

    @Override
    public String toString() {

        String output = "Playlist '" + name + "' has" + noLikes + " likes. Here are the songs:\n";

        for(Song song:songs)
        {
            output += "\uF0CB " + song.toString() + '\n';
        }
        return output;
    }
}
