package org.osca.dao;

import java.sql.SQLException;

public interface ImageDAO {
    public String getEmpPhotofilePic(int uid) throws SQLException, ClassNotFoundException;

    public String getMemPhotofilePic(int uid) throws SQLException, ClassNotFoundException;

    public String getUserPhotofilePic(int uid) throws SQLException, ClassNotFoundException;

    }
