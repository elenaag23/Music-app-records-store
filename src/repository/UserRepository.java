package repository;

import com.opencsv.CSVWriter;
import config.DatabaseConnection;
import model.User;
import model.typeofmusic.Artist;

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

public class UserRepository {

    private static UserRepository instance = null;

    public static synchronized UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }

    public static void writeCSV(String action, String fileName) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action) {
            case "userAdd": {
                String[] row = {"New user registered", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "userRead": {
                String[] row = {"Printed list of users", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

        }
    }

    public User addUser() throws IOException{

        Scanner scanner = new Scanner(System.in);
        List<User> users = getUsers();
        boolean success = true;

        System.out.println("Type an username for your account: ");
        String username = scanner.nextLine();

        while(true)
        {
            success = true;
            for(User user:users)
            {
                if (user.getUsername().equalsIgnoreCase(username))
                {System.out.println("This username is already being used. Please type another."); success = false;
                break;}
            }

            if(success) break;

            System.out.println("Your username: ");
            username = scanner.nextLine();
        }



        System.out.println("Your password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        String sql = "insert into user values (null, ?, ?) ";


        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            success = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(success == false) System.out.println("Sign up failed!");
        else {System.out.println("Account successfully created!");
        writeCSV("userAdd", "UserAudit.csv");}

        return user;
    }

    public List<User> getUsers() throws IOException{
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet userSet = statement.executeQuery();
            while (userSet.next())
            {
                int id = userSet.getInt("id");
                String username = userSet.getString("username");
                String password = userSet.getString("password");

                User user = new User(id, username, password);
                users.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        writeCSV("userRead", "UserAudit.csv");
        return users;
    }

    public User getUser(String username, String password)
    {
        User user = null;
        String sql = "select * from user where username = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet userSet = statement.executeQuery();
            if(userSet.next())
            {
                int id = userSet.getInt("id");
                String accPassword = userSet.getString("password");
                if(Objects.equals(accPassword, password)) user = new User(id, username, password);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(user == null) System.out.println("Wrong username or password. Please try again.");
        return user;
    }

    public User getUser(int userId)
    {
        User user = null;
        String sql = "select * from user where id = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet userSet = statement.executeQuery();
            if(userSet.next())
            {
                int id = userSet.getInt("id");
                String accUsername = userSet.getString("username");
                String accPassword = userSet.getString("password");
                user = new User(id, accUsername, accPassword);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(user == null) System.out.println("No user found.");
        return user;
    }
}
