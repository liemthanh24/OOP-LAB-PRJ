package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;
import hust.soict.hedspi.aims.media.comparator.MediaComparatorByCostTitle;
import hust.soict.hedspi.aims.media.comparator.MediaComparatorByTitleCost;

public abstract class Media implements Comparable<Media> {
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
        if (cost >= 0) {
            this.cost = cost;
        } else {
            System.err.println("Warning: Cost for '" + title + "' is negative (" + cost + "). Setting to 0.0.");
            this.cost = 0.0f;
        }
    }

    public Media(String title, String category, float cost) {
        this.id = ++nbMedia;
        this.title = title;
        this.category = category;
        if (cost >= 0) {
            this.cost = cost;
        } else {
            System.err.println("Warning: Cost for '" + title + "' is negative (" + cost + "). Setting to 0.0.");
            this.cost = 0.0f;
        }
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
        if (cost >= 0) {
            this.cost = cost;
        } else {
            System.err.println("Error: Cost cannot be negative. Value not changed for media ID " + this.id);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || !(obj instanceof Media)) return false;

        Media otherMedia = (Media) obj;

        boolean titleEquals;
        if (this.title == null) {
            titleEquals = (otherMedia.title == null);
        } else {
            titleEquals = this.title.equalsIgnoreCase(otherMedia.title); // So sánh không phân biệt hoa thường
        }

        boolean costEquals = Float.compare(this.cost, otherMedia.cost) == 0;

        return titleEquals && costEquals;
    }

    @Override
    public int hashCode() {
        String titleLower = (this.title != null) ? this.title.toLowerCase() : null;
        return Objects.hash(titleLower, cost);
    }


    public boolean isMatch(String titleToSearch) {
        if (this.title == null || titleToSearch == null) return false;
        return this.title.toLowerCase().contains(titleToSearch.toLowerCase());
    }

    public boolean isMatch(int idToSearch) {
        return this.id == idToSearch;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            return 1;
        }

        int titleComparison;
        if (this.title == null && other.title == null) {
            titleComparison = 0;
        } else if (this.title == null) {
            titleComparison = -1;
        } else if (other.title == null) {
            titleComparison = 1;
        } else {
            titleComparison = this.title.compareToIgnoreCase(other.title);
        }

        if (titleComparison != 0) {
            return titleComparison;
        }
        return Float.compare(other.cost, this.cost);
    }


    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Category: %s | Cost: %.2f $",
                id, (title != null ? title : "N/A"), (category != null ? category : "N/A"), cost);
    }
}
