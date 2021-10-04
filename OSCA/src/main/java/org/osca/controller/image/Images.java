package org.osca.controller.image;

import java.io.File;

public class Images {
    public String getImagePath(int id){
        File[] files = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos").listFiles();
        String idValue = String.valueOf(id);
        String picname = "default.jpg";

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                String[] fileID = file.getName().split(".");
                if (fileID[0].equals(idValue)) {
                    picname = fileID[0] + "." + fileID[1];

                    return "http://localhost:8080/OSCA_war_exploded/ProfilePhotos/" + picname;
                }
            }
        }

        return "http://localhost:8080/OSCA_war_exploded/ProfilePhotos/" + picname;
    }
}
