package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sample product data
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new ElectronicsProduct(
            "Used Laptop",
            "A lightly used laptop with 8GB RAM and 256GB SSD.",
            450.00,
            "Electronics",
            List.of("file:src/images/laptop.jpg"),
            "Dell",
            "XPS 13",
            "1 year warranty"
        ));

        sampleProducts.add(new FurnitureProduct(
            "Office Chair",
            "Ergonomic office chair with adjustable height and lumbar support.",
            150.00,
            "Furniture",
            List.of("file:src/images/chair.jpg"),
            "Mesh",
            "50cm x 50cm x 100cm",
            "Black"
        ));

        sampleProducts.add(new Product(
            "Used Book",
            "A fascinating novel in good condition.",
            20.00,
            "Books",
            List.of("file:src/images/book.jpg")
        ) {
            @Override
            public String getProductDetails() {
                return "Book in good condition.";
            }
        });

        // Create layout
        BorderPane root = new BorderPane();

        // Product Manager to store and manage products
        ProductManager productManager = new ProductManager();
        productManager.addProducts(sampleProducts);

        // Product List Area
        ProductListArea productListArea = new ProductListArea(productManager.getProducts());
        root.setCenter(productListArea.getProductListArea());

        // Navigation Bar
        NavigationBar navigationBar = new NavigationBar();
        root.setLeft(navigationBar.getNavigationBar(productListArea, productManager));

     // Top Layout: Filter by Price and Upload Product
        HBox topLayout = new HBox(10);
        //topLayout.setPadding(new Insets(10));
        topLayout.getStyleClass().add("top-layout");

        // Filter by Price Button
        Button filterByPriceButton = new Button("Filter by Price");
        filterByPriceButton.setOnAction(e -> {
            FilterMenu filterMenu = new FilterMenu();
            filterMenu.openPriceFilterDialog(productListArea, primaryStage);
        });

        // Upload Product Button
        Button uploadProductButton = new Button("Upload Product");
        uploadProductButton.setOnAction(e -> {
            OwnerUploadPage ownerUploadPage = new OwnerUploadPage();
            ownerUploadPage.start(new Stage());
        });

        // Add buttons to the top layout
        topLayout.getChildren().addAll(filterByPriceButton, uploadProductButton);
        root.setTop(topLayout);


     // Set the scene
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Link CSS
        primaryStage.setScene(scene);
        primaryStage.setTitle("Second-hand Goods Trading Platform");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
