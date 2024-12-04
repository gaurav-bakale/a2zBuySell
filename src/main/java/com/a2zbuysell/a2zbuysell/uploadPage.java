package com.a2zbuysell.a2zbuysell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class uploadPage {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField subcategoryField;

    @FXML
    private Label imageLabel;

    private File selectedImageFile;

    private final DBManager dbm = new DBManager();

    @FXML
    private void uploadPicture(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        Stage stage = (Stage) imageLabel.getScene().getWindow();
        selectedImageFile = fileChooser.showOpenDialog(stage);

        if (selectedImageFile != null) {
            imageLabel.setText(selectedImageFile.getName());
        } else {
            imageLabel.setText("No file selected");
        }
    }

    @FXML
    private void addProduct(ActionEvent event) throws SQLException, IOException {
        String name = nameField.getText();
        String priceText = priceField.getText();
        String description = descriptionField.getText();
        String categoryName = categoryField.getText().trim().toLowerCase(); // Case-insensitive
        String subcategoryName = subcategoryField.getText().trim().toLowerCase(); // Case-insensitive

        if (name.isEmpty() || priceText.isEmpty() || description.isEmpty() || categoryName.isEmpty() || subcategoryName.isEmpty() || selectedImageFile == null) {
            System.out.println("Please fill all fields and upload an image.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            // Insert or fetch category
            String categoryQuery = "INSERT OR IGNORE INTO categories (name) VALUES (?)";
            dbm.executeUpdate(categoryQuery, categoryName);

            String categoryIdQuery = "SELECT id FROM categories WHERE LOWER(name) = ?";
            int categoryId = (int) dbm.executeQuery(categoryIdQuery, categoryName).get(0).get(0);

            // Insert or fetch subcategory
            String subcategoryQuery = "INSERT OR IGNORE INTO subcategories (name, category_id) VALUES (?, ?)";
            dbm.executeUpdate(subcategoryQuery, subcategoryName, categoryId);

            String subcategoryIdQuery = "SELECT id FROM subcategories WHERE LOWER(name) = ?";
            int subcategoryId = (int) dbm.executeQuery(subcategoryIdQuery, subcategoryName).get(0).get(0);

            // Insert product
            String productQuery = "INSERT INTO products (title, description, price, condition, category_id, subcategory_id, user_id, date_created) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            dbm.executeUpdate(productQuery, name, description, price, "New", categoryId, subcategoryId, 1);

            // Insert image
            String lastIdQuery = "SELECT last_insert_rowid()";
            int productId = (int) dbm.executeQuery(lastIdQuery).get(0).get(0);

            String imageQuery = "INSERT INTO product_images (product_id, image) VALUES (?, ?)";
            byte[] imageBytes = Files.readAllBytes(selectedImageFile.toPath());
            dbm.executeUpdate(imageQuery, productId, imageBytes);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upload Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your product has been uploaded successfully!");
            alert.showAndWait();

            resetForm();
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format.");
        }
    }

    @FXML
    private void resetForm() {
        nameField.clear();
        priceField.clear();
        descriptionField.clear();
        categoryField.clear();
        subcategoryField.clear();
        imageLabel.setText("No file selected");
        selectedImageFile = null;
        System.out.println("Form reset.");
    }

    @FXML
    private void goBackToPreviousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-page.fxml"));
        Scene homePageScene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homePageScene);
        stage.setTitle("Home - A2Z Buy Sell");
        stage.show();
    }
}
