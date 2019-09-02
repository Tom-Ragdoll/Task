package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.database.userHandler;
import sample.model.user;
import sample.shaker.shake;

public class signUpControl extends Controller{

    @FXML
    private JFXTextField FirstName;

    @FXML
    private JFXTextField LastName;

    @FXML
    private JFXTextField Username;

    @FXML
    private JFXCheckBox Male;

    @FXML
    private JFXCheckBox Female;

    @FXML
    private JFXPasswordField Password;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize(){

        userHandler handler = new userHandler();

        //remove female select if male is selected
        Male.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(Female);
        });

        //remove male select if female is selected
        Female.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(Male);
        });

        //pass the data to the database
        signUpButton.setOnAction(event -> {

            String firstName = FirstName.getText();
            String lastName = LastName.getText();
            String userName = Username.getText();
            String password = Password.getText();

            //check male or femake
            String gender="Male";
            if (Female.isSelected()) {
                gender = "Female";
            }

            //check is all information have at least 3 chars
            if (firstName.length() >= 3 && lastName.length() >= 3 &&
                    userName.length() >= 3 && password.length() >= 3) {


                user signUpUser = new user(firstName,lastName,userName,password,gender);
                handler.signUpUser(signUpUser);
                showNextScreen("login");
            } else {
                if(firstName.length() < 3){
                    shake Shaker = new shake(FirstName);
                    Shaker.shake();
                }
                if(lastName.length() < 3){
                    shake Shaker = new shake(LastName);
                    Shaker.shake();
                }
                if(userName.length() < 3){
                    shake Shaker = new shake(Username);
                    Shaker.shake();
                }
                if(password.length() < 3){
                    shake Shaker = new shake(Password);
                    Shaker.shake();
                }

            }
        });
    }
}