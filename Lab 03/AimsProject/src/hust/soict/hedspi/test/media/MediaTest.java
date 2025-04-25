package hust.soict.hedspi.test.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
