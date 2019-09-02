package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class addControl extends Controller{
    @FXML
    private AnchorPane paneRoot;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label notTaskLabel;

    @FXML
    private ImageView addButton;

    @FXML
    private JFXButton logout;

    @FXML
    void initialize(){

        logout.setOnAction(event -> {
            logout.getScene().getWindow().hide();
            showNextScreen("login");
        });


        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            removeButton();
            paneRoot.getChildren().setAll(fadeIntoNextScreen("additem"));
        });
    }


    private void removeButton() {
        addButton.relocate(0, 20);
        notTaskLabel.relocate(0, 85);

        addButton.setOpacity(0);
        notTaskLabel.setOpacity(0);
    }
}
