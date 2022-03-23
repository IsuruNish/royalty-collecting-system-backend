package org.osca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String host = "localhost";
    private final String port = "3306";
    private final String database = "osca_database";
    private final String username = "root";
    private final String passdword = "";

    private static Connection connection;
    private static DBConnection dbcon;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://"+host+":"+port+"/"+database;
        connection = DriverManager.getConnection(url, username, passdword);
    }

    public static DBConnection getObj() throws SQLException, ClassNotFoundException {
        if(dbcon == null) dbcon = new DBConnection();

        return dbcon;
    }

    public static Connection getConnection(){
        return connection;
    }
}

