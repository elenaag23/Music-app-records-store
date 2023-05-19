package service;

import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.util.*;

public class Application {
    private static Application instance = null;

    private UserRepository userRepository;


    private void typeOfUser()
    {
        System.out.println("Choose the type of registration:");
        System.out.println("1 - Log in");
        System.out.println("2 - Sign up");
        System.out.println("0 - Exit program");

    }

    private void actionOption()
    {
        System.out.println("Choose one of the following actions");
        System.out.println("1 - Visit app");
        System.out.println("2 - Visit shop");
        System.out.println("0 - Log out");
        System.out.println("------------------------------------");

    }

    private void applicationServiceOption()
    {
        System.out.println("Choose one of the following actions:");
        System.out.println();
        System.out.println("1 - Add a song");
        System.out.println("2 - Play a song");
        System.out.println("3 - See details of a song");
        System.out.println("4 - Delete a song");
        System.out.println("-------------------------");
        System.out.println("5 - Add an episode");
        System.out.println("6 - Play an episode");
        System.out.println("7 - Show details of an episode");
        System.out.println("8 - Delete an episode");
        System.out.println("-------------------------");
        System.out.println("9 - Add a podcast");
        System.out.println("10 - Play a podcast");
        System.out.println("11 - See details of a podcast");
        System.out.println("12 - Delete a podcast");
        System.out.println("-------------------------");
        System.out.println("13 - Add a playlist");
        System.out.println("14 - Play a playlist");
        System.out.println("15 - See details of a playlist");
        System.out.println("16 - Delete a playlist");
        System.out.println("-------------------------");
        System.out.println("17 - Add an album");
        System.out.println("18 - Play an album");
        System.out.println("19 - See details of an album");
        System.out.println("20 - Delete an album");
        System.out.println("-------------------------");
        System.out.println("21 - Show media");
        System.out.println("-------------------------");
        System.out.println("Database actions");
        System.out.println("22 - Add an artist");
        System.out.println("23 - See artists");
        System.out.println("24 - Edit artist");
        System.out.println("25 - See an artist");
        System.out.println("26 - Remove an artist");
        System.out.println("27 - Add song to db");
        System.out.println("28 - See them songs");
        System.out.println("29 - Edit song");
        System.out.println("30 - Remove song");
        System.out.println("31 - Play a song");
        System.out.println("32 - Add album to db");
        System.out.println("33 - See them albums");
        System.out.println("34 - Edit album");
        System.out.println("35 - Play an album");
        System.out.println("36 - Remove album");

        System.out.println("37 - Add playlist to db");
        System.out.println("38 - See them playlists");
        System.out.println("39 - Edit playlist");
        System.out.println("40 - Play a playlist");
        System.out.println("41 - Remove playlist");

        System.out.println("-------------------------");
        System.out.println("0 - Go to main menu");
        System.out.println("------------------------------------");

    }

    private void shopServiceOption()
    {
        System.out.println("Choose one of the following actions: ");
        System.out.println();
        System.out.println("1 - Add a CD");
        System.out.println("2 - See details of CD");
        System.out.println("3 - Show the CD list");
        System.out.println("4 - Buy a CD");
        System.out.println("5 - Delete a CD");
        System.out.println("-------------------------");
        System.out.println("6 - Add a vinyl");
        System.out.println("7 - See details of vinyl");
        System.out.println("8 - Show the vinyl list");
        System.out.println("9 - Buy a vinyl");
        System.out.println("10 - Delete a vinyl");
        System.out.println("-------------------------");
        System.out.println("11 - Sort products by price");
        System.out.println("-------------------------");
        System.out.println("0 - Go to main menu");
        System.out.println("------------------------------------");
    }

    private void actions(ApplicationService service, ShopService shopService, ArtistService artistService, SongService songService,
                         AlbumService albumService, PlaylistService playlistService
                         ) throws IOException{
        Scanner var = new Scanner(System.in);
        service.addElements();
        shopService.addElements();

        while (true) {
                this.typeOfUser();
                int login = var.nextInt();
                User user;
                int userId;

            if(login == 1)
            {
                System.out.println("Type your username:");
                Scanner data = new Scanner(System.in);
                String provUsername = data.nextLine();
                System.out.println("Your password: ");
                String provPassword = data.nextLine();

                user = userRepository.getUser(provUsername, provPassword);

            }

            else if(login==2)
            {
                user = userRepository.addUser();
            }

            else if(login == 0) {
                System.out.println("Thank you for your visit! See you soon!");break;}
            else {System.out.println("Invalid option. Try again."); break;}


                this.actionOption();
                int choice = var.nextInt();

                if(choice == 1) {
                    User currentUser = userRepository.getUser(user.getUsername(), user.getPassword());

                    System.out.println("------------Welcome to our app!--------------");
                    while(true)
                    {
                        this.applicationServiceOption();
                        int appChoice = var.nextInt();
                        if(appChoice == 1) {
                            songService.addSong();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 2) {
                            songService.showSong();
                            songService.playSong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 3) {
                            songService.showSong();
                            songService.showASong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 4) {
                            songService.deleteSong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 5) {
                            service.addEpisode();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 6) {
                            service.showEpisode();
                            service.playEpisode();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 7) {
                            service.showEpisode();
                            service.showAnEpisode();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 8) {
                            service.deleteEpisode();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 9) {
                            service.addPodcast();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 10) {
                            service.showPodcast();
                            service.playPodcast();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 11) {
                            service.showPodcast();
                            service.showAPodcast();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 12) {
                            service.deletePodcast();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 13) {
                            playlistService.addPlaylist();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 14) {
                            playlistService.showPlaylist();
                            playlistService.playPlaylist();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 15) {
                            playlistService.showPlaylist();
                            playlistService.showAPlaylist();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 16) {
                            playlistService.deletePlaylist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 17) {
                            albumService.addAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 18) {
                            albumService.playAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 19) {
                            albumService.showAlbum();
                            albumService.showAnAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 20) {
                            albumService.deleteAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 21) {
                            service.showTypes();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 22) {
                            artistService.addArtist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 23) {
                            artistService.getDataArtist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 24) {
                            artistService.editArtist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 25) {
                            artistService.getArtist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 26) {
                            artistService.removeArtist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 27) {
                            songService.addDBSong(artistService);
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 28) {
                            songService.getSongs();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 29) {
                            songService.editSong(artistService);
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 30) {
                            songService.removeSong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 31) {
                            songService.getSong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 32){
                            albumService.addAlbumArtist(artistService);
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 33) {
                            albumService.getAlbums();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 34) {
                            albumService.editAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 35) {
                            albumService.getAlbum();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 36) {
                            albumService.removeAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 37) {
                            playlistService.addPlaylistSongs(currentUser);
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 38) {
                            playlistService.getPlaylists();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 39) {
                            playlistService.editPlaylist(currentUser.getId());
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 40) {
                            playlistService.getPlaylist();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 41) {
                            playlistService.removePlaylist(user.getId());
                            System.out.println("------------------------------------");

                        }


                        else if (appChoice == 0) {
                            System.out.println("------------------------------------");
                            break;
                        }
                    }
                }
                else if(choice == 2) {
                    System.out.println("---------------Welcome to the shop!-------------");
                    while(true)
                    {
                        this.shopServiceOption();
                        int shopChoice = var.nextInt();

                        if(shopChoice == 1)
                        {
                            shopService.addCD();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 2)
                        {
                            shopService.showCD();
                            shopService.showACD();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 3)
                        {
                            shopService.showCD();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 4)
                        {
                            shopService.buyCD();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 5)
                        {
                            shopService.deleteCD();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 6)
                        {
                            shopService.addVinyl();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 7)
                        {
                            shopService.showVinyls();
                            shopService.showAVinyl();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 8)
                        {
                            shopService.showVinyls();
                            System.out.println("------------------------------------");

                        }
                        else if(shopChoice == 9)
                        {
                            shopService.buyVinyl();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 10)
                        {
                            shopService.deleteVinyl();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 11)
                        {
                            shopService.sortByPrice();
                            System.out.println("------------------------------------");

                        }

                        else if(shopChoice == 0)
                        {
                            System.out.println("------------------------------------");
                            break;

                        }
                    }

                }

                else if(choice==0) {
                    System.out.println("---We hope to see you again soon!---");
                    System.out.println("------------------------------------");
                    break;
                }

            }


    }

    private Application(){this.userRepository = new UserRepository();}

    public static synchronized Application getInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }

    public void start() throws IOException
    {
        ApplicationService service = ApplicationService.getInstance();
        ShopService shopService = ShopService.getInstance();
        ArtistService artistService = ArtistService.getInstance();
        SongService songService = SongService.getInstance();
        AlbumService albumService = AlbumService.getInstance();
        PlaylistService playlistService = PlaylistService.getInstance();
        this.actions(service, shopService, artistService, songService, albumService, playlistService);


    }


}
