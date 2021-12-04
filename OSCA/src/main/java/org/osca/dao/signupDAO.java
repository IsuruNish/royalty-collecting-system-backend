package org.osca.dao;

import org.osca.model.ShowOrganizer;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface signupDAO {
//    public ArrayList<Show_Organizer>getAllPeople() throws SQLException, ClassNotFoundException;

    public boolean addSOs(ShowOrganizer user) throws SQLException, ClassNotFoundException, MessagingException;

    public int getBasicUserID(ShowOrganizer user) throws SQLException, ClassNotFoundException;

    public boolean verifyEmailSO(int uid) throws SQLException, ClassNotFoundException;

    public boolean verifyEmailMem(int uid) throws SQLException, ClassNotFoundException;

    public boolean verifyEmailEmp(int uid) throws SQLException, ClassNotFoundException;

}

