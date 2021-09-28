package org.osca.controller.interceptor;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebFilter("/*")
public class AuthInterceptor implements Filter {
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = ((HttpServletRequest) request).getRequestURI();
        PrintWriter out = response.getWriter();


        if (!url.equals("/OSCA_war_exploded/LoginServlet") && !url.equals("/OSCA_war_exploded/SignupServlet") ) {

            String token = req.getParameter("osca");
            int ut = 401;

            if (token != null) {
                JWebToken jwtObj = null;

                try {
                    jwtObj = new JWebToken(token);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }


                if (jwtObj.isValid()) {
                    ut = jwtObj.getUserType(token);
                }
            }


            if (ut == 401) {
                out.println(ut);
            }
            else{
                chain.doFilter(request, response);
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
