package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerAppLauncher extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

        URL location = getClass().getResource(STORE_FXML_FILE_PATH);

        if (location == null) {
            System.err.println("Cannot find FXML file at path: " + STORE_FXML_FILE_PATH);
            System.err.println("Please check if the FXML file exists at this location in your compiled output (e.g., target/classes or bin folder) and that the path is correct.");
            System.err.println("Expected structure in classpath: /hust/soict/hedspi/aims/screen/customer/view/Store.fxml");
            javafx.application.Platform.exit();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("AIMS Customer Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 89, 19.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars: A New Hope", "Sci-Fi", "George Lucas", 121, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Inception", "Sci-Fi", "Christopher Nolan", 148, 15.50f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        CompactDisc cd1 = new CompactDisc(4, "Thriller", "Pop", 9.99f, "Quincy Jones", "Michael Jackson");
        cd1.addTrack(new Track("Wanna Be Startin' Somethin'", 362));
        cd1.addTrack(new Track("Baby Be Mine", 260));
        cd1.addTrack(new Track("Thriller", 357));
        store.addMedia(cd1);

        CompactDisc cd2 = new CompactDisc(5, "The Dark Side of the Moon", "Progressive Rock", 12.50f, "Pink Floyd (Producer)", "Pink Floyd");
        cd2.addTrack(new Track("Speak to Me/Breathe", 233));
        cd2.addTrack(new Track("Time", 413));
        cd2.addTrack(new Track("Money", 382));
        store.addMedia(cd2);

        Book book1 = new Book(6, "The Hobbit", "Fantasy", 10.00f, new ArrayList<>(Arrays.asList("J.R.R. Tolkien")));
        Book book2 = new Book(7, "Sapiens: A Brief History of Humankind", "Non-Fiction", 14.75f, new ArrayList<>(Arrays.asList("Yuval Noah Harari")));
        Book book3 = new Book(8, "To Kill a Mockingbird", "Classic", 8.99f, new ArrayList<>(Arrays.asList("Harper Lee")));
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        DigitalVideoDisc dvd4 = new DigitalVideoDisc(9, "Pulp Fiction", "Crime", "Quentin Tarantino", 154, 12.99f);
        store.addMedia(dvd4);

        Book book4 = new Book(10, "1984", "Dystopian", 7.50f, new ArrayList<>(Arrays.asList("George Orwell")));
        store.addMedia(book4);

        CompactDisc cd3 = new CompactDisc(11, "Abbey Road", "Rock", 11.20f, "George Martin", "The Beatles");
        cd3.addTrack(new Track("Come Together", 259));
        cd3.addTrack(new Track("Something", 182));
        cd3.addTrack(new Track("Here Comes the Sun", 185));
        store.addMedia(cd3);

        launch(args);
    }
}