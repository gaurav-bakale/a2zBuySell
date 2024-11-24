package application;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListArea {

    private List<Product> allProducts;
    private VBox productListArea;
    private GridPane productGrid;

    public ProductListArea(List<Product> products) {
        this.allProducts = products;
        this.productListArea = new VBox(10);
        this.productGrid = new GridPane();
        productListArea.setPadding(new Insets(10));
        productGrid.setHgap(10);
        productGrid.setVgap(10);
        productListArea.getChildren().add(productGrid);
        showAllProducts();
    }

    public VBox getProductListArea() {
        return productListArea;
    }

    // Method to filter products by category
    public void filterProductsByCategory(String category) {
        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        updateProductList(filteredProducts);
    }

    // **Method to filter products by price range**
    public void filterProductsByPrice(double minPrice, double maxPrice) {
        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        updateProductList(filteredProducts);
    }

    // Updates the product grid with the filtered list of products
    private void updateProductList(List<Product> products) {
        productGrid.getChildren().clear();
        for (int i = 0; i < products.size(); i++) {
            ProductCard productCard = new ProductCard(products.get(i));
            productGrid.add(productCard.getProductCard(), i % 3, i / 3);
        }
    }

    // Displays all products
    private void showAllProducts() {
        updateProductList(allProducts);
    }
}
