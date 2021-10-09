package org.osca.service;

import org.osca.dao.SAchangeinfoDAO;
import org.osca.dao.SAchangeinfoDAOimpl;
import org.osca.dao.SAdbDAO;
import org.osca.dao.SAdbDAOImpl;
import org.osca.model.SuperAdminDashboard;

import java.sql.SQLException;
import java.util.ArrayList;

public class SAchangeinfoService {
    private SAchangeinfoDAO saDAO;

    public SAchangeinfoService(){
        saDAO = new SAchangeinfoDAOimpl();
    }

    public ArrayList<String> getCIDetails(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getUserDetails(uid);
    }

    public boolean updateUser(int uid, SuperAdminDashboard user) throws SQLException, ClassNotFoundException {
        return saDAO.updateUserDetails(uid, user);
    }

    public boolean updatePass(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException {
        return saDAO.updatePassword(uid, oldPass, newPass);
    }

    public boolean updateImagePath(int uid, String url) throws SQLException, ClassNotFoundException {
        return saDAO.updateProflePic(uid, url);
    }

    public boolean daleteImagePath(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.daleteImage(uid);
    }


}
