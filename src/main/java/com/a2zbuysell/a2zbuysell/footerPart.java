package com.a2zbuysell.a2zbuysell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class footerPart {

    public Text clickHeretoExit;
    public HBox homePageTitleBarHbox;
    @FXML
    private Button aboutButton;

    @FXML
    private Button contactButton;

    @FXML
    private Button privacyButton;

    @FXML
    private Button termsButton;

    @FXML
    void handleAboutButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("about-page.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();

    }

    @FXML
    void handleContactButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("contact-page.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();

    }

    @FXML
    void handlePrivacyButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("privacy-page.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();

    }

    @FXML
    void handleTermsButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("terms-page.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();

    }

    public void clickHereToExitClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        stage.setScene(scene);

        stage.setWidth(currentWidth);
        stage.setHeight(currentHeight);

        stage.show();
    }
}
