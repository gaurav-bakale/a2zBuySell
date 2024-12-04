package com.a2zbuysell.a2zbuysell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class homePage {

    public HBox homePageTitleBarHbox;
    public HBox seachBarHbox;
    DBManager dbm = new DBManager();

    @FXML
    public Button sellProduct;

    @FXML
    public Button logout;

    @FXML
    private Button aboutButton;

    @FXML
    private HBox appTitleBar;

    @FXML
    private Label appTitleLabel;

    @FXML
    private ComboBox<String> categoriesDropdown;

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
    private ComboBox<String> subcategoriesDropDown;

    @FXML
    private Label subcategoriesLabel;

    @FXML
    private Button termsButton;

    ArrayList<Product> products;
    HashMap<String, ArrayList<String>> categoriesMapping = new HashMap<>();

    // Cart to hold selected products
    private final List<Product> cart = new ArrayList<>();

    @FXML
    private Button viewCartButton;

    private Stage cartStage;

    // Method to add a product to the cart
    private void addToCart(Product product) {
        cart.add(product);
        System.out.println("Added to cart: " + product.getTitle());
    }



    void loadAllProducts() throws SQLException {
        ProductManager pm = new ProductManager();
        pm.loadProducts();
        products = pm.products;

    }

    void setProdutcts() {
        if (productsDetailsVbox.getChildren() != null){
            productsDetailsVbox.getChildren().clear();
        }
        
        for (Product product : products) {
            HBox hbox = new HBox();

            // Set up product image
            ImageView prodImage = new ImageView();
            byte[] imageBytes = product.getImage();
            if (imageBytes.length != 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                Image image = new Image(bis);
                prodImage.setImage(image);
                prodImage.setFitWidth(200);
                prodImage.setFitHeight(200);
                prodImage.setPreserveRatio(true);
            }

            // Set up product title and price
            Text productTitleText = new Text(product.getTitle());
            productTitleText.setWrappingWidth(200.0);
            productTitleText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            productTitleText.setFill(Color.DARKSLATEGRAY); // Set title color

            Text productPriceText = new Text("$" + product.getPrice());
            productPriceText.setWrappingWidth(100.0);
            productPriceText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            productPriceText.setFill(Color.FORESTGREEN); // Set price color

            prodImage.setOnMouseClicked(event -> {
                try {
                    productImageClick(product);
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Add to Cart button
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.setStyle(
                    "-fx-background-color: #32CD32; " + // Green background
                            "-fx-text-fill: white; " +           // White text
                            "-fx-font-size: 16px; " +            // Font size
                            "-fx-font-weight: bold; " +          // Bold font
                            "-fx-background-radius: 12px; " +    // Rounded corners
                            "-fx-padding: 10px 20px; " +         // Padding inside the button
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 2, 2);" // Drop shadow effect
            );
            addToCartButton.setOnAction(event -> addToCart(product));


            hbox.getChildren().addAll(prodImage, productTitleText, productPriceText, addToCartButton);
            hbox.setSpacing(10.0);
            hbox.setPadding(new Insets(20, 20, 20, 20));
            hbox.setAlignment(Pos.CENTER);

            productsDetailsVbox.getChildren().add(hbox);
        }
    }


    void setAllProducts() throws SQLException {
        loadAllProducts();
        setProdutcts();
    }

    void setCategoriesDropdown() throws SQLException {
        categoriesDropdown.getItems().clear();

        List<List<Object>> res = dbm.executeQuery("""
                    select
                    	c.id as category_id,
                    	c.name as category,
                    	s.id as subcategory_id,
                    	s.name as subcategory
                    from
                    	categories c
                    inner join
                    subcategories s
                    on
                    	c.id = s.category_id
            """);

        // create a hashmap of categories and subcategories mapping
        HashMap<String, ArrayList<String>> categoriesMapping = new HashMap<>();
        ArrayList<String> list;
        for (List<Object> r : res) {
            if (categoriesMapping.containsKey(r.get(1))) {
                categoriesMapping.get((String) r.get(1)).add((String) r.get(3));
            } else {
                list = new ArrayList<>();

                list.add((String) r.get(3));
                categoriesMapping.put(
                        (String) r.get(1),
                        list
                );
            }
        }
        this.categoriesMapping = categoriesMapping;
        categoriesDropdown.getItems().addAll(categoriesMapping.keySet());
    }



    void setSubcategoriesDropDown(String category){

        // set the subcategories based on the category
        subcategoriesDropDown.getItems().clear();
//        subcategoriesDropDown.setItems(FXCollections.observableArrayList());
//        subcategoriesDropDown.setPromptText("Select");
//        subcategoriesDropDown.setValue(null);
        if (categoriesMapping.containsKey(category)) {
            for (String subcategory : categoriesMapping.get(category)) {
                if (!subcategoriesDropDown.getItems().contains(subcategory)) {
                    subcategoriesDropDown.getItems().add(subcategory);
                }
            }
        }
        //subcategoriesDropDown.getItems().addAll(categoriesMapping.get(category));


    }

    @FXML
    public void categoryFilterClick(ActionEvent actionEvent) throws SQLException {

        String category = categoriesDropdown.getValue();

        // Reload all products before applying the filter
        loadAllProducts(); // Ensure the products list is freshly loaded
        if (category != null && !category.isEmpty()) {
            products.removeIf(product -> !product.getCategory().equals(category));
        }
        setProdutcts();

        // Update subcategories based on the selected category
        setSubcategoriesDropDown(category);
        categoriesDropdown.setDisable(true);
        // Clear the search box (optional)
        searchField.setText("");
    }


    @FXML
    public void subcategoryFilterClick(ActionEvent actionEvent) throws SQLException {
        String subcategory = subcategoriesDropDown.getValue();

        // Reload all products before applying the filter
        loadAllProducts(); // Ensure the products list is freshly loaded
        if (subcategory != null && !subcategory.isEmpty()) {
            products.removeIf(product -> !product.getSubcategory().equals(subcategory));
        }
        setProdutcts();
    }


    @FXML
    void clearFilters(ActionEvent event) throws SQLException {
        categoriesDropdown.setDisable(false);
        categoriesDropdown.setValue(null);
        setAllProducts();
        setCategoriesDropdown();
        subcategoriesDropDown.getItems().clear();
    }

    @FXML
    void searchFieldClick(ActionEvent event) {}

    @FXML
    void searchButton(ActionEvent event) throws SQLException {

        // clear the dropdowns
        setCategoriesDropdown();
        subcategoriesDropDown.getItems().clear();

        // load all the products
        setAllProducts();

        String searchString = searchField.getText();
        products.removeIf(product-> !product.getTitle().toLowerCase().contains(searchString.toLowerCase()));
        setProdutcts();

    }

    @FXML
    void productBuyNow(ActionEvent event) {

    }

    @FXML
    void productImageClick( Product finalProduct) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("product-page.fxml"));
        Scene scene = new Scene(loader.load());

        productPage productPageController = loader.getController();

        productPageController.initialize(finalProduct);

        Stage stage = (Stage) productsDetailsVbox.getScene().getWindow();
        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();
        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();

    }


    @FXML
    void initialize() throws IOException, SQLException {

        // set the products
        setAllProducts();

        // set the categories drop down
        setCategoriesDropdown();

        // viewCartButton = new Button("View Cart");
        viewCartButton.setStyle(
                "-fx-background-color: #1E90FF; " + // Blue background for View Cart
                        "-fx-text-fill: white; " +           // White text
                        "-fx-font-size: 16px; " +            // Font size
                        "-fx-font-weight: bold; " +          // Bold font
                        "-fx-background-radius: 12px; " +    // Rounded corners
                        "-fx-padding: 10px 20px; " +         // Padding inside the button
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 2, 2);" // Drop shadow effect
        );
        viewCartButton.setOnAction(event -> viewCart());
        // HBox.setMargin(viewCartButton, new Insets(0, 50, 0, 0));
        // Spacer
        // Region rightSpacer = new Region();
        // Region leftSpacer = new Region();
        // HBox.setMargin(leftSpacer, new Insets(0, 0, 0, 50));
        // HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        // HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        // homePageTitleBarHbox.getChildren().addFirst(leftSpacer);
        // homePageTitleBarHbox.getChildren().addAll(rightSpacer, viewCartButton);

    }

    @FXML
    private void viewCart() {
        // Close the existing cartStage if it is already open
        if (cartStage != null && cartStage.isShowing()) {
            cartStage.close();
        }

        VBox cartView = new VBox();
        cartView.setPadding(new Insets(20));
        cartView.setSpacing(10);

        Label totalAmountLabel = new Label(); // Label to show total amount
        totalAmountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Method to calculate and update total amount
        Runnable updateTotalAmount = () -> {
            double totalAmount = cart.stream().mapToDouble(Product::getPrice).sum();
            totalAmountLabel.setText("Total Amount: $" + totalAmount);
        };

        for (Product product : cart) {
            HBox hbox = new HBox();

            // Display product name and price
            Text productTitle = new Text(product.getTitle());
            productTitle.setWrappingWidth(200.0);
            Text productPrice = new Text("$" + product.getPrice());

            // Remove button
            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> {
                cart.remove(product); // Remove from cart
                viewCart(); // Refresh cart popup
            });

            hbox.getChildren().addAll(productTitle, productPrice, removeButton);
            hbox.setSpacing(10.0);
            cartView.getChildren().add(hbox);
        }

        // Update total amount initially
        updateTotalAmount.run();

// Buy Now button
        Button buyNowButton = new Button("Buy Now");
        buyNowButton.setStyle(
                "-fx-background-color: #FFA500; " + // Orange background
                        "-fx-text-fill: white; " +        // White text
                        "-fx-font-size: 16px; " +         // Font size
                        "-fx-font-weight: bold; " +       // Bold font
                        "-fx-background-radius: 12px; " + // Rounded corners
                        "-fx-padding: 10px 20px; " +      // Padding inside the button
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 2, 2);" // Drop shadow effect
        );

// Action when "Buy Now" is clicked
        buyNowButton.setOnAction(e -> {
            if (cart.isEmpty()) {
                // Show alert if the cart is empty
                Alert emptyCartAlert = new Alert(Alert.AlertType.WARNING);
                emptyCartAlert.setTitle("Cart is Empty");
                emptyCartAlert.setHeaderText(null);
                emptyCartAlert.setContentText("Your cart is empty. Please add items to your cart before proceeding.");
                emptyCartAlert.showAndWait();
            } else {
                // Calculate the total amount
                double totalAmount = cart.stream().mapToDouble(Product::getPrice).sum();

                // Set a default email for contact
                String email = "johndoe@example.com";

                // Show purchase summary in an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase Summary");
                alert.setHeaderText("Your Purchase Details");
                alert.setContentText(
                        "Total Amount: $" + totalAmount +
                                "\nContact Information:" +
                                "\nEmail: " + email
                );
                alert.showAndWait();
            }
        });

        cartView.getChildren().addAll(totalAmountLabel, buyNowButton);


        // Create and show the cart popup
        cartStage = new Stage();
        cartStage.setTitle("Cart");
        Scene scene = new Scene(cartView, 400, 300);
        cartStage.setScene(scene);
        cartStage.show();
    }


    public void sellProduct(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("upload-page.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));
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
