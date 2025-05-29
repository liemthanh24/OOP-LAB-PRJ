package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;

public class Store {
    private final ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add null media to store.");
            return;
        }
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Added to store: " + media.getTitle());
        } else {
            System.out.println("This media '" + media.getTitle() + "' is already in the store.");
        }
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove null media from store.");
            return;
        }
        if (itemsInStore.remove(media)) {
            System.out.println("Removed from store: " + media.getTitle());
        } else {
            System.out.println("This media '" + media.getTitle() + "' is not in the store.");
        }
    }

    public void printStore() {
        System.out.println("\n********** STORE **********");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is currently empty.");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                if (itemsInStore.get(i) != null) {
                     System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
                }
            }
        }
        System.out.println("****************************");
    }

    public Media searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        for (Media media : itemsInStore) {
            if (media != null && media.getTitle() != null && media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getMediaInStore() {
        return itemsInStore;
    }
    
    public Media searchById(int idToSearch) {
        for (Media media : itemsInStore) {
            if (media != null && media.getId() == idToSearch) {
                return media;
            }
        }
        return null;
    }


    public void playMedia(Media media) {
        if (media == null) {
            System.err.println("Error: Media to play cannot be null.");
            return;
        }
        
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println("PLAYER EXCEPTION while playing from store for " + media.getTitle() + ": " + e.getMessage());
            }
        } else {
            System.out.println("Error: This media '" + media.getTitle() + "' cannot be played.");
        }
    }
}
