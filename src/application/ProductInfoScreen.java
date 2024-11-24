package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductInfoScreen extends Application {

    private static Product product; // Static variable to hold the selected product

    public static void setProduct(Product p) {
        product = p;
    }

    @Override
    public void start(Stage primaryStage) {
        if (product == null) {
            System.out.println("No product data available.");
            return;
        }

        // Product Details
        String productName = product.getTitle();
        String productDescription = product.getDescription();
        double productPrice = product.getPrice();
        String contactInfo = "contact@example.com";  // Placeholder for contact info

        // Layout Components
        Label nameLabel = new Label(productName);
        nameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ImageView imageView = null;
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            String imagePath = product.getImages().get(0);
            Image productImage = new Image(imagePath);
            imageView = new ImageView(productImage);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
        }

        Label descriptionLabel = new Label("Description: " + productDescription);
        descriptionLabel.setStyle("-fx-font-size: 16px;");

        Button priceButton = new Button("Price: $" + productPrice);
        Button contactButton = new Button("Contact Info");
        contactButton.setOnAction(e -> System.out.println("Contacting seller at: " + contactInfo));

        // Styling buttons
        priceButton.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-size: 14px;");
        contactButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-size: 14px;");

        // Layout Organization
        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.CENTER_LEFT);
        imageBox.setPadding(new Insets(20));

        VBox descriptionBox = new VBox(10, descriptionLabel, priceButton, contactButton);
        descriptionBox.setPadding(new Insets(20));
        descriptionBox.setAlignment(Pos.TOP_LEFT);

        HBox mainContentBox = new HBox(20, imageBox, descriptionBox);
        mainContentBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(nameLabel);
        root.setCenter(mainContentBox);
        BorderPane.setAlignment(nameLabel, Pos.CENTER);
        BorderPane.setMargin(nameLabel, new Insets(10, 0, 10, 0));

        // Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Product Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
