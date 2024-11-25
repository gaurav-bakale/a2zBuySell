package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FilterMenu {

    public HBox getFilterMenu(ProductListArea productListArea, Stage primaryStage) {
        HBox filterMenu = new HBox(10);
        filterMenu.setPadding(new Insets(10));
        filterMenu.setStyle("-fx-background-color: #F0F0F0;");

        Button filterByPriceButton = new Button("Filter by Price");
        filterByPriceButton.setOnAction(e -> openPriceFilterDialog(productListArea, primaryStage));

        filterMenu.getChildren().addAll(filterByPriceButton);
        return filterMenu;
    }

    // Make this method public
    public void openPriceFilterDialog(ProductListArea productListArea, Stage parentStage) {
        // Create a new stage for filtering options
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter by Price");
        filterStage.initOwner(parentStage);

        // ComboBox for price ranges
        ComboBox<String> priceRangeComboBox = new ComboBox<>();
        priceRangeComboBox.getItems().addAll(
                "Below $50",
                "$50 - $100",
                "$100 - $500",
                "Above $500"
        );

        // Button to apply the filter
        Button applyButton = new Button("Apply Filter");
        applyButton.setOnAction(e -> {
            String selectedRange = priceRangeComboBox.getValue();
            if (selectedRange != null) {
                filterProductsByPriceRange(productListArea, selectedRange);
            }
            filterStage.close(); // Close the dialog after applying
        });

        // Layout for the dialog
        HBox layout = new HBox(10, priceRangeComboBox, applyButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FFFFFF;");

        // Scene and stage setup
        filterStage.setScene(new javafx.scene.Scene(layout, 300, 100));
        filterStage.show();
    }

    private void filterProductsByPriceRange(ProductListArea productListArea, String priceRange) {
        double minPrice = 0, maxPrice = Double.MAX_VALUE;

        switch (priceRange) {
            case "Below $50":
                maxPrice = 50;
                break;
            case "$50 - $100":
                minPrice = 50;
                maxPrice = 100;
                break;
            case "$100 - $500":
                minPrice = 100;
                maxPrice = 500;
                break;
            case "Above $500":
                minPrice = 500;
                break;
        }

        // Filter products by the selected price range
        productListArea.filterProductsByPrice(minPrice, maxPrice);
    }
}
