package service;

import java.util.*;

public class Application {
    private static Application instance = null;

    private void typeOfUser()
    {
        System.out.println("Choose the type of user:");
        System.out.println("1 - Admin");
        System.out.println("2 - User");
    }

    private void actionOption()
    {
        System.out.println("Choose one of the following actions");
        System.out.println("1 - Visit app");
        System.out.println("2 - Visit shop");
        System.out.println("0 - Exit program");
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

    private void actions(ApplicationService service, ShopService shopService)
    {
        Scanner var=new Scanner(System.in);
        service.addElements();
        shopService.addElements();

        //this.typeOfUser();
        //int ch = var.nextInt();

         while(true)
            {
                this.actionOption();
                int choice = var.nextInt();

                if(choice == 1) {
                    System.out.println("------------Welcome to our app!--------------");
                    while(true)
                    {
                        this.applicationServiceOption();
                        int appChoice = var.nextInt();
                        if(appChoice == 1) {
                            service.addSong();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 2) {
                            service.showSong();
                            service.playSong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 3) {
                            service.showSong();
                            service.showASong();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 4) {
                            service.deleteSong();
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

//                        else if(appChoice == 11) {
//                            service.showPodcast();
//                            System.out.println("Do you need more information about one of the podcasts? yes/no");
//                            Scanner var1 = new Scanner(System.in);
//                            String option = var1.nextLine();
//
//                            if(Objects.equals(option, "yes")) {
//                                service.showAPodcast();
//
//                            }
//                            else continue;
//                            System.out.println("------------------------------------");
//
//                        }

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
                            service.addPlaylist();
                            System.out.println("------------------------------------");

                        }
                        else if(appChoice == 14) {
                            service.showPlaylist();
                            service.playPlaylist();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 15) {
                            service.showPlaylist();
                            service.showAPlaylist();
                            System.out.println("------------------------------------");

                        }


                        else if(appChoice == 16) {
                            service.deletePlaylist();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 17) {
                            service.addAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 18) {
                            service.playAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 19) {
                            service.showAlbum();
                            service.showAnAlbum();
                            System.out.println("------------------------------------");

                        }

//                        else if(appChoice == 19) {
//                            service.showAlbum();
//                            System.out.println("Do you need more information about one of the albums? yes/no");
//                            Scanner var1 = new Scanner(System.in);
//                            String option = var1.nextLine();
//
//                            if(Objects.equals(option, "yes")) {
//                                service.showAnAlbum();
//
//                            }
//                            else continue;
//                            System.out.println("------------------------------------");
//
//                        }

                        else if(appChoice == 20) {
                            service.deleteAlbum();
                            System.out.println("------------------------------------");

                        }

                        else if(appChoice == 21) {
                            service.showTypes();
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




    private Application(){}

    public static synchronized Application getInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }

    public void start()
    {
        ApplicationService service = ApplicationService.getInstance();
        ShopService shopService = ShopService.getInstance();
        this.actions(service, shopService);


    }


}
