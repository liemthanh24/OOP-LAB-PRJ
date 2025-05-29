package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private final String title;
    private final int length;

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            System.err.println("Track title cannot be null or empty. Setting to 'Unknown Track'.");
            this.title = "Unknown Track";
        } else {
            this.title = title;
        }
        if (length < 0) {
            System.err.println("Track length cannot be negative for '" + this.title + "'. Setting to 0.");
            this.length = 0;
        } else {
            this.length = length;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + getTitle());
            System.out.println("Track length: " + getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive! Cannot play track '" + getTitle() + "'.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Track track = (Track) obj;

        if (length != track.length) return false;
        return title != null ? title.equals(track.title) : track.title == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        return "Track: " + title + " - Length: " + length;
    }
}
