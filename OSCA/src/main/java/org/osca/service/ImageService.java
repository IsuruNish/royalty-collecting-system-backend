package org.osca.service;

import org.osca.dao.ImageDAO;
import org.osca.dao.ImageDAOimpl;

import java.sql.SQLException;

//only to get the image path
public class ImageService {
    private ImageDAO saDAO;

    public ImageService(){
        saDAO = new ImageDAOimpl();
    }
    public String getEmpDP(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getEmpPhotofilePic(uid);
    }

    public String getMemDP(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getMemPhotofilePic(uid);
    }

    public String getUserDP(int uid) throws SQLException, ClassNotFoundException {
        return saDAO.getUserPhotofilePic(uid);
    }
}
