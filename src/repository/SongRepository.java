package repository;
import com.opencsv.CSVWriter;
import config.*;
import model.typeofmusic.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SongRepository {

    private static SongRepository instance = null;

    public static synchronized SongRepository getInstance() {
        if (instance == null)
            instance = new SongRepository();
        return instance;
    }

    private ArtistRepository artistRepository;

    public SongRepository() {
        this.artistRepository = ArtistRepository.getInstance();

    }

    public static void writeCSV(String action, String fileName) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action) {
            case "songAdd": {
                String[] row = {"New song added", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "songUpdate": {
                String[] row = {"Song updated", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "songShow": {
                String[] row = {"Song read", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "songDelete": {
                String[] row = {"Song deleted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "songPlay": {
                String[] row = {"Song played", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

        }
    }

    public void addSong(Song song) throws IOException{
        String sql = "insert into song values (null, ?, ?, ?, ?, ?) ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, song.getName());
            statement.setString(2, song.getDuration());
            statement.setString(3, song.getCategory());
            statement.setInt(4, 0);
            statement.setInt(5, song.getArtist().getId());
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Song was not added!");
        else {System.out.println("Song was added successfully!");
        writeCSV("songAdd", "SongAudit.csv");}
    }


    public List<Song> getSongs() throws IOException{
        String sql = "select * from song";
        List<Song> songs = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet songSet = statement.executeQuery();
            while (songSet.next())
            {
                int id = songSet.getInt("id");
                String name = songSet.getString("name");
                String duration = songSet.getString("duration");
                String category = songSet.getString("category");
                int noStreams = songSet.getInt("noStreams");
                int artistId = songSet.getInt("artistId");
                Artist artist = artistRepository.getArtist(artistId);
                Song song = new Song(id, category, name, artist, duration);
                song.setNoStreams(noStreams);
                songs.add(song);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public void editSongCategory(int songId) throws IOException
    {
        String sql = "update song set category = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new category of the song: ");
        String newCategory = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newCategory);
            statement.setInt(2, songId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The song was not found!");
        else {System.out.println("Song category updated successfully!");
        writeCSV("songUpdate", "SongAudit.csv");}
    }

    public void editSongName(int songId) throws IOException
    {
        String sql = "update song set name = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new name of the song: ");
        String newName = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, songId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The song was not found!");
        else {System.out.println("Song name updated successfully!");
        writeCSV("songUpdate", "SongAudit.csv");}
    }

    public void editSongDuration(int songId) throws IOException
    {
        String sql = "update song set duration = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new duration of the song: ");
        String newDuration = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newDuration);
            statement.setInt(2, songId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The song was not found!");
        else {System.out.println("Song duration updated successfully!");
        writeCSV("songUpdate", "SongAudit.csv");}
    }

    public void editSongArtist(int songId, int artistId) throws IOException
    {
        String sql = "update song set artistId = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, artistId);
            statement.setInt(2, songId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The song was not found!");
        else {System.out.println("Song artist updated successfully!");
        writeCSV("songUpdate", "SongAudit.csv");}
    }

    public void editStreams(int id) throws IOException
    {
        String query = "select noStreams from song where id = ? ";
        Song song = new Song();

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet songSet = statement.executeQuery();
            if(songSet.next())
            {
                int noStreams =  songSet.getInt("noStreams");
                song.setNoStreams(noStreams + 1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        String sql = "update song set noStreams = ? where id = ? ";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, song.getNoStreams());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Song getSong(int songId) throws IOException
    {
        Song song = null;
        String sql = "select * from song where id = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, songId);
            ResultSet songSet = statement.executeQuery();
            if(songSet.next())
            {
                int id = songSet.getInt("id");
                String name = songSet.getString("name");
                String category = songSet.getString("category");
                String duration = songSet.getString("duration");
                int noStreams =  songSet.getInt("noStreams");
                int artistId = songSet.getInt("artistId");
                Artist artist = artistRepository.getArtist(artistId);
                song = new Song(id, category, name, artist, duration);
                editStreams(id);
                song.setNoStreams(noStreams + 1);
                artistRepository.editStreams(artistId);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

//        if(song == null) System.out.println("There are no songs found");
//        else writeCSV("songShow", "SongAudit.csv");
        return song;
    }

    public Song getSong(String songName) throws IOException
    {
        Song song = null;
        String sql = "select * from song where name = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, songName);
            ResultSet songSet = statement.executeQuery();
            if(songSet.next())
            {
                int id = songSet.getInt("id");
                String name = songSet.getString("name");
                String category = songSet.getString("category");
                String duration = songSet.getString("duration");
                int noStreams =  songSet.getInt("noStreams");
                int artistId = songSet.getInt("artistId");
                Artist artist = artistRepository.getArtist(artistId);
                song = new Song(id, category, name, artist, duration);
                editStreams(id);
                song.setNoStreams(noStreams);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

//        if(song == null) System.out.println("There are no songs found");
//        else writeCSV("songShow", "SongAudit.csv");
        return song;
    }

    public void removeSong(int songId) throws IOException{
        String sql = "delete from song where id = ? ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, songId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Song not found!");
        else {System.out.println("The song was removed successfully!");
        writeCSV("songDelete", "SongAudit.csv");}
    }
}
