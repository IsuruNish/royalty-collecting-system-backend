//package org.osca.service;
//
//import org.osca.dao.ACheckStatDAO;
//import org.osca.dao.ACheckStatDAOImpl;
//import org.osca.dao.ADoneTableDAO;
//import org.osca.dao.ADoneTableDAODAOImpl;
//import org.osca.model.ADoneTable;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ADoneTableService {
//
//    private ADoneTableDAO aDoneTableDAO;
//
//    public ADoneTableService(){
//        aDoneTableDAO = new ADoneTableDAODAOImpl();
//    }
//
//    public String getTable(String year,String month) throws SQLException, ClassNotFoundException {
//
//        return aDoneTableDAO.getTableDetails(year,month);
//    }
//}
