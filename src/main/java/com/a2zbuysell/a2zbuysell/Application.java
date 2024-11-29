package com.a2zbuysell.a2zbuysell;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("a2zBuySell");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, SQLException {
        launch();
    }
}