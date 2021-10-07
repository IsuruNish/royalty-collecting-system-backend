package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.controller.login.Mail;
import org.osca.dao.UserDAO;
import org.osca.dao.UserDAOImpl;
import org.osca.model.ForgetPassword;
import org.osca.model.ShowOrganizer;
import org.osca.service.ForgetPasswordService;
import org.osca.service.signupService;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;

@WebServlet(name = "ForgetPasswordServlet", value = "/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HeaderAndBody data = new HeaderAndBody();

        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);

        System.out.println(body);

        ForgetPassword forgetpw;
        Gson gson = new Gson();
        forgetpw = gson.fromJson(body, ForgetPassword.class);
//        forgetpw.setUserType(5);
        ForgetPasswordService service =new ForgetPasswordService();

        boolean added = false;

        try {
            added = service.addEmail(forgetpw);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Mail javaMailUtil=new Mail();
//        try {
//            javaMailUtil.sendMail("oshadhae029@gmail.com",""+"1234");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }


//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String pinCode=request.getParameter("pin");
//        HttpSession session=request.getSession();
//        String sentPin= (String) session.getAttribute("pin");
//
//        if(pinCode.equals(sentPin)){
//            //same pin
//            out.println(1);
//        }else{
//            //wrong
//            out.println(0);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String email=request.getParameter("email");
//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        UserDAO userDAO=new UserDAOImpl();
//        try {
//            if(userDAO.isRegisteredUser(email)){
//                HttpSession session = request.getSession();
//
//                Mail javaMailUtil=new Mail();
//                SecureRandom rand = new SecureRandom();
//                String pin=""+rand.nextInt(10000);
//
//                session.setAttribute("email",email);
//                session.setAttribute("pin",pin);
//
//                try {
//                    javaMailUtil.sendMail(email,""+pin);
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//                out.println(1);
//            }else{
//                out.println(0);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
