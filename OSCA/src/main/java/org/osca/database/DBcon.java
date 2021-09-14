package org.osca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

    private final String host = "localhost";
    private final String port = "3306";
    private final String database = "json";
    private final String username = "root";
    private final String passdword = "";

    private Connection connection;
    private static DBcon dbcon;

    private DBcon() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://"+host+":"+port+"/"+database;
        connection = DriverManager.getConnection(url, username, passdword);
    }

    public static DBcon getObj() throws SQLException, ClassNotFoundException {
        if(dbcon == null) dbcon = new DBcon();

        return dbcon;
    }

    public Connection getConnecton(){
        return connection;
    }
}

