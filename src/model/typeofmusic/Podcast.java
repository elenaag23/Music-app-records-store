package model.typeofmusic;

import java.util.*;

public class Podcast extends TypeOfMusic {

    private String name;
    private String description;

    private List<Episode> episodes = new ArrayList<>();

    public Podcast() {
    }

    public Podcast(int id, String category) {
        super(id, category);
    }

    public Podcast(String name, String description, List<Episode> episodes) {
        this.name = name;
        this.description = description;
        this.episodes = episodes;
    }

    public Podcast(int id, String category, String name, String description, List<Episode> episodes) {
        super(id, category);
        this.name = name;
        this.description = description;
        this.episodes = episodes;
    }

    public void read()
    {
        Scanner var=new Scanner (System.in);

        System.out.println("Write the name of the podcast you want to add:");
        this.name= var.nextLine();

        System.out.println("Write the category of the podcast (Uppercase first: ex. Self-care): ");
        this.category = var.nextLine();

        System.out.println("Write the description of the podcast: ");
        this.description = var.nextLine();

        System.out.println("How many episodes do you want to exist in the current podcast?");
        int noEpisodes = Integer.parseInt(var.nextLine());

        int i = 0;
        while(i < noEpisodes)
        {
            Episode episode = new Episode();
            episode.read();
            episodes.add(episode);
            i++;
        }

    }

    @Override
    public String toString() {
        String output = "The podcast named" + name + " in the category of " + category + " is about " + description + " and it has the following episodes: \n";

        for (Episode episode: episodes)
        {
            output += "\uF076 " + episode.getName() + "\n";
        }

        return output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

}
