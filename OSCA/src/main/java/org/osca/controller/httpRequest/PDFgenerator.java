//package org.osca.controller.httpRequest;
//
//import com.pdfcrowd.*;
//import java.io.*;
//
//public class PDFgenerator {
//
//    public void pdf(int lid, int uid, String conName, String conType, String venue, String date) throws IOException {
//
//        try {
//            Pdfcrowd.HtmlToPdfClient client =
//                    new Pdfcrowd.HtmlToPdfClient("OSCA", "8303cb6da93c2b534e378e13223a5040");
//
//            String html = "<body style=\"border: 2px solid black; background-color: rgb(231, 231, 64);\">\n" +
//                    "\n" +
//                    "    <style>\n" +
//                    "\n" +
//                    "        .l{\n" +
//                    "            width: 250px;\n" +
//                    "            height: 250px;\n" +
//                    "            margin-top: 3%;\n" +
//                    "        }\n" +
//                    "        .input{\n" +
//                    "            width: 100%;\n" +
//                    "            height: fit-content;\n" +
//                    "            display: flex;\n" +
//                    "            flex-direction: column;\n" +
//                    "            justify-content: center;\n" +
//                    "            align-items: center;\n" +
//                    "            margin-bottom: 1%;   \n" +
//                    "            font-size: 16px;\n" +
//                    "            font-weight: 500;\n" +
//                    "\n" +
//                    "        }\n" +
//                    "\n" +
//                    "        .input div{\n" +
//                    "        width: 100%;\n" +
//                    "        display: flex;\n" +
//                    "        align-items: center;\n" +
//                    "        justify-content: center;\n" +
//                    "        }\n" +
//                    "\n" +
//                    "        .input label{\n" +
//                    "\n" +
//                    "        margin-right: 3%;\n" +
//                    "        font-weight: 500;\n" +
//                    "        margin-bottom: 1%;\n" +
//                    "        min-width: 20vh;\n" +
//                    "        }\n" +
//                    "\n" +
//                    "        .input input{\n" +
//                    "        border: none;\n" +
//                    "        border-radius: 0;\n" +
//                    "        width: 30%;\n" +
//                    "        margin-bottom: 1%;\n" +
//                    "        padding: 10px;\n" +
//                    "        font-size: 16px;\n" +
//                    "        outline: none;\n" +
//                    "        border-radius: 5px;\n" +
//                    "        box-shadow: grey 1px 3px 5px ;\n" +
//                    "        background-color: white;\n" +
//                    "        }\n" +
//                    "\n" +
//                    "        .k{\n" +
//                    "            margin-bottom: 2%;\n" +
//                    "        }\n" +
//                    "    </style>\n" +
//                    "\n" +
//                    "    <h1 style=\"text-align: center; margin-bottom: 5%;\">OSCA License</h1>\n" +
//                    "\n" +
//                    "    <div class=\"input\">\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">Concert ID</label>\n" +
//                    "            <input type=\"text\" name=\"name\" value=\"" +lid+"\" >\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">User ID</label>\n" +
//                    "            <input type=\"text\" name=\"singers\" value=\""+uid+"\">\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">Concert name</label>\n" +
//                    "            <input type=\"text\" name=\"version\" value=\""+conName+"\">\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">Concert type</label>\n" +
//                    "            <input type=\"text\" name=\"version\" value=\""+conType+"\">\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">Venue</label>\n" +
//                    "            <input type=\"text\" name=\"singers\" value=\""+venue+"\" >\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "        <div>\n" +
//                    "            <label for=\"\">Concert date</label>\n" +
//                    "            <input type=\"text\" name=\"singers\" value=\""+date+"\">\n" +
//                    "        </div>\n" +
//                    "\n" +
//                    "    </div>\n" +
//                    "\n" +
//                    "\n" +
//                    "    <div style=\"text-align:center; margin-top:60px;\">\n" +
//                    "        <img  class=\"l\"\n" +
//                    "        src = \"https://res.cloudinary.com/osca-lk/image/upload/v1638448233/Approved-seal-stamp-logo-on-transparent-background-PNG-removebg-preview_ngb7dc.png\">\n" +
//                    "    </div>\n" +
//                    "\n" +
//                    "    <div style=\"text-align:center; margin-top:60px;\">\n" +
//                    "        <img  style=\"width:100px; height:100px;\"\n" +
//                    "        src = \"https://res.cloudinary.com/osca-lk/image/upload/v1634884100/OSCA_maaq1t.jpg\">\n" +
//                    "    </div>\n" +
//                    "    \n" +
//                    "    <div style=\"text-align:center;  font-size:12px; font-style:italic;\" class=\"k\">\n" +
//                    "                    \n" +
//                    "                    Outstanding Song Creator's Association (OSCA)<br>\n" +
//                    "                    No.73, Sir James Peiris Mawatha, Colombo 02<br>\n" +
//                    "                    Tel: 0094 11 230 5070<br>\n" +
//                    "                    Tel/Fax : 0094 11 230 4070<br>\n" +
//                    "                    e-mail : cineoscasl@gmail.com<br>\n" +
//                    "    </div>    \n" +
//                    "</body>";
//
//            client.convertStringToFile(html, "C:\\\\Users\\\\Asus\\\\Desktop\\\\be\\\\osca-royalty-collector-backend\\\\OSCA\\\\src\\\\main\\\\webapp\\\\PDFs\\\\OSCA License.pdf");
//        }
//        catch(Pdfcrowd.Error why) {
//            System.err.println("Pdfcrowd Error: " + why);
//            throw why;
//        }
//        catch(IOException why) {
//            System.err.println("IO Error: " + why);
//            throw why;
//        }
//    }
//}
