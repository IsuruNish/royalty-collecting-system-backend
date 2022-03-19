package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AndroidPaymentDAO {
    public ArrayList<ArrayList<String>> getUpcomingPaymentsForAndroid(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getPastPaymentsForAndroid(int uid) throws SQLException, ClassNotFoundException;

}
