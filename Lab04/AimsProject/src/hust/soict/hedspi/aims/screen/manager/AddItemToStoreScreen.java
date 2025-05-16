package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;

    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setLayout(new BorderLayout());

        setJMenuBar(new StoreManagerScreen(store).createMenuBar());

        JLabel header = new JLabel("Add item to store", JLabel.CENTER);
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        form.add(tfTitle);

        form.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        form.add(tfCategory);

        form.add(new JLabel("Cost:"));
        tfCost = new JTextField();
        form.add(tfCost);

        addAdditionalFields(form);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addItem());

        form.add(new JLabel());
        form.add(btnAdd);

        add(form, BorderLayout.CENTER);

        setTitle("Add Item");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected abstract void addAdditionalFields(JPanel form);
    protected abstract void addItem();
}
