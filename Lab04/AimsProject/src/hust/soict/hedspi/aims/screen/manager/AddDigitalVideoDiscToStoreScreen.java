package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD");
    }

    @Override
    protected void addAdditionalFields(JPanel form) {
        form.add(new JLabel("Director:"));
        tfDirector = new JTextField(20);
        form.add(tfDirector);

        form.add(new JLabel("Length:"));
        tfLength = new JTextField(20);
        form.add(tfLength);
    }

    @Override
    protected void addItem() {
        try {
            if (tfTitle == null || tfCategory == null || tfCost == null || tfDirector == null || tfLength == null) {
                throw new IllegalStateException("One or more text fields are null");
            }

            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String costStr = tfCost.getText().trim();
            String director = tfDirector.getText().trim();
            String lengthStr = tfLength.getText().trim();

            if (title.isEmpty() || category.isEmpty() || costStr.isEmpty() || director.isEmpty() || lengthStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float cost = Float.parseFloat(costStr);
            int length = Integer.parseInt(lengthStr);

            int id = 1;
            if (!store.getMediaInStore().isEmpty()) {
                id = store.getMediaInStore().stream()
                        .mapToInt(Media::getId)
                        .max()
                        .orElse(0) + 1;
            }

            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
            store.addMedia(dvd);

            JOptionPane.showMessageDialog(this, "DVD added successfully!");
            dispose();
            new StoreManagerScreen(store).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost or length format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}