package sample.database;

import sample.model.user;

import java.sql.*;

public class userHandler extends database{


    public void signUpUser(user u) {
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insertUser);

            statement.setString(1, u.getFirstName());
            statement.setString(2, u.getLastName());
            statement.setString(3, u.getUserName());
            statement.setString(4, u.getPassword());
            statement.setString(5, u.getGender());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getuser(String username, String password) {
        ResultSet resultSet = null;

        if (!username.equals("") || !password.equals("")) {
            // select all from users where username and password match
            try {
                PreparedStatement statement = getDbConnection().prepareStatement(findMatchUser);
                statement.setString(1, username);
                statement.setString(2, password);

                resultSet = statement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }
}
