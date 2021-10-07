package org.osca.controller.httpRequest;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.util.Map;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class CloudinaryImage {

    public String storeImage(File file) throws IOException {
        System.out.println("come");
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "osca-lk",
                "api_key", "876115734312266",
                "api_secret", "-3fwCxBHc7ZsIhZ8-MWCH-4v_c4"));

        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }



//    public static void main(String[] args) throws IOException {
//        System.out.println("come");
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "osca-lk",
//                "api_key", "876115734312266",
//                "api_secret", "-3fwCxBHc7ZsIhZ8-MWCH-4v_c4"));
//
//        File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");
//        System.out.println(file.getClass().getSimpleName());
//        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        System.out.println(uploadResult);
//    }
}


