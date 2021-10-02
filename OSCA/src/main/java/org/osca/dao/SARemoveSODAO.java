package org.osca.dao;

import org.osca.model.ShowOrganizer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SARemoveSODAO {
    public ArrayList<ShowOrganizer> getShowOrganizers() throws SQLException, ClassNotFoundException;

    public boolean delShowOrganizers(ShowOrganizer so, int uid) throws SQLException, ClassNotFoundException;
}
