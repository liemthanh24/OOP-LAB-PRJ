package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.LimitExceededException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        try {
            store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f));
            store.addMedia(new Book(101, "The Art of War", "Strategy", 10.5f));
            CompactDisc cd1 = new CompactDisc(201, "Greatest Hits", "Pop", 15.0f, "Various Artists", "Taylor Swift");
            cd1.addTrack(new Track("Shake It Off", 3));
            store.addMedia(cd1);
            store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", "George Lucas", 0, 24.95f)); // DVD length 0
        } catch (Exception e) {
            System.err.println("Error initializing store: " + e.getMessage());
        }


        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    viewStore();
                    break;
                case "2":
                    updateStore();
                    break;
                case "3":
                    seeCurrentCart();
                    break;
                case "0":
                    System.out.println("Exiting AIMS...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-3): ");
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-4): ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-2): ");
    }

    public static void cartMenuOptions() {
        System.out.println("\nCart Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media from cart");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-5): ");
    }

    public static void seeCurrentCart() {
        if (cart.getItemsOrdered().isEmpty()){
            System.out.println("Your cart is currently empty.");
            return;
        }
        cart.printCart();
        while (true) {
            cartMenuOptions();
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    filterCart();
                    break;
                case "2":
                    sortCart();
                    break;
                case "3":
                    removeMediaFromCart();
                    break;
                case "4":
                    playMediaFromCart();
                    break;
                case "5":
                    if (cart.getItemsOrdered().isEmpty()) {
                        System.out.println("Cart is empty. Cannot place order.");
                    } else {
                        System.out.println("An order is created. Total cost: " + String.format("%.2f $", cart.totalCost()));
                        cart.getItemsOrdered().clear();
                        System.out.println("Cart has been emptied.");
                    }
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewStore() {
        if (store.getMediaInStore().isEmpty()){
            System.out.println("The store is currently empty.");
            return;
        }
        store.printStore();
        while (true) {
            storeMenu();
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    seeMediaDetailsFromStore();
                    break;
                case "2":
                    addMediaToCartFromStore();
                    break;
                case "3":
                    playMediaFromStore();
                    break;
                case "4":
                    seeCurrentCart();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void updateStore() {
        System.out.println("\nUpdate Store Options:");
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.print("Choose an option (1-2): ");
        String option = scanner.nextLine().trim();

        if (option.equals("1")) {
            System.out.println("Enter title for new DVD (simple add for now): ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            store.addMedia(new DigitalVideoDisc(title, "Default Category", "Default Director", 90, 9.99f));
        } else if (option.equals("2")) {
            if (store.getMediaInStore().isEmpty()){
                 System.out.println("Store is empty. Nothing to remove.");
                 return;
            }
            System.out.println("Enter title of media to remove: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            Media media = store.searchByTitle(title);
            if (media != null) {
                store.removeMedia(media);
            } else {
                System.out.println("Media with title '" + title + "' not found in store.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void seeMediaDetailsFromStore() {
        if (store.getMediaInStore().isEmpty()){
             System.out.println("Store is empty.");
             return;
        }
        System.out.print("Enter title of media to see details: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println("--- Media Details ---");
            System.out.println(media.toString());
            System.out.println("---------------------");
            while (true) {
                mediaDetailsMenu();
                String option = scanner.nextLine().trim();
                switch (option) {
                    case "1":
                        try {
                            cart.addMedia(media);
                        } catch (LimitExceededException e) {
                            System.err.println("Error adding to cart: " + e.getMessage());
                        }
                        break;
                    case "2":
                        if (media instanceof Playable) {
                            try {
                                ((Playable) media).play();
                            } catch (PlayerException e) {
                                System.err.println("PLAYER EXCEPTION for " + media.getTitle() + ": " + e.getMessage());
                            }
                        } else {
                            System.out.println("This media '" + media.getTitle() + "' cannot be played.");
                        }
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }

    public static void addMediaToCartFromStore() {
         if (store.getMediaInStore().isEmpty()){
             System.out.println("Store is empty. Cannot add to cart.");
             return;
        }
        System.out.print("Enter title of media to add to cart: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Media media = store.searchByTitle(title);
        if (media != null) {
            try {
                cart.addMedia(media);
            } catch (LimitExceededException e) {
                System.err.println("Error adding to cart: " + e.getMessage());
            }
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }

    public static void playMediaFromStore() {
        if (store.getMediaInStore().isEmpty()){
             System.out.println("Store is empty. Cannot play media.");
             return;
        }
        System.out.print("Enter title of media to play from store: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Media media = store.searchByTitle(title);
        if (media != null) {
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play();
                } catch (PlayerException e) {
                    System.err.println("PLAYER EXCEPTION for " + media.getTitle() + ": " + e.getMessage());
                }
            } else {
                System.out.println("This media '" + media.getTitle() + "' cannot be played.");
            }
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }

    public static void removeMediaFromCart() {
        if (cart.getItemsOrdered().isEmpty()){
             System.out.println("Cart is empty. Nothing to remove.");
             return;
        }
        System.out.print("Enter title of media to remove from cart: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Media mediaToRemove = null;
        for (Media m : cart.getItemsOrdered()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                mediaToRemove = m;
                break;
            }
        }

        if (mediaToRemove != null) {
            cart.removeMedia(mediaToRemove);
        } else {
            System.out.println("Media with title '" + title + "' not found in cart.");
        }
    }

    public static void playMediaFromCart() {
        if (cart.getItemsOrdered().isEmpty()){
             System.out.println("Cart is empty. Nothing to play.");
             return;
        }
        System.out.print("Enter title of media to play from cart: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Media mediaToPlay = null;
        for (Media media : cart.getItemsOrdered()) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                mediaToPlay = media;
                break;
            }
        }

        if (mediaToPlay != null) {
            if (mediaToPlay instanceof Playable) {
                try {
                    ((Playable) mediaToPlay).play();
                } catch (PlayerException e) {
                    System.err.println("PLAYER EXCEPTION for " + mediaToPlay.getTitle() + ": " + e.getMessage());
                }
            } else {
                System.out.println("This media '" + mediaToPlay.getTitle() + "' in cart cannot be played.");
            }
        } else {
            System.out.println("Cannot find media in cart with title '" + title + "'.");
        }
    }

    public static void filterCart() {
        if (cart.getItemsOrdered().isEmpty()){
             System.out.println("Cart is empty. Nothing to filter.");
             return;
        }
        System.out.println("\nFilter options:");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        System.out.print("Choose filter type (1-2): ");
        String option = scanner.nextLine().trim();
        if (option.equals("1")) {
            System.out.print("Enter ID to filter by: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                System.out.println("Filtering by ID (current Cart.searchById prints matches):");
                cart.searchById(id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a number.");
            }
        } else if (option.equals("2")) {
            System.out.print("Enter title to filter by: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Filter title cannot be empty.");
                return;
            }
            System.out.println("Filtering by title (current Cart.searchByTitle prints matches):");
            cart.searchByTitle(title);
        } else {
            System.out.println("Invalid filter option.");
        }
    }

    public static void sortCart() {
        if (cart.getItemsOrdered().isEmpty()){
             System.out.println("Cart is empty. Nothing to sort.");
             return;
        }
        System.out.println("\nSort options:");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost (descending)");
        System.out.print("Choose sort type (1-2): ");
        String optionInput = scanner.nextLine().trim();
        String sortBy = "";
        if (optionInput.equals("1")) {
            sortBy = "title";
        } else if (optionInput.equals("2")) {
            sortBy = "cost";
        } else {
            System.out.println("Invalid sort option.");
            return;
        }
        cart.sort(sortBy);
        System.out.println("Cart after sorting by " + sortBy + ":");
        cart.printCart();
    }
}
