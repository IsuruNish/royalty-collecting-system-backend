package org.osca.controller.httpRequest;

import com.google.gson.Gson;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


public class HeaderAndBody {

    public String getHeader(HttpServletRequest request){
        return request.getHeader("authorization");
    }

    public String getBody(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        return buffer.toString();
    }
}
