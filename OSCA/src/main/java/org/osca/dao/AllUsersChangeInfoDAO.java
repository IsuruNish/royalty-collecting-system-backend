package org.osca.dao;

import org.osca.model.ChangeInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AllUsersChangeInfoDAO{
    public ArrayList<String> getUserDetailsMember(int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getUserDetailsSO(int uid) throws SQLException, ClassNotFoundException;

    public boolean updateUserDetailsMember(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException;

    public boolean updateUserDetailsSO(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException;

    public boolean updatePasswordMember(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException;

    public boolean updatePasswordSO(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException;

    public boolean updateProflePicMember(int uid, String url) throws SQLException, ClassNotFoundException;

    public boolean updateProflePicSO(int uid, String url) throws SQLException, ClassNotFoundException;

    public boolean daleteImageMember(int uid) throws SQLException, ClassNotFoundException;

    public boolean daleteImageSO(int uid) throws SQLException, ClassNotFoundException;

    public boolean updateBank(int uid, String accNo, String bankName, String bankBranch) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllEmails2(int uid) throws SQLException, ClassNotFoundException;

}
