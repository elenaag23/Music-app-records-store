package model.typeofmusic;

import java.util.*;

public class Album extends TypeOfMusic {

    private String name;
    private int year;
    private Artist artist;
    private List<Song> albumSongs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(List<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }

    public Album() {
        this.artist = new Artist();
    }

    public Album(int id, String category) {
        super(id, category);
    }

    public Album(String name, int year, Artist artist, List<Song> albumSongs) {
        this.name = name;
        this.year = year;
        this.artist = artist;
        this.albumSongs = albumSongs;
    }

    public Album(String category, String name, int year, Artist artist) {
        super(category);
        this.name = name;
        this.year = year;
        this.artist = artist;
    }

    public Album(int id, String category, String name, int year, Artist artist, List<Song> albumSongs) {
        super(id, category);
        this.name = name;
        this.year = year;
        this.artist = artist;
        this.albumSongs = albumSongs;
    }

    public void read()
    {
        System.out.println("Write the name of the album that you want to add: ");
        Scanner var = new Scanner(System.in);
        this.name = var.nextLine();

        this.artist.read();

        System.out.println("What year was the album released?");
        this.year = Integer.parseInt(var.nextLine());

        System.out.println("Give the album a genre (Uppercase first: ex. Pop) : ");
        this.category = var.nextLine();

        System.out.println("How many songs are on the album?");
        int noSongs = Integer.parseInt(var.nextLine());

        int i = 0;
        while(i < noSongs)
        {
            Song song = new Song();
            song.readAlbum();

            albumSongs.add(song);
            i++;

        }

    }

    @Override
    public String toString() {

        String output = "Album named '" + name + "' by artist " + artist.getName() + " was released in " + year +
                ". It fits in the " + category + " genre. Its songs are : \n";

        for(Song song: albumSongs)
        {
            output += "\uF076 " + song.getName() + "\n";
        }

        return output;
    }
}
