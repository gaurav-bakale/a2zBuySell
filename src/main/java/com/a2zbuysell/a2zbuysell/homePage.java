package com.a2zbuysell.a2zbuysell;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class homePage {

    public Button electronicsButton;
    public Button furnitureButton;
    public Button booksButton;
    public Button clothingButton;
    public Button toysButton;
    public Button aboutButton;
    public Button contactButton;
    public Button termsButton;
    public Button privacyButton;
    public HBox appTitleBar;
    public Label appTitleLabel;
    public HBox searchHBox;
    public Label categoriesLabel;
    public ComboBox categoriesDropdown;
    public Label subcategoriesLabel;
    public ComboBox categoriesDropdown1;
    public VBox leftNavbar;
    public VBox mainPage;
    public HBox footer;
    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button cartButton;

    @FXML
    private GridPane productGrid;

    @FXML
    private Button buyNowButton1;

    @FXML
    private Button buyNowButton2;

    @FXML
    private Button buyNowButton3;

    @FXML
    private Button buyNowButton4;

    @FXML
    private Button buyNowButton5;

    // Define actions for buttons, for example, the search button action
    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText();
        System.out.println("Searching for: " + searchQuery);
        // Add actual search functionality here
    }

    @FXML
    private void handleLogin() {
        System.out.println("Login button clicked");
        // Add login functionality here
    }

    @FXML
    private void handleCart() {
        System.out.println("Cart button clicked");
        // Add cart functionality here
    }

    // Handle "Buy Now" actions for each product
    @FXML
    private void handleBuyNow1() {
        System.out.println("Buying Product 1");
    }

    @FXML
    private void handleBuyNow2() {
        System.out.println("Buying Product 2");
    }

    @FXML
    private void handleBuyNow3() {
        System.out.println("Buying Product 3");
    }

    @FXML
    private void handleBuyNow4() {
        System.out.println("Buying Product 4");
    }

    @FXML
    private void handleBuyNow5() {
        System.out.println("Buying Product 5");
    }
}