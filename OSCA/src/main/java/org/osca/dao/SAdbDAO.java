package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SAdbDAO {
    public ArrayList<String> getDetails(String fname, String lname, String email) throws SQLException, ClassNotFoundException;
}
