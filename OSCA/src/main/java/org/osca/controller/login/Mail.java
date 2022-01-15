package org.osca.controller.login;

//import org.osca.controller.httpRequest.PDFgenerator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Mail {
    public void sendMail(String recipient,String msg, String name) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("osca.g04@gmail.com", "OSCAinLK123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("osca.g04@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("OSCA");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        String medssage = "<div style=\"text-align:center;\">\n" +
                "<img  style=\"width:400px; height:300px;\"\n" +
                "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634887255/undraw_authentication_fsn5_t09krk.png\">\n" +
                "</div>"+
                "<div> <h1 style=\"font-size:50px; text-align:center;\">\n" +
                "  Password Reset </h1>\n" +
                "</div>\n" +
                "\n" +
                "<div style=\"font-size:16px; text-align:center;\">\n" +
                "Hello "+name+"," +
                "You can reset your password by clicking the button below.\n" +
                "</div>" +
                "<div style=\"text-align:center;\">\n" +
                "<a href ="+"'"+ msg+ "'"+" ><button \n" +
                "style=\"background-color: #008CBA; cursor: pointer;border: none; color: white;  padding: 15px 32px;  text-align: center;text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;\">\n" +
                "  Click Me\n" +
                "  </button></a>\n" +
                "</div>\n" +
                "<div style=\"text-align:center; margin-top:60px;\">\n" +
                "<img  style=\"width:100px; height:100px;\"\n" +
                "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\">\n" +
                "</div>" +
                "<div style=\"text-align:center; margin-top:-18px; font-size:12px; font-style:italic;\">\n" +
                "\n" +
                "Outstanding Song Creator's Association (OSCA)<br>\n" +
                "No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
                "\n" +
                "Tel: 0094 11 230 5070<br>\n" +
                "\n" +
                "Tel/Fax : 0094 11 230 4070<br>\n" +
                "\n" +
                "e-mail : cineoscasl@gmail.com<br>\n" +
                "  \n" +
                " </div>";

        mimeBodyPart.setContent(medssage, "text/html");

//        mimeBodyPart.setContent("<h1>OSCA </h1><h3>Password Reset</h3></br><b>"+msg+"</b>", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

    public void notifyUser(String recipient,String msg, String name) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("osca.g04@gmail.com", "OSCAinLK123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("osca.g04@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("OSCA");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        String text = "<div style=\"text-align:center;\">\n" +
                "<img  style=\"width:400px; height:300px;\"\n" +
                "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634883543/email1_nbnsxz.png\">\n" +
                "</div>\n" +
                "<div> <h1 style=\"font-size:50px; text-align:center;\">\n" +
                "  Welcome to OSCA </h1>\n" +
                "</div>\n" +
                "<div style=\"font-size:16px; text-align:center;\">\n" +
                "Hello "+ name+",\n" +
                "use the below user credentials to login to your account. <br>\n" +
                "  <br>\n" +
                "  Email - "+ recipient+"<br>\n" +
                "  Password - "+msg+"<br>\n" +
                "  <br>\n" +
                "Note: You can change your email and password once you login to the system.\n" +
                "</div>" +
                "<div style=\"text-align:center; margin-top:60px;\">\n" +
                "<img  style=\"width:100px; height:100px;\"\n" +
                "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\">\n" +
                "</div>" +
                "<div style=\"text-align:center; margin-top:-18px; font-size:12px; font-style:italic;\">\n" +
                "\n" +
                "Outstanding Song Creator's Association (OSCA)<br>\n" +
                "No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
                "\n" +
                "Tel: 0094 11 230 5070<br>\n" +
                "\n" +
                "Tel/Fax : 0094 11 230 4070<br>\n" +
                "\n" +
                "e-mail : cineoscasl@gmail.com<br>\n" +
                "  \n" +
                " </div>";

        mimeBodyPart.setContent(msg, "text/html");
        mimeBodyPart.setContent(text, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

    public void welcome(String msg,String recipient, String name) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("osca.g04@gmail.com", "OSCAinLK123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("osca.g04@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("OSCA");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        String message101 =
                "<div style=\"text-align:center;\">\n" +
                        "<img  style=\"width:450px; height:400px;\"\n" +
                        "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634883543/email1_nbnsxz.png\">\n" +
                        "</div>" +
                        "<div> <h1 style=\"font-size:50px; text-align:center;\">Welcome to OSCA </h1> </div>\n" +
                        "\n" +
                        "<div style=\"font-size:16px; text-align:center;\">\n" +
                        "Hello "+name+",\n" +
                        "we warmly welcome you to OSCA. Now you can apply for licenses and do your musical show without any issue.\n" +
                        "</div>" +
                        "<div style=\"text-align:center; margin-top:60px;\">\n" +
                        "<img  style=\"width:100px; height:100px;\"\n" +
                        "     src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\">\n" +
                        "</div>" +
                        "<div style=\"text-align:center; margin-top:-18px; font-size:12px; font-style:italic;\">\n" +
                        "\n" +
                        "Outstanding Song Creator's Association (OSCA)<br>\n" +
                        "No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
                        "\n" +
                        "Tel: 0094 11 230 5070<br>\n" +
                        "\n" +
                        "Tel/Fax : 0094 11 230 4070<br>\n" +
                        "\n" +
                        "e-mail : cineoscasl@gmail.com<br>\n" +
                        "  \n" +
                        " </div>";

//        mimeBodyPart.setContent("<h1>OSCA </h1><h3>Do not reply.</h3></br><h1>Welcome to OSCA!!!</br></h1>You have successfully created your account..<h3></h3></br><b>"+msg+"</b>", "text/html");
        mimeBodyPart.setContent(message101, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }


    public void licenseEmail(String msg , String fullname, ArrayList<String> data, String email) throws MessagingException, IOException {
//        PDFgenerator obj = new PDFgenerator();
//        obj.pdf(Integer.parseInt(data.get(0)),Integer.parseInt(data.get(1)),data.get(2),data.get(3),data.get(4),data.get(5));

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("osca.g04@gmail.com", "OSCAinLK123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("osca.g04@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("isurunishadha99@gmail.com"));
        message.setSubject("OSCA");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart pdfAttachment = new MimeBodyPart();

        String text = "<div style=\"text-align:center;\">\n" +
                "\t<img  style=\"width:400px; height:300px;\"\n" +
                "\tsrc = \"https://res.cloudinary.com/osca-lk/image/upload/v1638358133/undraw_approve_qwp7_rtefjg.png\">\n" +
                "</div>\n" +
                "\n" +
                "<div> <h1 style=\"font-size:50px; text-align:center;\"> License request accepted</h1>\n" +
                "</div> \n" +
                "\n" +
                "<div style=\"font-size:16px; text-align:center;\">Hello "+ fullname +"! Your license request is accepted by OSCA. You can claim it by contacting OSCA.\n" +
                "</div>\n" +
                "\n" +
                "<div style=\"text-align:center; margin-top:60px;\">\n" +
                "\t<img  style=\"width:100px; height:100px;\"\n" +
                "\tsrc = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\">\n" +
                "</div>\n" +
                "\n" +
                "<div style=\"text-align:center;  font-size:12px; font-style:italic;\">\n" +
                "                \n" +
                "                Outstanding Song Creator's Association (OSCA)<br>\n" +
                "                No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
                "                Tel: 0094 11 230 5070<br>\n" +
                "                Tel/Fax : 0094 11 230 4070<br>\n" +
                "                e-mail : cineoscasl@gmail.com<br>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n";

//        pdfAttachment.attachFile("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\PDFs\\OSCA license.pdf");
        mimeBodyPart.setContent(msg, "text/html");
        mimeBodyPart.setContent(text, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
//        multipart.addBodyPart(pdfAttachment);
        message.setContent(multipart);
        Transport.send(message);
    }


    public void verifyEmail(String recipient,String msg, String name, int uid) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("osca.g04@gmail.com", "OSCAinLK123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("osca.g04@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("OSCA");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        String text = "<div style=\"text-align:center;\">\n" +
                "  <img  style=\"width:400px; height:300px;\" src = \"https://res.cloudinary.com/osca-lk/image/upload/v1638560246/email_qoooca.png\">\n" +
                "</div>\n" +
                "\n" +
                "<div> \n" +
                "<h1 style=\"font-size:50px; text-align:center;\">\n" +
                "Email Verification</h1>\n" +
                " </div>\n" +
                "<div style=\"font-size:16px; text-align:center;\">\n" +
                "Hello "+name+"! You can verify your email by clicking the button below.\n" +
                "</div>\n" +
                "<div style=\"text-align:center; margin-top:10px;\">\n" +
                "  \n" +
                "  <a href =\"http://127.0.0.1:5500/landing_page/verifyEmail.html?"+uid+"\"><button style= \"background-color: #008CBA; cursor: pointer;border: none; color: white;  padding: 15px 32px;  text-align: center;text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;\">\n" +
                "Click Me\n" +
                "</button></a>\n" +
                "</div>\n" +
                "<div style=\"text-align:center; margin-top:60px;\">\n" +
                "<img style=\"width:100px; height:100px;\" src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\"> </div>\n" +
                "<div style=\"text-align:center; margin-top:1px; font-size:12px; font-style:italic;\">\n" +
                "Outstanding Song Creator's Association (OSCA)<br>\n" +
                "No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
                "Tel: 0094 11 230 5070<br>\n" +
                "Tel/Fax : 0094 11 230 4070<br>\n" +
                "e-mail : cineoscasl@gmail.com<br>\n" +
                "</div>";

        mimeBodyPart.setContent(msg, "text/html");
        mimeBodyPart.setContent(text, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

}
