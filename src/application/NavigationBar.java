package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationBar {

    public VBox getNavigationBar(ProductListArea productListArea, ProductManager productManager) {
        VBox navigationBar = new VBox(10);
        navigationBar.setPadding(new Insets(10));
        navigationBar.setStyle("-fx-background-color: #F0F0F0;");
        navigationBar.getStyleClass().add("navigation-bar");

        // Category buttons
        Button electronicsButton = new Button("Electronics");
        Button furnitureButton = new Button("Furniture");
        Button booksButton = new Button("Books");

        // Add functionality to filter by category
        electronicsButton.setOnAction(e -> productListArea.filterProductsByCategory("Electronics"));
        furnitureButton.setOnAction(e -> productListArea.filterProductsByCategory("Furniture"));
        booksButton.setOnAction(e -> productListArea.filterProductsByCategory("Books"));

        // Add category buttons to the navigation bar
        navigationBar.getChildren().addAll(electronicsButton, furnitureButton, booksButton);

        return navigationBar;
    }
}
