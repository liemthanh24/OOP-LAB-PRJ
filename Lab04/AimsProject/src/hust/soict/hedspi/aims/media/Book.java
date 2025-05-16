package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private final List<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(id, title, category, cost);
        this.authors.addAll(authors);
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Author already exists: " + authorName);
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.remove(authorName)) {
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author not found: " + authorName);
        }
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> setAuthors(List<String> authors) {
         this.authors.clear();
         this.authors.addAll(authors);
         return this.authors;
    }

    @Override
    public String toString() {
        return String.format("[Book] ID: %d | Title: %s | Category: %s | Cost: %.2f $",
                getId(), getTitle(), getCategory(), getCost());
    }
}