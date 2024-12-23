package com.a2zbuysell.a2zbuysell;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginPage {

    public HBox appTitleBar;
    public Label appTitleLabel;
    public Label userNameLabel;
    public Text loginMessageText;
    public Button aboutButton;
    public Button contactButton;
    public Button termsButton;
    public Button privacyButton;
    public VBox FullPageVbox;
    public Button createAccountButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label A2ZBuySell;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField userName;

    @FXML
    void login(MouseEvent event) {

        DBManager dbm = new DBManager();

        String query = "select username, password from users where username = ? and password = ?";

        boolean loginCheck = false;
        try {
            List<List<Object>> results = dbm.executeQuery(query, userName.getText(), password.getText());

            if (results.isEmpty()){
                loginCheck = false;
            } else {
                loginCheck = true;
            }
            // Process the results
            for (List<Object> row : results) {
                System.out.println("Row: " + row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (loginCheck){
        if(true){
            System.out.println("User logged in");
            loginMessageText.setText("Success!");

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("home-page.fxml"));

                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                double currentWidth = stage.getWidth();
                double currentHeight = stage.getHeight();

                stage.setScene(scene);

                stage.setWidth(currentWidth);
                stage.setHeight(currentHeight);

                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.print("Login failed");
            loginMessageText.setText("Either username or password is incorrect");
            loginMessageText.setFill(Color.RED);
        }
    }

    @FXML
    void initialize() {
    }

    public void createAccountButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-account-page.fxml"));

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
