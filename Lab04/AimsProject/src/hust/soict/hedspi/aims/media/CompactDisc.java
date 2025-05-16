package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private final String artist;
    private final ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        this(id, title, category, cost, 0, director, artist);
    }

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public String setArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track already exists: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Removed track: " + track.getTitle());
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }

    @Override
    public String toString() {
        return String.format("[CD] ID: %d | Title: %s | Category: %s | Cost: %.2f $ | Length: %d | Director: %s |  Artist: %s | Length: %d | Tracks:",
                getId(), getTitle(), getCategory(), getCost(), getLength(), getDirector(), getArtist(), getLength());
    }

    @Override
    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    @Override
    public void play() {
        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD artist: " + getArtist());
        for (Track track : tracks) {
            track.play();
        }
    }
}