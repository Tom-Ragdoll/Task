package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.database.taskHandler;
import sample.model.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class listViewControl extends Controller{

    @FXML
    private JFXListView<task> listTask;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private JFXCheckBox normalCheck;

    @FXML
    private JFXCheckBox importantCheck;

    @FXML
    private JFXButton logout;

    private ObservableList<task> tasks;

    @FXML
    void initialize() throws SQLException {
        taskHandler handler= new taskHandler();

        refreshList(handler);


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
                taskField.setText("");
                try {
                    refreshList(handler);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        normalCheck.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(importantCheck);
        });

        importantCheck.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            reMoveSelect(normalCheck);
        });
    }
    private void refreshList(taskHandler handler)throws SQLException {

        tasks= FXCollections.observableArrayList();
        ResultSet resultSet = handler.getTasksByUser(this.getCurrID());

        while (resultSet.next()) {
            task currTask = new task();
            currTask.setTaskId(resultSet.getInt("taskid"));
            currTask.setContent(resultSet.getString("task"));
            currTask.setDateCreated(resultSet.getTimestamp("datecreate"));
            currTask.setDescription(resultSet.getInt("description"));
            tasks.addAll(currTask);
        }

        listTask.setItems(tasks);
        listTask.setCellFactory(cellControl -> new cellControl());

    }



}