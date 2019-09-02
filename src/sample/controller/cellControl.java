package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.database.taskHandler;
import sample.model.task;

import java.io.IOException;

public class cellControl extends JFXListCell<task> {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label description;

    @FXML
    private Label task;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView deleteButton;

    private FXMLLoader fxmlLoader;

    private taskHandler handler;

    @Override
    public void updateItem(task myTask, boolean empty) {

        handler = new taskHandler(); //main change

        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        }else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            description.setText(myTask.getDescriptionString());
            task.setText(myTask.getContent());
            dateLabel.setText(myTask.getDateCreated().toString());

            if(myTask.getDescription()==1){
                Image icon =new Image("sample/assest/important.png");
                iconImageView.setImage(icon);
            }else{
                Image icon =new Image("sample/assest/normal.png");
                iconImageView.setImage(icon);
            }

            deleteButton.setOnMouseClicked(event -> {
                handler.deleteTask(myTask.getTaskId());
                getListView().getItems().remove(getItem());

            });

            setText(null);
            setGraphic(rootAnchorPane);
        }
    }

}
