package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfDirector;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD");
    }

    @Override
    protected void addAdditionalFields(JPanel form) {
        form.add(new JLabel("Artist:"));
        tfArtist = new JTextField(20);
        form.add(tfArtist);

        form.add(new JLabel("Director:"));
        tfDirector = new JTextField(20);
        form.add(tfDirector);
    }

    @Override
    protected void addItem() {
        try {
            if (tfTitle == null || tfCategory == null || tfCost == null || tfArtist == null || tfDirector == null) {
                throw new IllegalStateException("One or more text fields are null");
            }
            
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String costStr = tfCost.getText().trim();
            String artist = tfArtist.getText().trim();
            String director = tfDirector.getText().trim();
            
            if (title.isEmpty() || category.isEmpty() || costStr.isEmpty() || artist.isEmpty() || director.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            float cost = Float.parseFloat(costStr);
            
            int id = 1;
            if (!store.getMediaInStore().isEmpty()) {
                id = store.getMediaInStore().stream()
                        .mapToInt(Media::getId)
                        .max()
                        .orElse(0) + 1;
            }

            int length = 0;

            Collection<Track> tracks = Collections.emptyList();
            
            CompactDisc cd = new CompactDisc(id, title, category, cost, director, artist);
            store.addMedia(cd);


            JOptionPane.showMessageDialog(this, "CD added successfully!");
            dispose();
            new StoreManagerScreen(store).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}