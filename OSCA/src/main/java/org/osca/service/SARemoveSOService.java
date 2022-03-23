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

    public boolean deleteShowOrganizers(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException {
        return saRDAO.delShowOrganizers(so,uid, utype);
    }

    public ArrayList<ShowOrganizer> getShowOrganizersToRemove() throws SQLException, ClassNotFoundException {
        return saRDAO.getShowOrganizers();
    }


    public boolean deleteMembers(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException {
        return saRDAO.delMem(so,uid, utype);
    }

    public ArrayList<ShowOrganizer> getMembersToRemove() throws SQLException, ClassNotFoundException {
        return saRDAO.getMem();
    }

    public boolean deleteOSCAOfficials(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException {
        return saRDAO.deOO(so,uid, utype);
    }

    public ArrayList<ShowOrganizer> getOSCAofficalsToRemove() throws SQLException, ClassNotFoundException {
        return saRDAO.getOO();
    }

    public boolean deleteAdmins(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException {
        return saRDAO.delAdmin(so,uid, utype);
    }

    public ArrayList<ShowOrganizer> getAdminsToRemove() throws SQLException, ClassNotFoundException {
        return saRDAO.getAdmin();
    }

}
