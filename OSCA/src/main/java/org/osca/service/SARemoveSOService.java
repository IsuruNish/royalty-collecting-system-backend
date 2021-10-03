package org.osca.service;

import org.osca.dao.SARemoveSODAO;
import org.osca.dao.SARemoveSODAOImpl;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;
import java.util.ArrayList;

public class SARemoveSOService {
    private SARemoveSODAO saRDAO;

    public SARemoveSOService(){
        saRDAO = new SARemoveSODAOImpl();
    }

    public boolean deleteShowOrganizers(ShowOrganizer so, String fname, String lname, String email, int uid) throws SQLException, ClassNotFoundException {
        return saRDAO.delShowOrganizers(so,fname, lname, email, uid);
    }

    public ArrayList<ShowOrganizer> getShowOrganizersToRemove() throws SQLException, ClassNotFoundException {
        return saRDAO.getShowOrganizers();
    }
}