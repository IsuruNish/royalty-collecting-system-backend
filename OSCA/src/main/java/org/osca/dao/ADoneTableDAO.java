package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ADoneTableDAO {
    public String  getTableDetails(String year,String month) throws SQLException, ClassNotFoundException;

}
