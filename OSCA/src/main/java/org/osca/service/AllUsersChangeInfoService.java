package org.osca.service;

import org.osca.dao.AllUsersChangeInfoDAO;
import org.osca.dao.AllUsersChangeInfoDAOImpl;
import org.osca.model.ChangeInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllUsersChangeInfoService {

    private AllUsersChangeInfoDAO saDAO;

    public AllUsersChangeInfoService(){
        saDAO = new AllUsersChangeInfoDAOImpl();
    }

    public ArrayList<String> getDetailsOfMember(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getUserDetailsMember(uid);
    }

    public ArrayList<String> getDetailsOfShowOrganizer(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getUserDetailsSO(uid);
    }

    public boolean updateMember(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException {
        return saDAO.updateUserDetailsMember(uid, user);
    }

    public boolean updateShowOrganizer(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException {
        return saDAO.updateUserDetailsSO(uid, user);
    }

    public boolean updatePassMember(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException {
        return saDAO.updatePasswordMember(uid, oldPass, newPass);
    }

    public boolean updatePassShowOrganizer(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException {
        return saDAO.updatePasswordSO(uid, oldPass, newPass);
    }

    public boolean updateImagePathMember(int uid, String url) throws SQLException, ClassNotFoundException {
        return saDAO.updateProflePicMember(uid, url);
    }

    public boolean updateImagePathShowOrganizer(int uid, String url) throws SQLException, ClassNotFoundException {
        return saDAO.updateProflePicSO(uid, url);
    }

    public boolean daleteImagePathMember(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.daleteImageMember(uid);
    }

    public boolean daleteImagePathShowOrganizer(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.daleteImageSO(uid);
    }

    public boolean updateBankDetails(int uid, String accNo, String bankName, String bankBranch) throws SQLException, ClassNotFoundException {
        return saDAO.updateBank(uid,accNo, bankName, bankBranch);
    }
}
