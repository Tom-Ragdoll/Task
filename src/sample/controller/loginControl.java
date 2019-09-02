package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.database.taskHandler;
import sample.database.userHandler;
import sample.shaker.shake;

import java.sql.ResultSet;
import java.sql.SQLException;

public class loginControl extends Controller{

    private userHandler userhandler;
    private taskHandler taskhandler;

    @FXML
    private JFXTextField Username;

    @FXML
    private JFXPasswordField Password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton Signup;

    @FXML
    void initialize(){

        userhandler = new userHandler();
        taskhandler = new taskHandler();



        //login in
        loginButton.setOnAction(event -> {

            ResultSet resultSet = userhandler.getuser(Username.getText(),Password.getText());
            try {
                if (resultSet.next()){

                    this.setCurrID(resultSet.getInt("userid"));
                    int taskNumber=taskhandler.getTasksCount(this.getCurrID());
                    loginButton.getScene().getWindow().hide();

                    if(taskNumber==0) {//if these is not task jump to add item page
                        showNextScreen("add");

                    }else {//take user to list view page
                        showNextScreen("listView");
                    }
                }
                else{
                    //shake if no such user exist
                    shake userNameShaker = new shake(Username);
                    shake passwordShaker = new shake(Password);
                    passwordShaker.shake();
                    userNameShaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        //take user to the sign up page
        Signup.setOnAction(event -> {
            //take user to sign up page
            loginButton.getScene().getWindow().hide();
            showNextScreen("signUp");

        });

    }
}
