package com.a2zbuysell.a2zbuysell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

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
    void productImageClick(MouseEvent event) {

    }

    @FXML
    void searchButton(ActionEvent event) {

    }

    @FXML
    void searchFiledClick(ActionEvent event) {

    }
    @FXML
    void initialize() throws SQLException {
        ProductManager pm = new ProductManager();
        pm.loadProducts();

        Product product;
        for (int i = 0; i < pm.products.size(); i++) {

            product = pm.products.get(i);

            HBox hbox = new HBox();

            ImageView prodImage = new ImageView("https://b2861582.smushcdn.com/2861582/wp-content/uploads/2023/02/splash-01-605-v1.png?lossy=2&strip=1&webp=1");
            prodImage.setFitWidth(100);
            prodImage.setFitHeight(100);
            prodImage.setOnMouseClicked(this::productImageClick);
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
