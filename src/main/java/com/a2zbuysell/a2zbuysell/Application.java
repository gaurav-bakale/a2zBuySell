package com.a2zbuysell.a2zbuysell;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

            // Start the application
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setTitle("a2zBuySell");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, SQLException {
        launch();
    }
}