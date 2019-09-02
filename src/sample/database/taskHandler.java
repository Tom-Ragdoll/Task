package sample.database;

import sample.model.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class taskHandler extends database{

    public void insertTask(task task){
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insertTask);

            statement.setInt(1, task.getUserId());
            statement.setTimestamp(2, task.getDateCreated());
            statement.setInt(3, task.getDescription());
            statement.setString(4, task.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Delete Task
    public void deleteTask(int taskId) {
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(deleteTask);

            statement.setInt(1, taskId);
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getTasksByUser(int userId) {
        ResultSet resultTasks=null;
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(getByUser);
            statement.setInt(1, userId);
            resultTasks= statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTasks;
    }

    public int getTasksCount(int userId) {

        PreparedStatement statement = null;
        try {
            statement = getDbConnection().prepareStatement(getTask);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt("count(*)");
            }else{
                return 0;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
