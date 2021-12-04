package org.osca.controller.android;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.AndroidModel;
import org.osca.model.UserLoginModel;
import org.osca.service.LoginService;
import org.osca.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AndroidLoginServlet", value = "/AndroidLoginServlet")
public class AndroidLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String token = header.substring(7);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

        MemberService mem = new MemberService();
        ArrayList<String> arr = new ArrayList<>();

        try {
            arr = mem.getDashboardDetails(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        AndroidModel android = new AndroidModel();
        android.setFullname(arr.get(1));
        android.setfIncome(Double.parseDouble(arr.get(6)));
        android.setpIncome(Double.parseDouble(arr.get(5)));

        Gson g = new Gson();
        String ut =g.toJson(android);
        System.out.println(ut);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(ut);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderAndBody data = new HeaderAndBody();
        String body = data.getBody(request);

        UserLoginModel user = new UserLoginModel();
        Gson gson = new Gson();
        user = gson.fromJson(body, UserLoginModel.class);

        LoginService service = new LoginService();
        UserLoginModel RealUser = new UserLoginModel();

        System.out.println(user);
        int id = 0;
        try {
            RealUser = service.getUser2(user);
            id = service.getID2(user);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(id==0){
            Gson g = new Gson();
            String ut =g.toJson(new UserLoginModel(0));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(ut);
        }

        else{
            String token = new JWebToken(id).toString();
            Gson g = new Gson();
            String ut =g.toJson(new UserLoginModel(4, token));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().println(ut);
        }
    }




}
