package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OwnerUploadPage extends Application {

    private static final String UPLOAD_DIR = "src/images"; // Directory to save uploaded images

    @Override
    public void start(Stage primaryStage) {
        // Layout for the form
        GridPane formLayout = new GridPane();
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setPadding(new Insets(20));

        // Form fields
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField typeField = new TextField();
        TextField descriptionField = new TextField();
        Button uploadImageButton = new Button("Upload Picture");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        formLayout.add(new Label("Name:"), 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(new Label("Price:"), 0, 1);
        formLayout.add(priceField, 1, 1);
        formLayout.add(new Label("Type:"), 0, 2);
        formLayout.add(typeField, 1, 2);
        formLayout.add(new Label("Description:"), 0, 3);
        formLayout.add(descriptionField, 1, 3);
        formLayout.add(new Label("Pic:"), 0, 4);
        formLayout.add(uploadImageButton, 1, 4);

        // Image file holder
        final File[] selectedImageFile = new File[1];

        // Handle image upload
        uploadImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                selectedImageFile[0] = file;
                String uniqueFileName = System.currentTimeMillis() + "_" + file.getName();
                File destinationFile = new File(UPLOAD_DIR, uniqueFileName);

                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    FileOutputStream outputStream = new FileOutputStream(destinationFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                    inputStream.close();
                    outputStream.close();

                    imageView.setImage(new Image(destinationFile.toURI().toString()));
                    selectedImageFile[0] = destinationFile; // Update selected file path
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        formLayout.add(imageView, 2, 4);

        // Buttons
        Button submitButton = new Button("Add Product");
        Button resetButton = new Button("Reset");

        // Handle reset button
        resetButton.setOnAction(e -> {
            nameField.clear();
            priceField.clear();
            typeField.clear();
            descriptionField.clear();
            imageView.setImage(null);
        });

        // Handle submit button
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String price = priceField.getText();
            String type = typeField.getText();
            String description = descriptionField.getText();

            if (name.isEmpty() || price.isEmpty() || type.isEmpty() || selectedImageFile[0] == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields and upload an image.");
                alert.show();
                return;
            }

            // Simulate saving product information (you can integrate this with ProductManager)
            System.out.println("Product added: ");
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Type: " + type);
            System.out.println("Description: " + description);
            System.out.println("Image: " + selectedImageFile[0].getName());

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Product added successfully!");
            successAlert.showAndWait();

            // Reset form after submission
            resetButton.fire();
        });

        HBox buttonBox = new HBox(10, submitButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(formLayout);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Owner Product Upload");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
