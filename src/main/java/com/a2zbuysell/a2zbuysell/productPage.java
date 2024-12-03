package com.a2zbuysell.a2zbuysell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class productPage {

    @FXML
    public ImageView productImage;
    public Button goBackButton;

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
        productImage.setFitWidth(500);
        productImage.setFitHeight(500);
        productImage.setPreserveRatio(true);
        productImage.setPickOnBounds(true);

        titleText.setText(product.getTitle());
        priceText.setText("$" + product.getPrice());
        descriptionText.setText(product.getDescription());
        postedByText.setText(product.getUsername());
        contactInformationText.setText(product.getEmail() + "\n" + product.getPhone_number());



    }

    public void goBackClick(ActionEvent event) throws IOException, SQLException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-page.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();
    }
}
