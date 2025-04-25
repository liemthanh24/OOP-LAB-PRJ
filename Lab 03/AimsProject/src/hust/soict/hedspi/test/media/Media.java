package hust.soict.hedspi.test.media;

import java.util.Comparator;

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

