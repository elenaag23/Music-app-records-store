package service;

import model.User;
import model.typeofmusic.Album;
import model.typeofmusic.Artist;
import model.typeofmusic.Playlist;
import model.typeofmusic.Song;
import repository.AlbumRepository;
import repository.ArtistRepository;
import repository.PlaylistRepository;
import repository.SongRepository;
import repository.UserRepository;

import java.io.IOException;
import java.util.*;

public class PlaylistService {

    private static PlaylistService instance = null;
    private ArtistRepository artistRepository;

    private UserRepository userRepository;
    private SongRepository songRepository;
    private SongService songService;
    private PlaylistRepository playlistRepository;
    private ApplicationService applicationService;
    private List<Playlist> playlists;
    private List<Song> songs;
    HashMap<String, List<Playlist>> playlistCategories = new HashMap<String, List<Playlist>>();

    public static synchronized PlaylistService getInstance() {
        if (instance == null)
            instance = new PlaylistService();
        return instance;
    }

    private PlaylistService() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();
        this.playlistRepository = PlaylistRepository.getInstance();
        this.applicationService = ApplicationService.getInstance();
        this.userRepository = UserRepository.getInstance();
        this.songService = SongService.getInstance();
        this.playlists = applicationService.getPlaylists();
        this.songs = applicationService.getSongs();
        this.playlistCategories = applicationService.getPlaylistCategories();
    }

    public void addPlaylistSongs(User user) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the playlist:");
        String name = scanner.nextLine();
        System.out.println("Write the category of the playlist:");
        String category = scanner.nextLine();
        //System.out.println("hei id");
        //System.out.println(userId);

        //System.out.println(user.getUsername());

        Playlist playlist = new Playlist(category, name, user);
        playlistRepository.addPlaylist(playlist);

        int playlistId = playlistRepository.getPlaylists().get(playlistRepository.getPlaylists().size()-1).getId();

        System.out.println("Here is a list of all the songs: ");
        getSongs();

        System.out.println("How many songs do you want to add to the playlist?");

        int nb = scanner.nextInt();
        List<Song> playlistSongs = new ArrayList<>();
        List<Song> dbSongs = songRepository.getSongs();


        System.out.println("What songs do you want to add to the playlist?");
        System.out.println("Type the indexes of the songs to add: ");

        for(int i=0;i<nb;i++)
        {
            int ct = i+1;
            Scanner var = new Scanner(System.in);
            System.out.println("Song " + ct + ": ");
            int index = var.nextInt();
            Song song = dbSongs.get(index-1);
            boolean toAdd = validateSong(song, playlistSongs);
            if(toAdd) playlistSongs.add(song);
            playlistRepository.addSongInPlaylist(song.getId(), playlistId);
        }

        playlist.setSongs(playlistSongs);

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

        }

    }

    public boolean validateSong(Song song, List<Song> songs)
    {

        for(Song songRepo: songs)
        {
            if (songRepo.getName().equalsIgnoreCase(song.getName()) &&
                    songRepo.getArtist().getName().equalsIgnoreCase(song.getArtist().getName()))
            {
                return false;
            }

        }

        return true;

    }

    public void editPlaylist(int userId) throws IOException
    {
        List<Playlist> playlistDB = playlistRepository.getPlaylists();
        List<Playlist> userPlaylists = new ArrayList<>();
        for(Playlist playlist: playlistDB)
        {
            if(Objects.equals(userId, playlist.getUser().getId()))
            {
                userPlaylists.add(playlist);
            }
        }
        if (userPlaylists.isEmpty()) System.out.println("You have no playlists to edit. Create a playlist and try again.");

        else {
            int ct = 1;

            for(Playlist playlist:userPlaylists)
            {
                System.out.println(ct + " - " + playlist.getName());
                ct++;
            }

            System.out.println("Write the index of the playlist that you want to edit: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= userPlaylists.size()) {

                System.out.println("Please provide an index in the playlists' range.");
                index = scanner.nextInt();

            }
            System.out.println("What do you want to edit about the playlist? Choose one of the options: ");
            System.out.println("1 - Category");
            System.out.println("2 - Name");
            int option = scanner.nextInt();
            while(true)
            {
                if(option == 1) {playlistRepository.editPlaylistCategory(userPlaylists.get(index-1).getId()); break;}
                else if(option == 2) {playlistRepository.editPlaylistName(userPlaylists.get(index-1).getId()); break;}
                else System.out.println("Invalid option! Please try again.");
            }
        }


    }

    public void getPlaylists() throws IOException
    {
        List<Playlist> playlistDB = playlistRepository.getPlaylists();
        int cnt = 1;
        if (playlistDB.isEmpty()) System.out.println("There are no playlists to show. Add a playlist and try again.");
        else {

            for(Playlist playlist : playlistDB)
            {
                System.out.println(cnt + ". " + playlist.getName() + " - " + playlist.getUser().getUsername());
                cnt++;
            }

            playlistRepository.writeCSV("playlistShow", "PlaylistAudit.csv");

        }

    }

    public void getPlaylist() throws IOException
    {
        getPlaylists();
        List<Playlist> playlistDB = playlistRepository.getPlaylists();
        if(playlistDB.isEmpty()) System.out.println("There are no playlists to play.");
        else
        {
            System.out.println("Write the index of the playlist that you want to play: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= playlistDB.size()) {

                System.out.println("Please provide an index in the playlists' range.");
                index = scanner.nextInt();

            }
            int id = playlistDB.get(index - 1).getId();
            Playlist playlist = playlistRepository.getPlaylist(id);
            if(playlist == null) System.out.println("No playlist found.");
            else {playlistRepository.writeCSV("playlistPlay", "PlaylistAudit.csv");System.out.println(playlist);}
        }

    }

    public void removePlaylist(int userId) throws IOException
    {
        List<Playlist> playlistDB = playlistRepository.getPlaylists();
        List<Playlist> userPlaylists = new ArrayList<>();
        for(Playlist playlist: playlistDB)
        {
            if(Objects.equals(userId, playlist.getUser().getId()))
            {
                userPlaylists.add(playlist);
            }
        }
        if(userPlaylists.isEmpty()) System.out.println("You have no playlists to delete. Add a playlist and try again.");

        else {

            int ct = 1;

            for(Playlist playlist:userPlaylists)
            {
                System.out.println(ct + " - " + playlist.getName());
                ct++;
            }

            System.out.println("Write the index of the playlist that you want to remove: ");
            Scanner scanner = new Scanner(System.in);

            int index = scanner.nextInt();
            while (index < 0 || index - 1 >= userPlaylists.size()) {

                System.out.println("Please provide an index in the playlists' range.");
                index = scanner.nextInt();

            }

            playlistRepository.writeCSV("playlistDelete", "PlaylistAudit.csv");
            playlistRepository.removePlaylist(userPlaylists.get(index - 1).getId());


        }

    }

    public void playPlaylist() throws IOException
    {
        System.out.println("-----------Play a playlist----------");
        if (playlists.isEmpty())
        {System.out.println("There are no available playlists. Create a playlist now!");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Choose the index of the playlist you want to play");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex <= 0 || chooseIndex >= playlists.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the playlist you want to play");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            List<Song> sList = playlists.get(chooseIndex - 1).getSongs();

            for (Song song: sList)
            {
                int songStream = song.getNoStreams();
                song.setNoStreams(songStream + 1);

                int artistStream = song.getArtist().getNoStreams();
                song.getArtist().setNoStreams(artistStream + 1);
            }

            System.out.println(playlists.get(chooseIndex - 1));
            System.out.println();
            playlistRepository.writeCSV("playlistPlay", "PlaylistAudit.csv");
            System.out.println("Enjoy the playlist!");
            System.out.println("----------------------------------");
        }
        System.out.println("==========================================");
    }

    public void addSongToPlaylist(Playlist playlist) throws IOException
    {
        System.out.println("-----------Add song to playlist----------");
        songService.showSong();
        System.out.println("Choose the index of the song that you want to add to your playlist: ");
        Scanner var = new Scanner(System.in);
        int ind = Integer.parseInt(var.nextLine());
        List<Song> playSongs = playlist.getSongs();
        while(playSongs.contains(songs.get(ind - 1)))
        {
            System.out.println("This song already exists in the playlist! Please try again.");
            System.out.println("Choose the index of the song that you want to add to your playlist: ");
            ind = Integer.parseInt(var.nextLine());
        }

        playSongs.add(songs.get(ind - 1));

        playlistRepository.writeCSV("playlistSong", "PlaylistAudit.csv");

        System.out.println("Song added to the playlist succesfully!");
        System.out.println("----------------------------------");

    }

    public void addPlaylist() throws IOException
    {
        System.out.println("-----------Add a playlist----------");
        if(songs.isEmpty()) System.out.println("There are no available songs to create a playlist." +
                " Add songs and retry.");
        else{
            Playlist playlist = new Playlist();
            playlist.read();
            Scanner var = new Scanner(System.in);
            System.out.println("How many songs do you wish to add to the playlist?");
            int noSongs = Integer.parseInt(var.nextLine());

            int i = 0;
            while(i < noSongs)
            {
                addSongToPlaylist(playlist);
                i++;
            }

            playlists.add(playlist);

            String categ = playlist.getCategory();
            boolean found = false;

            for(String c: playlistCategories.keySet())
            {
                if(c.toLowerCase() == categ.toLowerCase())
                {
                    List<Playlist> playCateg = playlistCategories.get(c);
                    playCateg.add(playlist);
                    playlistCategories.put(c, playCateg);
                    found = true;
                    break;
                }
            }

            if(found == false)
            {
                playlistCategories.put(categ, List.of(playlist));
            }

            System.out.println("Playlist added succesfully!");
        }

        System.out.println("----------------------------------");
    }

    public void showPlaylist()
    {
        System.out.println("-----------Show playlists----------");
        if(playlists.isEmpty()) {
            System.out.println("There are no playlists to show. Visit the app and add a playlist.");
            System.out.println("----------------------------------");
        }
        else {
            System.out.println("Here is a list of all the playlists:");
            int i = 1;
            for(Playlist playlist:playlists)
            {
                System.out.println(i + " " + playlist.getName());
                i++;
            }
            System.out.println("----------------------------------");
        }

    }

    public void showAPlaylist() throws IOException
    {
        System.out.println("-----------Show playlist details----------");

        if(playlists.isEmpty()) System.out.println("There are no playlists to show. Add a playlist and try again.");

        else{
            System.out.println("Type the index of the playlist you want to get more details on: ");
            Scanner var = new Scanner(System.in);

            int ind = Integer.parseInt(var.nextLine());

            while(ind<=0 || ind>= playlists.size()+1)
            {
                System.out.println("Incorrect index. Please try again.");
                System.out.println("Type the index of the playlist you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            Playlist reqPlaylist = playlists.get(ind - 1);
            System.out.println(reqPlaylist);
            System.out.println("Do you want to add a song to this playlist? yes/no");
            String option = var.nextLine();
            if(Objects.equals(option, "yes"))
            {
                addSongToPlaylist(reqPlaylist);
            }
        }

        System.out.println("----------------------------------");
    }

    public void deletePlaylist() throws IOException
    {
        System.out.println("-----------Delete a playlist----------");
        if(playlists.isEmpty()) {
            System.out.println("You cannot delete a playlist as there are none available. Visit the app and add a playlist.");
            System.out.println("----------------------------------");
        }
        else {
            System.out.println("Here is a list of all the playlists:");
            int i = 1;

            for(Playlist playlist:playlists)
            {
                System.out.println(i + ". " + playlist.getName());
                i++;
            }

            System.out.println("Choose the index of the playlist that you want to delete: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice <=0 || indexChoice >= playlists.size()+1)
            {
                System.out.println("Invalid option. Try again.");
                System.out.println("Choose the index of the playlist that you want to delete: ");
                indexChoice = Integer.parseInt(var.nextLine());

            }

            String categ = playlists.get(indexChoice - 1).getCategory();
            System.out.println(categ);
            List<Playlist> playCateg = playlistCategories.get(categ);

            if(playCateg!=null)
            {
                for(Playlist p: playCateg)
                {
                    if(p == playlists.get(indexChoice - 1))
                    {
                        playCateg.remove(p);
                        playlistCategories.put(categ,playCateg);
                        break;
                    }
                }
            }



            playlists.remove(indexChoice - 1);
            playlistRepository.writeCSV("playlistDelete", "PlaylistAudit.csv");
            System.out.println("Playlist removed succesfully!");
            System.out.println("----------------------------------");
        }

    }
}
