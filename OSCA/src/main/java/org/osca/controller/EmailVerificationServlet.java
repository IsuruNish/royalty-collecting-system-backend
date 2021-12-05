package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.controller.login.Mail;
import org.osca.model.Respond;
import org.osca.model.ShowOrganizer;
import org.osca.model.UserLoginModel;
import org.osca.model.VerifyEmail;
import org.osca.service.LoginService;
import org.osca.service.SAdashboardService;
import org.osca.service.signupService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EmailVerificationServlet", value = "/EmailVerificationServlet")
public class EmailVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderAndBody data = new HeaderAndBody();
        String body = data.getBody(request);

        VerifyEmail emails;
        Gson gson = new Gson();
        emails = gson.fromJson(body, VerifyEmail.class);
        LoginService loginService = new LoginService();

        int ut = 0;
        ArrayList<Integer> uidAndut = new ArrayList<>();

        try {
            uidAndut = loginService.getUserIDfromPin(emails.getPin());
            emails.setUid(uidAndut.get(0));
            ut = uidAndut.get(1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if (ut == 5){
            try {
                loginService.verifyEmailShowORganizer(emails.getPin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (ut == 4){
            try {
                loginService.verifyEmailMember(emails.getPin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        }
        else if (ut == 3 || ut == 2 || ut == 1){
            String m = null;
            try {
                loginService.verifyEmailOfficial(emails.getPin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderAndBody data = new HeaderAndBody();
        String body = data.getBody(request);

        VerifyEmail emails;
        Gson gson = new Gson();
        emails = gson.fromJson(body, VerifyEmail.class);
        Mail service = new Mail();
        SAdashboardService saService= new SAdashboardService();
        LoginService loginService = new LoginService();

        if (emails.getReqType() == 1){
            int ut = 0;
            ArrayList<Integer> uidAndut = new ArrayList<>();

            try {
                uidAndut = loginService.getUserIDfromPin(emails.getPin());
                emails.setUid(uidAndut.get(0));
                ut = uidAndut.get(1);

                System.out.println(ut);
                System.out.println(emails.getUid());

//                ut = saService.getUserTypeUsingUid(emails.getUid());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }


            boolean can = false;
            try {
                can = loginService.checkEmailSendTime(emails.getPin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            System.out.println(can);
            if (can){
                if (ut == 5){
                    String m = null;
                    try {
                        m = saService.getShowOrganizerEmail(emails.getUid());
                        String n = saService.getShowOrganizerName(emails.getUid());
                        service.verifyEmail(m,"",n,emails.getPin());
                    } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else if (ut == 4){
                    String m = null;
                    try {
                        m = saService.getMemberEmail(emails.getUid());
                        String n = saService.getMemberName(emails.getUid());
                        service.verifyEmail(m,"",n,emails.getPin());
                    } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                        throwables.printStackTrace();
                    }

                }
                else if (ut == 3 || ut == 2 || ut == 1){
                    String m = null;
                    try {
                        m = saService.getEmployeeEmail(emails.getUid());
                        String n = saService.getSuperadminName(emails.getUid());
                        service.verifyEmail(m,"",n,emails.getPin());
                    } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                        throwables.printStackTrace();
                    }
                }

                Gson g = new Gson();
                Respond ok = new Respond();
                ok.setOk(1);
                String obj =g.toJson(ok);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(obj);

            }

            else{
                Gson g = new Gson();
                Respond ok = new Respond();
                ok.setOk(60);
                String obj =g.toJson(ok);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(obj);
            }


        }

        else if (emails.getReqType() == 2){
            int ut = 0;
            ArrayList<Integer> uidAndut = new ArrayList<>();

            try {
                uidAndut = loginService.getUserIDfromPin(emails.getPin());
                emails.setUid(uidAndut.get(0));
                ut = uidAndut.get(1);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            boolean can = false;
            if (ut == 5){
                try {
                    can = loginService.verifyEmailShowORganizer(emails.getPin());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (ut == 4){
                try {
                    can = loginService.verifyEmailMember(emails.getPin());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            }
            else if (ut == 3 || ut == 2 || ut == 1){
                try {
                   can = loginService.verifyEmailOfficial(emails.getPin());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
            }

            Gson g = new Gson();
            Respond ok = new Respond();
            ok.setOk(1);
            String obj =g.toJson(ok);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(obj);

            }


        }


    }
}
