package service;

import java.util.*;

import model.shop.Product;
import model.typeofmusic.Artist;
import model.shop.CD;
import model.shop.Vinyl;
import model.typeofmusic.Album;
import model.typeofmusic.Song;

public class ShopService {

    private static ShopService instance = null;

    private List<CD> CDs = new ArrayList<>();

    private List<Vinyl> vinyls = new ArrayList<>();

    private List<Product> products = new ArrayList<>();
    public static synchronized ShopService getInstance() {
        if (instance == null)
            instance = new ShopService();
        return instance;
    }

    private ShopService () {}

    public void addElements()
    {

        Artist harry = new Artist(14, "Harry Styles");
        Artist depeche = new Artist(15, "Depeche Mode");
        Artist olivia = new Artist(16, "Olivia Rodrigo");

        Song song15 = new Song(15, "Pop rock", "Music for a sushi restaurant", harry, "3:50");
        Song song16 = new Song(16, "Pop rock", "As it was", harry, "3:20");
        Song song17 = new Song(17, "Soul music", "Love of my life", harry, "3:30");
        Song song18 = new Song(18, "Soul music", "Matilda", harry, "3:50");
        Song song19 = new Song(19, "Pop rock", "Satellite", harry, "3:10");
        Song song20 = new Song(20, "Electropop", "Daydreaming", harry, "3:10");
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

        List<Song> harrys =  new ArrayList<>(Arrays.asList(song15, song16, song17, song18, song19, song20));
        Album album1 = new Album(1, "Pop rock", "Harry's house", 2022, harry, harrys);

        List<Song> depeches =  new ArrayList<>(Arrays.asList(song21, song22, song23, song24, song25));
        Album album2 = new Album(2, "Alternative rock", "The Singles", 1998, depeche, depeches);

        List<Song> olivias =  new ArrayList<>(Arrays.asList(song26, song27, song28, song29, song30));
        Album album3 = new Album(3, "Sadcore", "Sour", 2021, olivia, olivias);

        CD cd1 = new CD(1, 12,20,album3);
        CD cd2 = new CD(2, 15,25,album2);
        CD cd3 = new CD(3, 10,30,album1);

        CDs.addAll(Arrays.asList(cd1,cd2,cd3));

        Vinyl vin1 = new Vinyl(1,25,25,"Old + modern Rock", Arrays.asList(album1,album2));
        Vinyl vin2 = new Vinyl(2,23, 20,"New pop hits", Arrays.asList(album1, album3));

        vinyls.addAll(Arrays.asList(vin1,vin2));


    }

    public void buyCD()
    {
        System.out.println("-----------Buy a CD----------");
        if (CDs.isEmpty())
        {System.out.println("Unfortunately, there are no CDs available. Please come back later!");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the CDs: ");
            int i = 1;

            for(CD cd: CDs)
            {
                System.out.println(i + ". " + cd.getAlbum().getName() + " - "  + cd.getPrice() + " euro");
                System.out.println(cd.getNoInStock() + " in stock ");
                i++;
            }

            System.out.println("Choose the index of the CD that you want to buy: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice < 0 || indexChoice > CDs.size() + 1)
            {
                System.out.println("Invalid index. Try again.");
                System.out.println("Choose the index of the CD that you want to buy: ");
                indexChoice = Integer.parseInt(var.nextLine());

            }

            int number = CDs.get(indexChoice - 1).getNoInStock();
            CDs.get(indexChoice - 1).setNoInStock(number - 1);

            System.out.println("Your order has been processed! Thank you for choosing us!");
            System.out.println("----------------------------------");

        }
    }

    public void buyVinyl()
    {
        System.out.println("-----------Buy a Vinyl----------");
        if (vinyls.isEmpty())
        {System.out.println("Unfortunately, there are no vinyls available. Please come back later!");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the vinyls: ");
            int i = 1;

            for(Vinyl vinyl: vinyls)
            {
                System.out.println(i + ". " + vinyl.getName() + " - " + vinyl.getPrice() + " euro" );
                System.out.println(vinyl.getNoInStock() + " in stock ");
                i++;
            }

            System.out.println("Choose the index of the vinyl that you want to buy: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice < 0 || indexChoice > vinyls.size() + 1)
            {
                System.out.println("Invalid index. Try again.");
                System.out.println("Choose the index of the vinyl that you want to buy: ");
                indexChoice = Integer.parseInt(var.nextLine());

            }

            int number = vinyls.get(indexChoice - 1).getNoInStock();
            vinyls.get(indexChoice - 1).setNoInStock(number - 1);

            System.out.println("Your order has been processed! Thank you for choosing us!");
            System.out.println("----------------------------------");

        }
    }

    public void addCD()
    {
        System.out.println("-----------Add a CD----------");
        CD cd = new CD();
        cd.read();
        CDs.add(cd);

        System.out.println("CD added succesfully!");
        System.out.println("----------------------------------");

    }

    public void showCD()
    {
        System.out.println("-----------Show CDs----------");
        if (CDs.isEmpty())
        {System.out.println("There are no available CDs. Visit the shop and add a CD.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the CDs: ");
            int i = 1;
            for(CD cd:CDs)
            {
                System.out.println(i + " " + cd.getAlbum().getName());
                i++;
            }
            System.out.println("----------------------------------");
        }
    }

    public void showACD()
    {
        System.out.println("-----------Show CD details----------");
        if(CDs.isEmpty()) System.out.println("There are no CDs to show. Please add a CD and then try again.");
        else{
            System.out.println("Type the index of the CD you want to get more details on: ");
            Scanner var = new Scanner(System.in);

            int ind = Integer.parseInt(var.nextLine());

            while(!CDs.contains(CDs.get(ind - 1)))
            {
                System.out.println("The provided index doesn't exist. Please try again.");
                System.out.println("Type the index of the CD you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            CD reqCD = CDs.get(ind - 1);
            System.out.println(reqCD);

            System.out.println("----------------------------------");
        }

    }

    public void deleteCD()
    {
        System.out.println("-----------Delete a CD----------");
        if (CDs.isEmpty())
        {System.out.println("You cannot delete a CD as there are none available. Visit the shop and add a CD.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the CDs: ");
            int i = 1;

            for(CD cd: CDs)
            {
                System.out.println(i + ". " + cd.getAlbum().getName());
                i++;
            }

            System.out.println("Choose the index of the CD that you want to delete: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice < 0 || indexChoice > CDs.size() + 1)
            {
                System.out.println("Invalid index. Try again.");
                System.out.println("Choose the index of the CD that you want to delete: ");
                indexChoice = Integer.parseInt(var.nextLine());

            }

            CDs.remove(indexChoice - 1);
            System.out.println("CD removed succesfully!");
            System.out.println("----------------------------------");

        }
    }

    public void addVinyl()
    {
        System.out.println("-----------Add a vinyl----------");

        Vinyl vinyl = new Vinyl();
        vinyl.read();
        vinyls.add(vinyl);

        System.out.println("Vinyl added succesfully!");
        System.out.println("----------------------------------");
    }

    public void showVinyls()
    {
        System.out.println("-----------Show vinyls----------");
        if (vinyls.isEmpty())
        {System.out.println("There are no available vinyls. Visit the shop and add a vinyl.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the vinyls: ");

            for(Vinyl vinyl:vinyls)
            {
                System.out.println("\uF0F8  " + vinyl.toString());
            }
            System.out.println("----------------------------------");
        }
    }

    public void showAVinyl()
    {
        System.out.println("-----------Show vinyl details----------");
        if(vinyls.isEmpty()) System.out.println("There are no vinyls to show. Add a vinyl and try again");
        else
        {
            System.out.println("Type the index of the vinyl that you want to get more details on: ");
            Scanner var = new Scanner(System.in);
            int ind = Integer.parseInt(var.nextLine());

            while(ind <0 || ind >= vinyls.size())
            {
                System.out.println("Incorrect index. Please try again.");
                System.out.println("Type the index of the vinyl that you want to get more details on: ");
                ind = Integer.parseInt(var.nextLine());
            }

            Vinyl vin = vinyls.get(ind - 1);
            System.out.println(vin);
        }
        System.out.println("----------------------------------");
    }

    public void deleteVinyl()
    {
        System.out.println("-----------Delete a vinyl----------");
        if (vinyls.isEmpty())
        {System.out.println("You cannot delete a vinyl as there are none available. Visit the shop and add a vinyl.");
            System.out.println("----------------------------------"); }
        else {
            System.out.println("Here is a list of all the vinyls: ");
            int i = 1;

            for(Vinyl vinyl: vinyls)
            {
                System.out.println(i + ". " + vinyl.getName());
                i++;
            }

            System.out.println("Choose the index of the vinyl that you want to delete: ");

            Scanner var = new Scanner(System.in);
            int indexChoice = Integer.parseInt(var.nextLine());

            while(indexChoice <= 0 || indexChoice > vinyls.size() + 1)
            {
                System.out.println("Invalid index. Try again.");
                System.out.println("Choose the index of the vinyl that you want to delete: ");
                indexChoice = Integer.parseInt(var.nextLine());
            }

            CDs.remove(indexChoice - 1);
            System.out.println("Vinyl removed succesfully!");
            System.out.println("----------------------------------");

        }
    }

    public void sortByPrice()
    {
        for(CD cd:CDs)
        {
            products.add(cd);
        }

        for(Vinyl vinyl:vinyls)
        {
            products.add(vinyl);
        }

        Collections.sort(products);


        for (Product p:products)
        {
            if(p instanceof CD)
            {
                System.out.println("CD - " + ((CD) p).getAlbum().getName() + " - " + p.getPrice() + " euro");
            }

            else if(p instanceof Vinyl)
            {
                System.out.println("Vinyl - " + ((Vinyl) p).getName() + " - " + p.getPrice() + " euro");
            }
        }
    }

}
