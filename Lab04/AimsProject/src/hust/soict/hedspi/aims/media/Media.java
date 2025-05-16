package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import hust.soict.hedspi.aims.media.comparator.MediaComparatorByCostTitle;
import hust.soict.hedspi.aims.media.comparator.MediaComparatorByTitleCost;

public class Media {
    private static int nbMedia = 0;
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(String title) {
        this.id = ++nbMedia;
        this.title = title;
    }

    public Media(int id, String title, String category, float cost) {
        this.id = ++nbMedia;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(String title, String category, float cost) {
        this.id = ++nbMedia;
        this.title = title;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Media media = (Media) obj;

        return this.title != null && this.title.equals(media.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public boolean isMatch(int id) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();


    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

}
