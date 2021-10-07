package org.osca.controller.interceptor;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebFilter("/*")
public class AuthInterceptor implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = ((HttpServletRequest) request).getRequestURI();

        if (!url.equals("/OSCA_war_exploded/LoginServlet") && !url.equals("/OSCA_war_exploded/SignupServlet")) {
            System.out.println(url);
            HeaderAndBody data = new HeaderAndBody();
            String header = data.getAuthenticationHeader(req);
            PrintWriter out = response.getWriter();

            if (header != null) {
                String token = header.substring(7);

                int ut = 404;

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

                if (ut == 404) {
                    out.println(ut);
                }
                else {
                    chain.doFilter(request, response);
                }

            } else {
                out.println(404);
            }
        }

        else {
            chain.doFilter(request, response);
        }
    }
}
