package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": viewStore(); break;
                case "2": updateStore(); break;
                case "3": cartMenu(); break;
                case "0": System.out.println("Exiting AIMS..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        cart.printCart();
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            String option = scanner.nextLine();
            switch (option) {
                case "1": filterCart(); break;
                case "2": sortCart(); break;
                case "3": removeMediaFromCart(); break;
                case "4": playMediaFromCart(); break;
                case "5": System.out.println("An order is created."); cart.getItemsOrdered().clear(); return;
                case "0": return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    public static void viewStore() {
        store.printStore();
        while (true) {
            storeMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1": seeMediaDetails(); break;
                case "2": addMediaToCart(); break;
                case "3": playMedia(); break;
                case "4": cart.printCart(); break;
                case "0": return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    public static void updateStore() {
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        String option = scanner.nextLine();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        if (option.equals("1")) {
            store.addMedia(new DigitalVideoDisc(title));
        } else if (option.equals("2")) {
            hust.soict.hedspi.aims.media.Media media = store.searchByTitle(title);
            if (media != null) store.removeMedia(media);
            else System.out.println("Media not found.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void seeMediaDetails() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        hust.soict.hedspi.aims.media.Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media);
            while (true) {
                mediaDetailsMenu();
                String option = scanner.nextLine();
                switch (option) {
                    case "1": cart.addMedia(media); break;
                    case "2": if (media instanceof Playable) ((Playable) media).play(); else System.out.println("Cannot play this media."); break;
                    case "0": return;
                    default: System.out.println("Invalid option.");
                }
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void addMediaToCart() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) cart.addMedia(media);
        else System.out.println("Media not found.");
    }

    public static void playMedia() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media instanceof Playable) ((Playable) media).play();
        else System.out.println("Cannot play this media.");
    }

    public static void removeMediaFromCart() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) cart.removeMedia(media);
        else System.out.println("Media not found.");
    }

    public static void playMediaFromCart() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        for (Media media : cart.getItemsOrdered()) {
            if (media.getTitle().equalsIgnoreCase(title) && media instanceof Playable) {
                ((Playable) media).play();
                return;
            }
        }
        System.out.println("Cannot find playable media in cart with that title.");
    }

    public static void filterCart() {
        System.out.println("1. Filter by id\n2. Filter by title");
        String option = scanner.nextLine();
        if (option.equals("1")) {
            System.out.println("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            cart.searchById(id);
        } else if (option.equals("2")) {
            System.out.println("Enter title: ");
            String title = scanner.nextLine();
            cart.searchByTitle(title);
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void sortCart() {
        System.out.println("1. Sort by title\n2. Sort by cost");
        String option = scanner.nextLine();
        cart.sort(option);
        cart.printCart();
    }
}