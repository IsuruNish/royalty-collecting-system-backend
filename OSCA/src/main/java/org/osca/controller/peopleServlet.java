package org.osca.controller;

import com.google.gson.Gson;
import org.osca.model.people;
import org.osca.service.peopleService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//
//@WebServlet(name = "peopleServlet", value = "/mypeople")

public class peopleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        peopleService service = new peopleService();
        ArrayList<people>pplList = new ArrayList<people>();

        try {
            pplList = service.getPeople();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Gson json = new Gson();
        String jsonObj = json.toJson(pplList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(jsonObj);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));
        String uni = req.getParameter("uni");

        peopleService service = new peopleService();


        boolean added = false;

        try {
            added = service.addPeople(new people(id,name,uni));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//
//        List<People> message = getDetails();
//        req.setAttribute("peoples",message);
//
//        if(added){
//            req.setAttribute("message","ADDED successfully");
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("form.jsp");
//            requestDispatcher.forward(req,res);
//        }
//        else{
//            req.setAttribute("message","NOT added");
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("form.jsp");
//            requestDispatcher.forward(req,res);
//        }
    }
}
