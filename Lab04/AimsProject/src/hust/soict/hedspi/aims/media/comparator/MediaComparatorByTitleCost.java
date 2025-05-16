package hust.soict.hedspi.aims.media.comparator;

import java.util.Comparator;
import hust.soict.hedspi.aims.media.Media;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleCompare != 0) return titleCompare;
        return Double.compare(m2.getCost(), m1.getCost());
    }
}