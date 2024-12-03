package com.a2zbuysell.a2zbuysell;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class createAccountPage {

    public Button goBackButton;
    public VBox FullPageVbox;
    public Button createAccountButton;
    public HBox appTitleBar;
    public Label appTitleLabel;
    public Label formTitle;
    public HBox homePageTitleBarHbox;

    @FXML
    private Text createAccountMessageText;

    @FXML
    private Label createNewAccount;

    @FXML
    private TextField email;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField firstName;

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField lastName;

    @FXML
    private Label lastNameLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button privacyButton;

    @FXML
    private Button termsButton;

    @FXML
    private TextField userName;

    @FXML
    private Label userNameLabel;

    @FXML
    void createAccountButtonClick(ActionEvent event) throws SQLException {
        //coding

        String usernametext = userName.getText();
        String pwdtext = password.getText();
        String emailText = email.getText();
        String firstNameText=firstName.getText();
        String lastNameText=lastName.getText();
        String phoneNumberText=phoneNumber.getText();

        if (usernametext.isEmpty() || pwdtext.isEmpty() || emailText.isEmpty()) {
            createAccountMessageText.setText("Please fill in all fields!");
            return;
        }
        DBManager dbm = new DBManager();
        String query2 = "select username from users where username = ? ";
        List<List<Object>> results = dbm.executeQuery(query2, userName.getText());
        if (!results.isEmpty()){
            createAccountMessageText.setText("Account has existed!");
            userName.clear();
            password.clear();
            email.clear();
            firstName.clear();
            lastName.clear();
            phoneNumber.clear();

            return;
        }

        String phoneRegex = "\\d*";
        if (!phoneNumberText.matches(phoneRegex)) {
            createAccountMessageText.setText("phone number only is number!");
            phoneNumber.clear();
            return;
        }

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!emailText.matches(emailRegex)) {
            createAccountMessageText.setText("Invalid email format!");
            email.clear();
            return;
        }
        String query = "INSERT INTO users (username, email, password, first_name, last_name, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        int i= dbm.executeUpdate(query,usernametext,pwdtext,emailText,firstNameText,lastNameText,phoneNumberText);
        if (i > 0) {
            createAccountMessageText.setText("Account created successfully!");
        } else {
            createAccountMessageText.setText("Failed to create account. Try again!");
        }



    }

    @FXML
    void login(MouseEvent event) {

    }


    public void goBackButtonClick(ActionEvent event) throws IOException {

        //coding
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