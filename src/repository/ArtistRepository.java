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

public class ArtistRepository {

    private static ArtistRepository instance = null;

    public static synchronized ArtistRepository getInstance() {
        if (instance == null)
            instance = new ArtistRepository();
        return instance;
    }

    public static void writeCSV(String action, String fileName) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action) {
            case "artistAdd": {
                String[] row = {"New artist added", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "artistUpdate": {
                String[] row = {"Artist updated", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "artistShow": {
                String[] row = {"Artist read", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "artistDelete": {
                String[] row = {"Artist deleted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

        }
    }

    public void addArtist(Artist artist) throws IOException{
        String sql = "insert into artist values (null, ?, ?) ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.setInt(2, 0);
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Artist not added!");
        else {System.out.println("Artist was added successfully!");
        writeCSV("artistAdd", "ArtistAudit.csv");}
    }

    public List<Artist> getDataArtist() throws IOException{
        String sql = "select * from artist";
        List<Artist> artists = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet artistSet = statement.executeQuery();
            while (artistSet.next())
            {
                int id = artistSet.getInt("id");
                String name = artistSet.getString("name");
                int noStreams = artistSet.getInt("noStreams");

                Artist artist = new Artist(id, name, noStreams);
                artists.add(artist);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        //writeCSV("artistShow", "ArtistAudit.csv");
        return artists;
    }

    public void editArtist(int artistId) throws IOException
    {
        String sql = "update artist set name = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new name of the artist: ");
        String newName = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, artistId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The artist was not found!");
        else {System.out.println("Artist updated successfully!");
        writeCSV("artistUpdate", "ArtistAudit.csv");}
    }
    public void editStreams(int id) throws IOException
    {
        Artist artist = getArtist(id);
        int noStreams = artist.getNoStreams();
        String sql = "update artist set noStreams = ? where id = ? ";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, noStreams + 1);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public Artist getArtist(int artistId) throws IOException
    {
        Artist artist = null;
        String sql = "select * from artist where id = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, artistId);
            ResultSet artistSet = statement.executeQuery();
            if(artistSet.next())
            {
                int id = artistSet.getInt("id");
                String name = artistSet.getString("name");
                int noStreams =  artistSet.getInt("noStreams");
                artist = new Artist(id, name, noStreams);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        //writeCSV("artistShow", "ArtistAudit.csv");
        return artist;
    }

    public void removeArtist(int artistId) throws IOException{
        String sql = "delete from artist where id = ? ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, artistId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Artist not found!");
        else {System.out.println("The artist was removed successfully!");
        writeCSV("artistDelete", "ArtistAudit.csv");}
    }
}
