package org.osca.dao;

import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.service.SAdashboardService;
import sun.applet.Main;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestsDAOImpl implements RequestsDAO{

    public ArrayList<ArrayList<String>> getLicenseAppReq(int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT concert_id, Concert_Name, user_id, concert_date, venue, type,  Payment_slip_link  FROM concert WHERE status = ? AND rejected = 0;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, type);
        ResultSet resultSet = stmt.executeQuery();
        ResultSet rs;
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();

            String q4 = "SELECT first_name, last_name FROM basic_users WHERE user_id = ? ;";
            PreparedStatement stmt2 = connection.prepareStatement(q4);

            stmt2.setInt(1, resultSet.getInt(3));
            rs = stmt2.executeQuery();

            String fname = null;
            String lname = null;

            if (rs.next()) {
                fname = rs.getString(1);
                lname = rs.getString(2);
            }

            data.add(resultSet.getString(1));
            data.add(resultSet.getString(2));
            data.add(fname);
            data.add(lname);
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(5));
            data.add(resultSet.getString(6));
            data.add(resultSet.getString(7));
            data.add(resultSet.getString(3));

            finalOne.add(data);
        }

        return finalOne;
    }

    public ArrayList<ArrayList<String>> getSongRegReq(int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT temp_song_id, song_name, version, published_year, documentation_link, member_id, emp_id FROM song_requests WHERE status = ? AND Type_of_request = 1 AND rejected = 0 ;";
        PreparedStatement stmt = connection.prepareStatement(q3);


        stmt.setInt(1, type);
        ResultSet resultSet = stmt.executeQuery();

        ResultSet rs;
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        String fname = null;
        String lname = null;

        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();
            int id = 0;

            if (resultSet.getInt(7) == 0){
                String q4 = "SELECT first_name, last_name FROM members WHERE member_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(6));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(6);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            else{
                String q4 = "SELECT first_name, last_name FROM officials WHERE emp_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(7));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(7);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            data.add(resultSet.getString(1));
            data.add(resultSet.getString(2));
            data.add(resultSet.getString(3));
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(5));
            data.add(fname);
            data.add(lname);
            data.add(String.valueOf(id));

            finalOne.add(data);

            fname = null;
            lname = null;
        }

        return finalOne;
    }


    public ArrayList<ArrayList<String>> getSongOwnReq(int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT temp_song_id, song_name, version, published_year, documentation_link, member_id, emp_id FROM song_requests WHERE status = ? AND Type_of_request = 2 AND rejected = 0;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, type);
        ResultSet resultSet = stmt.executeQuery();

        ResultSet rs;
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        String fname = null;
        String lname = null;

        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();
            int id = 0;

            if (resultSet.getInt(7) == 0){
                String q4 = "SELECT first_name, last_name FROM members WHERE member_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(6));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(6);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            else{
                String q4 = "SELECT first_name, last_name FROM officials WHERE emp_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(7));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(7);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            data.add(resultSet.getString(1));
            data.add(resultSet.getString(2));
            data.add(resultSet.getString(3));
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(5));
            data.add(fname);
            data.add(lname);
            data.add(String.valueOf(id));

            finalOne.add(data);

            fname = null;
            lname = null;
        }

        return finalOne;
    }

    public ArrayList<ArrayList<String>> getSongDelReq(int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT temp_song_id, song_name, version, published_year, documentation_link, member_id, emp_id FROM song_requests WHERE status = ? AND Type_of_request = 3 AND rejected = 0;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, type);
        ResultSet resultSet = stmt.executeQuery();

        ResultSet rs;
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        String fname = null;
        String lname = null;

        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();
            int id = 0;

            if (resultSet.getInt(7) == 0){
                String q4 = "SELECT first_name, last_name FROM members WHERE member_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(6));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(6);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            else{
                String q4 = "SELECT first_name, last_name FROM officials WHERE emp_id = ? ;";
                PreparedStatement stmt2 = connection.prepareStatement(q4);
                stmt2.setInt(1, resultSet.getInt(7));
                rs = stmt2.executeQuery();
                id = resultSet.getInt(7);
                if (rs.next()) {
                    fname = rs.getString(1);
                    lname = rs.getString(2);
                }
            }

            data.add(resultSet.getString(1));
            data.add(resultSet.getString(2));
            data.add(resultSet.getString(3));
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(5));
            data.add(fname);
            data.add(lname);
            data.add(String.valueOf(id));

            finalOne.add(data);

            fname = null;
            lname = null;
        }

        return finalOne;
    }



    public Boolean setLicenseReqAccept(int id, int type) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET status = ? WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,type);
        stmt.setInt(2,id);

        if (type == 2) {
            boolean done1 = stmt.executeUpdate() > 0;
            boolean done2 = newLicense(id);

            boolean done3 = isCloseConcert(id);


//            ArrayList<String> dataForEmail = getLicenseEmialDetails(id);
//            Mail objMail = new Mail();
//            SAdashboardService serviceSA = new SAdashboardService();
//            String fulName = serviceSA.getShowOrganizerFULLName(Integer.parseInt(dataForEmail.get(6)));
//            String emailSO = serviceSA.getShowOrganizerEmail(Integer.parseInt(dataForEmail.get(6)));
//            objMail.licenseEmail("",fulName, dataForEmail, emailSO );

            ArrayList <Integer> songIDs = new ArrayList<>();
            ArrayList <Integer> tempSongIDs = new ArrayList<>();
            ArrayList <ArrayList <Integer>> composerIDs = new ArrayList<>();
            ArrayList <ArrayList <Integer>> writterIDs = new ArrayList<>();
            String concertDate = null;
            double totalFee = 0;

            boolean done10 = false;
            boolean done20 = false;
            boolean done30 = false;
            boolean done40 = false;
            if (done3){
                concertDate = getDateForConcert(id);
                songIDs = getSongIDsInConcert(id);
                tempSongIDs = getTempSongIDsInConcert(songIDs);
                totalFee = getTotalFeeForConcert(id);

                totalFee = totalFee/ tempSongIDs.size();
//                totalFee = 5000.0;

                int index = 0;
                for (Integer tempSongID : tempSongIDs) {
                    writterIDs = getWrittersForConcert(tempSongID);
                    composerIDs = getComposersForConcert(tempSongID);
                    done10 = putIncomingForMembersComposers(id,totalFee/2,composerIDs.get(0),concertDate, songIDs.get(index));
                    done20 = putIncomingForMembersWritters(id,totalFee/2,writterIDs.get(0),concertDate,songIDs.get(index));
                    done30 = putIncomingForMembersComposers(id,0,composerIDs.get(1),concertDate, songIDs.get(index));
                    done40 = putIncomingForMembersWritters(id,0,writterIDs.get(1),concertDate,songIDs.get(index));

                    index++;
                    if (!(done10 && done20 && done30 && done40)){
                        return false;
                    }
                }
                return true;

            }

            else{
                return done2 && done1;
            }
        }
        else{
            return stmt.executeUpdate() > 0;
        }
    }

    public Boolean setLicenseReqDeny(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET Rejected = 1  WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);

        return stmt.executeUpdate() > 0;
    }

    public Boolean newLicense(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO license (concert_id) VALUE(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    public ArrayList<String> getLicenseEmialDetails(int cid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT user_id, concert_name, concert_date, venue, type, user_id FROM concert WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, cid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> data = new ArrayList<>();

        if (resultSet.next()) {

            data.add(String.valueOf(cid));
            data.add(String.valueOf(resultSet.getInt(1)));
            data.add(resultSet.getString(2));
            data.add(resultSet.getString(5));
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(3));
            data.add(resultSet.getString(6));
        }

        System.out.println(data);
        return data;
    }





    public Boolean setSongRegReqAccept(int id, int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET status = ? WHERE temp_song_id = ? AND type_of_request = 1;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,type);
        stmt.setInt(2,id);


        if (type == 2) {
            boolean done1 = stmt.executeUpdate() > 0;
            boolean done2 = newSong(id);

            return done2 && done1;
        }
        else{
            return stmt.executeUpdate() > 0;
        }
    }

    public Boolean setSongRegReqDeny(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET Rejected = 1 WHERE temp_song_id = ? AND type_of_request = 1;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);

        return stmt.executeUpdate() > 0;
    }

    public Boolean newSong(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO song (temp_song_id,Delete_status,Approved_Date) VALUE(?,?,CURRENT_DATE)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,0);

        return preparedStatement.executeUpdate() > 0;
    }



    public Boolean setSongOwnReqAccept(int id, int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET status = ? WHERE temp_song_id = ? AND type_of_request = 2;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,type);
        stmt.setInt(2,id);

        if (type == 2) {
            boolean done1 = stmt.executeUpdate() > 0;
            boolean done2 = changeOwnership(id);

            boolean done3 = false;
            boolean done4 = false;


            int songID = getSongID(id);
            ArrayList<Integer> concertIds =  getConcertIDs(songID);
            done3 = setDeleteInSongIncome2(concertIds, songID);
            done4 = updateSongIncomeTable2(concertIds, songID);


            return done2 && done1 && done3 && done4;
        }
        else{
            return stmt.executeUpdate() > 0;
        }
    }

    public Boolean setSongOwnReqDeny(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET Rejected = 1 WHERE temp_song_id = ? AND type_of_request = 2;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);
        return stmt.executeUpdate() > 0;
    }

    public Boolean changeOwnership(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Corresponding_id FROM song_requests WHERE temp_song_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        int currentID = 0;
        if(resultSet.next()){
            currentID = resultSet.getInt(1);
        }

        String q1 = "UPDATE song SET temp_song_id = ?, Approved_Date = CURRENT_DATE WHERE temp_song_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);

        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,currentID);

        return preparedStatement.executeUpdate() > 0;
    }

    public Boolean setSongDelReqAccept(int id, int type) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET status = ? WHERE temp_song_id = ? AND type_of_request = 3;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,type);
        stmt.setInt(2,id);

        if (type == 2) {
            boolean done1 = stmt.executeUpdate() > 0;
            boolean done2 = deleteSong(id);

            return done2 && done1;
        }
        else{
            return stmt.executeUpdate() > 0;
        }
    }

    public Boolean setSongDelReqDeny(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET Rejected = 1 WHERE temp_song_id = ? AND type_of_request = 3;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);

        return stmt.executeUpdate() > 0;
    }

    public Boolean deleteSong(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT Corresponding_id FROM song_requests WHERE temp_song_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        int tempID = 0;
        if (resultSet.next()){
            tempID = resultSet.getInt(1);
        }

        String q = "UPDATE song_requests SET s_flag = 1 WHERE temp_song_id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(q);
        stmt2.setInt(1,tempID);
        boolean done1 =  stmt2.executeUpdate() > 0;

        String q1 = "UPDATE song SET Delete_status = 1, Approved_Date = CURRENT_DATE WHERE temp_song_id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);
        preparedStatement.setInt(1,tempID);
        boolean done2 =   preparedStatement.executeUpdate() > 0;

        return done1 && done2;
    }

    public Boolean isCloseConcert(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT type FROM concert WHERE concert_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        boolean done = false;
        String whatType = null;
        if (resultSet.next()){
            whatType = resultSet.getString(1);

            return whatType.equals("Close");
        }
        return false;
    }

    public ArrayList<Integer> getSongIDsInConcert(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT song_id FROM concert_songs WHERE concert_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> x = new ArrayList<>();
        while (resultSet.next()){
            x.add(resultSet.getInt(1));
            System.out.println(x);
        }

        return x;
    }



    public ArrayList<Integer> getTempSongIDsInConcert(ArrayList<Integer> ids) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        ArrayList<Integer> x = new ArrayList<>();
        System.out.println(ids);

        for (Integer id: ids){
            System.out.println(id);
            String q0 = "SELECT temp_song_id FROM song WHERE song_id =?;";
            PreparedStatement stmt = connection.prepareStatement(q0);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()){
                x.add(resultSet.getInt(1));
            }
        }
        return x;
    }


    public double getTotalFeeForConcert(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT Fee_without_commission FROM concert WHERE concert_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        double x = 0;

        if (resultSet.next()){
            x = resultSet.getDouble(1);
        }
        return x;
    }



    public String getDateForConcert(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT concert_Date FROM concert WHERE concert_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        String  x = null;

        if (resultSet.next()){
            x = resultSet.getString(1);
        }
        return x;
    }


    public ArrayList<ArrayList<Integer>> getComposersForConcert(int Tempid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT member_id FROM song_request_composers WHERE temp_song_id = ? AND Member_Active_Status = 'M';";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,Tempid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<ArrayList<Integer>> finalOne = new ArrayList<>();

        while (resultSet.next()){
            x.add(resultSet.getInt(1));
        }
        finalOne.add(x);


        String q1 = "SELECT member_id FROM song_request_composers WHERE temp_song_id = ? AND Member_Active_Status = 'N';";
        PreparedStatement stmt1 = connection.prepareStatement(q1);
        stmt1.setInt(1,Tempid);
        ResultSet resultSet1 = stmt1.executeQuery();

        ArrayList<Integer> x2 = new ArrayList<>();

        while (resultSet1.next()){
            x2.add(resultSet1.getInt(1));
        }
        finalOne.add(x2);

        return finalOne;
    }



    public ArrayList<ArrayList<Integer>> getWrittersForConcert(int Tempid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT member_id FROM song_request_song_writers WHERE temp_song_id = ? AND Member_Active_Status = 'M';";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,Tempid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<ArrayList<Integer>> finalOne = new ArrayList<>();

        while (resultSet.next()){
            x.add(resultSet.getInt(1));
        }
        finalOne.add(x);

        String q1 = "SELECT member_id FROM song_request_song_writers WHERE temp_song_id = ? AND Member_Active_Status = 'N';";
        PreparedStatement stmt1 = connection.prepareStatement(q1);
        stmt1.setInt(1,Tempid);
        ResultSet resultSet1 = stmt1.executeQuery();

        ArrayList<Integer> x2 = new ArrayList<>();

        while (resultSet1.next()){
            x2.add(resultSet1.getInt(1));
        }
        finalOne.add(x2);

        return finalOne;
    }



    public Boolean putIncomingForMembersComposers(int id, double amount, ArrayList<Integer> members, String concertDate, int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        double oneAmount = amount/members.size();
        boolean done = false;
        for (Integer member : members) {
            String query = "INSERT INTO song_income VALUE(?,?,?,?,?,?,?,?) ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, concertID);
            preparedStatement.setInt(3, member);
            preparedStatement.setDate(4, java.sql.Date.valueOf(concertDate));
            preparedStatement.setDouble(5, oneAmount);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);

            done = preparedStatement.executeUpdate() > 0;

            if (!done) {
                return false;
            }
        }
        return true;
    }

    public Boolean putIncomingForMembersWritters(int id, double amount, ArrayList<Integer> members, String concertDate, int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        double oneAmount = amount/members.size();
        boolean done = false;
        for (Integer member : members) {
            String query = "INSERT INTO song_income VALUE(?,?,?,?,?,?,?,?) ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, concertID);
            preparedStatement.setInt(3, member);
            preparedStatement.setDate(4, java.sql.Date.valueOf(concertDate));
            preparedStatement.setDouble(5, oneAmount);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);

            done = preparedStatement.executeUpdate() > 0;

            if (!done) {
                return false;
            }
        }
        return true;
    }


    //for new member registrations

    // from here //
    // from here //
    // from here //
    // from here //
    //gets the concert IDs where this non-member had
    public boolean allDoneForTheNewNonMember(int uid) throws SQLException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> concertIDandSongIDs = new ArrayList<>();
        boolean done = false;
        boolean done2 = false;

        concertIDandSongIDs = getConcertAndSongIDsOfNonMembers(uid);
        done = setDeleteInSongIncome(concertIDandSongIDs);
        done2 = updateSongIncomeTable(concertIDandSongIDs);

        return  done && done2;
    }

    public ArrayList<ArrayList<Integer>> getConcertAndSongIDsOfNonMembers(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT concert_id, song_id FROM song_income WHERE member_id = ? AND delete_flag = 0 cancel_status = 0 AND is_paid = 0;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,uid);
        ResultSet resultSet = stmt.executeQuery();


        ArrayList<ArrayList<Integer>> finalOne = new ArrayList<>();

        while (resultSet.next()){
            ArrayList<Integer> x = new ArrayList<>();
            ArrayList<Integer> y = new ArrayList<>();

            x.add(resultSet.getInt(1));
            y.add(resultSet.getInt(2));

            finalOne.add(x);
            finalOne.add(y);
        }

        return finalOne;
    }

    //setting the song IDs as delete
    public boolean setDeleteInSongIncome(ArrayList<ArrayList<Integer>> concertAndSongIDs) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        boolean done = false;
        for (ArrayList<Integer> concertAndSongID : concertAndSongIDs) {
            String q = "UPDATE song_income SET delete_flag = 1 WHERE concert_id = ? AND song_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(q);

            stmt.setInt(1, concertAndSongID.get(0));
            stmt.setInt(2, concertAndSongID.get(1));

            done = stmt.executeUpdate() > 0;
        }

        return done;
    }



    //this adds new rows in the song income table
    public Boolean updateSongIncomeTable(ArrayList<ArrayList<Integer>> concertAndSongIDs) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        for (ArrayList<Integer> concertAndSongID : concertAndSongIDs) {
            int songIDs = 0;
            int tempSongID = 0;
            ArrayList <ArrayList <Integer>> composerIDs = new ArrayList<>();
            ArrayList <ArrayList <Integer>> writterIDs = new ArrayList<>();
            String concertDate = null;
            double totalFee = 0;
            boolean done10 = false;
            boolean done20 = false;
            boolean done30 = false;
            boolean done40 = false;

            concertDate = getDateForConcert(concertAndSongID.get(0));
            tempSongID = getTempSongIDsInConcert2(concertAndSongID.get(1));
            totalFee = getTotalFeeForConcert(concertAndSongID.get(0));
//            totalFee = 5000.0;

            writterIDs = getWrittersForConcert(tempSongID);
            composerIDs = getComposersForConcert(tempSongID);
            done10 = putIncomingForMembersComposers(concertAndSongID.get(0), totalFee/2, composerIDs.get(0), concertDate, concertAndSongID.get(1));
            done20 = putIncomingForMembersWritters(concertAndSongID.get(0), totalFee/2, writterIDs.get(0), concertDate, concertAndSongID.get(1));
            done30 = putIncomingForMembersComposers(concertAndSongID.get(0), 0, composerIDs.get(1), concertDate, concertAndSongID.get(1));
            done40 = putIncomingForMembersWritters(concertAndSongID.get(0), 0, writterIDs.get(1), concertDate, concertAndSongID.get(1));

            if (!(done10 && done20 && done30 && done40)) {
                return false;
            }
        }

        return true;
    }


    public int getTempSongIDsInConcert2(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT temp_song_id FROM song WHERE song_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q0);

        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt(1);
        }

        return 0;
    }

    // till here //
    // till here //
    // till here //
    // till here //

    //make a function for ownership changes//
    //get songID for the corresponding tempSongID
    public int getSongID(int tsid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT song_id FROM song WHERE temp_song_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,tsid);
        ResultSet resultSet = stmt.executeQuery();

        int x = 0;

        if (resultSet.next()){
            x = resultSet.getInt(1);
        }
        return x;
    }


    //get concertIDs for the corresponding songID
    public ArrayList<Integer> getConcertIDs(int sid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT concert_id FROM song_income WHERE song_id = ? AND cancel_status = 0 AND is_paid = 0 AND delete_flag = 0;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,sid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> x = new ArrayList<>();

        while (resultSet.next()){
            x.add(resultSet.getInt(1));
        }
        return x;
    }

    public boolean setDeleteInSongIncome2(ArrayList<Integer> concertIDs, int songID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        boolean done = false;
        for (Integer ids : concertIDs) {
            String q = "UPDATE song_income SET delete_flag = 1 WHERE concert_id = ? AND song_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(q);

            stmt.setInt(1, ids);
            stmt.setInt(2, songID);

            done = stmt.executeUpdate() > 0;
        }

        return done;
    }


    public Boolean updateSongIncomeTable2(ArrayList<Integer> concertIDs, int songID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        int tempSongID = getTempSongIDsInConcert2(songID);

        for (Integer ids : concertIDs) {

            ArrayList <ArrayList <Integer>> composerIDs = new ArrayList<>();
            ArrayList <ArrayList <Integer>> writterIDs = new ArrayList<>();
            String concertDate = null;
            double totalFee = 0;
            boolean done10 = false;
            boolean done20 = false;
            boolean done30 = false;
            boolean done40 = false;

            concertDate = getDateForConcert(ids);
            totalFee = getTotalFeeForConcert(ids);

            int songNo = getTotSongsForChangeSongOwnership(ids);
            totalFee = totalFee/songNo;

//            totalFee = 5000.0;

            writterIDs = getWrittersForConcert(tempSongID);
            composerIDs = getComposersForConcert(tempSongID);
            done10 = putIncomingForMembersComposers(ids, totalFee/2, composerIDs.get(0), concertDate, songID);
            done20 = putIncomingForMembersWritters(ids, totalFee/2, writterIDs.get(0), concertDate, songID);
            done30 = putIncomingForMembersComposers(ids, 0, composerIDs.get(1), concertDate, songID);
            done40 = putIncomingForMembersWritters(ids, 0, writterIDs.get(1), concertDate, songID);

            if (!(done10 && done20 && done30 && done40)) {
                return false;
            }
        }
        return true;
    }



    public int getTotSongsForChangeSongOwnership(int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q0 = "SELECT COUNT(*) FROM (SELECT * FROM song_income WHERE Concert_ID = ? GROUP BY song_ID) AS T;";
        PreparedStatement stmt = connection.prepareStatement(q0);
        stmt.setInt(1,concertID);
        ResultSet resultSet = stmt.executeQuery();

        int x = 0;

        if (resultSet.next()){
            x = resultSet.getInt(1);
        }
        return x;
    }


}
