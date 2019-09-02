package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {
    //curr user ID
    private static int currID;

    protected int getCurrID() {
        return currID;
    }

    protected void setCurrID(int currID) {
        this.currID = currID;
    }

    protected void reMoveSelect(CheckBox check) {
        if (check.isSelected()) { check.setSelected(false); }
    }

    protected void showNextScreen(String next){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/"+next+".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected AnchorPane fadeIntoNextScreen(String next){
        AnchorPane formPane= new AnchorPane();
        try {
            formPane = FXMLLoader.load(
                    getClass().getResource("/sample/view/"+next+".fxml"));

            FadeTransition rootTransition = new FadeTransition(Duration.millis(800), formPane);
            rootTransition.setFromValue(0f);
            rootTransition.setToValue(1f);
            rootTransition.setCycleCount(1);
            rootTransition.setAutoReverse(false);
            rootTransition.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return formPane;
    }
}
