package service;

import model.typeofmusic.Artist;
import model.typeofmusic.Song;
import repository.ArtistRepository;
import repository.SongRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SongService {

    private static SongService instance = null;
    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    private List<Song> songs;
    public HashMap<String, List<Song>> songCategories;

    public static synchronized SongService getInstance() {
        if (instance == null)
            instance = new SongService();
        return instance;
    }

    SongService() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();
        ApplicationService applicationService = ApplicationService.getInstance();
        this.songs = applicationService.getSongs();
        this.songCategories = applicationService.getSongCategories();
    }

    public void addDBSong(ArtistService artistService) throws IOException
    {
        System.out.println("Please add the song name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Add the song category: ");
        String category = scanner.nextLine();
        System.out.println("Add the song duration (minutes:seconds): ");
        String duration = scanner.nextLine();
        System.out.println("Here is a list of all the artists: ");
        artistService.getDataArtist();
        System.out.println("Does the song belong to one of these artists? y/n");
        String option = scanner.nextLine();
        while(true)
        {
            if(Objects.equals(option, "y"))
            {
                System.out.println("Write the index of the artist that the song belongs to: ");
                int index = scanner.nextInt();
                while (index < 0 || index - 1 >= artistRepository.getDataArtist().size()) {

                    System.out.println("Please provide an index in the artists' range.");
                    index = scanner.nextInt();

                }

                List<Artist> artistDB = artistRepository.getDataArtist();
                Artist artist = artistDB.get(index-1);
                Song song = new Song(category, name, artist, duration);
                validateSong(song);

                break;
            }

            else if(Objects.equals(option, "n")){

                Artist artist = new Artist();
                artist.read();
                List<Artist> artistDB = artistRepository.getDataArtist();
                for(int i=0;i<artistDB.size();i++)
                {
                    if (artistDB.get(i).getName().equalsIgnoreCase(artist.getName()))
                    {
                        System.out.println("You cannot add an artist that is already in the database!");
                        break;
                    }
                }
                artistRepository.addArtist(artist);
                Song song = new Song(category, name, artistRepository.getDataArtist().get(artistDB.size()), duration);
                validateSong(song);
                break;

            }


            else System.out.println("Invalid option. Please try again"); }

    }

    public void editSong(ArtistService artistService) throws IOException
    {
        getSongs();
        List<Song> songDB = songRepository.getSongs();
        if (songDB.isEmpty()) System.out.println("There are no songs to edit.");

        else {
            System.out.println("Write the index of the song that you want to edit: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= songDB.size()) {

                System.out.println("Please provide an index in the songs' range.");
                index = scanner.nextInt();

            }
            System.out.println("What do you want to edit about the song? Choose one of the options: ");
            System.out.println("1 - Category");
            System.out.println("2 - Name");
            System.out.println("3 - Duration");
            System.out.println("4 - Artist");
            int option = scanner.nextInt();
            while(true)
            {
                if(option == 1) {songRepository.editSongCategory(songDB.get(index-1).getId()); break;}
                else if(option == 2) {songRepository.editSongName(songDB.get(index-1).getId()); break;}
                else if(option == 3) {songRepository.editSongDuration(songDB.get(index-1).getId()); break;}
                else if(option == 4) {editSongArtist(songDB.get(index-1).getId(), artistService); break;}
                else System.out.println("Invalid option! Please try again.");
            }
        }


    }

    public void editSongArtist(int id, ArtistService artistService) throws IOException
    {

        Scanner scanner = new Scanner(System.in);
        List<Artist> artistDB = artistRepository.getDataArtist();
        System.out.println("Here is a list of all the artists: ");
        artistService.getDataArtist();
        System.out.println("Does the song belong to one of these artists? y/n");
        String option = scanner.nextLine();
        while(true)
        {
            if(Objects.equals(option, "y"))
            {
                System.out.println("Write the index of the artist that the song belongs to: ");
                int artistIndex = scanner.nextInt();
                while (artistIndex < 0 || artistIndex - 1 >= artistRepository.getDataArtist().size()) {

                    System.out.println("Please provide an index in the songs' range.");
                    artistIndex = scanner.nextInt();

                }

                Artist artist = artistDB.get(artistIndex-1);
                songRepository.editSongArtist(id, artist.getId());
                break;
            }

            else if(Objects.equals(option, "n")){

                Artist artist = new Artist();
                artist.read();
                boolean success = true;
                for(int i=0;i<artistDB.size();i++)
                {
                    if (artistDB.get(i).getName().equalsIgnoreCase(artist.getName()))
                    {
                        System.out.println("You cannot add an artist that is already in the database!");
                        success = false;
                        break;
                    }
                }
                if(success)
                {
                    artistRepository.addArtist(artist);
                    artistDB = artistRepository.getDataArtist();
                    artist = artistDB.get(artistDB.size()-1);
                    songRepository.editSongArtist(id, artist.getId());
                }

                else System.out.println("The song was not updated.");

                break;

            }

            else System.out.println("Invalid option. Please try again.");
        }


    }

    public void validateSong(Song song) throws IOException
    {
        List<Song> songDB = songRepository.getSongs();
        boolean success = true;

        for(Song songRepo: songDB)
        {
            if (songRepo.getName().equalsIgnoreCase(song.getName()) &&
                    songRepo.getArtist().getName().equalsIgnoreCase(song.getArtist().getName()))
            {System.out.println("You cannot add a song that is already in the database!"); success = false;}


        }

        if(success)
        {
            songRepository.addSong(song);
        }

        else System.out.println("The song was not added to the database.");

    }

    public void getSongs() throws IOException
    {
        List<Song> songDB = songRepository.getSongs();
        int cnt = 1;
        if (songDB.isEmpty()) System.out.println("There are no songs to show. Add a song and try again.");
        else {

            for(Song song : songDB)
            {
                System.out.println(cnt + ". " + song.getName() + " - " + song.getArtist().getName());
                cnt++;
            }
            songRepository.writeCSV("songShow", "SongAudit.csv");


        }

    }

    public void getSong() throws IOException
    {
        getSongs();
        List<Song> songDB = songRepository.getSongs();
        if(songDB.isEmpty()) System.out.println("There are no songs to play.");
        else
        {
            System.out.println("Write the index of the song that you want to play: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= songRepository.getSongs().size()) {

                System.out.println("Please provide an index in the songs' range.");
                index = scanner.nextInt();

            }
            Song song = songRepository.getSong(songDB.get(index - 1).getId());
            System.out.println(song);
        }

        songRepository.writeCSV("songPlay", "SongAudit.csv");

    }

    public void removeSong() throws IOException
    {
        getSongs();
        List<Song> songDB = songRepository.getSongs();
        if(songDB.isEmpty()) System.out.println("There are no songs to delete. Add a song and try again.");

        else {
            System.out.println("Write the index of the song that you want to remove: ");
            Scanner scanner = new Scanner(System.in);

            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= songDB.size()) {

                System.out.println("Please provide an index in the songs' range.");
                index = scanner.nextInt();

            }
            songRepository.removeSong(songDB.get(index - 1).getId());



        }

    }

    public void addSong() throws IOException{
        System.out.println("-----------Add a song----------");
        Song song = new Song();
        song.read();

        songs.add(song);

        String categ = song.getCategory();
        boolean found = false;

        for(String c : songCategories.keySet())
        {
            if (categ.toLowerCase() == c.toLowerCase())
            {
                List<Song> songCateg = songCategories.get(c);
                songCateg.add(song);
                songCategories.put(c, songCateg);
                found = true;
                break;
            }
        }

        if(found == false)
        {
            songCategories.put(categ, List.of(song));
        }

        songRepository.writeCSV("songAdd", "SongAudit.csv");


        System.out.println("Song added succesfully!");
        System.out.println("----------------------------------");

    }

    public void deleteSong() throws IOException{
        System.out.println("-----------Delete a song----------");
        if (songs.isEmpty())
        {System.out.println("You cannot delete a song as there are no available songs. Visit the app and add a song.");
            System.out.println("----------------------------------"); }
        else {
            int i = 1;
            for (Song song : songs) {
                System.out.println(i + ". " + song.getName());
                i++;
            }
            System.out.println("Choose the index of the song you want to delete");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex < 0 || chooseIndex > songs.size()) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the song you want to delete");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            String categ = songs.get(chooseIndex - 1).getCategory();
            List<Song> songCateg = songCategories.get(categ);


            for(Song song:songCateg)
            {

                if(song == songs.get(chooseIndex - 1))
                {
                    songCateg.remove(song);
                    songCategories.put(categ, songCateg);
                    break;
                }

            }

            songs.remove(chooseIndex - 1);
            songRepository.writeCSV("songDelete", "SongAudit.csv");
            System.out.println("Song removed succesfully!");
            System.out.println("----------------------------------");


        }

    }

    public void showSong() {
        System.out.println("-----------Show songs----------");
        if (songs.isEmpty())
        {System.out.println("There are no available songs. Visit the app and add a song.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here are the app's songs: ");
            int i = 1;
            for (Song song : songs) {
                System.out.println(i + " " + song.getName() + " - " + song.getArtist().getName());
                i++;
            }
        }
        System.out.println("----------------------------------");
    }

    public void showASong() throws IOException
    {
        System.out.println("-----------Show song details----------");
        if(songs.isEmpty()) System.out.println("There are no songs to show. Add a song and try again.");
        else{
            System.out.println("Type the index of the song you want to get more details on: ");
            Scanner var = new Scanner(System.in);

            int ind = Integer.parseInt(var.nextLine());

            while(ind <= 0 || ind >= songs.size()+1)
            {
                System.out.println("The index provided does not exist. Please try again.");
                System.out.println("Type the index of the song you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            Song reqSong = songs.get(ind - 1);
            songRepository.writeCSV("songRead", "SongAudit.csv");
            System.out.println(reqSong);
        }

        System.out.println("----------------------------------");

    }

    public void playSong() throws IOException
    {
        System.out.println("-----------Play song----------");
        if (songs.isEmpty())
        {System.out.println("There are no available songs. Visit the app and add a song.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Choose the index of the song you want to play");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex <= 0 || chooseIndex >= songs.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the song you want to play");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            int songStream = songs.get(chooseIndex - 1).getNoStreams();
            songs.get(chooseIndex - 1).setNoStreams(songStream + 1);

            int artistStream = songs.get(chooseIndex - 1).getArtist().getNoStreams();
            songs.get(chooseIndex - 1).getArtist().setNoStreams(artistStream + 1);

            System.out.println(songs.get(chooseIndex - 1));
            System.out.println();
            songRepository.writeCSV("songPlay", "SongAudit.csv");
            System.out.println("Enjoy the song!");
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
    }
}
