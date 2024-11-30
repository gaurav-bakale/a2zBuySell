package com.a2zbuysell.a2zbuysell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class homePage {

    public Button sellProduct;
    public Button logout;
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
    void productImageClick(MouseEvent event, Product finalProduct) throws IOException, SQLException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product-page.fxml"));

        // Load the scene
        Scene scene = new Scene(loader.load());

//         Get the controller from the FXMLLoader
        productPage productPageController = loader.getController();
//
        // Pass the productId to the controller's initialize method
        productPageController.initialize(finalProduct);

        // Create a new Stage to show the product page
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);  // Set the fixed width
        stage.setHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchButton(ActionEvent event) {

    }

    @FXML
    void searchFiledClick(ActionEvent event) {

    }

    Product activeProduct;
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
            prodImage.setPreserveRatio(true);
            prodImage.setPickOnBounds(true);

            Product finalProduct = product;
            prodImage.setOnMouseClicked(event -> {
                try {
                    productImageClick(event, finalProduct);
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            Text productTitleText = new Text(product.getTitle());
            productTitleText.setWrappingWidth(200.0);

            Text productPriceText = new Text("$" +product.getPrice());
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

            // set the categories

////            DBManager dbm = new DBManager();
////
////            List<List<Object>> res = dbm.executeQuery("""
////                    select
////                    	c.id as category_id,
////                    	c.name as category,
////                    	s.id as subcategory_id,
////                    	s.name as subcategory
////                    from
////                    	categories c
////                    inner join
////                    subcategories s
////                    on
////                    	c.id = s.category_id
////            """);
////
////            ObservableList<String> categories = FXCollections.observableArrayList(
////                    "Electronics",
////                    "Clothing",
////                    "Books",
////                    "Furniture",
////                    "Toys"
////            );
//
//            // Set options to the ComboBox
//            categoriesDropdown.setItems(categories);
        }
    }

    public void sellProduct(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }
}
