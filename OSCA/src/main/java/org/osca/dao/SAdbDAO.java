package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SAdbDAO {
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException;

    public String getSAName(int uid) throws SQLException, ClassNotFoundException;
}
