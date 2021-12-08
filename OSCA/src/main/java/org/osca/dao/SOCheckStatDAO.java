package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SOCheckStatDAO {
    public ArrayList<ArrayList<Double>>  getDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getYearDetails(int uid) throws SQLException, ClassNotFoundException;
}
