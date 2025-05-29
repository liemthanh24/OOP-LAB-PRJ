package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import java.util.ArrayList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private final ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) throws LimitExceededException {
        if (media == null) {
            System.out.println("Cannot add null media to cart.");
            return;
        }

        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException("The cart is full. Cannot add more items (limit: " + MAX_NUMBERS_ORDERED + ").");
        } else if (itemsOrdered.contains(media)) {
            System.out.println("This media '" + media.getTitle() + "' is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("Added to cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove null media from cart.");
            return;
        }
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        } else {
            System.out.println("This media '" + media.getTitle() + "' is not in the cart. Cannot remove.");
        }
    }

    public float totalCost() {
        float total = 0f;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost();
            }
        }
        return total;
    }

    public void printCart() {
        System.out.println("********** CART **********");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is currently empty.");
        } else {
            for (int i = 0; i < itemsOrdered.size(); i++) {
                if (itemsOrdered.get(i) != null) {
                    System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
                }
            }
            System.out.printf("Total cost: %.2f $\n", totalCost());
        }
        System.out.println("***************************");
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media != null && media.getId() == id) {
                System.out.println("Found by ID: " + media.toString());
                return media;
            }
        }
        System.out.println("No media found with ID: " + id);
        return null;
    }

    public Media searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Search title cannot be null or empty.");
            return null;
        }
        for (Media media : itemsOrdered) {
            if (media != null && media.getTitle() != null && media.getTitle().equalsIgnoreCase(title.trim())) {
                System.out.println("Found by Title: " + media.toString());
                return media;
            }
        }
        System.out.println("No media found with title: " + title);
        return null;
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void sort(String option) {
        if (option == null || itemsOrdered.isEmpty()) {
            if (itemsOrdered.isEmpty()) System.out.println("Cart is empty, nothing to sort.");
            else System.out.println("Invalid sort option (null).");
            return;
        }
        switch (option.toLowerCase().trim()) {
            case "title":
                itemsOrdered.sort((Media m1, Media m2) -> {
                    if (m1 == null || m1.getTitle() == null) return (m2 == null || m2.getTitle() == null) ? 0 : 1;
                    if (m2 == null || m2.getTitle() == null) return -1;
                    return m1.getTitle().compareToIgnoreCase(m2.getTitle());
                });
                System.out.println("Cart sorted by title.");
                break;
            case "cost":
                itemsOrdered.sort((Media m1, Media m2) -> {
                    if (m1 == null) return (m2 == null) ? 0 : 1;
                    if (m2 == null) return -1;
                    return Float.compare(m2.getCost(), m1.getCost());
                });
                System.out.println("Cart sorted by cost (descending).");
                break;
            default:
                System.out.println("Invalid sort option: " + option);
        }
    }
}
