//package org.osca.controller;
//
//import org.osca.controller.auth.JWebToken;
//import org.osca.controller.httpRequest.HeaderAndBody;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//
//@WebServlet(name = "ADoneTableServlet", value = "/ADoneTableServlet")
//public class ADoneTableServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        HeaderAndBody data = new HeaderAndBody();
//        String header = data.getAuthenticationHeader(request);
//        String token = header.substring(7);
//
//        JWebToken tokennObj = null;
//        try {
//            tokennObj = new JWebToken(token);
//        } catch (
//                NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        assert tokennObj != null;
//        int uid = tokennObj.getUserID(token);
//        int userType = tokennObj.getUserType(token);
//
//    }
//
//
//
//    }
//
//
//
