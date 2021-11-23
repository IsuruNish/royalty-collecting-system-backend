package org.osca.service;

import org.osca.dao.RequestsDAO;
import org.osca.dao.RequestsDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class RequestsService {
    private RequestsDAO obj;

    public RequestsService(){
        obj = new RequestsDAOImpl();
    }

    public ArrayList<ArrayList<String>> getLicenseAppReqData(int type) throws SQLException, ClassNotFoundException {
        return obj.getLicenseAppReq(type);
    }

    public ArrayList<ArrayList<String>> getSongRegReqData(int type) throws SQLException, ClassNotFoundException {
        return obj.getSongRegReq(type);
    }

    public ArrayList<ArrayList<String>> getSongOwnReqData(int type) throws SQLException, ClassNotFoundException {
        return obj.getSongOwnReq(type);
    }

    public ArrayList<ArrayList<String>> getSongDelReqData(int type) throws SQLException, ClassNotFoundException {
        return obj.getSongDelReq(type);
    }


    public Boolean AcceptLicenseRequest(int id, int type) throws SQLException, ClassNotFoundException {
        return obj.setLicenseReqAccept(id, type);
    }
    public Boolean DenyLicenseRequest(int id) throws SQLException, ClassNotFoundException {
        return obj.setLicenseReqDeny(id);
    }

    public Boolean AcceptSongRegRequest(int id, int type) throws SQLException, ClassNotFoundException {
        return obj.setSongRegReqAccept(id, type);
    }
    public Boolean DenySongRegRequest(int id) throws SQLException, ClassNotFoundException {
        return obj.setSongRegReqDeny(id);
    }

    public Boolean AcceptSongOwnRequest(int id, int type) throws SQLException, ClassNotFoundException {
        return obj.setSongOwnReqAccept(id, type);
    }
    public Boolean DenySongOwnRequest(int id) throws SQLException, ClassNotFoundException {
        return obj.setSongOwnReqDeny(id);
    }

    public Boolean AccpetSongDelRequest(int id, int type) throws SQLException, ClassNotFoundException {
        return obj.setSongDelReqAccept(id, type);
    }
    public Boolean DenySongDelRequest(int id) throws SQLException, ClassNotFoundException {
        return obj.setSongDelReqDeny(id);
    }
}
