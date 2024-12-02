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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class homePage {

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


    void loadAllProducts() throws SQLException {
        ProductManager pm = new ProductManager();
        pm.loadProducts();
        products = pm.products;

    }

    void setProdutcts(){

        Product product;
        productsDetailsVbox.getChildren().clear();
        for (int i = 0; i < products.size(); i++) {

            product = products.get(i);

            HBox hbox = new HBox();

            ImageView prodImage = new ImageView();
            byte[] imageBytes = product.getImage();
            if (imageBytes.length != 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                Image image = new Image(bis);
                prodImage.setImage(image);
                prodImage.setFitWidth(100);
                prodImage.setFitHeight(100);
                prodImage.setPreserveRatio(true);
                prodImage.setPickOnBounds(true);
            }

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
//            productBuyButton.setOnAction(this::productBuyNow);
            productBuyButton.setOnMouseClicked(event -> {
                try {
                    productImageClick(event, finalProduct);
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            hbox.getChildren().addAll(prodImage, productTitleText, productPriceText, productBuyButton);
            hbox.setLayoutX(10.0);
            hbox.setLayoutY(10.0);
            hbox.setSpacing(10.0);
            hbox.setPadding(new Insets(20,20,20,20));
            hbox.setAlignment(Pos.CENTER);

            productsDetailsVbox.getChildren().add(hbox);

        }
        productsDetailsVbox.setFillWidth(true);
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
        subcategoriesDropDown.setPromptText("Select");
        subcategoriesDropDown.setValue("");
        subcategoriesDropDown.getItems().addAll(
                categoriesMapping.get(category)
        );


    }

    @FXML
    public void categoryFilterClick(ActionEvent actionEvent) throws SQLException {

        String category = categoriesDropdown.getValue();

        // filter the products according to the category
        setAllProducts();
        products.removeIf(product-> !product.getCategory().equals(category));
        setProdutcts();

        // set the subcategories based on the category selected
        setSubcategoriesDropDown(category);

        // clear the search box
        searchField.setText("");
    }

    @FXML
    public void subcategoryFilterClick(ActionEvent actionEvent) throws SQLException {

        // filter products based on the subcategory selected
        String subcategory = subcategoriesDropDown.getValue();

        // filter the products according to the category
        products.removeIf(product-> !product.getSubcategory().equals(subcategory));
        setProdutcts();
    }

    @FXML
    void clearFilters(ActionEvent event) throws SQLException {
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
        stage.setWidth(1200);  // Set the fixed width
        stage.setHeight(900);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize() throws IOException, SQLException {

        // set the products
        setAllProducts();

        // set the categories drop down
        setCategoriesDropdown();

    }

    public void sellProduct(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("upload-page.fxml"));
        Scene uploadPageScene = new Scene(loader.load());

        // Get the current stage (window)
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(uploadPageScene);
        stage.setTitle("Sell Product - A2Z Buy Sell");
        stage.show();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));

        // Load the scene
        Scene scene = new Scene(loader.load());

        // shift to login page
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setWidth(1200);  // Set the fixed width
        stage.setHeight(900);
        stage.setScene(scene);
        stage.show();
    }

}
