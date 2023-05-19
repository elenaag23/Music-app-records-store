package repository;

import com.opencsv.CSVWriter;
import config.DatabaseConnection;
import model.User;
import model.typeofmusic.Album;
import model.typeofmusic.Artist;
import model.typeofmusic.Playlist;
import model.typeofmusic.Song;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PlaylistRepository {

    private static PlaylistRepository instance = null;

    public static synchronized PlaylistRepository getInstance() {
        if (instance == null)
            instance = new PlaylistRepository();
        return instance;
    }

    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    private UserRepository userRepository;

    public PlaylistRepository() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();
        this.userRepository = UserRepository.getInstance();

    }

    public static void writeCSV(String action, String fileName) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action) {
            case "playlistAdd": {
                String[] row = {"New playlist added", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "playlistUpdate": {
                String[] row = {"Playlist updated", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "playlistShow": {
                String[] row = {"Playlist read", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "playlistDelete": {
                String[] row = {"Playlist deleted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "playlistPlay": {
                String[] row = {"Playlist played", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "playlistSong": {
                String[] row = {"Song added to playlist", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

        }
    }
    public void addPlaylist(Playlist playlist) throws IOException {
        String sql = "insert into playlist values (null, ?, ?, ?) ";
        int userId = playlist.getUser().getId();
        //int newId = userRepository.getUser(userId).getId();
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, playlist.getName());
            statement.setString(2, playlist.getCategory());
            statement.setInt(3, userId);
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Playlist not added!");
        else {System.out.println("Playlist created successfully!");
        writeCSV("playlistAdd", "PlaylistAudit.csv");}
    }

    public void addSongInPlaylist(int songId, int playlistId) throws IOException{
        String sql = "insert into songinplaylist values (?, ?) ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, songId);
            statement.setInt(2, playlistId);
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Playlist not added!");

    }

    public List<Playlist> getPlaylists() throws IOException{
        String sql = "select * from playlist";
        List<Playlist> playlists = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet playlistSet = statement.executeQuery();
            while (playlistSet.next()) {
                int id = playlistSet.getInt("id");
                String name = playlistSet.getString("name");
                String category = playlistSet.getString("category");
                int userId = playlistSet.getInt("userId");
                User user = userRepository.getUser(userId);

                String newQuery = "select songId from songinplaylist where playlistId = ?";
                List<Song> songs = new ArrayList<>();
                try (PreparedStatement newStatement = DatabaseConnection.getInstance().prepareStatement(newQuery)) {
                    newStatement.setInt(1, id);
                    ResultSet songSet = newStatement.executeQuery();
                    while (songSet.next()) {
                        int songId = songSet.getInt("songId");
                        Song song = songRepository.getSong(songId);
                        songs.add(song);
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Playlist playlist = new Playlist(id, category, name, songs, user);
                playlists.add(playlist);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        //writeCSV("playlistShow", "PlaylistAudit.csv");
        return playlists;
    }

    public void editPlaylistName(int playlistId) throws IOException
    {
            String sql = "update playlist set name = ? where id = ? ";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please provide the new name of the playlist: ");
            String newName = scanner.nextLine();
            boolean success =  false;

            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setString(1, newName);
                statement.setInt(2, playlistId);
                statement.executeUpdate();
                success = true;
            } catch(SQLException e) {
                e.printStackTrace();
            }

            if(success == false) System.out.println("The playlist was not found!");
            else {System.out.println("Playlist updated successfully!");
            writeCSV("playlistUpdate", "PlaylistAudit.csv");}

    }

    public void editPlaylistCategory(int playlistId) throws IOException
    {
        String sql = "update playlist set category = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new category of the playlist: ");
        String newCategory = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newCategory);
            statement.setInt(2, playlistId);
            statement.executeUpdate();

            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The playlist was not found!");
        else {System.out.println("Playlist updated successfully!");
        writeCSV("playlistUpdate", "PlaylistAudit.csv");}
    }

    public Playlist getPlaylist(int playlistId) throws IOException
    {
        Playlist playlist = null;
        String sql = "select * from playlist where id = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, playlistId);
            ResultSet playlistSet = statement.executeQuery();
            if(playlistSet.next())
            {
                int id = playlistSet.getInt("id");
                String name = playlistSet.getString("name");
                String category = playlistSet.getString("category");
                int userId =  playlistSet.getInt("userId");
                User user = userRepository.getUser(userId);

                String newQuery = "select songId from songinplaylist where playlistId = ?";
                List<Song> songs = new ArrayList<>();
                try (PreparedStatement newStatement = DatabaseConnection.getInstance().prepareStatement(newQuery)) {
                    newStatement.setInt(1, id);
                    ResultSet songSet = newStatement.executeQuery();
                    while (songSet.next()) {
                        int songId = songSet.getInt("songId");
                        Song song = songRepository.getSong(songId);
                        songs.add(song);
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                playlist = new Playlist(id, category, name, songs, user);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        //writeCSV("playlistShow", "PlaylistAudit.csv");
        return playlist;
    }

    public void removePlaylist(int playlistId) throws IOException{

        String sql = "delete from playlist where id = ? ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, playlistId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Playlist not found!");
        else {System.out.println("The playlist was removed successfully!");
        writeCSV("playlistDelete", "PlaylistAudit.csv");}

    }


}
