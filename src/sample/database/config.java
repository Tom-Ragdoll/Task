package sample.database;

public class config {
    //config

    protected String connectionString = "jdbc:mysql://localhost:3306/task?serverTimezone=GMT";
    protected String classForName = "com.mysql.cj.jdbc.Driver";
    protected String dbUser = "root";
    protected String dbPass = "1234";

    //users table
    protected String insertUser = "INSERT INTO users(firstname,lastname,username,password," +
            "gender)VALUES(?,?,?,?,?)";

    protected String findMatchUser = "SELECT * FROM users WHERE " +
            "username=? AND password=?";

    //tasks table

    protected String getTask = "SELECT COUNT(*) FROM tasks WHERE userid=?";

    protected String insertTask = "INSERT INTO tasks(userid,datecreate,description," +
            "task)VALUES(?,?,?,?)";

    protected String deleteTask = "DELETE FROM tasks WHERE taskid=?";

    protected String getByUser = "SELECT * FROM tasks WHERE userid=?"+
            " ORDER BY description DESC";
}
