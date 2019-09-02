package sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database extends config{

    protected Connection dbConnect;

    protected Connection getDbConnection(){
        try {
            Class.forName(classForName);
            dbConnect = DriverManager.getConnection(connectionString, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dbConnect;
    }

}
