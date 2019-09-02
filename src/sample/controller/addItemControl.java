package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import sample.database.taskHandler;
import sample.model.task;

import java.util.Calendar;

public class addItemControl extends Controller{

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private CheckBox normalCheck;

    @FXML
    private CheckBox importantCheck;

    @FXML
    private JFXButton logout;

    @FXML
    void initialize() {
        taskHandler handler= new taskHandler();

        logout.setOnAction(event -> {
            logout.getScene().getWindow().hide();
            showNextScreen("login");
        });

        saveTaskButton.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskContent = taskField.getText().trim();
            int taskDescription = 0;
            if (importantCheck.isSelected()){ taskDescription=1; }

            if (!taskContent.equals("") ) {
                //create new task with input information
                task task = new task(this.getCurrID(), taskDescription, taskContent, timestamp);
                handler.insertTask(task);
                showNextScreen("listView");
            }
        });


        normalCheck.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(importantCheck);
        });

        importantCheck.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(normalCheck);
        });
    }

}
