package org.osca.dao;

import org.osca.model.ShowOrganizer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SARemoveSODAO {
    public ArrayList<ShowOrganizer> getShowOrganizers() throws SQLException, ClassNotFoundException;

    public boolean delShowOrganizers(ShowOrganizer so, String fname, String lname, String email, int uid) throws SQLException, ClassNotFoundException;
}
