package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationBar {

    public VBox getNavigationBar(ProductListArea productListArea, ProductManager productManager) {
        VBox navigationBar = new VBox(10);
        navigationBar.setPadding(new Insets(10));
        navigationBar.setStyle("-fx-background-color: #F0F0F0;");

        // Example category buttons
        Button electronicsButton = new Button("Electronics");
        Button furnitureButton = new Button("Furniture");
        Button booksButton = new Button("Books");

        // Add functionality to filter by category
        electronicsButton.setOnAction(e -> productListArea.filterProductsByCategory("Electronics"));
        furnitureButton.setOnAction(e -> productListArea.filterProductsByCategory("Furniture"));
        booksButton.setOnAction(e -> productListArea.filterProductsByCategory("Books"));

        // Add "Upload Product" button for the owner
        Button uploadProductButton = new Button("Upload Product");
        uploadProductButton.setOnAction(e -> {
            OwnerUploadPage ownerUploadPage = new OwnerUploadPage();
            ownerUploadPage.start(new javafx.stage.Stage());
        });

        // Add buttons to the navigation bar
        navigationBar.getChildren().addAll(electronicsButton, furnitureButton, booksButton, uploadProductButton);

        return navigationBar;
    }
}
