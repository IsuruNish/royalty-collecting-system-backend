package org.osca.dao;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OOlicensePaymentDAO {
    public ArrayList<ArrayList<String>> getLicensePaymentInfo() throws SQLException, ClassNotFoundException;

    public Boolean paymentAccepted(int id) throws SQLException, ClassNotFoundException, IOException, MessagingException;
    public Boolean paymentDeny(int id) throws SQLException, ClassNotFoundException, IOException, MessagingException;
    }
