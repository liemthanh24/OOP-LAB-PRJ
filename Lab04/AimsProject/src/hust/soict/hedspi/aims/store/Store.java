package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import java.util.ArrayList;

public class Store {
    private final ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Added to store: " + media.getTitle());
        } else {
            System.out.println("This media is already in the store: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed from store: " + media.getTitle());
        } else {
            System.out.println("This media is not in the store: " + media.getTitle());
        }
    }

    public void printStore() {
        System.out.println("********** STORE **********");
        int i = 1;
        for (Media media : itemsInStore) {
            System.out.println(i + ". " + media);
            i++;
        }
        System.out.println("****************************");
    }

    public Media searchByTitle(String title ) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println(media);
                return media;
            }
        }
        System.out.println("No media found with title: " + title);
        return null;
    }

    public ArrayList<Media> getMediaInStore() {
        return itemsInStore;
    }

    public ArrayList<Media> getMediaInStoreByTitle() {
        return itemsInStore;
    }

    public Media getMediaInStoreById() {
        for (Media media : itemsInStore) {
            int id = 0;
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public void playMedia(Media media) {
        if (media == null) {
            System.out.println("Error: Media cannot be null");
            return;
        }

        if (!itemsInStore.contains(media)) {
            System.out.println("Error: Media is not available in the store.");
            return;
        }

        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Error: This media cannot be played. Title: " + media.getTitle());
        }
    }
}
