package org.osca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if(connection==null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/tutorials","root","password");

        }
        return connection;
    }
}
