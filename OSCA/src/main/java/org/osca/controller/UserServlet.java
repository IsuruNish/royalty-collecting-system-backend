package org.osca.controller;

import org.osca.model.User;
import org.osca.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "UserServlet", value = "/users",description = "UserServlet return json")
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users=new ArrayList<>();
        users=service.getUsers();

        Gson gson = new Gson();
        String userJSON =gson.toJson(users);
//        PrintWriter printWriter=response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        printWriter.write(userJSON);
//        printWriter.close();
        response.getWriter().println(userJSON);


        }
    }



