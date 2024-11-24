package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ProductCard {
    private Product product;

    public ProductCard(Product product) {
        this.product = product;
    }

    public VBox getProductCard() {
        VBox productCard = new VBox(10);
        productCard.setAlignment(Pos.CENTER);
        productCard.setStyle("-fx-border-color: #D3D3D3; -fx-padding: 10;");

        ImageView imageView = new ImageView(new Image(product.getImages().get(0)));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(product.getTitle());
        productCard.getChildren().addAll(imageView, nameLabel);

        // Add click event to open ProductInfoScreen
        productCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ProductInfoScreen.setProduct(product); // Pass selected product to ProductInfoScreen
                Stage productInfoStage = new Stage();
                try {
                    new ProductInfoScreen().start(productInfoStage); // Open product details screen
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return productCard;
    }
}
