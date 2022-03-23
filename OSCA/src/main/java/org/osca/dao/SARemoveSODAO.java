package org.osca.dao;

import org.osca.model.ShowOrganizer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SARemoveSODAO {
    public ArrayList<ShowOrganizer> getShowOrganizers() throws SQLException, ClassNotFoundException;

    public boolean delShowOrganizers(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException;


    public boolean delMem(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException;
    public ArrayList<ShowOrganizer> getMem() throws SQLException, ClassNotFoundException;


    public boolean deOO(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException;
    public ArrayList<ShowOrganizer> getOO() throws SQLException, ClassNotFoundException;


    public boolean delAdmin(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException;
    public ArrayList<ShowOrganizer> getAdmin() throws SQLException, ClassNotFoundException;
}
