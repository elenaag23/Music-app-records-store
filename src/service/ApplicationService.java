package service;
import model.typeofmusic.Artist;
import model.typeofmusic.Song;
import model.typeofmusic.Podcast;
import model.typeofmusic.Episode;
import model.typeofmusic.Playlist;
import model.typeofmusic.Album;
import java.util.*;

public class ApplicationService {

    private static ApplicationService instance = null;
    private List<Song> songs = new ArrayList<>();
    private List<Podcast> podcasts = new ArrayList<>();
    private List<Episode> episodes = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();

    private List<Artist> artists = new ArrayList<>();

    HashMap<String, List<Song>> songCategories = new HashMap<String, List<Song>>();
    HashMap<String, List<Album>> albumCategories = new HashMap<String, List<Album>>();
    HashMap<String, List<Playlist>> playlistCategories = new HashMap<String, List<Playlist>>();
    HashMap<String, List<Episode>> episodeCategories = new HashMap<String, List<Episode>>();
    HashMap<String, List<Podcast>> podcastCategories = new HashMap<String, List<Podcast>>();

    Set<String> categories = new HashSet<String> ();



    private ApplicationService() {
    }

    public List<Song> getSongs() {
        return songs;
    }

    public HashMap<String, List<Song>> getSongCategories() {
        return songCategories;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public HashMap<String, List<Playlist>> getPlaylistCategories() {
        return playlistCategories;
    }

    public HashMap<String, List<Album>> getAlbumCategories() {
        return albumCategories;
    }

    public static synchronized ApplicationService getInstance() {
        if (instance == null)
            instance = new ApplicationService();
        return instance;
    }

    public void addElements()
    {
        Artist bonJovi = new Artist(1, "Jon Bon Jovi");
        Artist queen = new Artist(2, "Queen");
        Artist guns = new Artist(3, "Guns 'n Roses");
        Artist amy = new Artist(4, "Amy Winehouse");
        Artist arc = new Artist(5, "Artic Monkeys");
        Artist strokes = new Artist(6, "The Strokes");
        Artist lord = new Artist(7, "Lord Huron");
        Artist lana = new Artist(8, "Lana Del Rey");
        Artist bruno = new Artist(9, "Bruno Mars");
        Artist david = new Artist(10, "David Guetta");
        Artist nothing = new Artist(11, "Nothing But Thieves");
        Artist wknd = new Artist(12, "The Weeknd");
        Artist george = new Artist(13, "George Michael");
        Artist harry = new Artist(14, "Harry Styles");
        Artist depeche = new Artist(15, "Depeche Mode");
        Artist olivia = new Artist(16, "Olivia Rodrigo");

        artists.add(bonJovi);
        artists.add(queen);
        artists.add(guns);
        artists.add(amy);
        artists.add(arc);
        artists.add(strokes);
        artists.add(lord);
        artists.add(lana);
        artists.add(bruno);
        artists.add(david);
        artists.add(nothing);
        artists.add(wknd);
        artists.add(george);
        artists.add(harry);
        artists.add(depeche);
        artists.add(olivia);


        Song song1 = new Song(1,"Rock", "Livin on a prayer", bonJovi, "3:40");
        Song song2 = new Song(2, "Rock", "We will rock you", queen, "4:00");
        Song song3 = new Song(3, "Rock", "Don't cry", guns, "4:20");
        Song song4 = new Song(4, "Pop", "A Kind of Magic", queen, "4:05");
        Song song5 = new Song(5, "Soul music","Back to black", amy, "3:50");
        Song song6 = new Song(6, "Indie", "Knee socks", arc, "3:15");
        Song song7 = new Song(7, "Indie", "The adults are talking", strokes, "4:30");
        Song song8 = new Song(8, "Folk", "The night we met", lord, "3:20");
        Song song9 = new Song(9, "Sadcore", "Blue Jeans", lana, "3:30");
        Song song10 = new Song(10, "Pop", "Versace on the floor", bruno, "4:10");
        Song song11 = new Song(11, "Electronic", "Love don't let me go", david, "3:49");
        Song song12 = new Song(12, "Sadcore", "If I Get High", nothing, "2:50");
        Song song13 = new Song(13, "R&B", "In the night", wknd, "3:20");
        Song song14 = new Song(14, "Soul music", "Careless Whisper", george, "4:00");
        Song song15 = new Song(15, "Pop rock", "Music for a sushi restaurant", harry, "3:50");
        Song song16 = new Song(16, "Pop rock", "As it was", harry, "3:20");
        Song song17 = new Song(17, "Soul music", "Love of my life", harry, "3:30");
        Song song18 = new Song(18, "Soul music", "Matilda", harry, "3:50");
        Song song19 = new Song(19, "Pop rock", "Satellite", harry, "3:10");
        Song song20 = new Song(20, "Electronic", "Daydreaming", harry, "3:10");
        Song song21 = new Song(21, "Alternative", "Enjoy the silence", depeche, "3:40");
        Song song22 = new Song(22, "Rock", "Personal Jesus", depeche, "3:20");
        Song song23 = new Song(23, "Punk", "Strangelove", depeche, "3:10");
        Song song24 = new Song(24, "Electronic", "Policy of truth", depeche, "4:10");
        Song song25 = new Song(25, "Alternative", "Stripped", depeche, "4:20");
        Song song26 = new Song(26, "Pop", "Good for you", olivia, "3:50");
        Song song27 = new Song(27, "Sadcore", "Drivers license", olivia, "3:30");
        Song song28 = new Song(28, "Sadcore", "Deja vu", olivia, "3:20");
        Song song29 = new Song(29, "Pop rock", "Brutal", olivia, "3:40");
        Song song30 = new Song(30, "Sadcore", "Enough for you", olivia, "4:20");

        songs.add(song1);
        songs.add(song2);
        songs.add(song3);
        songs.add(song4);
        songs.add(song5);
        songs.add(song6);
        songs.add(song7);
        songs.add(song8);
        songs.add(song9);
        songs.add(song10);
        songs.add(song11);
        songs.add(song12);
        songs.add(song13);
        songs.add(song14);
        songs.add(song15);
        songs.add(song16);
        songs.add(song17);
        songs.add(song18);
        songs.add(song19);
        songs.add(song20);
        songs.add(song21);
        songs.add(song22);
        songs.add(song23);
        songs.add(song24);
        songs.add(song25);
        songs.add(song26);
        songs.add(song27);
        songs.add(song28);
        songs.add(song29);
        songs.add(song30);

        List<Song> rock = new ArrayList<>();
        rock.add(song1);
        rock.add(song2);
        rock.add(song3);

        songCategories.put("Rock", rock);

        List<Song> pop = new ArrayList<>();
        pop.add(song4);
        pop.add(song10);
        pop.add(song26);
        songCategories.put("Pop", pop);

        List<Song> soul = new ArrayList<>();
        soul.add(song5);
        soul.add(song14);
        soul.add(song17);
        soul.add(song18);
        songCategories.put("Soul music", soul);

        List<Song> indie = new ArrayList<>();
        indie.add(song6);
        indie.add(song7);
        songCategories.put("Indie", indie);

        List<Song> folk = new ArrayList<>();
        folk.add(song8);
        songCategories.put("Folk", folk);

        List<Song> sad = new ArrayList<>();
        sad.add(song9);
        sad.add(song12);
        sad.add(song27);
        sad.add(song28);
        sad.add(song30);
        songCategories.put("Sadcore", sad);

        List<Song> electro = new ArrayList<>();
        electro.add(song11);
        electro.add(song20);
        electro.add(song24);
        songCategories.put("Electronic", electro);

        List<Song> r = new ArrayList<>();
        r.add(song13);
        songCategories.put("R&B", r);

        List<Song> pop_rock = new ArrayList<>();
        pop_rock.add(song15);
        pop_rock.add(song16);
        pop_rock.add(song19);
        pop_rock.add(song29);
        songCategories.put("Pop rock", pop_rock);

        List<Song> alt = new ArrayList<>();
        alt.add(song21);
        alt.add(song25);
        songCategories.put("Alternative", alt);

        List<Song> punk = new ArrayList<>();
        punk.add(song23);
        songCategories.put("Punk", punk);


        List<Song> list1 = new ArrayList<>();
        list1.add(song3);
        list1.add(song5);
        list1.add(song8);
        Playlist play1 = new Playlist(1, "Chill music", "Study sounds", list1);

        List<Song> list2 = new ArrayList<>();
        list2.add(song1);
        list2.add(song2);
        list2.add(song3);
        Playlist play2 = new Playlist(2, "Rock", "Rockin'", list2);

        List<Song> list3 = new ArrayList<>();
        list3.add(song1);
        list3.add(song2);
        list3.add(song3);
        list3.add(song4);
        Playlist play3 = new Playlist(3, "80's'", "Oldies", list3);

        List<Song> list4 = new ArrayList<>();
        list4.add(song5);
        list4.add(song9);
        list4.add(song12);
        list4.add(song14);
        Playlist play4 = new Playlist(4, "Sad", "Feelings", list4);

        List<Song> list5 = new ArrayList<>();
        list5.add(song2);
        list5.add(song11);
        list5.add(song13);
        Playlist play5 = new Playlist(5, "House", "Hype", list5);

        playlists.add(play1);
        playlists.add(play2);
        playlists.add(play3);
        playlists.add(play4);
        playlists.add(play5);


        List<Playlist> chill = new ArrayList<>();
        chill.add(play1);

        List<Playlist> Rock = new ArrayList<>();
        Rock.add(play2);

        List<Playlist> old = new ArrayList<>();
        old.add(play3);

        List<Playlist> Sad = new ArrayList<>();
        Sad.add(play4);

        List<Playlist> house = new ArrayList<>();
        house.add(play5);

        List<Playlist> test = new ArrayList<>();
        test.add(play5);
        test.add(play2);

        playlistCategories.put("Chill music", chill);
        playlistCategories.put("Rock", Rock);
        playlistCategories.put("80's", old);
        playlistCategories.put("Sad", Sad);
        playlistCategories.put("House", house);
        playlistCategories.put("Test", test);

        List<Song> harrys =  new ArrayList<>();
        harrys.add(song15);
        harrys.add(song16);
        harrys.add(song17);
        harrys.add(song18);
        harrys.add(song19);
        harrys.add(song20);
        Album album1 = new Album(1, "Pop rock", "Harry's house", 2022, harry, harrys);

        List<Song> depeches =  new ArrayList<>();
        depeches.add(song21);
        depeches.add(song22);
        depeches.add(song23);
        depeches.add(song24);
        depeches.add(song25);
        Album album2 = new Album(2, "Alternative rock", "The Singles", 1998, depeche, depeches);

        List<Song> olivias =  new ArrayList<>();
        olivias.add(song26);
        olivias.add(song27);
        olivias.add(song28);
        olivias.add(song29);
        olivias.add(song30);
        Album album3 = new Album(3, "Sadcore", "Sour", 2021, olivia, olivias);

        List<Album> la1 = new ArrayList<>();
        la1.add(album1);
        albumCategories.put("Pop rock", la1);

        List<Album> la2 = new ArrayList<>();
        la2.add(album2);
        albumCategories.put("Alternative Rock", la2);

        List<Album> la3 = new ArrayList<>();
        la3.add(album3);
        albumCategories.put("Sadcore", la3);

        albums.add(album1);
        albums.add(album2);
        albums.add(album3);


        Episode ep1 = new Episode(1, "Psychology", 1, "Trepiedul vietii: creier, minte si relatii",
                "Viata nu este doar despre creier, ci si despre minte si relatii", "19:12", "Nov 2019");
        Episode ep2 = new Episode(2, "Psychology", 2, "Banii aduc fericirea?",
                "Ce inseamna banii pentru creierul nostru", "31:27", "Sep 2020");

        List<Episode> mind = new ArrayList<>();
        mind.add(ep1);
        mind.add(ep2);
        Podcast pod1 = new Podcast(1, "Psychology", "Mind architect", "Self knowledge and " +
                "personal development", mind );

        Episode ep3 = new Episode(3, "Books", 1, "Friedrich Nietzsche - Beyond good and evil",
                "Filosophy as form of life, suffering and sacrifice ", "53:00", "Feb 2021");
        Episode ep4 = new Episode(4, "Books", 2, "Fyodor Dostoyevsky - Crime and punishment",
                "The very first novel of Dostoyevsky's 'mature' period of writing", "60:00", "Sep 2021");
        List<Episode> lect = new ArrayList<>();
        lect.add(ep3);
        lect.add(ep4);
        Podcast pod2 = new Podcast(2, "Books", "Online lectures", "About reading" , lect );


        Episode ep5 = new Episode(4, "Science", 1, "Stephen Wolfram - Physics, ChatGPT & AI",
                "Wolfram lecture from Mindfest 2023", "60:05", "Mar 2023");
        Episode ep6 = new Episode(5, "Science", 2, "Non-dualism, conciousness, God and death",
                "Rupert Spira comments on these main topics", "240:49", "Mar 2023");

        List<Episode> sci = new ArrayList<>();
        sci.add(ep5);
        sci.add(ep6);
        Podcast pod3 = new Podcast(3, "Documentary", "The theory of everything", "Exploring" +
                "theoretical physics, conciousness and artificial intelligence" , sci );

        episodes.add(ep1);
        episodes.add(ep2);
        episodes.add(ep3);
        episodes.add(ep4);
        episodes.add(ep5);
        episodes.add(ep6);

        podcasts.add(pod1);
        podcasts.add(pod2);
        podcasts.add(pod3);

        List<Episode> le1 = new ArrayList<>();
        le1.add(ep1);
        le1.add(ep2);
        episodeCategories.put("Psychology", le1);

        List<Episode> le2 = new ArrayList<>();
        le2.add(ep3);
        le2.add(ep4);
        episodeCategories.put("Books", le2);

        List<Episode> le3 = new ArrayList<>();
        le3.add(ep5);
        le3.add(ep6);
        episodeCategories.put("Science", le3);

        List<Podcast> lp1 = new ArrayList<>();
        lp1.add(pod1);
        podcastCategories.put("Psychology", lp1);

        List<Podcast> lp2 = new ArrayList<>();
        lp2.add(pod2);
        podcastCategories.put("Books", lp2);

        List<Podcast> lp3 = new ArrayList<>();
        lp3.add(pod3);
        podcastCategories.put("Documentary", lp3);



    }

    public void showSong() {
        System.out.println("-----------Show songs----------");
        if (songs.isEmpty())
        {System.out.println("There are no available songs. Visit the app and add a song.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here are the app's songs: ");
            int i = 1;
            for (Song song : songs) {
                System.out.println(i + " " + song.getName() + " - " + song.getArtist().getName());
                i++;
            }
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
    public void showAlbum()
    {
        System.out.println("-----------Show albums----------");
        if(playlists.isEmpty()) {
            System.out.println("There are no albums to show. Visit the app and add an album.");
            System.out.println("----------------------------------");
        }
        else {
            System.out.println("Here is a list of all the albums:");
            int i = 1;
            for(Album album:albums)
            {
                System.out.println(i + " " + album.getName());
                i++;
            }
            System.out.println("----------------------------------");
        }

    }


    public void showTypes()
    {
        System.out.println("-----------Show media----------");
        System.out.println("What are you searching for?");
        System.out.println("1 - Songs");
        System.out.println("2 - Playlists");
        System.out.println("3 - Episodes");
        System.out.println("4 - Podcasts");
        System.out.println("5 - Albums");
        System.out.println("6 - Categories");

        Scanner var = new Scanner(System.in);

        int option = var.nextInt();

        while(true)
        {
            if(option == 1)
            {
                this.showSong();
                break;
            }

            else if(option == 2)
            {
                this.showPlaylist();
                break;
            }

            else if(option == 3)
            {
                this.showEpisode();
                break;
            }

            else if(option == 4)
            {
                this.showPodcast();
                break;
            }

            else if(option == 5)
            {
                this.showAlbum();
                break;
            }

            else if(option == 6)
            {
                this.showByCategory();
                break;
            }

            else break;
        }


    }

    public void playEpisode()
    {
        System.out.println("-----------Play episode----------");
        if (episodes.isEmpty())
        {System.out.println("There are no available episodes. Visit the app and add an episode.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Choose the index of the episode you want to play");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex <= 0 || chooseIndex >= episodes.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the episode you want to play");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            System.out.println(episodes.get(chooseIndex - 1));
            System.out.println();
            System.out.println("Enjoy the episode!");
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
    }

    public void playPodcast()
    {
        System.out.println("-----------Play podcast----------");
        if (podcasts.isEmpty())
        {System.out.println("There are no available podcasts. Visit the app and add a podcast.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Choose the index of the podcast you want to play");
            Scanner var = new Scanner(System.in);
            int chooseIndex = Integer.parseInt(var.nextLine());
            while (chooseIndex <= 0 || chooseIndex >= podcasts.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the podcast you want to play");
                chooseIndex = Integer.parseInt(var.nextLine());
            }

            System.out.println(podcasts.get(chooseIndex - 1));
            System.out.println();
            System.out.println("Enjoy the podcast!");
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
    }


    public void addEpisode() {
        System.out.println("-----------Add an episode----------");

        Episode episode = new Episode();
        episode.read();

        episodes.add(episode);

        String categ = episode.getCategory();
        boolean found = false;

        for(String c : episodeCategories.keySet())
        {
            if (categ.toLowerCase() == c.toLowerCase())
            {
                List<Episode> epCateg = episodeCategories.get(c);
                epCateg.add(episode);
                episodeCategories.put(c, epCateg);
                found = true;
                break;
            }
        }

        if(found == false)
        {
            episodeCategories.put(categ, List.of(episode));
        }

        System.out.println("Episode added succesfully!");
        System.out.println("----------------------------------");
    }

    public void showEpisode() {

        System.out.println("-----------Show episodes----------");
        if (episodes.isEmpty())
        {System.out.println("There are no available episodes. Visit the app and add an episode.");
            System.out.println("----------------------------------");}
        else {
            int i = 1;
            System.out.println("Here are the app's episodes: ");
            for (Episode episode : episodes) {
                System.out.println(i + " " + episode.getName());
                i++;
            }
            System.out.println("----------------------------------");
        }
    }

    public void showAnEpisode()
    {
        System.out.println("-----------Show episode details----------");
        if(episodes.isEmpty()) System.out.println("There are no episodes to show. Add an episode and try again.");

        else{
            System.out.println("Type the index of the episode you want to get more details on: ");
            Scanner var = new Scanner(System.in);

            int ind = Integer.parseInt(var.nextLine());

            while(ind <=0 || ind >= episodes.size()+1)
            {
                System.out.println("Incorrect index. Please try again.");
                System.out.println("Type the index of the episode you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            Episode reqEp = episodes.get(ind - 1);
            System.out.println(reqEp);
        }

        System.out.println("----------------------------------");

    }

    public void deleteEpisode() {
        System.out.println("-----------Delete an episode----------");
        if (songs.isEmpty()) {
            System.out.println("You cannot delete an episode as there are none available. Visit the app and add an episode.");
            System.out.println("----------------------------------");
        }
        else {
            int i = 1;
            for (Episode episode : episodes) {
                System.out.println(i + ". " + episode.getName());
                i++;
            }

            System.out.println("Choose the index of the episode that you want to remove: ");
            Scanner var = new Scanner(System.in);
            int indexChoose = Integer.parseInt(var.nextLine());
            while (indexChoose <= 0 || indexChoose > episodes.size() + 1) {
                System.out.println("Invalid index. Retry.");
                System.out.println("Choose the index of the episode that you want to remove: ");
                indexChoose = Integer.parseInt(var.nextLine());
            }
            String categ = episodes.get(indexChoose - 1).getCategory();
            List<Episode> epCateg = episodeCategories.get(categ);

            for(Episode episode: epCateg)
            {
                if(episode == episodes.get(indexChoose - 1))
                {
                    epCateg.remove(episode);
                    episodeCategories.put(categ,epCateg);
                    break;
                }
            }
            episodes.remove(indexChoose - 1);
            System.out.println("Episode was removed succesfully!");
            System.out.println("----------------------------------");

        }

        }

        public void addPodcast()
        {
            System.out.println("-----------Add a podcast----------");
            Podcast podcast = new Podcast();
            podcast.read();
            podcasts.add(podcast);

            String categ = podcast.getCategory();
            boolean found = false;

            for(String c : podcastCategories.keySet())
            {
                if (c.toLowerCase() == categ.toLowerCase())
                {
                    List<Podcast> podCateg = podcastCategories.get(c);
                    podCateg.add(podcast);
                    podcastCategories.put(c, podCateg);
                    found = true;
                    break;
                }
            }

            if(found == false)
            {
                podcastCategories.put(categ, List.of(podcast));
            }

            System.out.println("Podcast added succesfully!");
            System.out.println("----------------------------------");
        }

        public void showPodcast()
        {
            System.out.println("-----------Show podcasts----------");
            if(podcasts.isEmpty()) {
                System.out.println("There are no podcasts to show. Visit the app and add a podcast.");
                System.out.println("----------------------------------");
            }
            else{
                System.out.println("Here is a list of all the app's podcasts: ");
                int i = 1;
                for(Podcast podcast:podcasts)
                {
                    System.out.println( i + " " + podcast.getName());
                    i++;
                }
                System.out.println("----------------------------------");
            }

        }

        public void showAPodcast()
        {
            System.out.println("-----------Show podcast details----------");
            if(podcasts.isEmpty()) System.out.println("There are no podcasts to show. Add a podcast and try again.");
            else
            {
                System.out.println("Type the index of the podcast you want to get more details on: ");
                Scanner var = new Scanner(System.in);

                int ind = Integer.parseInt(var.nextLine());

                while(ind<=0 || ind>=podcasts.size()+1)
                {
                    System.out.println("The index you provided does not exist. Please try again.");
                    System.out.println("Type the index of the podcast you want to get more details on: ");
                    ind = Integer.parseInt(var.nextLine());
                }

                Podcast reqPod = podcasts.get(ind - 1);
                System.out.println(reqPod);
                System.out.println("Do you want to add an episode to this podcast? yes/no");
                String option = var.nextLine();
                if(Objects.equals(option, "yes"))
                {
                    addEpisodeToPodcast(reqPod);
                }
            }

            System.out.println("----------------------------------");
        }

        public void addEpisodeToPodcast(Podcast podcast)
        {
            System.out.println("-----------Add episode to podcast----------");
            showEpisode();
            System.out.println("Choose the index of the episode that you want to add to your podcast: ");
            Scanner var = new Scanner(System.in);
            int ind = Integer.parseInt(var.nextLine());
            List<Episode> podcastEpisodes = podcast.getEpisodes();
            while(podcastEpisodes.contains(episodes.get(ind - 1)))
            {
                System.out.println("This episode already exists in the podcast! Please try again.");
                System.out.println("Choose the index of the episode that you want to add to your podcast: ");
                ind = Integer.parseInt(var.nextLine());
            }

            podcastEpisodes.add(episodes.get(ind - 1));

            System.out.println("Episode added to the podcast succesfully!");
            System.out.println("----------------------------------");
        }

        public void deletePodcast()
        {
            System.out.println("-----------Delete a podcast----------");
            if (podcasts.isEmpty()) {
                System.out.println("You cannot delete a podcast as there are none available. Visit the app and add a podcast.");
                System.out.println("----------------------------------");
            }
            else{
                int i = 1;
                for(Podcast podcast:podcasts)
                {
                    System.out.println(i + ". " + podcast.getName());
                    i++;
                }

                System.out.println("Choose the index of the podcast that you want to delete: ");
                Scanner var = new Scanner(System.in);
                int indexChoice = Integer.parseInt(var.nextLine());

                while(indexChoice <= 0 || indexChoice >= podcasts.size() + 1)
                {
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("Choose the index of the podcast that you want to delete: ");
                    indexChoice = Integer.parseInt(var.nextLine());
                }

                String categ = podcasts.get(indexChoice - 1).getCategory();
                List<Podcast> podCateg = podcastCategories.get(categ);

                if(podCateg!=null)
                {
                    for(Podcast p: podCateg)
                    {
                        if(p == podcasts.get(indexChoice - 1))
                        {
                            podCateg.remove(p);
                            podcastCategories.put(categ,podCateg);
                            break;
                        }
                    }
                }



                podcasts.remove(indexChoice-1);
                System.out.println("Podcast removed succesfully!");
                System.out.println("----------------------------------");

            }
        }
    public void showByCategory()
    {
        System.out.println("Firstly, what media category are you searching for?");
        System.out.println("1 - Songs");
        System.out.println("2 - Playlists");
        System.out.println("3 - Podcasts");
        System.out.println("4 - Episodes");
        System.out.println("5 - All media");

        Scanner var = new Scanner(System.in);

        while(true)
        {
            int option = Integer.parseInt(var.nextLine());

            if(option == 1){
                System.out.println("-----------Song categories----------");
                for(String c:songCategories.keySet())
                {
                    System.out.println(" " + c);
                }

                System.out.println("Write the category you are looking for (Please write the category with uppercase first): ");
                String op2 = var.nextLine();
                List<Song> songCateg = songCategories.get(op2);

                if(songCateg != null)
                {
                    for(Song song:songCateg)
                    {
                        System.out.println(song.getName() + " - " + song.getArtist().getName());
                    }
                }
                break;

            }

            else if(option == 2){
                System.out.println("-----------Playlist categories----------");
                for(String c:playlistCategories.keySet())
                {
                    System.out.println(" " + c);
                }

                System.out.println("Write the category you are looking for (Please write the category with uppercase first - ex.Pop): ");
                String op2 = var.nextLine();
                List<Playlist> playCateg = playlistCategories.get(op2);

                if(playCateg != null)
                {
                    for(Playlist playlist:playCateg)
                    {
                        System.out.println(playlist.getName());
                    }
                }
                break;
            }

            else if(option == 3){
                System.out.println("-----------Podcast categories----------");
                for(String c:podcastCategories.keySet())
                {
                    System.out.println(" " + c);
                }

                System.out.println("Write the category you are looking for (Please write the category with uppercase first): ");
                String op2 = var.nextLine();
                List<Podcast> podCateg = podcastCategories.get(op2);

                if(podCateg != null)
                {
                    for(Podcast podcast:podCateg)
                    {
                        System.out.println(podcast.getName());
                    }
                }

                break;

            }

            else if(option == 4){
                System.out.println("-----------Episode categories----------");
                for(String c:episodeCategories.keySet())
                {
                    System.out.println(" " + c);
                }

                System.out.println("Write the category you are looking for (Please write the category with uppercase first): ");
                String op2 = var.nextLine();
                List<Episode> epCateg = episodeCategories.get(op2);

                if(epCateg != null)
                {
                    for(Episode episode:epCateg)
                    {
                        System.out.println(episode.getName());
                    }
                }

                break;
            }

            else if(option == 5){
                System.out.println("-----------All categories----------");
                for(String c:songCategories.keySet())
                {
                    categories.add(c);
                }
                for(String c:playlistCategories.keySet())
                {
                    categories.add(c);
                }
                for(String c:podcastCategories.keySet())
                {
                    categories.add(c);
                }
                for(String c:episodeCategories.keySet())
                {
                    categories.add(c);
                }

                for(String c: categories)
                {
                    System.out.println("\uF0B0\t " + c);
                }


                System.out.println("Write the category you are looking for (Please write the category with uppercase first): ");
                String op2 = var.nextLine();
                List<Song> songCateg = songCategories.get(op2);
                List<Playlist> playCateg = playlistCategories.get(op2);
                List<Podcast> podCateg = podcastCategories.get(op2);
                List<Episode> epCateg = episodeCategories.get(op2);

                if(songCateg != null)
                {
                    System.out.println("Songs: ");
                    for(Song song:songCateg)
                    {
                        System.out.println(song.getName() + " - " + song.getArtist().getName());
                    }
                }

                if(playCateg != null)
                {
                    System.out.println("Playlists: ");
                    for(Playlist playlist:playCateg)
                    {
                        System.out.println(playlist.getName());
                    }
                }

                if(podCateg != null)
                {
                    System.out.println("Podcasts: ");
                    for(Podcast podcast:podCateg)
                    {
                        System.out.println(podcast.getName());
                    }
                }

                if(epCateg != null)
                {
                    System.out.println("Episodes: ");
                    for(Episode episode:epCateg)
                    {
                        System.out.println(episode.getName());
                    }
                }

                break;

            }

            else break;
        }


    }


    }

