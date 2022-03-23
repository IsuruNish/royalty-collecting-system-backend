package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdbDAO {
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException;
}
