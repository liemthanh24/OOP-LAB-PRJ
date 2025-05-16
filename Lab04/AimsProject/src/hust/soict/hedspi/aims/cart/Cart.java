package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private final ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full. Cannot add more items.");
        } else if (itemsOrdered.contains(media)) {
            System.out.println("This media is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("Added to cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        } else {
            System.out.println("This media is not in the cart.");
        }
    }

    public float totalCost() {
        return itemsOrdered.stream().map(Media::getCost).reduce(0f, Float::sum);
    }

    public void printCart() {
        System.out.println("********** CART **********");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i));
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***************************");
    }

    public void searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() != id) {
                continue;
            }
            System.out.println(media);
            return;
        }
        System.out.println("No media found with ID: " + id);
    }

    public void searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println(media);
                return;
            }
        }
        System.out.println("No media found with title: " + title);
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void sort(String option) {
        switch (option) {
            case "title":
                itemsOrdered.sort((Media m1, Media m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));
                break;
            case "cost":
                itemsOrdered.sort((Media m1, Media m2) -> Float.compare(m2.getCost(), m1.getCost()));
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}
