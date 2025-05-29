package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;
import java.util.ArrayList;
import java.util.Iterator;

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

    public void addTrack(Track track) {
        if (track == null) {
            System.out.println("Cannot add a null track.");
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track '" + track.getTitle() + "' already exists in CD '" + getTitle() + "'.");
        }
    }

    public void removeTrack(Track track) {
        if (track == null) {
            System.out.println("Cannot remove a null track.");
            return;
        }
        if (tracks.remove(track)) {
            System.out.println("Removed track: " + track.getTitle() + " from CD '" + getTitle() + "'.");
        } else {
            System.out.println("Track '" + track.getTitle() + "' not found in CD '" + getTitle() + "'.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[CD] ID: %d | Title: %s | Category: %s | Artist: %s | Cost: %.2f $ | Total Length: %d mins",
                getId(), getTitle(), getCategory(), getArtist(), getCost(), getLength()));
        if (!tracks.isEmpty()) {
            sb.append("\nTracks:\n");
            for (int i = 0; i < tracks.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(tracks.get(i).toString()).append("\n");
            }
        } else {
            sb.append("\nNo tracks added yet.\n");
        }
        return sb.toString();
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            if (track != null) {
                totalLength += track.getLength();
            }
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.tracks.isEmpty()) {
            throw new PlayerException("ERROR: CD '" + getTitle() + "' has no tracks to play.");
        }

        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD '" + getTitle() + "' has a total length of non-positive value. Cannot play.");
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD Artist: " + getArtist());
        System.out.println("Total CD Length: " + getLength() + " minutes.");
        System.out.println("--- Tracks ---");

        Iterator<Track> iter = tracks.iterator();
        Track nextTrack;
        boolean hasPlayedSomething = false;

        while (iter.hasNext()) {
            nextTrack = iter.next();
            if (nextTrack == null) continue;

            try {
                nextTrack.play();
                hasPlayedSomething = true;
            } catch (PlayerException e) {
                System.err.println("Error playing track '" + nextTrack.getTitle() + "' in CD '" + getTitle() + "': " + e.getMessage());
                throw new PlayerException("Failed to play CD '" + getTitle() + "' because track '" + nextTrack.getTitle() + "' could not be played. Reason: " + e.getMessage(), e);
            }
        }
        if (!hasPlayedSomething && !tracks.isEmpty()){
             throw new PlayerException("ERROR: CD '" + getTitle() + "' contains tracks but none could be played (all might have non-positive length).");
        }
        System.out.println("--- Finished playing CD: " + getTitle() + " ---");
    }
}
