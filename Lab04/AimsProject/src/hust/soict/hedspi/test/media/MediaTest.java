package hust.soict.hedspi.test.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Media {
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = (m1, m2) -> {
        int titleComparison = m1.getTitle().compareTo(m2.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }
        return Float.compare(m1.getCost(), m2.getCost());
    };

    public static final Comparator<Media> COMPARE_BY_COST_TITLE = (m1, m2) -> {
        int costComparison = Float.compare(m2.getCost(), m1.getCost());
        if (costComparison != 0) {
            return costComparison;
        }
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    };

    protected String title;
    protected float cost;

    public Media(String title, float cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Media: " + title + ", Cost: $" + cost;
    }

    public void clear() {
        title = null;
        cost = 0;
    }
}

class Book extends Media {
    private final String author;

    public Book(String title, String author, float cost) {
        super(title, cost);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + ", Cost: $" + cost;
    }
}

class DVD extends Media {
    private final String director;

    public DVD(String title, String director, float cost) {
        super(title, cost);
        this.director = director;
    }

    @Override
    public String toString() {
        return "DVD: " + title + " directed by " + director + ", Cost: $" + cost;
    }
}

class CompactDisc extends Media {
    private final String artist;

    public CompactDisc(String title, String artist, float cost) {
        super(title, cost);
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CD: " + title + " by " + artist + ", Cost: $" + cost;
    }
}

public class MediaTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();
        // Adding Book examples
        mediae.add(new Book("Java Programming", "John Doe", 15.5f));
        mediae.add(new Book("Algorithms", "Jane Smith", 19.9f));
        mediae.add(new Book("Java Programming", "Alice Brown", 10.0f));
        // Adding DVD examples
        mediae.add(new DVD("The Matrix", "Wachowski Sisters", 12.99f));
        mediae.add(new DVD("Inception", "Christopher Nolan", 14.5f));
        // Adding CompactDisc examples
        mediae.add(new CompactDisc("Abbey Road", "The Beatles", 9.99f));
        mediae.add(new CompactDisc("Thriller", "Michael Jackson", 11.5f));

        // Print before sorting
        System.out.println("Before sorting:");
        for (Media m : mediae) {
            System.out.println(m.toString());
        }

        // Sort by title then cost
        Collections.sort(mediae, Media.COMPARE_BY_TITLE_COST);
        System.out.println("\nAfter sorting by title then cost:");
        for (Media m : mediae) {
            System.out.println(m.toString());
        }

        // Sort by cost
        Collections.sort(mediae, Media.COMPARE_BY_COST_TITLE);
        System.out.println("\nAfter sorting by cost:");
        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}