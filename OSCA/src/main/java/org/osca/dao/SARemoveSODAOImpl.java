package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.ShowOrganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SARemoveSODAOImpl implements SARemoveSODAO {
    public ArrayList<ShowOrganizer> getShowOrganizers() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_id, first_name, last_name, email, phone_number, profile_pic FROM basic_users WHERE delete_status = 0 ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        while(resultSet.next()) {
            String path = null;
            if (resultSet != null) {
                if(resultSet.getString(6) == null){
                    path = "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
                }
                else{
                    path = resultSet.getString(6);
                }
                users.add(new ShowOrganizer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        path
                ));
            }
        }

        return users;
    }

    @Override
    public boolean delShowOrganizers(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q2 = "UPDATE basic_users SET delete_status = 1, deleted_by = ?, deleted_on = CURRENT_DATE WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q2);

        stmt.setInt(1,uid);
        stmt.setInt(2,so.getUid());


        return stmt.executeUpdate() > 0;
    }



    public boolean delMem(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q2 = "UPDATE members SET delete_status = 1, deleted_by = ?, deleted_on = CURRENT_DATE WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q2);

        stmt.setInt(1,uid);
        stmt.setInt(2,so.getUid());

        return stmt.executeUpdate() > 0;
    }

    public ArrayList<ShowOrganizer> getMem() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT member_id, first_name, last_name, email, phone_number, profile_pic FROM members WHERE delete_status = 0 AND Member_Active_Status = 'M' ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        while(resultSet.next()) {
            String path = null;
            if (resultSet != null) {
                if(resultSet.getString(6) == null){
                    path = "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
                }
                else{
                    path = resultSet.getString(6);
                }
                users.add(new ShowOrganizer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        path
                ));
            }
        }

        return users;
    }


    public boolean deOO(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q2 = "UPDATE officials SET delete_status = 1, deleted_by = ?, deleted_on = CURRENT_DATE WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q2);

        stmt.setInt(1,uid);
        stmt.setInt(2,so.getUid());

        return stmt.executeUpdate() > 0;

    }
    public ArrayList<ShowOrganizer> getOO() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT emp_id, first_name, last_name, email, phone_number, profile_pic FROM officials WHERE delete_status = 0 AND user_type = 3 ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        while(resultSet.next()) {
            String path = null;
            if (resultSet != null) {
                if(resultSet.getString(6) == null){
                    path = "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
                }
                else{
                    path = resultSet.getString(6);
                }
                users.add(new ShowOrganizer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        path
                ));
            }
        }

        return users;
    }


    public boolean delAdmin(ShowOrganizer so, int uid, int utype) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q2 = "UPDATE officials SET delete_status = 1, deleted_by = ?, deleted_on = CURRENT_DATE WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q2);

        stmt.setInt(1,uid);
        stmt.setInt(2,so.getUid());

        return stmt.executeUpdate() > 0;
    }

    public ArrayList<ShowOrganizer> getAdmin() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT emp_id, first_name, last_name, email, phone_number, profile_pic FROM officials WHERE delete_status = 0 AND user_type = 2 ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        while(resultSet.next()) {
            String path = null;
            if (resultSet != null) {
                if(resultSet.getString(6) == null){
                    path = "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
                }
                else{
                    path = resultSet.getString(6);
                }
                users.add(new ShowOrganizer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        path
                ));
            }
        }

        return users;
    }
}
