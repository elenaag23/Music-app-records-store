package model.typeofmusic;

import java.util.*;

public class Episode extends TypeOfMusic{

    private int epNumber;
    private String name;

    private String about;
    private String duration;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEpNumber() {
        return epNumber;
    }

    public void setEpNumber(int epNumber) {
        this.epNumber = epNumber;
    }

    public Episode() {
    }

    public Episode(int id, String category, int epNumber, String name, String about, String duration, String date) {
        super(id, category);
        this.epNumber = epNumber;
        this.name = name;
        this.about = about;
        this.duration = duration;
        this.date = date;
    }

    public Episode(int epNumber, String name, String about, String duration, String date) {
        this.epNumber = epNumber;
        this.name = name;
        this.about = about;
        this.duration = duration;
        this.date = date;
    }

    public void read()
    {
        Scanner var = new Scanner(System.in);
        System.out.println("Write the name of the episode: ");
        this.name=var.nextLine();

        System.out.println("Write the duration of the episode: ");
        this.duration = var.nextLine();

        System.out.println("Write a category for the episode (Uppercase first: ex. Pshychology): ");
        this.category = var.nextLine();

        System.out.println("Write a short description of the episode: ");
        this.about = var.nextLine();

        System.out.println("Write the date of the episode air (month - year): ");
        this.date = var.nextLine();
    }

    @Override
    public String toString() {
        return "Episode number " + epNumber + " is named '" + name + "' and it is " + duration + " minutes long. It aired on " + date +
                " and it belongs to the " + category + " category of episodes. " +
                "\n About: " + about;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
