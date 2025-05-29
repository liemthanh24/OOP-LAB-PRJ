package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book");
    }

    @Override
    protected void addAdditionalFields(JPanel form) {
        form.add(new JLabel("Authors:"));
        tfAuthors = new JTextField(20);
        form.add(tfAuthors);
    }

    @Override
    protected void addItem() {
        try {
            if (tfTitle == null || tfCategory == null || tfCost == null || tfAuthors == null) {
                throw new IllegalStateException("One or more text fields are null");
            }

            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String costStr = tfCost.getText().trim();
            String authors = tfAuthors.getText().trim();

            if (title.isEmpty() || category.isEmpty() || costStr.isEmpty() || authors.isEmpty()) {
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

            Book book = new Book(id, title, category, cost);

            Arrays.stream(authors.split(","))
                    .map(String::trim)
                    .filter(author -> !author.isEmpty())
                    .forEach(book::addAuthor);

            store.addMedia(book);

            JOptionPane.showMessageDialog(this, "Book added successfully!");
            dispose();
            new StoreManagerScreen(store).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
