package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OOdbDAO {
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException;

    public String getOOName(int uid) throws SQLException, ClassNotFoundException;
}
