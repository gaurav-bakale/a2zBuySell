package com.a2zbuysell.a2zbuysell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class homePage {

    @FXML
    private Button aboutButton;

    @FXML
    private HBox appTitleBar;

    @FXML
    private Label appTitleLabel;

    @FXML
    private ComboBox<?> categoriesDropdown;

    @FXML
    private Label categoriesLabel;

    @FXML
    private Button clearFiltersButton;

    @FXML
    private Button contactButton;

    @FXML
    private HBox footer;

    @FXML
    private VBox leftNavbar;

    @FXML
    private Button privacyButton;

    @FXML
    private Button productBuyButton;

    @FXML
    private HBox productDetailHBox;

    @FXML
    private ImageView productImage;

    @FXML
    private Text productPriceText;

    @FXML
    private Text productTitleText;

    @FXML
    private VBox productsDetailsVbox;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private HBox searchHBox;

    @FXML
    private ComboBox<?> subcategoriesDropDown;

    @FXML
    private Label subcategoriesLabel;

    @FXML
    private Button termsButton;

    @FXML
    void clearFilters(ActionEvent event) {

    }

    @FXML
    void productBuyNow(ActionEvent event) {

    }

    @FXML
    void productImageClick(MouseEvent event) throws IOException, SQLException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/a2zbuysell/a2zbuysell/productPage.fxml"));

        // Load the scene
        Scene scene = new Scene(loader.load());

        // Get the controller from the FXMLLoader
        productPage productPageController = loader.getController();

        // Pass the productId to the controller's initialize method
        Product product = null;
        productPageController.initialize(product);

        // Create a new Stage to show the product page
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchButton(ActionEvent event) {

    }

    @FXML
    void searchFiledClick(ActionEvent event) {

    }
    @FXML
    void initialize() throws IOException, SQLException {
        ProductManager pm = new ProductManager();
        pm.loadProducts();

        Product product;
        for (int i = 0; i < pm.products.size(); i++) {

            product = pm.products.get(i);

            HBox hbox = new HBox();

            ImageView prodImage = new ImageView();
            byte[] imageBytes = product.getImage();
            if (imageBytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                Image image = new Image(bis);
                prodImage.setImage(image);
            }
            prodImage.setFitWidth(100);
            prodImage.setFitHeight(100);
            prodImage.setOnMouseClicked(event -> {
                try {
                    productImageClick(event);
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            prodImage.setPreserveRatio(true);
            prodImage.setPickOnBounds(true);

            Text productTitleText = new Text(product.getTitle());
            productTitleText.setWrappingWidth(200.0);

            Text productPriceText = new Text(STR."$\{product.getPrice()}");
            productPriceText.setWrappingWidth(100.0);

            Button productBuyButton = new Button("Buy now");
            productBuyButton.setOnAction(this::productBuyNow);

            hbox.getChildren().addAll(prodImage, productTitleText, productPriceText, productBuyButton);
            hbox.setLayoutX(10.0);
            hbox.setLayoutY(10.0);
            hbox.setSpacing(10.0);
            hbox.setPadding(new Insets(20,20,20,20));
            hbox.setAlignment(Pos.CENTER);

            productsDetailsVbox.getChildren().add(hbox);


        }
    }
}
