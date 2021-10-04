package org.osca.controller.image;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Images {

    public Boolean storeImage(HttpServletRequest request) throws IOException, ServletException {
        boolean done = false;
        for (Part p : request.getParts()) {
            p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\" + p.getSubmittedFileName());
            done = true;
        }
        return done;
    }

    public String getImagePath(int id) {
        File[] files = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos").listFiles();
        String idValue = String.valueOf(id);
        String picname = "default.jpg";

        assert files != null;
        for (File file : files) {

            if (file.isFile()) {
                String[] fileID = file.getName().split("\\\\");
                String name = fileID[0].substring(0,fileID[0].length()-4);
                String extention = fileID[0].substring(fileID[0].length()-3);

                if (name.equals(idValue)) {
                    picname = name + "." + extention;
                    System.out.println(picname);

                    return "http://localhost:8080/OSCA_war_exploded/ProfilePhotos/" + picname;
                }
            }
        }

        return "http://localhost:8080/OSCA_war_exploded/ProfilePhotos/" + picname;
    }
}
