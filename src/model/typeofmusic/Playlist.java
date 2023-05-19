package model.typeofmusic;

import model.User;

import java.util.*;

public class Playlist extends TypeOfMusic {

    private String name;
    private List<Song> songs = new ArrayList<>();

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Playlist(int id, String category, String name, List<Song> songs, User user) {
        super(id, category);
        this.name = name;
        this.songs = songs;
        this.user = user;
    }

    public Playlist(String category, String name, User user) {
        super(category);
        this.name = name;
        this.user = user;
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

        String output = "Playlist '" + name + "' fits in the " + category + " genre. Here are the songs:\n";

        for(Song song:songs)
        {
            output += "\uF0CB " + song.toString() + '\n';
        }
        return output;
    }
}
