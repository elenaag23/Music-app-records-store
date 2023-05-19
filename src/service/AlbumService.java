package service;

import model.typeofmusic.Album;
import model.typeofmusic.Artist;
import model.typeofmusic.Song;
import repository.AlbumRepository;
import repository.ArtistRepository;
import repository.SongRepository;
import service.ApplicationService;

import java.io.IOException;
import java.util.*;

public class AlbumService {

    private static AlbumService instance = null;
    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    private AlbumRepository albumRepository;

    private ApplicationService applicationService;

    private List<Album> albums;

    HashMap<String, List<Album>> albumCategories;

    public static synchronized AlbumService getInstance() {
        if (instance == null)
            instance = new AlbumService();
        return instance;
    }

    AlbumService() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();
        this.albumRepository = AlbumRepository.getInstance();
        this.applicationService = ApplicationService.getInstance();
        this.albums = applicationService.getAlbums();
        this.albumCategories = applicationService.getAlbumCategories();

    }

    public void addAlbumArtist(ArtistService artistService) throws IOException
    {
        System.out.println("Here is a list of artists.");
        artistService.getDataArtist();
        System.out.println("Does the album belong to one of these artists? y/n");
        Scanner scanner = new Scanner(System.in);
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
                addAlbumSongs(artist);
                break;
            }
            else if(Objects.equals(option, "n")) {
                boolean success = true;
                Artist artist = new Artist();
                artist.read();
                List<Artist> artistDB = artistRepository.getDataArtist();
                for (int i = 0; i < artistDB.size(); i++) {
                    if (artistDB.get(i).getName().equalsIgnoreCase(artist.getName())) {
                        System.out.println("You cannot add an artist that is already in the database!");
                        success = false;
                        break;
                    }
                }
                if (success)
                {
                    artistRepository.addArtist(artist);
                    addAlbumSongs(artistRepository.getDataArtist().get(artistRepository.getDataArtist().size()-1));
                }
                else System.out.println("Album addition failed as the artist data was invalid.");
                break;
        }
            else System.out.println("Invalid option. Please try again.");
        }
    }

    public void addAlbumSongs(Artist artist) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the album:");
        String name = scanner.nextLine();
        System.out.println("Write the category of the album:");
        String category = scanner.nextLine();
        System.out.println("Write the year of the album release:");
        int year = scanner.nextInt();

        Album album = new Album(category, name, year, artist);
        albumRepository.addAlbum(album);

        int albumId = albumRepository.getAlbums().get(albumRepository.getAlbums().size()-1).getId();

        System.out.println("How many songs do you want to add to the album?");

        int nb = scanner.nextInt();
        List<Song> albumSongs = new ArrayList<>();

        for(int i=0;i<nb;i++)
        {
            Scanner var = new Scanner(System.in);
            System.out.println("Write the songs' name: ");
            String songName = var.nextLine();
            System.out.println("Write the song duration: ");
            String duration = var.nextLine();
            Song song = new Song(songName, duration);
            song.setArtist(artist);
            song.setCategory(category);
            int songId = validateSong(song);
            song.setId(songId);
            albumSongs.add(song);
            albumRepository.addSongInAlbum(songId, albumId);
        }

        album.setAlbumSongs(albumSongs);

    }

    public int validateSong(Song song) throws IOException
    {
        List<Song> songDB = songRepository.getSongs();

        for(Song songRepo: songDB)
        {
            if (songRepo.getName().equalsIgnoreCase(song.getName()) &&
                    songRepo.getArtist().getName().equalsIgnoreCase(song.getArtist().getName()))
            {
                int foundSong = songRepository.getSong(songRepo.getName()).getId();
                return foundSong;
            }


        }
            songRepository.addSong(song);
            int foundSong = songRepository.getSongs().get(songRepository.getSongs().size()-1).getId();


        return foundSong;

    }

    public void editAlbum() throws IOException
    {
        getAlbums();
        List<Album> albumDB = albumRepository.getAlbums();
        if (albumDB.isEmpty()) System.out.println("There are no albums to edit.");

        else {
            System.out.println("Write the index of the album that you want to edit: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= albumDB.size()) {

                System.out.println("Please provide an index in the albums' range.");
                index = scanner.nextInt();

            }
            System.out.println("What do you want to edit about the album? Choose one of the options: ");
            System.out.println("1 - Category");
            System.out.println("2 - Name");
            System.out.println("3 - Year");
            int option = scanner.nextInt();
            while(true)
            {
                if(option == 1) {albumRepository.editAlbumCategory(albumDB.get(index-1).getId()); break;}
                else if(option == 2) {albumRepository.editAlbumName(albumDB.get(index-1).getId()); break;}
                else if(option == 3) {albumRepository.editAlbumYear(albumDB.get(index-1).getId()); break;}
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

    public void getAlbums() throws IOException
    {
        List<Album> albumDB = albumRepository.getAlbums();
        int cnt = 1;
        if (albumDB.isEmpty()) System.out.println("There are no albums to show. Add an album and try again.");
        else {

            for(Album album : albumDB)
            {
                System.out.println(cnt + ". " + album.getName() + " - " + album.getArtist().getName());
                cnt++;
            }

            albumRepository.writeCSV("albumShow", "AlbumAudit.csv");

        }

    }

    public void getAlbum() throws IOException
    {
        getAlbums();
        List<Album> albumDB = albumRepository.getAlbums();
        if(albumDB.isEmpty()) System.out.println("There are no albums to play.");
        else
        {
            System.out.println("Write the index of the album that you want to play: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= albumRepository.getAlbums().size()) {

                System.out.println("Please provide an index in the albums' range.");
                index = scanner.nextInt();

            }
            int id = albumDB.get(index - 1).getId();
            Album album = albumRepository.getAlbum(id);
            if(album == null) System.out.println("No album found.");
            else {
                albumRepository.writeCSV("albumPlay", "AlbumAudit.csv");
                System.out.println(album);
            }

        }

    }

    public void removeAlbum() throws IOException
    {
        getAlbums();
        List<Album> albumDB = albumRepository.getAlbums();
        if(albumDB.isEmpty()) System.out.println("There are no albums to delete. Add an album and try again.");

        else {
            System.out.println("Write the index of the album that you want to remove: ");
            Scanner scanner = new Scanner(System.in);

            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= albumDB.size()) {

                System.out.println("Please provide an index in the albums' range.");
                index = scanner.nextInt();

            }
            albumRepository.removeAlbum(albumDB.get(index - 1).getId());


        }

    }

    public void playAlbum() throws IOException
    {
        System.out.println("-----------Play an album----------");
        if (albums.isEmpty())
        {System.out.println("Unfortunately, there are no albums available.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Choose the index of the album you want to play");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex <= 0 || chooseIndex >= albums.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the album you want to play");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            List<Song> sList = albums.get(chooseIndex - 1).getAlbumSongs();

            for (Song song: sList)
            {
                int songStream = song.getNoStreams();
                song.setNoStreams(songStream + 1);

                int artistStream = song.getArtist().getNoStreams();
                song.getArtist().setNoStreams(artistStream + 1);
            }

            System.out.println(albums.get(chooseIndex - 1));
            System.out.println();
            albumRepository.writeCSV("albumPlay", "AlbumAudit.csv");
            System.out.println("Enjoy the album!");
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
    }

    public void addAlbum() throws IOException
    {
        System.out.println("-----------Add an album----------");

        Album album = new Album();
        album.read();
        albums.add(album);

        String categ = album.getCategory();
        boolean found = false;

        for(String a: albumCategories.keySet())
        {
            if(a.toLowerCase() == categ.toLowerCase())
            {
                List<Album> albumCateg = albumCategories.get(a);
                albumCateg.add(album);
                albumCategories.put(a, albumCateg);
                found = true;
                break;
            }
        }

        if(found == false)
        {
            albumCategories.put(categ, List.of(album));
        }

        albumRepository.writeCSV("albumAdd", "AlbumAudit.csv");

        System.out.println("Album added succesfully!");
        System.out.println("----------------------------------");
    }

    public void showAlbum()
    {
        System.out.println("-----------Show albums----------");

        if(albums.isEmpty())
        {
            System.out.println("There are no albums to show. Visit the app and add an album.");
            System.out.println("----------------------------------");
        }

        else{
            System.out.println("Here is a list of all the albums: ");
            int i = 1;
            for(Album album: albums)
            {
                System.out.println( i + " " + album.getName() + " - " + album.getArtist().getName());
                i++;
            }

            System.out.println("----------------------------------");
        }
    }

    public void showAnAlbum() throws IOException
    {
        System.out.println("-----------Show album details----------");

        if(albums.isEmpty()) System.out.println("There are no albums to show. Add an album and try again.");

        else{
            System.out.println("Type the index of the album you want to get more details on: ");
            Scanner var = new Scanner(System.in);

            int ind = Integer.parseInt(var.nextLine());

            while(ind<=0 || ind>= albums.size() + 1)
            {
                System.out.println("Incorrect index. Please try again.");
                System.out.println("Type the index of the album you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            Album reqAlbum = albums.get(ind - 1);
            albumRepository.writeCSV("albumShow", "AlbumAudit.csv");
            System.out.println(reqAlbum);
        }

        System.out.println("----------------------------------");
    }

    public void deleteAlbum() throws IOException
    {
        System.out.println("-----------Delete an album----------");

        if(albums.isEmpty())
        {
            System.out.println("You cannot delete an album as there are none available. Visit the app and add an album.");
            System.out.println("----------------------------------");
        }

        else{
            System.out.println("Here is a list of all the albums: ");
            int i = 1;

            for(Album album: albums)
            {
                System.out.println(i + ". " + album.getName());
                i++;
            }

            System.out.println("Choose the index of the album that you want to delete: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice <=0 || indexChoice >= albums.size() + 1)
            {
                System.out.println("Invalid index. Try again.");
                System.out.println("Choose the index of the album that you want to delete: ");
                indexChoice = Integer.parseInt(var.nextLine());
            }

            String categ = albums.get(indexChoice - 1).getCategory();
            List<Album> albumCateg = albumCategories.get(categ);

            if(albumCateg!=null)
            {
                for(Album a: albumCateg)
                {
                    if(a == albums.get(indexChoice - 1))
                    {
                        albumCateg.remove(a);
                        albumCategories.put(categ,albumCateg);
                        break;
                    }
                }
            }



            albums.remove(indexChoice - 1);
            albumRepository.writeCSV("albumDelete", "AlbumAudit.csv");
            System.out.println("Album removed succesfully!");
            System.out.println("----------------------------------");
        }
    }
}
