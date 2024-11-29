package com.a2zbuysell.a2zbuysell;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class productPage {

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

        // Set the text fields with the fetched product data
        titleText.setText(product.getTitle());
        priceText.setText("$" + product.getPrice());
        descriptionText.setText(product.getDescription());
//        postedByText.setText(product.getPostedBy());
//        contactInformationText.setText(product.getContactInfo());
    }

}
