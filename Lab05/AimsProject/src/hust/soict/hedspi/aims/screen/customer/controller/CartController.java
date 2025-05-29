package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store; // Import Store

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList; // Import FilteredList
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // Import cho chuyển màn hình
import javafx.scene.Node; // Import cho chuyển màn hình
import javafx.scene.Parent; // Import cho chuyển màn hình
import javafx.scene.Scene; // Import cho chuyển màn hình
import javafx.scene.control.Alert; // Import cho thông báo
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage; // Import cho chuyển màn hình
import javafx.beans.value.ChangeListener; // Import ChangeListener
import javafx.beans.value.ObservableValue; // Import ObservableValue

import java.io.IOException; // Import cho xử lý Exception khi chuyển màn hình
import java.util.function.Predicate; // Import Predicate cho FilteredList

public class CartController {

    private Cart cart;
    private Store store; 

    private ObservableList<Media> masterData;
    private FilteredList<Media> filteredData;


    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    @FXML
    private Button btnViewStore;

    @FXML
    private Button btnPlaceOrder;

    public CartController(Cart cart, Store store) {
        this.cart = cart;
        this.store = store;
        if (cart.getItemsOrdered() != null) {
            this.masterData = FXCollections.observableArrayList(cart.getItemsOrdered());
        } else {
            this.masterData = FXCollections.observableArrayList();
        }
        this.filteredData = new FilteredList<>(masterData, p -> true);
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        tblMedia.setItems(filteredData);

        updateCostLabel();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        if (filterCategory != null) {
            filterCategory.selectedToggleProperty().addListener(new ChangeListener<javafx.scene.control.Toggle>() {
                @Override
                public void changed(ObservableValue<? extends javafx.scene.control.Toggle> observable,
                                    javafx.scene.control.Toggle oldValue, javafx.scene.control.Toggle newValue) {
                    showFilteredMedia(tfFilter.getText());
                }
            });
        }
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    private void showFilteredMedia(String filterText) {
        String lowerCaseFilterText = filterText.toLowerCase();

        filteredData.setPredicate(new Predicate<Media>() {
            @Override
            public boolean test(Media media) {
                if (filterText == null || filterText.isEmpty()) {
                    return true;
                }

                if (radioBtnFilterId.isSelected()) {
                    try {
                        int filterId = Integer.parseInt(lowerCaseFilterText);
                        return media.getId() == filterId;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else if (radioBtnFilterTitle.isSelected()) {
                    return media.getTitle().toLowerCase().contains(lowerCaseFilterText);
                }
                return true;
            }
        });
    }


    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));

            fxmlLoader.setController(new ViewStoreController(store, cart));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Could not load the store screen.");
            alert.setContentText("Please check the FXML file path or contact support.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            if (selectedMedia instanceof Playable) {
                try {
                    System.out.println("Playing: " + selectedMedia.getTitle() + "...");
                    ((Playable) selectedMedia).play();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Playing Media");
                    alert.setHeaderText(null);
                    alert.setContentText("Now playing: " + selectedMedia.getTitle());
                    alert.showAndWait();

                } catch (Exception e) {
                    System.err.println("Error playing media: " + e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Playback Error");
                    alert.setHeaderText("Cannot play " + selectedMedia.getTitle());
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not Playable");
                alert.setHeaderText(null);
                alert.setContentText(selectedMedia.getTitle() + " is not a playable media type.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a media item from the table to play.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);

            masterData.remove(selectedMedia);
            showFilteredMedia(tfFilter.getText());

            updateCostLabel();
            System.out.println("Removed: " + selectedMedia.getTitle());
        } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a media item from the table to remove.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
            return;
        }

        System.out.println("Place Order button pressed. Total cost: " + cart.totalCost());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Thank You!");
        alert.setContentText("Your order has been placed successfully for a total of: " + String.format("%.2f $", cart.totalCost()));
        alert.showAndWait();

        cart.getItemsOrdered().clear();
        masterData.clear();

        updateCostLabel();
    }

    private void updateCostLabel() {
        if (costLabel != null) {
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
        }
    }
}
