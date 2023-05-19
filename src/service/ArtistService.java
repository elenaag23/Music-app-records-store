package service;

import model.typeofmusic.Artist;
import model.typeofmusic.Song;
import repository.AlbumRepository;
import repository.ArtistRepository;
import repository.SongRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ArtistService {

    private static ArtistService instance = null;
    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    public static synchronized ArtistService getInstance() {
        if (instance == null)
            instance = new ArtistService();
        return instance;
    }

    ArtistService() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();}

        public void addArtist() throws IOException
    {
            System.out.println("Please specify the artist details: ");
            Artist artist = new Artist();
            artist.read();
            List<Artist> artistDB = artistRepository.getDataArtist();
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
            if(success) artistRepository.addArtist(artist);
            else System.out.println("The artist was not added to the database.");
        }

        public void getDataArtist() throws IOException{
            List<Artist> artistDB = artistRepository.getDataArtist();
            int cnt = 1;
            if(artistDB.isEmpty()) System.out.println("There are no artists to show. Add an artist and try again.");
            else {

                for(Artist art : artistDB)
                {
                    System.out.println(cnt + ". " + art.getName());
                    cnt++;
                }

            }

        }

        public void editArtist() throws IOException
        {
            getDataArtist();
            List<Artist> artistDB = artistRepository.getDataArtist();
            if(artistDB.isEmpty()) System.out.println("There are no artists to edit.");
            else
            {
                System.out.println("Write the index of the artist that you want to edit: ");
                Scanner scanner = new Scanner(System.in);
                int index = scanner.nextInt();
                while (index < 0 || index - 1 >= artistRepository.getDataArtist().size()) {

                    System.out.println("Please provide an index in the artists' range.");
                    index = scanner.nextInt();

                }
                artistRepository.editArtist(artistDB.get(index - 1).getId());
            }

        }

        public void getArtist() throws IOException
        {
            getDataArtist();
            List<Artist> artistDB = artistRepository.getDataArtist();
            if(artistDB.isEmpty()) System.out.println("There are no artists to see.");
            else
            {
                System.out.println("Write the index of the artist that you want to get more details on: ");
                Scanner scanner = new Scanner(System.in);
                int index = scanner.nextInt();
                while (index < 0 || index - 1 >= artistRepository.getDataArtist().size()) {

                    System.out.println("Please provide an index in the artists' range.");
                    index = scanner.nextInt();

                }
                Artist artist = artistRepository.getArtist(artistDB.get(index - 1).getId());
                if(artist == null) System.out.println("There are no artists found");
                else {artistRepository.writeCSV("artistShow", "ArtistAudit.csv");System.out.println(artist); }
            }

        }

        public void removeArtist() throws IOException
        {
            getDataArtist();
            List<Artist> artistDB = artistRepository.getDataArtist();
            if(artistDB.isEmpty()) System.out.println("There are no artists to remove.");
            else {
                System.out.println("Write the index of the artist that you want to remove: ");
                Scanner scanner = new Scanner(System.in);
                int index = scanner.nextInt();
                while (index < 0 || index - 1 >= artistRepository.getDataArtist().size()) {

                    System.out.println("Please provide an index in the artists' range.");
                    index = scanner.nextInt();

                }
                artistRepository.removeArtist(artistDB.get(index - 1).getId());
            }

        }

    }

