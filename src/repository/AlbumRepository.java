package repository;
import com.opencsv.CSVWriter;
import config.*;
import model.typeofmusic.*;
import service.AlbumService;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AlbumRepository {

    private static AlbumRepository instance = null;

    public static synchronized AlbumRepository getInstance() {
        if (instance == null)
            instance = new AlbumRepository();
        return instance;
    }

    private ArtistRepository artistRepository;
    private SongRepository songRepository;


    public AlbumRepository() {
        this.artistRepository = ArtistRepository.getInstance();
        this.songRepository = SongRepository.getInstance();

    }

    public static void writeCSV(String action, String fileName) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action) {
            case "albumAdd": {
                String[] row = {"New album added", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "albumUpdate": {
                String[] row = {"Album updated", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "albumShow": {
                String[] row = {"Album read", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "albumDelete": {
                String[] row = {"Album deleted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "albumPlay": {
                String[] row = {"Album played", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

        }
    }
    public void addAlbum(Album album) throws IOException {
        String sql = "insert into album values (null, ?, ?, ?, ?) ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, album.getName());
            statement.setString(2, album.getCategory());
            statement.setInt(3, album.getYear());
            statement.setInt(4, album.getArtist().getId());
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Album not added!");
        else {System.out.println("Album created successfully!");
        writeCSV("albumAdd", "AlbumAudit.csv");}
    }

    public void addSongInAlbum(int songId, int albumId) {
        String sql = "insert into songinalbum values (?, ?) ";
        boolean success = false;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, songId);
            statement.setInt(2, albumId);
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Album not added!");
    }

    public List<Album> getAlbums() throws IOException{
        String sql = "select * from album";
        List<Album> albums = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet albumSet = statement.executeQuery();
            while (albumSet.next()) {
                int id = albumSet.getInt("id");
                String name = albumSet.getString("name");
                String category = albumSet.getString("category");
                int year = albumSet.getInt("year");
                int artistId = albumSet.getInt("artistId");
                Artist artist = artistRepository.getArtist(artistId);

                String newQuery = "select songId from songinalbum where albumId = ?";
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

                Album album = new Album(id, category, name, year, artist, songs);
                albums.add(album);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }


        return albums;
    }

    public void editAlbumName(int albumId) throws IOException
    {
        String sql = "update album set name = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new name of the album: ");
        String newName = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, albumId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The album was not found!");
        else {System.out.println("Album updated successfully!");
        writeCSV("albumUpdate", "AlbumAudit.csv");}
    }

    public void editAlbumCategory(int albumId) throws IOException
    {
        String sql = "update album set category = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new category of the album: ");
        String newCategory = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newCategory);
            statement.setInt(2, albumId);
            statement.executeUpdate();

            String newQuery = "select songId from songinalbum where albumId = ?";
            try (PreparedStatement newStatement = DatabaseConnection.getInstance().prepareStatement(newQuery)) {
                newStatement.setInt(1, albumId);
                ResultSet songSet = newStatement.executeQuery();
                while (songSet.next()) {
                    int songId = songSet.getInt("songId");
                    String songQuery = "update song set category = ? where id = ? ";
                    try (PreparedStatement songStatement = DatabaseConnection.getInstance().prepareStatement(songQuery)) {
                        songStatement.setString(1, newCategory);
                        songStatement.setInt(2, songId);
                        songStatement.executeUpdate();
                        success = true;
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The album was not found!");
        else {System.out.println("Album updated successfully!");
        writeCSV("albumUpdate", "AlbumAudit.csv");}
    }

    public void editAlbumYear(int albumId) throws IOException
    {
        String sql = "update album set year = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the new year of the album: ");
        String newYear = scanner.nextLine();
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, newYear);
            statement.setInt(2, albumId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The album was not found!");
        else {System.out.println("Album updated successfully!");
        writeCSV("albumUpdate", "AlbumAudit.csv");}
    }

    public void editAlbumArtist(int albumId, int artistId) throws IOException
    {
        String sql = "update album set artistId = ? where id = ? ";
        Scanner scanner = new Scanner(System.in);
        boolean success =  false;

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, artistId);
            statement.setInt(2, albumId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("The album was not found!");
        else {System.out.println("Album updated successfully!");
        writeCSV("albumUpdate", "AlbumAudit.csv");}
    }

    public Album getAlbum(int albumId) throws IOException
    {
        Album album = null;
        String sql = "select * from album where id = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, albumId);
            ResultSet albumSet = statement.executeQuery();
            if(albumSet.next())
            {
                int id = albumSet.getInt("id");
                String name = albumSet.getString("name");
                String category = albumSet.getString("category");
                int year =  albumSet.getInt("year");
                int artistId =  albumSet.getInt("artistId");
                Artist artist = artistRepository.getArtist(artistId);

                String newQuery = "select songId from songinalbum where albumId = ?";
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

                album = new Album(id, category, name, year, artist, songs);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return album;
    }

    public void removeAlbum(int albumId) throws IOException {
        String sql = "delete from album where id = ? ";
        boolean success = false;
        System.out.println(albumId);
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, albumId);
            statement.executeUpdate();
            success = true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Album not found!");
        else {System.out.println("The album was removed successfully!");
        writeCSV("albumDelete", "AlbumAudit.csv");}
    }
}
