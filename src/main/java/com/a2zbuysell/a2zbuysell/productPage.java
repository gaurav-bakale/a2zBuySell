package com.a2zbuysell.a2zbuysell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class productPage {

    @FXML
    public ImageView productImage;
    public ImageView goBackButton;

    @FXML
    private Button aboutButton;

    @FXML
    private HBox appTitleBar;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Button contactButton;

    @FXML
    private Label contactInformationLabel;

    @FXML
    private Text contactInformationText;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Text descriptionText;

    @FXML
    private HBox footer;

    @FXML
    private Label postedByLabel;

    @FXML
    private Text postedByText;

    @FXML
    private Label priceLabel;

    @FXML
    private Text priceText;

    @FXML
    private Button privacyButton;

    @FXML
    private Button termsButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Text titleText;
    // Method to initialize the page with product details
    public void initialize(Product product) throws SQLException {

        // Fetch product details using productId
        byte[] imageBytes = product.getImage();
        if (imageBytes != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            Image image = new Image(bis);
            productImage.setImage(image);
        }
//        productImage.setFitWidth(100);
//        productImage.setFitHeight(100);
//        productImage.setPreserveRatio(true);
//        productImage.setPickOnBounds(true);

        titleText.setText(product.getTitle());
        priceText.setText("$" + product.getPrice());
        descriptionText.setText(product.getDescription());
        postedByText.setText(product.getUsername());
        contactInformationText.setText(product.getEmail() + "\n" + product.getPhone_number());



    }

    public void goBackClick(MouseEvent mouseEvent) throws IOException, SQLException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-page.fxml"));

        // Load the scene
        Scene scene = new Scene(loader.load());

//         Get the controller from the FXMLLoader
        homePage homePageController = loader.getController();
//
        // Pass the productId to the controller's initialize method
        homePageController.initialize();

        // Create a new Stage to show the product page
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setWidth(1200);  // Set the fixed width
        stage.setHeight(900);
        stage.setScene(scene);
        stage.show();
    }
}
