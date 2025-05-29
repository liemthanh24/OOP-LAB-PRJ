package hust.soict.hedspi.aims.screen.customer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.LimitExceededException; // Import
import javafx.scene.control.Alert;

public class ItemController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private HBox actionBox;

    private Media media;
    private Cart cart;

    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        if (media != null) {
            lblTitle.setText(media.getTitle());
            lblCost.setText(String.format("%.2f $", media.getCost()));
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
                btnPlay.setManaged(true);
            } else {
                btnPlay.setVisible(false);
                btnPlay.setManaged(false);
            }
        } else {
            lblTitle.setText("N/A");
            lblCost.setText("N/A");
            btnPlay.setVisible(false);
            btnPlay.setManaged(false);
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (this.media != null && this.cart != null) {
            try {
                this.cart.addMedia(this.media);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Added");
                alert.setHeaderText(null);
                alert.setContentText("'" + this.media.getTitle() + "' has been added to your cart.");
                alert.showAndWait();
            } catch (LimitExceededException e) {
                System.err.println("Error adding item to cart: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cart Full");
                alert.setHeaderText("Could not add '" + this.media.getTitle() + "' to cart.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An unexpected error occurred while adding the item.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            System.out.println("Error: Media item or Cart is null. Cannot add to cart.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Item Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot add item. Media or Cart information is missing.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (this.media != null && this.media instanceof Playable) {
            Alert alertPlaying = new Alert(Alert.AlertType.INFORMATION);
            alertPlaying.setTitle("Playing Media");
            alertPlaying.setHeaderText(null);
            alertPlaying.setContentText("Now playing: " + media.getTitle() + "...");
            alertPlaying.show();

            try {
                ((Playable) this.media).play();
            } catch (Exception e) {
                alertPlaying.close();
                System.err.println("Error during playback for " + media.getTitle() + ": " + e.getMessage());
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Playback Error");
                alertError.setHeaderText("Could not play " + media.getTitle());
                alertError.setContentText(e.getMessage());
                alertError.showAndWait();
            }
        } else {
            String message = "Cannot play. ";
            if (this.media == null) {
                message += "No media selected.";
            } else {
                message += "'" + this.media.getTitle() + "' is not playable.";
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not Playable");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}
