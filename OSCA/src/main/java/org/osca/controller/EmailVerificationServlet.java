package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.controller.login.Mail;
import org.osca.model.ShowOrganizer;
import org.osca.model.VerifyEmail;
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

@WebServlet(name = "EmailVerificationServlet", value = "/EmailVerificationServlet")
public class EmailVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        if (emails.getReqType() == 1){

            int ut = 0;

            try {
                ut = saService.getUserTypeUsingUid(emails.getUid());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            if (ut == 5){
                String m = null;
                try {
                    m = saService.getShowOrganizerEmail(emails.getUid());
                    String n = saService.getShowOrganizerName(emails.getUid());
                    service.verifyEmail(m,"",n,emails.getUid());
                } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (ut == 4){
                String m = null;
                try {
                    m = saService.getMemberEmail(emails.getUid());
                    String n = saService.getMemberName(emails.getUid());
                    service.verifyEmail(m,"",n,emails.getUid());
                } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                    throwables.printStackTrace();
                }

            }
            else if (ut == 3){
                String m = null;
                try {
                    m = saService.getEmployeeEmail(emails.getUid());
                    String n = saService.getSuperadminName(emails.getUid());
                    service.verifyEmail(m,"",n,emails.getUid());
                } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }
}
