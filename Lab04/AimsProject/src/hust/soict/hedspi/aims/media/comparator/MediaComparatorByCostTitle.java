package hust.soict.hedspi.aims.media.comparator;

import java.util.Comparator;
import hust.soict.hedspi.aims.media.Media;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        int costCompare = Double.compare(m2.getCost(), m1.getCost()); // cost giảm dần
        if (costCompare != 0) return costCompare;
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}